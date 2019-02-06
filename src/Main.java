import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.sun.org.apache.bcel.internal.classfile.Field;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader ficLecture;
		try {
			ficLecture = new BufferedReader(new FileReader("inputData.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}

}
