package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Connect;

public class Chambre {
	private int id;
	private int num_chambre;
	public int id_hotel;
	public int id_categorie;
	
	public Chambre() {
		
	}
	
	public Chambre (int num_chambre, int id_hotel, int id_categorie){
		this.num_chambre = num_chambre;
		this.id_hotel = id_hotel;
		this.id_categorie = id_categorie;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum_chambre() {
		return num_chambre;
	}
	public void setNum_chambre(int num_chambre) {
		this.num_chambre = num_chambre;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public int getIdChambre(Connect connexion, int num_chambre, int id_hotel) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String chambre_sql = "SELECT id FROM chambre WHERE num_chambre = "+num_chambre+" AND hotel_id = "+id_hotel+"";
		ResultSet rs =state.executeQuery(chambre_sql);
		while(rs.next()){
			int id = rs.getInt("id");
			return id;
		}
		return id;
		
	}
}