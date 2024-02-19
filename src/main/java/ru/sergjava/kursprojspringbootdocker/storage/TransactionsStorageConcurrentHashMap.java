package ru.sergjava.kursprojspringbootdocker.storage;

import org.springframework.stereotype.Component;
import ru.sergjava.kursprojspringbootdocker.model.TransferTransactions;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionsStorageConcurrentHashMap implements TransactionsStorage {
    private final ConcurrentHashMap<Long, TransferTransactions> transactionsMap = new ConcurrentHashMap<>();

    @Override
    public TransferTransactions saveTransaction(TransferTransactions transferTransactions) {
        transactionsMap.put(transferTransactions.getOperationID(), transferTransactions);
        return transferTransactions;
    }

    @Override
    public TransferTransactions getTransactionsByID(Long operationID) {
        return transactionsMap.get(operationID);
    }
}
