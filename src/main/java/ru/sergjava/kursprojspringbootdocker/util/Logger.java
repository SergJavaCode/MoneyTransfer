package ru.sergjava.kursprojspringbootdocker.util;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.sergjava.kursprojspringbootdocker.model.TransferTransactions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Logger {
    @Value("${log.file}")
    private String logFile;
    @Setter
    private TransferTransactions transferTransactions;

    public boolean writeLog(String status) throws IOException {
        String message;
        if (transferTransactions == null) {
            message = status;
        } else {
            message = String.format(("Транзакция (ID %d) с карты № %s на карту № %s в суммме %d %s с комиссией %d %s. Статуc: %s"),
                    transferTransactions.getOperationID(),
                    transferTransactions.getTransfer().getCardFromNumber(),
                    transferTransactions.getTransfer().getCardToNumber(),
                    transferTransactions.getTransfer().getAmount().getValue(),
                    transferTransactions.getTransfer().getAmount().getCurrency(),
                    transferTransactions.getCommission(),
                    transferTransactions.getTransfer().getAmount().getCurrency(),
                    status);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.now();
        String formattedDateTime = ldt.format(formatter);
        try (FileWriter writerLog = new FileWriter(logFile, true)) {
            writerLog.write(formattedDateTime + " " + message);
            writerLog.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
