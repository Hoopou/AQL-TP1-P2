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

public class Main {

	public static void main(String[] args) {

		ArrayList<Client> arrayClients = new ArrayList<Client>();
		ArrayList<Plat> arrayPlats = new ArrayList<Plat>();
		ArrayList<Commandes> arrayCommandes = new ArrayList<Commandes>();

		BufferedReader ficLecture = null;
		try {
			ficLecture = new BufferedReader(new FileReader("inputData.txt"));
			String ancien = null;
			String line = null;
			while ((line = ficLecture.readLine()) != null) {
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
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter("output.txt"));
				writer.write("Les entrées du fichier ne sont pas conformes!");
				writer.close();
				System.exit(0);
			} catch (IOException y) {
				// TODO Auto-generated catch block
				y.printStackTrace();
			}
		}
		;

		System.out.println("Bienvenue chez Barette!\r\n" + "Factures:");

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write("Bienvenue chez Barette!\r\n" + "Factures:\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (Client c : arrayClients) {
				writer.write(c.getName() + ": ");
				System.out.print(c.getName() + ": ");
				for (Commandes commande : arrayCommandes) {
					if (commande.Contains(c)) {
						writer.write(commande.getFacture() + "$\n");
						System.out.println(commande.getFacture() + "$");
						break;
					} else if (commande == arrayCommandes.get(arrayCommandes.size() - 1)) {
						writer.write("0.00$\n");
						System.out.println("0.00$");
					}
				}
			}
			writer.close();
		} catch (Exception e) {

		}

	}

}
