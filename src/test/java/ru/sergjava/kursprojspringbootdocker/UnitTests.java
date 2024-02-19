package ru.sergjava.kursprojspringbootdocker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sergjava.kursprojspringbootdocker.model.Confirm;
import ru.sergjava.kursprojspringbootdocker.model.TransferTransactions;
import ru.sergjava.kursprojspringbootdocker.storage.TransactionsStorageConcurrentHashMap;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Amount;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;
import ru.sergjava.kursprojspringbootdocker.service.MoneyTransactionServiceImpl;
import ru.sergjava.kursprojspringbootdocker.util.Logger;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class UnitTests {
    @Mock
    TransactionsStorageConcurrentHashMap transactionsStorageConcurrentHashMap;
    @Mock
    Amount amount;
    @Mock
    Logger logger;
    @Mock
    Transfer transfer;
    @Mock
    Confirm confirm;
    @Mock
    TransferTransactions transferTransactions;

    //проверяем корректность обращений к метдам хранилища транзакций
    @Test
    public void usageTSCHashMap() throws IOException {
        Mockito.when(logger.writeLog(any())).thenReturn(true);
        Mockito.when(amount.getValue()).thenReturn(100);
        Mockito.when(transfer.getAmount()).thenReturn(amount);
        Mockito.when(confirm.getOperationId()).thenReturn("1");
        Mockito.when(transactionsStorageConcurrentHashMap.getTransactionsByID(any())).thenReturn(transferTransactions);
        Mockito.when(transactionsStorageConcurrentHashMap.saveTransaction(any()))
                .thenReturn(new TransferTransactions(55L, transfer, 1));

        MoneyTransactionServiceImpl moneyTransactionService = new MoneyTransactionServiceImpl(transactionsStorageConcurrentHashMap, logger);
        moneyTransactionService.createMoneyTransaction(transfer);
        moneyTransactionService.confirmTransactions(confirm);
        Mockito.verify(transactionsStorageConcurrentHashMap, times(1)).saveTransaction(any());
        Mockito.verify(transactionsStorageConcurrentHashMap, times(1)).getTransactionsByID(any());
    }

}
