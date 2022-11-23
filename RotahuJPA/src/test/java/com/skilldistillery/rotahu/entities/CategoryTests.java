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

class CategoryTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Category category;

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
		category = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		category = null;
	}

	@Test
	void test_Category_mapping() {
		assertNotNull(category);
		assertEquals("groceries", category.getName());
	}

	@Test
	void test_Category_Expense_mapping() {
		String expected = "groceries";
		String actual = category.getExpenses().get(0).getName();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_Category_Income_mapping() {
		String expected = "";
		String actual = category.getIncomes().get(0).getName();
		
		assertEquals(expected, actual);
	}
}
