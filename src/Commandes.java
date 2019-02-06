
public class Commandes {
	private Client client;
	private Plat plat;
	private int quantite;
	
	public Commandes(Client client , Plat plat , int quantite) {
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
}
