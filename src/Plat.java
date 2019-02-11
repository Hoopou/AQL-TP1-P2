//*********************************************************************
//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Plat.java
//
//********************************************************************* 

public class Plat {
	private String name = null;
	private double prix = 0.0;

	public Plat(String name, double prix) {
		this.name = name;
		this.prix = prix;
	}

	public double getPrix() {
		return this.prix;
	}

	public boolean Equals(Object arg0) {
		// TODO Auto-generated method stub
		return name.equals(arg0.toString());
	}
}
