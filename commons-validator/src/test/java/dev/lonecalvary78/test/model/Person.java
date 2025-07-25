package dev.lonecalvary78.test.model;

import jakarta.validation.constraints.NotBlank;

public record Person(@NotBlank String firtsName, @NotBlank String lastName) {
    public static Person of(String firtsName, String lastName) {
        return new Person(firtsName, lastName);
    }
}
