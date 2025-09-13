package com.example.genesis.repository;

import com.example.genesis.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findByChapterNumber(int chapterNumber);
}
