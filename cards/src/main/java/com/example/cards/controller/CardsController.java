package com.example.cards.controller;

import com.example.cards.config.AccountsServiceConfig;
import com.example.cards.model.Cards;
import com.example.cards.model.Customer;
import com.example.cards.model.Properties;
import com.example.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    public final CardsRepository cardsRepository;
    public final AccountsServiceConfig accountsConfig;

    public CardsController(CardsRepository cardsRepository,
                           AccountsServiceConfig accountsConfig) {
        this.cardsRepository = cardsRepository;
        this.accountsConfig = accountsConfig;
    }

    @PostMapping("/myCards")
    public List<Cards> getInfoAboutCards(@RequestBody Customer customerId) {
        List<Cards> cards = cardsRepository.findByCustomerId(customerId.getCustomerId());

        if(cards != null) {
            return cards;
        } else {
            return null;
        }
    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }
}