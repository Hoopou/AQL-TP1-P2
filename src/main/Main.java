package main;

//*********************************************************************
//
//Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
//Date : 11 février 2019
//Fichier : Main.java
//
//********************************************************************* 

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;

import org.junit.internal.runners.InitializationError;

import DataObject.Client;
import DataObject.Commandes;
import DataObject.Plat;

public class Main {
	
	private static String input = "input.txt";
	private static ArrayList<String> arrayErreurs = new ArrayList<String>();
	private static boolean format = false;
	
	private static FileManager fileManager = new FileManager();
	private static ArrayList<Client> arrayClients = new ArrayList<Client>();
	private static ArrayList<Plat> arrayPlats = new ArrayList<Plat>();
	private static ArrayList<Commandes> arrayCommandes = new ArrayList<Commandes>();
	private static NumberFormat formatter = new DecimalFormat("#0.00");

	public static void main(String[] args) {
		//test 
		System.out.println(Helper.isInputFileConform(input));
		System.out.println(Helper.clientsVide(input));
		System.out.println(Helper.PlatsVide(input));
		System.out.println(Helper.CommandesVide(input));
		
		//Erreur format fichier incorrect
		if(!Helper.isInputFileConform(input)) {
			arrayErreurs.add("Erreur: Le format du fichier est incorrect.");
			format = true;
		}
		//Erreur aucun clients
		if(Helper.clientsVide(input)) {
			arrayErreurs.add("Erreur: Le fichier ne contient aucun clients.");
		}
		//Erreur aucun plat
		if(Helper.PlatsVide(input)) {
			arrayErreurs.add("Erreur: Le fichier ne contient aucun plats.");
		}
		//Erreur aucune commande
		if(Helper.CommandesVide(input)) {
			arrayErreurs.add("Erreur: Le fichier ne contient aucune commandes.");
		}
		
		boolean Reader = fileManager.setReader(input, "Erreur lors de l'initialisation du lecteur du fichier input");

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
		fileManager.writeLine("Erreurs et commandes incorrectes:",
				"Erreur lors de l'écriture dans le fichier de sortie");
		
		ecrireErreurs(fileManager);
		
		fileManager.writeLine("\nBienvenue chez Barette!\r", "Erreur lors de l'écriture dans le fichier de sortie");
		fileManager.writeLine("Facture:", "Erreur lors de l'écriture dans le fichier de sortie");

		double total = 0;
		boolean factureVide = true;
		
		for (Client c : arrayClients) {
			for (Commandes commande : arrayCommandes) {
				if (commande.Contains(c) && commande.getFacture() != null) {
					fileManager.write(c.getName() + ": " + commande.getFacture() + "$\n",
							"Erreur lors de l'écriture dans le fichier");
					total += commande.getPrix();
					factureVide = false;
					System.out.println(c.getName() + ": " + commande.getFacture() + "$");
					break;
				} else if (commande == arrayCommandes.get(arrayCommandes.size() - 1)) {
//					fileManager.write("0.00$\n");
//					System.out.println("0.00$");
				}
			}
		}
		if(!factureVide) {
			fileManager.writeLine("\nSous-Total:\t" + formatter.format(total) + "$", "Erreur lors de l'écriture dans le fichier");
			fileManager.writeLine("TPS:\t\t" + formatter.format(calculerTaxes(total, 0.05)) + "$", "Erreur lors de l'écriture dans le fichier");
			fileManager.writeLine("TVQ:\t\t" + formatter.format(calculerTaxes(total, 0.1)) + "$", "Erreur lors de l'écriture dans le fichier");
			fileManager.writeLine("Total:\t\t" + formatter.format(calculerTaxes(total, 1.15)) + "$", "Erreur lors de l'écriture dans le fichier");
		}
	}
	
	public static double calculerTaxes(double total, double t) {
		double retour;
		retour = total * t;
		return retour;
	}
	
	public static void ecrireErreurs(FileManager fileManager) {
		if(format) {
			fileManager.writeLine("Erreur: Format du fichier incorrect", "Erreur lors de l'écriture dans le fichier");
			fileManager.closeAll("Erreur lors de la fermeture du reader ou du writer");
			System.exit(0);
		}else {
			for(String a : arrayErreurs) {
				fileManager.writeLine(a, "Erreur lors de l'écriture dans le fichier");
			}	
		}
	}
}
