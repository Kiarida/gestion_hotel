package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import database.Connect;

public class Reservation {
	private int id;
	private int nb_pers;
	private Date date_deb;
	private Date date_fin;
	public int id_client;
	public int id_facture;
	public int id_chambre;
	private boolean reservation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNb_pers() {
		return nb_pers;
	}
	public void setNb_pers(int nb_pers) {
		this.nb_pers = nb_pers;
	}
	public Date getDate_deb() {
		return date_deb;
	}
	public void setDate_deb(Date date_deb) {
		this.date_deb = date_deb;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_facture() {
		return id_facture;
	}
	public void setId_facture(int id_facture) {
		this.id_facture = id_facture;
	}
	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public boolean isReservation() {
		return reservation;
	}
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}
	
	public Reservation(int id_client, int nb_pers, Date date_debut, Date date_fin, boolean reservation,int id_chambre){
		//this.id_chambre = id_chambre;
		this.id_client = id_client;
		this.nb_pers = nb_pers;
		this.date_deb = date_debut;
		this.date_fin = date_fin;
		this.reservation = reservation;
		this.id_chambre=id_chambre;
	}
	
	public void createReservation(Connect connexion) throws SQLException{
		connexion.connection();
		Facture facture = new Facture(this.id_client,0, false);
		int id_facture = facture.createFacture(connexion);
		this.id_facture = id_facture;
		Statement state = connexion.getConnect().createStatement();
		
		String sql ="INSERT into reservation (chambre_id, facture_id, client_id, nb_pers, date_deb, date_fin) "
				+ "VALUES ("+this.id_chambre+", "+this.id_facture+", "+this.id_client+", "+this.nb_pers+", '"+this.date_deb+"', '"+this.date_fin+"')";
		
		//Retourne la clé primaire (l'id) du nouvel enregistrement
		state.executeUpdate(sql, state.RETURN_GENERATED_KEYS);
		
		ResultSet rs = state.getGeneratedKeys();
		
		if(rs.next()){
			
			int id = rs.getInt(1);
			System.out.println("Réservation créée. Veuillez noter votre numéro de réservation : "+id);
		}
		else{
			System.out.println("Erreur, la réservation n'a pas été créée.");
		}
		connexion.getConnect().close();

		
	}
	
	public int findReservationByClient(Connect connexion, Client client) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String sql = "SELECT * FROM reservation WHERE client_id = "+client.getId();
		ResultSet rs = state.executeQuery(sql);
		if(rs.next()){
			
			int id = rs.getInt("id");
			
		}
		connexion.getConnect().close();
		return id;
	}
	
	public void editReservation(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "UPDATE reservation SET nb_pers="+this.nb_pers+", chambre_id = "+this.id_chambre+", date_debut = "+this.date_deb+", date_fin = "+this.date_fin+"";
		state.executeUpdate(sql);
		
		connexion.getConnect().close();
	}
		
}
	
	
	