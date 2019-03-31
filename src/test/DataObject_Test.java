package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import DataObject.Client;
import main.FileManager;
import main.Helper;
import main.Main;

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

	@Test
	void testFile() {
		assertEquals(true, Helper.isInputFileConform("./inputData.txt"), "Le fichier est conforme");
	}

	@Test
	void testFichierInputExistant() {
		boolean erreur = false;
		erreur = new FileManager().setReader("FichierInexistant.txt", "Le fichier FichierInexistant.txt n'existe pas");
		assertEquals(false, erreur, "Un fichier inexistant est considéré comme existant par le programme");
	}
	
	@Test
	void testTaxes() {
		assertEquals(5.75, Main.calculerTaxes(5, 1.15), "Calcul de taxe fonctionne");
	}
	
	@Test
	void testClientsVide() {
		assertEquals(true, Helper.clientsVide("./inputErreurAucunClient.txt"), "Il n'y a aucun client dans le fichier inputErreurAucunClient.txt");
	}
	
	@Test
	void testPlatsVide() {
		assertEquals(true, Helper.PlatsVide("./inputErreurAucunPlat.txt"), "Il n'y a aucun plats dans le fichier inputErreurAcuunPlat.txt");
	}
	
	@Test
	void testCommandesVide() {
		assertEquals(true, Helper.CommandesVide("./inputErreurAucuneCommande.txt"), "Il n'y a aucune commandes dans le fichier inputErreurAucuneCommande.txt");
	}
}
