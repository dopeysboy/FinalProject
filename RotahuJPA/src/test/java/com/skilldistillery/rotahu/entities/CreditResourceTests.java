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

class CreditResourceTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private CreditResource creditResource;

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
		creditResource = em.find(CreditResource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		creditResource = null;
	}

	@Test
	void test() {
		assertNotNull(creditResource);
//		assertEquals("", creditResource.get);
	}

}
