package ru.sergjava.kursprojspringbootdocker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CustomException {
    private final String message;
    private final UUID id;

}
