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

class DebtTypeTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DebtType debtType;

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
		debtType = em.find(DebtType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		debtType = null;
	}

	@Test
	void test_DebtType_mapping() {
		assertNotNull(debtType);
		assertEquals("Credit Card", debtType.getDescription());
	}

	@Test
	void test_DebtType_Debt_mapping() {
		String expected = "Navy Federal Credit Card";
		String actual = debtType.getDebts().get(0).getName();
		
		assertEquals(expected, actual);
	}
}
