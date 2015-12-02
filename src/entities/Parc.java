package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Connect;

public class Parc {
	
	//Création d'un hôtel dans la BDD
	public void creationHotel(Connect connexion, Hotel hotel) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "INSERT INTO hotel (classe_id, nom, adresse) VALUES ("+hotel.getClasse()+",'"+hotel.getNom()+"','"+hotel.getAdresse()+"')";
		state.executeUpdate(sql);
		connexion.getConnect().close();
	}
	
	public void modifierHotel(Connect connexion, Hotel hotel) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "UPDATE hotel SET classe_id = "+hotel.getIdClasseEtoiles(connexion, hotel.getClasse())+",nom = '"+hotel.getNom()+"',adresse = '"+hotel.getAdresse()+"' WHERE id = "+hotel.getId()+"";
		state.executeUpdate(sql);
		connexion.getConnect().close();
	}

	public void listeHotel(Connect connexion) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT * FROM hotel JOIN classe ON classe_id = classe.id GROUP BY hotel.id";
		rs = state.executeQuery(sql);
		while(rs.next())
		{
		   
		    System.out.println(rs.getInt("id")+". "+rs.getInt("nb_etoiles")+" étoiles - "+rs.getString("nom")+" - "+rs.getString("adresse")+"");
		   
		}
		connexion.getConnect().close();
	}
	
	public void consultClients(Connect connexion) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT * FROM client";
		rs = state.executeQuery(sql);
		while(rs.next())
		{
		    System.out.println(rs.getString("id")+" ");
		    System.out.println(rs.getString("nom")+" ");
		    System.out.println(rs.getString("prenom")+" - ");
		    System.out.println(rs.getString("adresse")+" ");
		    System.out.println(rs.getString("ville"));
		}
		connexion.getConnect().close();
	}
	
}
