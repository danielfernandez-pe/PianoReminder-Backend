package com.dfernandezyopla.PianoReminder.Auth.Entities;

public class UserToken {
    String token;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
