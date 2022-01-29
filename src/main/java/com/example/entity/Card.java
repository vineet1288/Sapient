package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARDDETAILS")
public class Card {
	
	private static final long serialVersionUID = -2147468513335906679L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "card_number")
	private String cardNumber;
    
    @Column(name = "name")
	private String name;
    
    @Column(name = "balance")
	private String balance;
    
    @Column(name = "card_limit")
	private String cardLimit;

	public Card() {
		super();
	}

	public Card(String cardNumber, String name, String balance, String cardLimit) {
		super();
		this.cardNumber = cardNumber;
		this.name = name;
		this.balance = balance;
		this.cardLimit = cardLimit;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}
    
}
