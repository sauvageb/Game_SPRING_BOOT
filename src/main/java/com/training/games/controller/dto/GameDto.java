package com.training.games.controller.dto;

import com.training.games.repository.entity.Game;

import java.time.LocalDate;

public class GameDto {

    private Long id;

    private String name;

    private String description;

    private LocalDate createdAt;

    public static GameDto from(Game g) {
        GameDto dto = new GameDto();
        dto.setId(g.getId());
        dto.setName(g.getName());
        dto.setDescription(g.getDescription());
        dto.setCreatedAt(g.getCreatedAt());
        return dto;
    }


    public GameDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
