package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Helper {


	public static String getNomFacture() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd-HH_mm_ss");
		return "Facture-du-" + sdf.format(cal.getTime()) + ".txt";
	}
	
	public static boolean isInputFileConform(String path) {
		ArrayList<FileContent> arrayContenu = new ArrayList<FileContent>();
		arrayContenu.add(new FileContent("clients:"));
		arrayContenu.add(new FileContent("commandes:"));
		arrayContenu.add(new FileContent("plats:"));
		arrayContenu.add(new FileContent("fin"));

		boolean isConform = true;
			
			// initialisation du file manager
			FileManager fm = new FileManager();
			if (!fm.setReader(path, "Erreur lors de l'initialisation du lecteur du fichier input")) {
				System.out.println("Arret du programme!");
				System.exit(0);
			}
			
			String line = null;
			line = fm.readLine("Erreur lors de la lecture du fichier - Helper - isInputFileConform - premiere ligne du fichier text");
		//tant qu'il y a des lignes dans le fichier text, prendre la ligne refactorée
			while (line != null) {
				String RefactoredLine = getRefactoredLigne(line);
				for (FileContent tempContenu : arrayContenu) {					//pour toutes les entêtes dans arrayContenu, 
					if (RefactoredLine.equals(tempContenu.getLigne())) {				//si la ligne refactorée égal a un des entêtes, mettre la ligne présent à true
						tempContenu.setEstPresent(true);
					}

			} // fin for
			line = fm.readLine("Erreur lors de la lecture du fichier - Helper - isInputFileConform - pour chaque ligne du fichier");
		} // fin while
		fm.closeAll("Erreur lors de la fermeture du reader ou du writer");

		for (FileContent tempContenu : arrayContenu) { // mettre à jour isConform en fonction de chaque valeur des file managers
			if (!tempContenu.getEstPresent()) {
				isConform = false;
			}
		}
		return isConform;
	}
	
	public static boolean clientsVide(String path) {
		ArrayList<FileContent> arrayContenu = implementerContenuFichier(path);
		for (FileContent tempContenu : arrayContenu) { 
			if (tempContenu.getLigne().equals("clients:")) {
				return tempContenu.getArrayLignes().size() == 0;
			}
		}
		return true;
	}
	
	public static boolean PlatsVide(String path) {
		ArrayList<FileContent> arrayContenu = implementerContenuFichier(path);
		for (FileContent tempContenu : arrayContenu) { 
			if (tempContenu.getLigne().equals("plats:")) {
				return tempContenu.getArrayLignes().size() == 0;
			}
		}
		return true;
	}
	
	public static boolean CommandesVide(String path) {
		ArrayList<FileContent> arrayContenu = implementerContenuFichier(path);
		for (FileContent tempContenu : arrayContenu) { 
			if (tempContenu.getLigne().equals("commandes:")) {
				return tempContenu.getArrayLignes().size() == 0;
			}
		}
		return true;
	}
	
	private static ArrayList<FileContent> implementerContenuFichier(String path) {
		//si le fichier est conforme: exécuter la méthode
		ArrayList<FileContent> arrayContenu = new ArrayList<FileContent>();
		if(isInputFileConform(path)) {
			arrayContenu.add(new FileContent("clients:"));
			arrayContenu.add(new FileContent("commandes:"));
			arrayContenu.add(new FileContent("plats:"));
			arrayContenu.add(new FileContent("fin"));
			
			// initialisation du file manager
			FileManager fm = new FileManager();
			if (!fm.setReader(path, "Erreur lors de l'initialisation du lecteur du fichier input")) {
				System.out.println("Arret du programme!");
				System.exit(0);
			}
						
			String line = null;
			line = fm.readLine("Erreur lors de la lecture du fichier");
			
			FileContent currentContent = null;
			
			//tant qu'il y a des lignes dans le fichier text, prendre la ligne refactorée			
			while (line != null) {
				String RefactoredLine = getRefactoredLigne(line);
				for (FileContent tempContenu : arrayContenu) {					//pour toutes les entêtes dans arrayContenu, 
					if (RefactoredLine.equals(tempContenu.getLigne())) { 		//si la ligne refactorée égal a un des entêtes, définir l'ancien fileContent a celui-ci
						tempContenu.setEstPresent(true);
						currentContent = tempContenu;	
						break;
					}else{														//sinon: ajouter la ligne dans currentContent
						if(tempContenu.equals(arrayContenu.get(arrayContenu.size()-1))) {
							currentContent.getArrayLignes().add(line);							
						}
					}
				} // fin for
				line = fm.readLine("Erreur lors de la lecture du fichier");
			} // fin while			
			fm.closeAll("Erreur lors de la fermeture du reader ou du writer");
		}//fin si
		return arrayContenu;
	}//fin de implementerContenuFichier

	private static String getRefactoredLigne(String rawLine) {
		rawLine = rawLine.replaceAll(" ", "");
		return rawLine.toLowerCase();
	}

	private static class FileContent {
		public String ligne = "";
		public boolean estPresent = false;
		public ArrayList<String> arrayLigne = new ArrayList<String>();

		public FileContent(String ligne) {
			setLigne(ligne);
		}

		public String getLigne() {
			return ligne;
		}

		public void setLigne(String ligne) {
			this.ligne = ligne;
		}

		public boolean getEstPresent() {
			return estPresent;
		}

		public void setEstPresent(boolean estPresent) {
			this.estPresent = estPresent;
		}
		
		public ArrayList<String> getArrayLignes(){
			return arrayLigne;
		}
	}
}
