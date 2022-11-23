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

class IncomeTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Income income;

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
		income = em.find(Income.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		income = null;
	}

	@Test
	void test_basic_mappings() {
		assertNotNull(income);
		assertEquals(7500, income.getAmount());
	}
	
	@Test
	void test_income_mapping_category() {
		assertNotNull(income);
		assertNotNull(income.getCategory());
	}
	
	@Test
	void test_income_mapping_frequency() {
		assertNotNull(income);
		assertNotNull(income.getFrequency());
	}
	
	@Test
	void test_income_mapping_user() {
		assertNotNull(income);
		assertNotNull(income.getUser());
	}

}
