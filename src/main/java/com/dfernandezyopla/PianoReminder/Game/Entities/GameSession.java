package com.dfernandezyopla.PianoReminder.Game.Entities;

import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

@Entity
public class GameSession {
    @Id
    String id;
    Instant date;
    Integer points;
    Integer correctSightReadingQuestions;
    Integer correctHistoryQuestions;
    Integer totalSightReadingQuestions;
    Integer totalHistoryQuestions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public GameSession() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCorrectSightReadingQuestions() {
        return correctSightReadingQuestions;
    }

    public void setCorrectSightReadingQuestions(Integer correctSightReadingQuestions) {
        this.correctSightReadingQuestions = correctSightReadingQuestions;
    }

    public Integer getCorrectHistoryQuestions() {
        return correctHistoryQuestions;
    }

    public void setCorrectHistoryQuestions(Integer correctHistoryQuestions) {
        this.correctHistoryQuestions = correctHistoryQuestions;
    }

    public Integer getTotalSightReadingQuestions() {
        return totalSightReadingQuestions;
    }

    public void setTotalSightReadingQuestions(Integer totalSightReadingQuestions) {
        this.totalSightReadingQuestions = totalSightReadingQuestions;
    }

    public Integer getTotalHistoryQuestions() {
        return totalHistoryQuestions;
    }

    public void setTotalHistoryQuestions(Integer totalHistoryQuestions) {
        this.totalHistoryQuestions = totalHistoryQuestions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
