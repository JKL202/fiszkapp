package com.example.fiszkapp;

public class Flashcard {
    private long id;
    private String question;
    private String answer;

    public Flashcard() {}

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Gettery i settery
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}