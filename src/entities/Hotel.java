package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import database.Connect;

public class Hotel {
	private int id;
	private String nom;
	private String adresse;
	public int id_classe;
	
	public Hotel(){
		
	}
	
	public Hotel (int id_classe, String nom, String adresse){
		this.id_classe = id_classe;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public Hotel(int id, int id_classe, String nom, String adresse){
		this.id = id;
		this.id_classe = id_classe;
		this.nom = nom;
		this.adresse = adresse;
	}
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
	
	//Ajout d'une chambre pour l'hôtel
	public void ajoutChambre(Connect connexion, Parc parc) throws SQLException{
		parc.listeHotel(connexion);
		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		System.out.println("Entrez le numéro de l'hôtel");
		while(reponse == -1){
			reponse = sc.nextInt();
		}
		this.id = reponse;
		int num_chambre = compterChambre(connexion) + 1;
		listeCategorie(connexion);
		reponse = -1;
		
		while(reponse == -1){
			reponse = sc.nextInt();
		}
		Statement state = connexion.getConnect().createStatement();
		Chambre chambre = new Chambre(num_chambre,this.id,reponse);
		chambre.createChambre(connexion);
		connexion.getConnect().close();
		
	}
	public void editChambre(Connect connexion, Parc parc) throws SQLException{
		parc.listeHotel(connexion);
		Scanner sc = new Scanner(System.in);
		int reponse = -1;
		System.out.println("Entrez le numéro de l'hôtel");
		while(reponse == -1){
			reponse = sc.nextInt();
		}
		this.id = reponse;
		int num = -1;
		System.out.println("Liste des chambres dans l'hôtel");
		listeChambres(connexion);
		System.out.println("Quelle chambre voulez-vous modifier ?");
		while(num == -1){
			num = sc.nextInt();
		}
		System.out.println("Choisissez une catégorie parmi celles disponibles");
		int categorie = -1;
		listeCategorie(connexion);
		while(categorie == -1){
			categorie = sc.nextInt();
		}
		Chambre chambre = new Chambre(num,this.id, categorie);
		chambre.modifChambre(connexion);
	}
	
	public int compterChambre(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT COUNT(*) AS rawcount FROM chambre WHERE hotel_id = "+this.id+"";
		ResultSet rs = state.executeQuery(sql);
		int totalChambre = 0;
		while(rs.next()){
			totalChambre = rs.getInt("rawcount");
		}
		return totalChambre;
	}
	
	//Récupérer l'id de la classe en fonction du nombre d'étoiles
	public int getIdClasseEtoiles(Connect connexion, int nb_etoiles) throws SQLException{
		int classe = 0;
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT id FROM classe WHERE nb_etoiles = "+nb_etoiles+"";
		rs = state.executeQuery(sql);
		while(rs.next()){
			classe = rs.getInt("id");
		}
		return classe;
	}
	
	//Liste des catégories de chambres disponibles pour un hôtel
	public void listeCategorie(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT * FROM tarif_chambre INNER JOIN hotel ON tarif_chambre.classe_id = hotel.classe_id INNER JOIN categorie ON categorie.id = tarif_chambre.categorie_id WHERE hotel.id = "+this.id+"";
		ResultSet rs = state.executeQuery(sql);
		System.out.println("Sélectionner le numéro de la catégorie disponible");
		while(rs.next()){
			System.out.println(rs.getInt("categorie_id")+" - "+rs.getString("libelle"));
		}
	}
	
	//Liste des chambres
	public void listeChambres(Connect connexion) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT num_chambre, categorie.libelle FROM chambre "
				+ "JOIN categorie ON chambre.categorie_id = categorie.id "
				+ "WHERE chambre.hotel_id = "+this.id+"";
		rs = state.executeQuery(sql);
		while(rs.next()){
			System.out.println(rs.getString("num_chambre")+" - "+rs.getString("libelle"));
		}
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