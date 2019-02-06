import java.util.ArrayList;

public class Conteneur {
	private String name = null;
	private ArrayList<String> arraycontenu = new ArrayList<String>();
	
	public Conteneur(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getListContenu(){
		return this.arraycontenu;
	}
	
	
}
