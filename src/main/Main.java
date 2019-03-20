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
		
		try {
			fileManager.setReader("inputData.txt");
		} catch (InitializationError e2) {
			System.out.println("Erreur lors de l'initialisation du lecteur du fichier input");
			System.exit(0);
		}
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
					Plat plat = new Plat(line.split(" ")[0], Double.parseDouble(line.split(" ")[1])); //pour ligne du plat
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
			try {
				fileManager.setWriter(Helper.getNomFacture());				
			}catch(Exception g) {
				System.out.println("Erreur fatal de lecture ");
				System.exit(0);
			}
			
			try {
				if (!fileManager.write("Les entrées du fichier ne sont pas conformes!")) {
					System.exit(0);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Erreur fatal d'écriture dns le fichier output ");
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
		try {
			fileManager.setWriter(Helper.getNomFacture());						
		}catch(Exception e) {
			System.out.println("Erreur lors de la création du fichier de sortie");
		}
		
		System.out.println("Bienvenue chez Barette!\r\n" + "Factures:");		
		try {
			fileManager.writeLine("Commandes et erreurs incorrectes:");
			fileManager.writeLine("dfsrgfs");
			
			fileManager.writeLine("\nBienvenue chez Barette!\r");
			fileManager.writeLine("Facture:");
		}catch(Exception e) {
			System.out.println("Erreur lors de l'écriture dans le fichier de sortie");
		}

		for (Client c : arrayClients) {
			for (Commandes commande : arrayCommandes) {
				if (commande.Contains(c) && commande.getFacture() != null) {
					try {
						fileManager.write(c.getName() + ": " + commande.getFacture() + "$\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur lors de l'écriture dans le fichier");
					}
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
