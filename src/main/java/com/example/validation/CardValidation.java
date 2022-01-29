package com.example.validation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import com.example.entity.Card;

public class CardValidation {

	public Optional<String> validateCard(Card card) {
		Optional<String> msg = isValidLength(card.getCardNumber());

		msg = msg.isEmpty() ? isLuhnPass(card.getCardNumber()) : msg;
		msg = msg.isEmpty() ? validateNumber(card.getBalance()) : msg;
		msg = msg.isEmpty() ? validateNumber(card.getCardLimit()) : msg;
		msg = msg.isEmpty() ? validateCardBalance(card.getBalance()) : msg;
		return msg;
	}

	public Optional<String> validateNumber(String value) {
		try {
			new BigDecimal(Optional.ofNullable(value).get());
		} catch (NumberFormatException exception) {
			return Optional.of("Invalid number ");
		}
		return Optional.empty();
	}

	public Optional<String> validateCardBalance(String balance) {
		String[] arr = String.valueOf(balance).split("\\.");
		long count = Arrays.asList(arr).stream().map(e-> Integer.valueOf(e)).filter(e-> e!=0).count();
		if (count!=0) {
			return Optional.of("New card should start with zero balance");
		}
		return Optional.empty();
	}

	private Optional<String> isValidLength(String cardNumber) {
		Optional<String> msg = Optional.of("invalid card length");
		try {
			Long.parseLong(cardNumber);
		} catch (NumberFormatException exception) {
			return Optional.of("invalid card data");
		}

		if (cardNumber.length() > 19) {
			return msg;
		}
		return Optional.empty();
	}

	private Optional<String> isLuhnPass(String cardNumber) {
		return !checkLuhn(cardNumber) ? Optional.of("Luhn check fails") : Optional.empty();
	}

	private boolean checkLuhn(String cardNo) {
		int nDigits = cardNo.length();

		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = cardNo.charAt(i) - '0';

			if (isSecond == true)
				d = d * 2;

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}
}
