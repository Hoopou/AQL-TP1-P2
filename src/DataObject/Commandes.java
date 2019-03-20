package DataObject;
//*********************************************************************
//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Commandes.java
//
//********************************************************************* 

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Commandes implements InterfaceLigneFichier{
	private Client client;
	private Plat plat;
	private int quantite = 0;
	private final double taxe= 1.15;

	public Commandes(Client client, Plat plat, int quantite) {
		this.client = client;
		this.plat = plat;
		this.quantite = quantite;
	}

	public boolean Contains(Client client) {
		return this.client.Equals(client);
	}

	public Plat getPlat() {
		return this.plat;
	}

	public Client getClient() {
		return this.client;
	}

	public int getQuantite() {
		return this.quantite;
	}
	
	public double getPrix() {
		return getPlat().getPrix() * getQuantite();
	}

	public String getFacture() {
		if(getPrix() == 0)
			return null;
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(getPrix());
	}

	@Override
	public void InterfaceLigneFichier(String ligneFichier) {
		// TODO Auto-generated method stub
		
	}
}
