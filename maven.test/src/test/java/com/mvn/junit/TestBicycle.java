package com.mvn.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestBicycle {
	
	Bicycle bike = null;
	
	DatabaseConnection dbCon = Mockito.mock(DatabaseConnection.class);
	
	@Before
	public void init() {
		bike = new Bicycle(dbCon);
	}

	@Test
	public void testSum() {
		
		when(dbCon.checkUserPass("gyula", "jelszo")).thenReturn(true);
		
		
		Integer expected = 3;
		assertEquals(expected, bike.sum("gyula", "jelszo", 1, 1, 1));
		verify(dbCon).checkUserPass("gyula", "jelszo");
	}
}
