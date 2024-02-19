package ru.sergjava.kursprojspringbootdocker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergjava.kursprojspringbootdocker.model.Confirm;
import ru.sergjava.kursprojspringbootdocker.model.transfer.SuccessfulOperationObject;
import ru.sergjava.kursprojspringbootdocker.model.transfer.Transfer;
import ru.sergjava.kursprojspringbootdocker.service.MoneyTransactionService;

import java.io.IOException;

@RestController
public class MoneyTransactionController {
    private final MoneyTransactionService moneyTransactionService;

    public MoneyTransactionController(MoneyTransactionService moneyTransactionService) {
        this.moneyTransactionService = moneyTransactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<SuccessfulOperationObject> createTransfer(@RequestBody Transfer transfer) throws IOException {
        System.out.println("Получен запрос: "+ transfer.toString());
        return ResponseEntity.ok(
                new SuccessfulOperationObject(moneyTransactionService.createMoneyTransaction(transfer))
        );

    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<SuccessfulOperationObject> confirmOperation(@RequestBody Confirm confirm) throws IOException {
        System.out.println("Получен запрос: "+ confirm.toString());
        return ResponseEntity.ok(
                new SuccessfulOperationObject(moneyTransactionService.confirmTransactions(confirm))
        );

    }


}
