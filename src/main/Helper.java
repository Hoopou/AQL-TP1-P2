package main;
//*********************************************************************
//
//Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
//Date : 4 mars 2019
//Fichier : Helper.java
//
//********************************************************************* 

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
			FileManager fm = new FileManager();
			try {
				fm.setReader(path);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				System.out.println("Erreur lors de l'initialisation du lecteur du fichier input");
				break;
			}
			String line = null;
			try {
				line = fm.readLine();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Erreur lors de la lecture du fichier");
			}

			while (line != null) {
				line = getRefactoredLigne(line);
				if (line.equals(tempContenu.getLigne())) {
//					System.out.println("est présent: " + line);
					tempContenu.setEstPresent(true);
					break;
				}
				try {
					line = fm.readLine();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Erreur lors de la lecture du fichier");
				}
			} // fin while

			try {
				fm.closeAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	}
}
