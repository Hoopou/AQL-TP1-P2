package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Helper {

	private static ArrayList<FileContent> arrayContenu = new ArrayList<FileContent>();

	public static String getNomFacture() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd-HH:mm:ss");
		return "Facture-du-" + sdf.format(cal.getTime()) + ".txt";
	}

	public static boolean isInputFileConform(String path) {
		arrayContenu.add(new FileContent("clients:"));
		arrayContenu.add(new FileContent("commandes:"));
		arrayContenu.add(new FileContent("plats:"));
		arrayContenu.add(new FileContent("fin"));

		boolean isConform = true;
		for(FileContent tempContenu : arrayContenu){
			FileManager fm = new FileManager();
			fm.setReader(path);
			String line = fm.readLine();
		
			while (line != null) {
				line = getRefactoredLigne(line);
				if(line.equals(tempContenu.getLigne())) {
					System.out.println("est présent: " + line);
					tempContenu.setEstPresent(true);
					break;
				}
				line = fm.readLine();
			}//fin while
			
			fm.closeAll();			
		}
		
		for(FileContent tempContenu : arrayContenu){
			if(!tempContenu.getEstPresent()) {
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
