package ru.sergjava.kursprojspringbootdocker.model.transfer;

import lombok.Data;

@Data
public class Transfer {
    private final String cardFromNumber;
    private final String cardFromValidTill;
    private final String cardFromCVV;
    private final String cardToNumber;
    private final Amount amount;
}
