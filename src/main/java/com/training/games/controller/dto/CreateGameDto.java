package com.training.games.controller.dto;

public class CreateGameDto {

    private String name;

    private String description;

    public CreateGameDto() {
    }

    public CreateGameDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
