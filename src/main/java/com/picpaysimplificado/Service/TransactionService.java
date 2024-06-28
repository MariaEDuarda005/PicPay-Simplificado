package com.picpaysimplificado.Service;

import com.picpaysimplificado.doman.User.User;
import com.picpaysimplificado.doman.transaction.Transaction;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate; // spring oferece para fazer comunicações http serviços, atraves dela pode fazer chamadas http do tipo get, post, etc


    public void createTransaction(TransactionDTO transaction) throws Exception{
        User sender =  this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value()); // função do user service para validar se o usuario existe e se ele tem saldo

        boolean isAuthorazed = this.authorizeTransaction(sender, transaction.value());

        // fazer se a transação se não estiver autorizada
        if (!isAuthorazed){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value()); // transaction.value() pois tem que pegar do DTO
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        // o novo balance do meu sender vai ser o get balance subtraido o valor da transação
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        // o novo balance do meu receiver vai ser o get balance adicionado o valor da transação
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        // Salva a transação e os usuários atualizados no repositório
        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (authorizeResponse.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = authorizeResponse.getBody();
            if (responseBody != null && "success".equals(responseBody.get("status"))) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                if (data != null && Boolean.TRUE.equals(data.get("authorization"))) {
                    return true;  // Transação autorizada
                }
            }
        }

        return false; // Transação não autorizada ou qualquer outra condição que não caia nos ifs anteriores
    }
}
