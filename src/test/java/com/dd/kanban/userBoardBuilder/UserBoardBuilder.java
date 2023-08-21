package com.dd.kanban.userBoardBuilder;

import com.dd.kanban.entity.UserBoard;

public class UserBoardBuilder {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;

    public UserBoardBuilder() {
        this.name = "Darnos Lima";
        this.username = "Darnos Teste";
        this.email = "darnos@teste.com";
        this.password = "p@55!/!/0r|)";
        this.enabled = true;
        this.tokenExpired = false;
    }

    public UserBoard build() {
        UserBoard user = new UserBoard();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setTokenExpired(tokenExpired);

        return user;
    }

    public UserBoardBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBoardBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBoardBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBoardBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBoardBuilder withEnable(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBoardBuilder withTokenExpired(Boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
        return this;
    }
}
