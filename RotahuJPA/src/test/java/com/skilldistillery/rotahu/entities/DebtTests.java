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

class DebtTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Debt debt;

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
		debt = em.find(Debt.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		debt = null;
	}

	@Test
	void test_debt_basic_mappings() {
		assertNotNull(debt);
		assertNotNull(debt.getName());
		assertNotNull(debt.getInitialBalance());
		assertEquals("Navy Federal Credit Card", debt.getName());
		assertEquals(7500, debt.getInitialBalance());
	}

	@Test
	void test_Debt_Payment_mapping() {
		double expected = 750;
		double actual = debt.getPayments().get(0).getAmount();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Debt_DebtType_mapping() {
		String expected = "Credit Card";
		String actual = debt.getDebtType().getName();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Debt_DebtLender_mapping() {
		String expected = "Navy Federal";
		String actual = debt.getDebtLender().getName();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Debt_User_mapping() {
		String expected = "admin";
		String actual = debt.getUser().getUsername();
		
		assertEquals(expected, actual);
	}
}
