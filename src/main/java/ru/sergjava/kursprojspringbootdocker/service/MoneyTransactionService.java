package ru.sergjava.kursprojspringbootdocker.service;

import ru.sergjava.kursprojspringbootdocker.model.Confirm;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;

import java.io.IOException;

public interface MoneyTransactionService {
    public String createMoneyTransaction(Transfer transfer) throws IOException;

    public String confirmTransactions(Confirm confirm) throws IOException;
}
