package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
