package com.skilldistillery.rotahu.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RatingTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rating rating;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("RotahuJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		rating = em.find(Rating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		rating = null;
	}

	@Test
	void test_basic_mappings() {
		assertNotNull(rating);
		assertEquals(5, rating.getRate());
	}
	
	@Test
	void test_rating_mapping_debt_lender() {
		assertNotNull(rating);
		assertNotNull(rating.getDebtLender());
	}
	
	@Test
	void test_rating_mapping_debt_users() {
		assertNotNull(rating);
		assertNotNull(rating.getUser());
	}

}
