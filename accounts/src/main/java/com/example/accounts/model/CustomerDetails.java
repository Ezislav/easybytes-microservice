package com.example.accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class CustomerDetails {

    private Accounts accounts;
    private List<Cards> cardsList;
    private List<Loans> loansList;
}
