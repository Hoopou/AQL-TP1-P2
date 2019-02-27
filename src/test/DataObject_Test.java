package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import main.Client;

@RunWith(MockitoJUnitRunner.class)
class DataObject_Test {

	@Mock
	Client client = new Client("Serge");
	
	@Test
	void Client_Name() {
		assertEquals("Serge", client.getName(), "Nom du client");
	}

	@Test
	void Client() {
		Client clientTemp = new Client("Serge");
		assertEquals(true, client.Equals(clientTemp), "Equals du client");
	}
	
}
