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

class DebtLenderTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DebtLender debtLender;

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
		debtLender = em.find(DebtLender.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		debtLender = null;
	}

	@Test
	void test_DebtLender_mapping() {
		assertNotNull(debtLender);
		assertEquals("Navy Federal", debtLender.getName());
	}
	
	@Test
	void test_DebtLender_Debt_mapping() {
		double expected = 18.65;
		double actual = debtLender.getDebts().get(0).getMonthlyInterestRate();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_DebtLender_Rating_mapping() {
		String expected = "1 year 0% APR";
		String actual = debtLender.getRatings().get(0).getDescription();
		
		assertEquals(expected, actual);
	}
}
