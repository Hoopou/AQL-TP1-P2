package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public boolean setReader(String file) {
		boolean success = false;
		try {
			reader = new BufferedReader(new FileReader(file));
			success = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean setWriter(String file) {
		boolean success = false;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			success = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	public void writeLine(String line) {
		try {
			writer.write(line + "\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean write(String line) throws WritingErreur {
		boolean success = false;
		try {
			writer.write(line);
			success = true;
		} catch (Exception e) {
			throw new WritingErreur();
		}
		return success;
	}
	
	public String readLine() throws ReadingErreur {
		String line = null;
		try {
			line = reader.readLine();
		} catch (Exception e) {
			// TODO: handle exception
			throw new ReadingErreur();
		}
		return line;
	}
	
	public void closeAll() throws Exception {
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
			throw new ClosingErreur();
		}
		
	}
	
	private class ClosingErreur extends Exception{}
	private class ReadingErreur extends Exception{}
	private class WritingErreur extends Exception{}
	private class InitialisationErreur extends Exception{}
	
}
