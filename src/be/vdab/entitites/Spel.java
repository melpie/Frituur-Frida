package be.vdab.entitites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



public class Spel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_BEURTEN = 10;
	private String saus;
	private Set<Character> geprobeerdeLetters;
	private int verkeerdeBeurten;
	private StringBuilder sausMetPuntjes;
	private boolean gewonnen;
	private boolean verloren;

	public Spel(String saus) {
		this.saus = saus;
		sausMetPuntjes = new StringBuilder(saus.length());
		for (int teller = 0; teller < saus.length(); teller++) {
		sausMetPuntjes.append('.');
		}
		geprobeerdeLetters = new HashSet<>();
	}
	
	public void doeGok(char letter) {
		geprobeerdeLetters.add(letter);
		int letterPosistieInSaus = saus.indexOf(letter);
		if (letterPosistieInSaus == -1) {
			verkeerdeBeurten++;
			if (verkeerdeBeurten == MAX_BEURTEN) {
				verloren = true;
			}
		} else {
			do {
				sausMetPuntjes.setCharAt(letterPosistieInSaus, letter);
				letterPosistieInSaus = saus.indexOf(letter, letterPosistieInSaus + 1);
			} while (letterPosistieInSaus != -1);
			if (sausMetPuntjes.indexOf(".") == -1) {
				gewonnen = true;
			}
		}
	}
	
	public String getSausMetPuntjes() {
		return sausMetPuntjes.toString();
	}
	
	public String getSaus() {
		return saus;
	}
	
	public Set<Character> getGeprobeerdeLetters() {
		return geprobeerdeLetters;
	}
	
	public int getVerkeerdeBeurten() {
		return verkeerdeBeurten;
	}
	
	public boolean isGewonnen() {
		return gewonnen;
	}
	
	public boolean isVerloren() {
		return verloren;
	}

}