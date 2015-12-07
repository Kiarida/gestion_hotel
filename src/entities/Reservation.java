package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

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
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	public void createReservation(Connect connexion) throws SQLException, ParseException{
		connexion.connection();
		Facture facture = new Facture(this.id_client,0, false);
		
		
		int total = facture.createFacture(connexion, this);
		
		System.out.println("Devis : "+total+"€");
		Scanner scanner = new Scanner(System.in);
		String reponse = "";
		System.out.println("Souhaitez-vous confirmer ? O/N");
		while(!reponse.equals("O") && !reponse.equals("N")){
			reponse = scanner.nextLine();
			System.out.println("Faux");
		}
		System.out.println("wat");
		if(reponse == "N"){
			System.out.println("Annulation de la procédure");
			
		}
		else{
			System.out.println("hello");
			this.id_facture = facture.insertFacture(connexion);;
			
			
			
			
			Statement state = connexion.getConnect().createStatement();
			java.sql.Date date_today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			this.reservation = true;
			//Si la date de début est celle d'aujourd'hui, alors on met le boolean reservation à faux
			String date_form = date_today.toString();
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date_today_temp= sdf2.parse(date_form);
			Date date_to = new Date(date_today_temp.getTime());
	
			if(this.date_deb.compareTo(date_to)==0){
				this.reservation = false;
			}
			
		
			
			
			
			String sql ="INSERT into reservation (chambre_id, facture_id, client_id, nb_pers, date_deb, date_fin, reservation) "
					+ "VALUES ("+this.id_chambre+", "+this.id_facture+", "+this.id_client+", "+this.nb_pers+", '"+this.date_deb+"', '"+this.date_fin+"', "+this.reservation+")";
			
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
	}
	
	
	
	/*public void editReservation(Connect connexion, String param) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "UPDATE reservation SET nb_pers="+this.nb_pers+", chambre_id = "+this.id_chambre+", date_debut = "+this.date_deb+", date_fin = "+this.date_fin+"";
		state.executeUpdate(sql);
		
		connexion.getConnect().close();
	}*/
	
	public void editReservation(Connect connexion, String param1) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = null;
		if(param1 == "dates"){
			sql = "UPDATE reservation SET date_deb ='"+this.getDate_deb()+"', date_fin='"+this.getDate_fin()+"' WHERE id ="+this.getId()+"";
			System.out.println(sql);
		}
		else if(param1 == "lieu"){
			sql = "UPDATE reservation SET chambre_id="+this.getId_chambre()+" WHERE id ="+this.getId()+"";
		}
		else if(param1 == "personnes"){
			sql = "UPDATE reservation SET nb_pers="+this.getNb_pers()+" WHERE id ="+this.getId()+"";
		}
		state.executeUpdate(sql);
		
		connexion.getConnect().close();
	}
		
	
	public void getReservationById(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = null;
		sql = "SELECT * FROM reservation where id ="+this.getId();
		ResultSet rs = state.executeQuery(sql);
		while(rs.next()){
			this.date_deb=rs.getDate("date_deb");
			this.date_fin=rs.getDate("date_fin");
		}
		connexion.getConnect().close();
	}
	
	public void deleteReservation(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = null;
		sql = "DELETE facture FROM facture LEFT JOIN reservation ON facture_id = facture.id where reservation.id ="+this.getId(); 
		state.executeUpdate(sql);
		connexion.getConnect().close();
	}
}
	
	
	