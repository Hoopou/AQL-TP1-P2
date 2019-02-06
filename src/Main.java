import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.classfile.Field;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Conteneur> arrayConteneur = new ArrayList<Conteneur>();
		Conteneur conteneurActuel = null;
		BufferedReader ficLecture = null;
		try {
			ficLecture = new BufferedReader(new FileReader("inputData.txt"));
			
			String line;
			while ((line = ficLecture.readLine()) != null) {
				if(line.endsWith(":")) {
					if(conteneurActuel != null) {
						arrayConteneur.add(conteneurActuel);
					}
					conteneurActuel = new Conteneur(line.replace(":", ""));
					
				}else {
					conteneurActuel.getListContenu().add(line);
				}
			}
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		    
	}

}
