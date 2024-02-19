package ru.sergjava.kursprojspringbootdocker.model;

import lombok.Data;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;

@Data
public class TransferTransactions {
    private final Long operationID;
    private final Transfer transfer;
    private final int commission;
}
