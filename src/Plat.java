
public class Plat {
	private String name = null;
	private double prix;
	public Plat(String name , double prix) {
		this.name = name;
		this.prix = prix;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public boolean Equals(Object arg0) {
		// TODO Auto-generated method stub
		return name.equals(arg0.toString());
	}
}	
