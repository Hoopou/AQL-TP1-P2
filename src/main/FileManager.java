package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.internal.runners.InitializationError;

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
			System.err.println(erreurString);
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
			System.err.println(erreurString);
		}
		return success;
	}
	
	public void writeLine(String line, String erreurString){
		try {
			writer.write(line + "\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(erreurString);
		}
	}
	
	public boolean write(String line, String erreurString){
		boolean success = false;
		try {
			writer.write(line);
			success = true;
		} catch (Exception e) {
			System.err.println(erreurString);
		}
		return success;
	}
	
	public String readLine(String erreurString) {
		String line = null;
		try {
			line = reader.readLine();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(erreurString);
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
			System.err.println(erreurString);
		}
		
	}
	
}
