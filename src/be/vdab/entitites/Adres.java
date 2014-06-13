package be.vdab.entitites;

public class Adres {
	
	private String huisNr;
	private String straat;
	private Gemeente gemeente;
	
	public String getHuisNr() {
		return huisNr;
	}
	
	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}
	
	public String getStraat() {
		return straat;
	}
	
	public void setStraat(String straat) {
		this.straat = straat;
	}
	
	public Gemeente getGemeente() {
		return gemeente;
	}
	
	public void setGemeente(Gemeente gemeente) {
		this.gemeente = gemeente;
	}	

}
