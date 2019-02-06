import java.io.BufferedReader;
import java.io.FileReader;
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
				if(line.endsWith(":")) {
					
						ancien = line.replace(" :", "");
					
				}else if(ancien.equals("Clients")) {
					Client client = new Client(line);
					arrayClients.add(client);
				}else if(ancien.equals("Plats")) { 
					Plat plat = new Plat(line.split(" ")[0] , Double.parseDouble(line.split(" ")[1]));
					arrayPlats.add(plat);
				}else if(ancien.equals("Commandes")) { 
					for(Client nom : arrayClients) {
						if(nom.getName().equals(line.split(" ")[0])) {
							for(Plat plat : arrayPlats) {
								if(plat.Equals(line.split(" ")[1])) {
									Commandes commande = new Commandes(nom , plat , Integer.parseInt(line.split(" ")[2]));
									arrayCommandes.add(commande);
									break;
								}
							}
						}
					}
				}
			}
			
			
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Les entrées du fichier ne sont pas conformes!");
		};
		
		System.out.println("Bienvenue chez Barette!\r\n" + 
		"Factures:");
		
		for(Client c : arrayClients) {
			System.out.print(c.getName() + ": ");
			for(Commandes commande: arrayCommandes) {
				if(commande.Contains(c)) {
					System.out.println(commande.getPlat().getPrix()*commande.getQuantite());
				}
			}
		}
		    
	}

}
