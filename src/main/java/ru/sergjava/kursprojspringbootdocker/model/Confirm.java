package ru.sergjava.kursprojspringbootdocker.model;

import lombok.Data;

@Data
public class Confirm {
    private final String operationId;
    private final String code;
}
