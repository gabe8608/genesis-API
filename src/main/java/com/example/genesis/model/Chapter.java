package com.example.genesis.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="chapter_number", unique = true, nullable = false)
    private int chapterNumber;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Verse> verses = new ArrayList<>();

    public Chapter() {}

    public Chapter(int chapterNumber) { this.chapterNumber = chapterNumber; }

    public Long getId() { return id; }
    public int getChapterNumber() { return chapterNumber; }
    public void setChapterNumber(int chapterNumber) { this.chapterNumber = chapterNumber; }

    public List<Verse> getVerses() { return verses; }
    public void setVerses(List<Verse> verses) { this.verses = verses; }

    public void addVerse(Verse v) {
        verses.add(v);
        v.setChapter(this);
    }
}
