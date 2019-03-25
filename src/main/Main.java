package main;
//*********************************************************************

//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Main.java
//
//********************************************************************* 

import java.util.ArrayList;

import org.junit.internal.runners.InitializationError;

import DataObject.Client;
import DataObject.Commandes;
import DataObject.Plat;

public class Main {
	private static FileManager fileManager = new FileManager();
	private static ArrayList<Client> arrayClients = new ArrayList<Client>();
	private static ArrayList<Plat> arrayPlats = new ArrayList<Plat>();
	private static ArrayList<Commandes> arrayCommandes = new ArrayList<Commandes>();

	public static void main(String[] args) {

		boolean Reader = fileManager.setReader("inputErreurAucunClient.txt", "Erreur lors de l'initialisation du lecteur du fichier input");
		if(!Reader) {
			System.err.println("Arret du programme");
			System.exit(0);
		}

		try {
			String ancien = null;
			String line = null;
			while ((line = fileManager.readLine("Erreur lors de la lecture dans le fichier text")) != null) {
				if (line.endsWith(":")) {
					ancien = line.replace(" :", "");
				} else if (ancien.equals("Clients")) {
					arrayClients.add(new Client(line));
				} else if (ancien.equals("Plats")) {
					arrayPlats.add(new Plat(line));
				} else if (ancien.equals("Commandes")) {
					for (Client nom : arrayClients) {
						if (nom.getName().equals(line.split(" ")[0])) {
							for (Plat plat : arrayPlats) {
								if (plat.Equals(line.split(" ")[1])) {
									arrayCommandes.add(new Commandes(nom, plat, line));
									break;
								}
							}
						}
					}
				}
			}

			System.out.println("");
		} catch (Exception e) {
			fileManager.setWriter(Helper.getNomFacture(), "Erreur fatal de lecture ");

			if (!fileManager.write("Les entrées du fichier ne sont pas conformes!",
					"Erreur fatal d'écriture dns le fichier output ")) {
				System.exit(0);
			}
		}

		fileManager.closeAll("Erreur lors de la fermeture du reader ou du writer");

		ecrireFactures();

		fileManager.closeAll("Erreur lors de la fermeture du reader ou du writer");


	}

	private static void ecrireFactures() {
		fileManager.setWriter(Helper.getNomFacture(), "Erreur lors de la création du fichier de sortie");

		System.out.println("Bienvenue chez Barette!\r\n" + "Factures:\n");
		fileManager.writeLine("Commandes et erreurs incorrectes:",
				"Erreur lors de l'écriture dans le fichier de sortie");
		fileManager.writeLine("\nBienvenue chez Barette!\r", "Erreur lors de l'écriture dans le fichier de sortie");
		fileManager.writeLine("Facture:", "Erreur lors de l'écriture dans le fichier de sortie");

		for (Client c : arrayClients) {
			for (Commandes commande : arrayCommandes) {
				if (commande.Contains(c) && commande.getFacture() != null) {
					fileManager.write(c.getName() + ": " + commande.getFacture() + "$\n",
							"Erreur lors de l'écriture dans le fichier");
					System.out.println(c.getName() + ": " + commande.getFacture() + "$");
					break;
				} else if (commande == arrayCommandes.get(arrayCommandes.size() - 1)) {
//					fileManager.write("0.00$\n");
//					System.out.println("0.00$");
				}
			}
		}
	}

}
