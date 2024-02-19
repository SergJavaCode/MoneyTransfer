package ru.sergjava.kursprojspringbootdocker.service;

import org.springframework.stereotype.Service;
import ru.sergjava.kursprojspringbootdocker.model.Confirm;
import ru.sergjava.kursprojspringbootdocker.model.TransferTransactions;
import ru.sergjava.kursprojspringbootdocker.storage.TransactionsStorage;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;
import ru.sergjava.kursprojspringbootdocker.util.Logger;

import java.io.IOException;

@Service
public class MoneyTransactionServiceImpl implements MoneyTransactionService {

    public MoneyTransactionServiceImpl(TransactionsStorage transactionsStorage, Logger logger) {
        this.transactionsStorage = transactionsStorage;
        this.logger = logger;
    }

    private final TransactionsStorage transactionsStorage;

    private Long operationID = 0L;
    private final Logger logger;

    public String createMoneyTransaction(Transfer transfer) throws IOException {
        TransferTransactions createdTransaction;

//        //эмуляция ошибки срвера------------------------------------------------------
//        if (transfer.getAmount().getValue() == 2) {
//            throw new ServerErrorException("Ошибка на стороне сервера!", null);
//        }
//        //----------------------------------------------------------------------------
        operationID += 1;
        createdTransaction = new TransferTransactions(operationID, transfer, transfer.getAmount().getValue() / 100);
        logger.setTransferTransactions(createdTransaction);
        createdTransaction = transactionsStorage.saveTransaction(createdTransaction);
        logger.writeLog("прошла проверку, ожидает подтверждения.\n");
        return createdTransaction.getOperationID().toString();
    }

    @Override
    public String confirmTransactions(Confirm confirm) throws IOException {
        TransferTransactions confirmTransaction;
        confirmTransaction = transactionsStorage.getTransactionsByID(Long.valueOf(confirm.getOperationId()));
        logger.setTransferTransactions(confirmTransaction);
        if (confirmTransaction != null) {
            logger.writeLog("подтверждена.\n");
        }
        return confirmTransaction.getOperationID().toString();
    }

}
