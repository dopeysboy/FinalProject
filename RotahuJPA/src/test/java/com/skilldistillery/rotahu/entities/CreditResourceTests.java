package com.skilldistillery.rotahu.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
	void test_CreditResource_mapping() {
		assertNotNull(creditResource);
		assertEquals("nice resource", creditResource.getDescription());
		assertEquals(null, creditResource.getImageUrl());
		assertEquals(null, creditResource.getSiteUrl());
	}
	@Test
	void test_CreditResource_datetime_mapping() {
		// 2022-11-22 12:00:00
		LocalDateTime expected = LocalDateTime.of(2022, 11, 22, 12, 0, 0);
		LocalDateTime actual = creditResource.getCreatedAt().atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("America/Denver")).toLocalDateTime();
		
		assertEquals(expected, actual);
	}

	@Test
	void test_CreditResource_ServedTo_User_mapping() {
		assertNotNull(creditResource);
		/*
		String expected = "";
		String actual = creditResource.getServedTo().get(0).getUsername();
		
		assertEquals(expected, actual);
		*/
	}
	
	@Test
	void test_CreditResource_CreatedBy_User_mapping() {
		String expected = "admin";
		String actual = creditResource.getCreatedBy().getUsername();
		
		assertEquals(expected, actual);
	}
}
