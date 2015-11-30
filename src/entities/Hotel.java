package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Connect;

public class Hotel {
	private int id;
	private String nom;
	private String adresse;
	public int id_classe;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getClasse() {
		return id_classe;
	}
	public void setClasse(int id_classe) {
		this.id_classe = id_classe;
	}
	
	//Liste des chambres disponibles à une date donnée
	public void listeChambresDispo(Connect connexion, int id_hotel, Date date_deb, Date date_fin) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT num_chambre, categorie.libelle FROM chambre "
				+ "JOIN categorie ON chambre.categorie_id = categorie.id "
				+ "WHERE chambre.hotel_id = "+id_hotel+" AND chambre.id NOT IN"
				+ "(SELECT reservation.chambre_id FROM reservation "
				+ "WHERE chambre.hotel_id = "+id_hotel+" AND "
				+ "('"+date_deb+"' NOT BETWEEN reservation.date_deb AND reservation.date_fin) OR ('"+date_fin+"' NOT BETWEEN reservation.date_deb AND reservation.date_fin) "
				+ "OR( '"+date_deb+"' <= reservation.date_deb AND '"+date_fin+"' >= reservation.date_fin ))";
		rs = state.executeQuery(sql);
		while(rs.next())
		{
		   
		    System.out.println(rs.getString("num_chambre")+" - "+rs.getString("libelle"));
		   
		}
		connexion.getConnect().close();
	}
}