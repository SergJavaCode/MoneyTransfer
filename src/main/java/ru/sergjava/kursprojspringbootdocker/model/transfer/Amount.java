package ru.sergjava.kursprojspringbootdocker.model.transfer;

import lombok.Data;

@Data
public class Amount {
    private final Integer value;

    private final String currency;
}
