package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {
	public static String getNomFacture() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd-HH:mm:ss");
        return "Facture-du-" + sdf.format(cal.getTime()) + ".txt";
	}
	
}
