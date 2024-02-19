package ru.sergjava.kursprojspringbootdocker.storage;

import ru.sergjava.kursprojspringbootdocker.model.TransferTransactions;

public interface TransactionsStorage {
    public TransferTransactions saveTransaction(TransferTransactions transferTransactions);

    public TransferTransactions getTransactionsByID(Long operationID);
}
