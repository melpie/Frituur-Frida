package be.vdab.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ArrayUtils;
import be.vdab.entitites.Saus;

public class SausenDAO {
	
	private static final Map<Long, Saus> sausen = new ConcurrentHashMap<>();
    
    static {
    	sausen.put(1L, new Saus(1L, "coctail", new String[] {"mayonnaise","ketchup","cognac"}));
    	sausen.put(2L, new Saus(2L, "mayonnaise", new String[] {"eidooier","mosterd","olie","azijn","peper","zout"}));
    	sausen.put(3L, new Saus(3L, "mosterd", new String[] {"mosterdzaden","azijn","peper","zout","water"}));
    	sausen.put(4L, new Saus(4L, "tartare", new String[] {"mayonaise","augurkjes","sjalotjes","kappertjes","peterselie","peper","zout"}));
    	sausen.put(5L, new Saus(5L, "vinaigrette", new String[] {"azijn","olie","peper","zou","mosterd"}));
    }
	
    public ArrayList<Saus> findAll() {
        return new ArrayList<Saus>(sausen.values());
    }
    
    public Iterable<Saus> findByIngredient(String ingredient) {
        List<Saus> resultaat = new ArrayList<>();
        for (Saus saus : sausen.values()) {
            if (ArrayUtils.contains((Object[])saus.getIngredienten(), ingredient)) {
                resultaat.add(saus);
            }
        }
        return resultaat;
    }
    
    public void removeSaus(long nummer) {
    	sausen.remove(nummer);
    }
    
    public int getNumber() {
    	return sausen.values().size();
    }
    
    public Saus read(long nummer) {
        return sausen.get(nummer);
    }
	
}
