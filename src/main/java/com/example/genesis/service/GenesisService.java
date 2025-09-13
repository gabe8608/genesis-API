package com.example.genesis.service;

import com.example.genesis.dto.*;
import com.example.genesis.model.Chapter;
import com.example.genesis.model.Verse;
import com.example.genesis.repository.ChapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GenesisService {
    private final ChapterRepository chapterRepository;
    private final WebClient webClient;
    private static final int MAX_CHAPTERS = 50;

    public GenesisService(ChapterRepository chapterRepository, WebClient.Builder webClientBuilder) {
        this.chapterRepository = chapterRepository;
        // base URL points to the Bible API used in the challenge
        this.webClient = webClientBuilder.baseUrl("https://bible-api.com/data/web/GEN").build();
    }

    public GenesisResponse getGenesisUpTo(int cap) {
        if (cap < 1 || cap > MAX_CHAPTERS)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "capítulo inválido (1..50)");

        List<ChapterDto> result = IntStream.rangeClosed(1, cap)
                .mapToObj(this::getChapterDto)
                .collect(Collectors.toList());

        return new GenesisResponse(result);
    }

    private ChapterDto getChapterDto(int chapterNumber) {
        Optional<Chapter> opt = chapterRepository.findByChapterNumber(chapterNumber);
        if (opt.isPresent()) {
            Chapter c = opt.get();
            List<VerseDto> verses = c.getVerses().stream()
                    .sorted(Comparator.comparingInt(Verse::getVerseNumber))
                    .map(v -> new VerseDto(v.getVerseNumber(), v.getText()))
                    .collect(Collectors.toList());
            return new ChapterDto(c.getChapterNumber(), verses);
        }

        // If not in DB, call the external API and persist
        BibleApiResponse apiResp = webClient.get()
                .uri("/{n}", chapterNumber)
                .retrieve()
                .bodyToMono(BibleApiResponse.class)
                .block();

        if (apiResp == null || apiResp.getVerses() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener capítulo " + chapterNumber);
        }

        Chapter chapterEntity = new Chapter(chapterNumber);
        apiResp.getVerses().forEach(vdto -> {
            Verse v = new Verse(vdto.getVerse(), vdto.getText());
            chapterEntity.addVerse(v);
        });

        chapterRepository.save(chapterEntity);

        List<VerseDto> verses = apiResp.getVerses().stream()
                .map(v -> new VerseDto(v.getVerse(), v.getText()))
                .collect(Collectors.toList());
        return new ChapterDto(apiResp.getChapter(), verses);
    }
}
