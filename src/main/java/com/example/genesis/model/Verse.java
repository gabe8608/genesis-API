package com.example.genesis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "verses")
public class Verse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int verseNumber;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    public Verse() {}
    public Verse(int verseNumber, String text) {
        this.verseNumber = verseNumber;
        this.text = text;
    }

    public Long getId() { return id; }
    public int getVerseNumber() { return verseNumber; }
    public void setVerseNumber(int verseNumber) { this.verseNumber = verseNumber; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Chapter getChapter() { return chapter; }
    public void setChapter(Chapter chapter) { this.chapter = chapter; }
}
