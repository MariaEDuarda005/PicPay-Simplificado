package com.picpaysimplificado.controllers;

import com.picpaysimplificado.doman.transaction.Transaction;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
