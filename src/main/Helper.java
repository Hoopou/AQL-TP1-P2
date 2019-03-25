package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.internal.runners.InitializationError;

public class Helper {

	private static ArrayList<FileContent> arrayContenu = new ArrayList<FileContent>();

	public static String getNomFacture() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd-HH_mm_ss");
		return "Facture-du-" + sdf.format(cal.getTime()) + ".txt";
	}
	
	public static boolean isInputFileConform(String path) {
		arrayContenu.add(new FileContent("clients:"));
		arrayContenu.add(new FileContent("commandes:"));
		arrayContenu.add(new FileContent("plats:"));
		arrayContenu.add(new FileContent("fin"));

		boolean isConform = true;
		for (FileContent tempContenu : arrayContenu) {
			
//region initialisation_fileManager
			FileManager fm = new FileManager();
			if (fm.setReader(path, "Erreur lors de l'initialisation du lecteur du fichier input")) {
				System.out.println("Arret du programme!");
				System.exit(0);
			}
//endregion
			
			String line = null;
			line = fm.readLine("Erreur lors de la lecture du fichier");

			while (line != null) {
				line = getRefactoredLigne(line);
				if (line.equals(tempContenu.getLigne())) {
//					System.out.println("est présent: " + line);
					tempContenu.setEstPresent(true);
					break;
				}

				line = fm.readLine("Erreur lors de la lecture du fichier");
			} // fin while

			fm.closeAll("Erreur lors de la fermeture du reader ou du writer");
		}

		for (FileContent tempContenu : arrayContenu) {
			if (!tempContenu.getEstPresent()) {
				isConform = false;
			}
		}

		return isConform;
	}

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
