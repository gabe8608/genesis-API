package com.example.genesis.dto;

import java.util.List;

public class ChapterDto {
    private int chapter;
    private List<VerseDto> verses;

    public ChapterDto() {}
    public ChapterDto(int chapter, List<VerseDto> verses) { this.chapter = chapter; this.verses = verses; }

    public int getChapter() { return chapter; }
    public void setChapter(int chapter) { this.chapter = chapter; }

    public List<VerseDto> getVerses() { return verses; }
    public void setVerses(List<VerseDto> verses) { this.verses = verses; }
}
