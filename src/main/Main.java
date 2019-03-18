package main;
//*********************************************************************
//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Main.java
//
//********************************************************************* 

import java.util.ArrayList;

import DataObject.Client;
import DataObject.Commandes;
import DataObject.Plat;

public class Main {
	private static FileManager fileManager = new FileManager();
	private static ArrayList<Client> arrayClients = new ArrayList<Client>();
	private static ArrayList<Plat> arrayPlats = new ArrayList<Plat>();
	private static ArrayList<Commandes> arrayCommandes = new ArrayList<Commandes>();

	public static void main(String[] args) {
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
		
		try {
			fileManager.closeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ecrireFactures();
		try {
			fileManager.closeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void ecrireFactures() {
		System.out.println("Bienvenue chez Barette!\r\n" + "Factures:");

		fileManager.setWriter("output.txt");
		
		fileManager.writeLine("Bienvenue chez Barette!\r");
		fileManager.writeLine("Factures:");

		for (Client c : arrayClients) {
			for (Commandes commande : arrayCommandes) {
				if (commande.Contains(c) && commande.getFacture() != null) {
					fileManager.write(c.getName() + ": " + commande.getFacture() + "$\n");
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
