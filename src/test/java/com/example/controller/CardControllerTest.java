package com.example.controller;


import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.card.CardApplicationTests;
import com.example.entity.Card;

@TestMethodOrder(OrderAnnotation.class)
public class CardControllerTest extends CardApplicationTests {

	@Test
	@Order(2)
	public void addNewCard() throws Exception {
		String uri = "/add";
		Card card = getCard();

		String inputJson = super.mapToJson(card);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(HttpStatus.OK.value(), status);
		String response = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(response, "Details Added");
	}
	
	@Test
	@Order(3)
	public void addNewCardInvalidCardLength() throws Exception {
		String uri = "/add";
		Card card = getCard();
		card.setCardNumber("37424545540012600000");

		String inputJson = super.mapToJson(card);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), status);
		String response = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(response, "invalid card data");
	}
	
	@Test
	@Order(4)
	public void addNewCardInvalidCardData() throws Exception {
		String uri = "/add";
		Card card = getCard();
		card.setCardNumber("374245455400126#$");
		
		String inputJson = super.mapToJson(card);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), status);
		String response = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(response, "invalid card data");
	}
	
	@Test
	@Order(5)
	public void addNewCardWithSomeBalance() throws Exception {
		String uri = "/add";
		Card card = getCard();
		card.setBalance("1.00");

		String inputJson = super.mapToJson(card);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), status);
		String response = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(response, "New card should start with zero balance");
	}

	@Test
	@Order(5)
	public void getAllCards() throws Exception {
		String uri = "/getAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(HttpStatus.OK.value(), status);
		String response = mvcResult.getResponse().getContentAsString();
		Assert.assertNotNull(response);
	}
	
	public Card getCard() {
		Card card = new Card();
		card.setCardNumber("374245455400126");
		card.setName("Vineet Gupta");
		card.setBalance("0.00");
		card.setCardLimit("12000.00");
		return card;
	}
}
