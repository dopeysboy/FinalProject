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

class ExpenseTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Expense expense;

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
		expense = em.find(Expense.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		expense = null;
	}

	@Test
	void test() {
		assertNotNull(expense);
		assertEquals("groceries", expense.getName());
	}

}
