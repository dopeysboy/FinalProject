package com.skilldistillery.rotahu.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTests {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_basic_mappings() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
	
	@Test
	void test_user_mapping_incomes() {
		assertNotNull(user);
		assertNotNull(user.getIncomes());
	}
	
	@Test
	void test_user_mapping_expenses() {
		assertNotNull(user);
		assertNotNull(user.getExpenses());
	}
	
	@Test
	void test_user_mapping_debts() {
		assertNotNull(user);
		assertNotNull(user.getDebts());
	}
	
	@Test
	void test_user_mapping_createdResources() {
		assertNotNull(user);
		assertNotNull(user.getCreatedResources());
	}
	
	@Test
	void test_user_mapping_creditResources() {
		assertNotNull(user);
		assertNotNull(user.getCreditResources());
	}

}
