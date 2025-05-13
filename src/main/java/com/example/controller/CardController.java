package com.example.controller;

import com.example.entity.Card;
import com.example.repository.CardRepository;
import com.example.validation.CardValidation;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {
	private final Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
    private CardRepository repository;

	@Autowired
	CardValidation validation;

	private final Counter myCounter;

	public CardController(MeterRegistry registry) {
		this.myCounter = Counter.builder("my_card_counter")
				.description("A Card counter metric")
				.register(registry);
	}
	
	@PostMapping(path="/add" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<Object> addNewCard(@RequestBody Card card){
		System.out.println(card);
		myCounter.increment();
		Optional<String> validate = this.validation.validateCard(card);
		if(!validate.isEmpty()) {
			logger.info("Validation failed for card number", validate.get());
			return new ResponseEntity<>(validate.get(), HttpStatus.BAD_REQUEST);
		}
		this.repository.save(card);
		return new ResponseEntity<>("Details Added", HttpStatus.OK);
	}
	
	@GetMapping(path="/getAll" , produces = "application/json")
	public List<Card> getAllCards() {
		return this.repository.findAll();
	}
	
}
