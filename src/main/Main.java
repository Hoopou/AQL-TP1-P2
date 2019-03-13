package main;
//*********************************************************************
//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Main.java
//
//********************************************************************* 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DataObject.Client;
import DataObject.Commandes;
import DataObject.Plat;

public class Main {

	public static void main(String[] args) {
		ArrayList<Client> arrayClients = new ArrayList<Client>();
		ArrayList<Plat> arrayPlats = new ArrayList<Plat>();
		ArrayList<Commandes> arrayCommandes = new ArrayList<Commandes>();
		FileManager fileManager = new FileManager();
		fileManager.setReader("inputData.txt");
		try {
			String ancien = null;
			String line = null;
			while ((line = fileManager.readLine()) != null) {
				if (line.endsWith(":")) {

					ancien = line.replace(" :", "");

				} else if (ancien.equals("Clients")) {
					Client client = new Client(line);
					arrayClients.add(client);
				} else if (ancien.equals("Plats")) {
					Plat plat = new Plat(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));
					arrayPlats.add(plat);
				} else if (ancien.equals("Commandes")) {
					for (Client nom : arrayClients) {
						if (nom.getName().equals(line.split(" ")[0])) {
							for (Plat plat : arrayPlats) {
								if (plat.Equals(line.split(" ")[1])) {
									Commandes commande = new Commandes(nom, plat, Integer.parseInt(line.split(" ")[2]));
									arrayCommandes.add(commande);
									break;
								}
							}
						}
					}
				}
			}

			System.out.println("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Les entrées du fichier ne sont pas conformes!");
			fileManager.setWriter("output.txt");
			if(!fileManager.write("Les entrées du fichier ne sont pas conformes!")) {
				System.exit(0);
			}
		}
		fileManager.closeAll();
		System.out.println("Bienvenue chez Barette!\r\n" + "Factures:");

		fileManager.setWriter("output.txt");
		
		fileManager.writeLine("Bienvenue chez Barette!\r");
		fileManager.writeLine("Factures:");

		for (Client c : arrayClients) {
			fileManager.write(c.getName() + ": ");
			System.out.print(c.getName() + ": ");
			for (Commandes commande : arrayCommandes) {
				if (commande.Contains(c)) {
					fileManager.write(commande.getFacture() + "$\n");
					System.out.println(commande.getFacture() + "$");
					break;
				} else if (commande == arrayCommandes.get(arrayCommandes.size() - 1)) {
					fileManager.write("0.00$\n");
					System.out.println("0.00$");
				}
			}
		}
		fileManager.closeAll();

	}

}
