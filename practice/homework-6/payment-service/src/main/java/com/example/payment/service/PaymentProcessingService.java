package com.example.payment.service;

import com.example.payment.dto.PaymentEventDto;
import com.example.payment.entity.AccountEntity;
import com.example.payment.entity.InboxMessage;
import com.example.payment.repository.AccountRepository;
import com.example.payment.repository.InboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentProcessingService {

    private final InboxRepository inboxRepository;
    private final AccountRepository accountRepository;

    public PaymentProcessingService(InboxRepository inboxRepository,
                                    AccountRepository accountRepository) {
        this.inboxRepository = inboxRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public boolean handlePaymentEvent(String payload) {
        ObjectMapper mapper = new ObjectMapper();
        PaymentEventDto event;

        try {
            event = mapper.readValue(payload, PaymentEventDto.class);
        } catch (Exception e) {
            InboxMessage inbox = new InboxMessage();
            inbox.setId(UUID.fromString("113b9c61-939b-48d5-b764-ab057554b5fa"));
            inbox.setEventType("ORDER_FAILED");
            inbox.setPayload(payload);
            inbox.setErrorMessage("invalid payload format");
            inbox.setReceivedAt(LocalDateTime.now());
            inbox.setProcessed(false);
            inboxRepository.save(inbox);
            return false;
        }

        if (inboxRepository.existsById(event.getOrderId())) {
            InboxMessage inbox = new InboxMessage();
            inbox.setId(event.getOrderId());
            inbox.setEventType("ORDER_FAILED");
            inbox.setPayload(payload);
            inbox.setErrorMessage("Message already processed");
            inbox.setReceivedAt(LocalDateTime.now());
            inbox.setProcessed(false);
            inboxRepository.save(inbox);
            return false;
        }

        AccountEntity account = accountRepository.findById(event.getUserId()).orElse(null);

        if (account == null) {
            InboxMessage inbox = new InboxMessage();
            inbox.setId(event.getOrderId());
            inbox.setEventType("ORDER_FAILED");
            inbox.setPayload(payload);
            inbox.setErrorMessage("account not exist");
            inbox.setReceivedAt(LocalDateTime.now());
            inbox.setProcessed(false);
            inboxRepository.save(inbox);
            return false;
        }

        if (account.getBalance() < event.getAmount()) {
            InboxMessage inbox = new InboxMessage();
            inbox.setId(event.getOrderId());
            inbox.setEventType("ORDER_FAILED");
            inbox.setPayload(payload);
            inbox.setErrorMessage("not enough money");
            inbox.setReceivedAt(LocalDateTime.now());
            inbox.setProcessed(false);
            inboxRepository.save(inbox);
            return false;
        }

        account.setBalance(account.getBalance() - event.getAmount());
        accountRepository.save(account);

        InboxMessage inbox = new InboxMessage();
        inbox.setId(event.getOrderId());
        inbox.setEventType("ORDER_PAID");
        inbox.setPayload(payload);
        inbox.setReceivedAt(LocalDateTime.now());
        inbox.setProcessed(true);

        inboxRepository.save(inbox);

        return true;
    }
}