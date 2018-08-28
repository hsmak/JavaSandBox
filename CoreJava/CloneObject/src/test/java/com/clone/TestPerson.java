/*
 * Test case
 */
package com.clone;

import com.clone.Address;
import com.clone.Person;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class TestPerson{


    @Test
	public void testShallowCloning() {
		
		Person actualPerson;
		Person expectedPerson;
		
		actualPerson = new Person("Husain", new Address("1", "2", "3", 4, "5"));
		expectedPerson = (Person)actualPerson.shallowCloning();

        assertThat(expectedPerson, not(actualPerson));//hamcrest assertion
        assertThat(expectedPerson.getAddress(), is(actualPerson.getAddress()));//hamcrest assertion

		assertNotSame(expectedPerson, actualPerson);
		assertEquals(expectedPerson.getAddress(), actualPerson.getAddress());
		
		actualPerson.changeAddress(new Address());
        assertThat(expectedPerson.getAddress(), is(actualPerson.getAddress()));//hamcrest assertion
		assertEquals(expectedPerson.getAddress(), actualPerson.getAddress());
		
		actualPerson.setAddress(new Address());
        assertThat(expectedPerson.getAddress(), not(actualPerson.getAddress())); //hamcrest assertion
		assertNotSame(expectedPerson.getAddress(), actualPerson.getAddress());
	}

    @Test
	public void testDeepCloning() {
		
		Person actualPerson;
		Person expectedPerson;
		
		actualPerson = new Person("Husain", new Address("1", "2", "3", 4, "5"));
		expectedPerson = (Person)actualPerson.deepCloning();

        assertThat(expectedPerson, not(actualPerson));//hamcrest assertion
        assertThat(expectedPerson.getAddress(), not(actualPerson.getAddress()));//hamcrest assertion

		assertNotSame(expectedPerson, actualPerson);
		assertNotSame(expectedPerson.getAddress(), actualPerson.getAddress());
		
		actualPerson.changeAddress(new Address());
        assertThat(expectedPerson.getAddress(), not(actualPerson.getAddress()));//hamcrest assertion
		assertNotSame(expectedPerson.getAddress(), actualPerson.getAddress());
		
		actualPerson.setAddress(new Address());
        assertThat(expectedPerson.getAddress(), not(actualPerson.getAddress()));//hamcrest assertion
		assertNotSame(expectedPerson.getAddress(), actualPerson.getAddress());
	}

}
