
package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import be.vdab.entitites.Saus;

public class SausenDAO extends AbstractDAO {
	private static final String FIND_ALL_SQL =
		"select sausen.nummer, sausen.naam as sausnaam," +
		" ingredienten.naam as ingredientnaam" +
		" from sausen left join sauseningredienten" +
		" on sausen.nummer=sauseningredienten.sausnummer" +
		" left join ingredienten " +
		" on sauseningredienten.ingredientnummer=ingredienten.nummer" +
		" order by sausen.naam";
	
	private static final String FIND_BY_INGREDIENT_SQL =
		"select sausen.nummer, sausen.naam" +
		" from sausen inner join sauseningredienten " +
		" on sausen.nummer=sauseningredienten.sausnummer" +
		" inner join ingredienten " +
		"on sauseningredienten.ingredientnummer=ingredienten.nummer" +
		" where ingredienten.naam = ?" + " order by sausen.naam";
	
	private static final String DELETE_SQL =
		"delete from sausen where nummer=?";
	
	public ArrayList<Saus> findAll() {
		try (Connection connection = getConnection();
			Statement statement=connection.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL)) {
			ArrayList<Saus> sausen = new ArrayList<Saus>();
			resultSet.next();
			while (!resultSet.isAfterLast()) {
				sausen.add(resultSetRijNaarSausMetIngredienten(resultSet));
			}
			return sausen;
		} catch (SQLException ex) {
			throw new DAOException("Kan sausen niet lezen uit database", ex);
		}
	}
	
	private Saus resultSetRijNaarSausMetIngredienten(ResultSet resultSet)
				throws SQLException {
		long nummer = resultSet.getLong("nummer");
		String[] ingredienten = new String[]{};
		String ingredient = resultSet.getString("ingredientnaam");
		if (!resultSet.wasNull()) {
			ArrayUtils.add(ingredienten, ingredient);
		}
		Saus saus = new Saus(nummer, resultSet.getString("sausnaam"), ingredienten);
		while (resultSet.next() && resultSet.getLong("nummer") == nummer) {
			ArrayUtils.add(saus.getIngredienten(), resultSet.getString("ingredientnaam"));
		}
		return saus;
	}

	public Iterable<Saus> findByIngredient(String ingredient) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_INGREDIENT_SQL)) {
			statement.setString(1, ingredient);
			List<Saus> sausen = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					sausen.add(resultSetRijNaarSausZonderIngredienten(resultSet));
				}
				return sausen;
			}
		} catch (SQLException ex) {
			throw new DAOException("Kan sausen niet lezen uit database", ex);
		}
	}

	private Saus resultSetRijNaarSausZonderIngredienten(ResultSet resultSet)
			throws SQLException {
		return new Saus(resultSet.getLong("nummer"), resultSet.getString("naam"), new String[]{});
	}
	
	public void removeSaus(long nummer) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
			statement.setLong(1, nummer);
			statement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException("Kan saus " + nummer
			+ " niet verwijderen uit database", ex);
		}
	}
}

//package be.vdab.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import org.apache.commons.lang3.ArrayUtils;
//import be.vdab.entitites.Saus;
//
//public class SausenDAO {
//	
//	private static final Map<Long, Saus> sausen = new ConcurrentHashMap<>();
//  
//  static {
//  	sausen.put(1L, new Saus(1L, "coctail", new String[] {"mayonnaise","ketchup","cognac"}));
//  	sausen.put(2L, new Saus(2L, "mayonnaise", new String[] {"eidooier","mosterd","olie","azijn","peper","zout"}));
//  	sausen.put(3L, new Saus(3L, "mosterd", new String[] {"mosterdzaden","azijn","peper","zout","water"}));
//  	sausen.put(4L, new Saus(4L, "tartare", new String[] {"mayonaise","augurkjes","sjalotjes","kappertjes","peterselie","peper","zout"}));
//  	sausen.put(5L, new Saus(5L, "vinaigrette", new String[] {"azijn","olie","peper","zou","mosterd"}));
//  }
//	
//  public ArrayList<Saus> findAll() {
//      return new ArrayList<Saus>(sausen.values());
//  }
//  
//  public Iterable<Saus> findByIngredient(String ingredient) {
//      List<Saus> resultaat = new ArrayList<>();
//      for (Saus saus : sausen.values()) {
//          if (ArrayUtils.contains((Object[])saus.getIngredienten(), ingredient)) {
//              resultaat.add(saus);
//          }
//      }
//      return resultaat;
//  }
//  
//  public void removeSaus(long nummer) {
//  	sausen.remove(nummer);
//  }
//  
//  public int getNumber() {
//  	return sausen.values().size();
//  }
//  
//  public Saus read(long nummer) {
//      return sausen.get(nummer);
//  }
//	
//}


























