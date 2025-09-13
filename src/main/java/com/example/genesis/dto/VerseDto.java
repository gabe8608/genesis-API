package com.example.genesis.dto;

public class VerseDto {
    private int verse;
    private String text;

    public VerseDto() {}
    public VerseDto(int verse, String text) { this.verse = verse; this.text = text; }

    public int getVerse() { return verse; }
    public void setVerse(int verse) { this.verse = verse; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
