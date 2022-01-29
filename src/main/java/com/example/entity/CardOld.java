package com.example.entity;

public class CardOld {
	private String cardNumber;
	private String name;
	private String balance;
	private String cardLimit;
	
//	public Card(String cardNumber, String name, String balance, String cardLimit) {
//		super();
//		this.cardNumber = cardNumber;
//		this.name = name;
//		this.balance = balance;
//		this.cardLimit = cardLimit;
//	}

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
