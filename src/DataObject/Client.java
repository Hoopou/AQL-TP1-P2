package DataObject;
//*********************************************************************

//
// Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
// Date : 11 février 2019
// Fichier : Client.java
//
//********************************************************************* 

public class Client implements InterfaceLigneFichier {
	private String name = null;

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean Equals(Client client) {
		// TODO Auto-generated method stub
		return name.equals(client.getName());
	}

	@Override
	public void InterfaceLigneFichier(String ligneFichier) {

	}

}
