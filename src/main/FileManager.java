package main;
//*********************************************************************
//
//Programmeur : Vincent Boutot et Jean-Sébastien Beaulne
//Date : 4 mars 2019
//Fichier : FileManager.java
//
//********************************************************************* 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public boolean setReader(String file, String erreurString) {
		boolean success = false;
		try {
			reader = new BufferedReader(new FileReader(file));
			success = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logErreur(erreurString);
		}
		return success;
	}
	
	public boolean setWriter(String file, String erreurString){
		boolean success = false;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			success = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logErreur(erreurString);
		}
		return success;
	}
	
	public void writeLine(String line, String erreurString){
		try {
			writer.write(line + "\n");
		} catch (Exception e) {
			// TODO: handle exception
			logErreur(erreurString);
		}
	}
	
	public boolean write(String line, String erreurString){
		boolean success = false;
		try {
			writer.write(line);
			success = true;
		} catch (Exception e) {
			logErreur(erreurString);
		}
		return success;
	}
	
	public String readLine(String erreurString) {
		String line = null;
		try {
			line = reader.readLine();
		} catch (Exception e) {
			// TODO: handle exception
			if(e.getMessage().equals("Stream closed")) {
				return null;
			}else {
				logErreur(erreurString);
			}
		}
		return line;
	}
	
	public void closeAll(String erreurString) {
		boolean erreur = false;
		if(reader != null) {
			try {
				reader.close();
			} catch (Exception e) {
				erreur = true;
			}
		}
		if(writer != null) {
			try {
				writer.close();
			} catch (Exception e) {
				erreur = true;
			}			
		}
		
		if(erreur) {
			logErreur(erreurString);
		}
		
	}
	
	private void logErreur(String erreur) {
		System.err.println(erreur);
		if(writer != null) {
			writeLine(erreur, "");
		}
	}
	
}
