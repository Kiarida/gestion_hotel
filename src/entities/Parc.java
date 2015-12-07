package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
		    System.out.println(rs.getString("id")+" "+rs.getString("nom")+" "+rs.getString("prenom")+" - "+rs.getString("adresse")+" "+rs.getString("ville"));
		}
		connexion.getConnect().close();
	}
	
	public void afficherOccupationChambre(Connect connexion) throws SQLException, ParseException{
		Scanner sc = new Scanner(System.in);
		String response = null;
		System.out.println("Choisissez l'hôtel à consulter : ");
		this.listeHotel(connexion);
		while(response == null){
			response = sc.nextLine();
		}
		
		Hotel h = new Hotel();
		h.setId(Integer.parseInt(response));
		
		response = null;
		System.out.println("Entrez la date (dd/mm/aaaa) ou la période voulue (dd/mm/aaaa-dd/mm/aaaa): ");
		while(response == null){
			response = sc.nextLine();
		}
		String[] chaine = response.split("-");	
		
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date_debut_temp= sdf.parse(chaine[0]);
		Date date_debut = new Date(date_debut_temp.getTime());
		Date date_fin = date_debut;
		if(chaine.length>1){
			java.util.Date date_fin_temp= sdf.parse(chaine[1]);
			date_fin = new Date(date_fin_temp.getTime());
		}
		h.occupationChambre(connexion, date_debut, date_fin);
		
	}
	
	public void afficherArrivees(Connect connexion) throws SQLException, ParseException{
		Scanner sc = new Scanner(System.in);
		String response = null;
		System.out.println("Choisissez l'hôtel à consulter : ");
		this.listeHotel(connexion);
		while(response == null){
			response = sc.nextLine();
		}
		Hotel h = new Hotel();
		h.setId(Integer.parseInt(response));
		response = null;
		System.out.println("Entrez la date voulue (dd/mm/aaaa) : ");
		while(response == null){
			response = sc.nextLine();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date_debut_temp= sdf.parse(response);
		Date date_debut = new Date(date_debut_temp.getTime());
		
		h.arriveesJour(connexion, date_debut);
	
	}
	
	public void afficherListePresta(Connect connexion) throws SQLException, ParseException{
		//this.listeHotel(connexion);
		Scanner sc = new Scanner(System.in);
		String response = null;
		System.out.println("Entrez votre numéro client OU votre nom, prénom et date de naissance (format nom;prenom;jj/mm/aaaa");
		while(response == null){
			response = sc.nextLine();
		}
		Client client = new Client();
		Reservation reservation = new Reservation();
		String[] chaine = response.split(";");
		if(chaine.length > 1){	
			client.setNom(chaine[0]);
			client.setPrenom(chaine[1]);
			client.setNaissance(chaine[2]);
			client.findClientByParams(connexion);
		}
		else{
			client.setId(Integer.parseInt(response));
		}
		HashMap<Integer, ArrayList<Object>> hash = client.findReservationsSejoursByClient(connexion);
		int response_res = -1;
		System.out.println("Choisissez une réservation ou un séjour pour y ajouter des services : ");
		while(response_res == -1){
			response_res =sc.nextInt();
		}
		
		ArrayList<Object> array = hash.get(response_res);
		
		reservation.setId(response_res);
		reservation.setDate_deb((Date)array.get(0));
		reservation.setDate_fin((Date)array.get(1));
		Hotel h = new Hotel();
		h.setId((Integer)array.get(2));
		h.setClasse((Integer)array.get(3));
		Facture f = new Facture();
		f.setId((Integer)array.get(5));
		f.setId_client(client.getId());
		Chambre c = new Chambre();
		c.setId_categorie((Integer)array.get(4));
		Prestation p = new Prestation();
		int boucle = 0;
		while(boucle != 10 ){
			HashMap<Integer, Prestation> hashmap = h.getListePrestations(connexion, c);
			response_res = -1;
			System.out.println("10 : Retour au menu.");
			System.out.println("Choisissez une prestation : ");
			while(response_res == -1){
				response_res = sc.nextInt();
			}
			if(response_res == 10){
				boucle = 10;
				break;
			}
		
			
			p = hashmap.get(response_res);
			
			System.out.println("Choisissez les dates de début et de fin du service (format jj/mm/aaaa-jj/mm/aaaa) : ");
			sc.nextLine();
			String response_date = null;
			while(response_date == null){
				response_date=sc.nextLine();
			}
			
			String[] date = response_date.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date_debut_temp= sdf.parse(date[0]);
			java.util.Date date_fin_temp= sdf.parse(date[1]);
			Date date_debut = new Date(date_debut_temp.getTime());
			Date date_fin = new Date(date_fin_temp.getTime());
			
			if(date_debut.after(date_fin) || date_debut.after(reservation.getDate_fin()) || date_debut.before(reservation.getDate_deb()) || date_fin.after(reservation.getDate_fin())){
				System.out.println("Erreur : les dates ne correspondent pas. Retour au menu.");
				break;
			}
			int diff = (int)((date_fin.getTime() - date_debut.getTime()) / (24 * 60 * 60 * 1000));
			client.achatPrestation(connexion, f, p, diff);
			
			//boucle = sc.nextInt();
		}
		
		
		//Hotel h = new Hotel();
		//h.setId(reponse);
		
	}
	
	public int verifReservation(Connect connexion, Date date_debut, Date date_fin, int id_client) throws SQLException{
		ResultSet rs = null;
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql = "SELECT * FROM reservation WHERE (date_deb BETWEEN '"+date_debut+"' AND '"+date_fin+"' AND date_fin BETWEEN '"+date_debut+"' AND '"+date_fin+"') AND client_id = "+id_client+"";
		
		rs = state.executeQuery(sql);
		if (rs.next() ) {
			System.out.println("Vous avez déjà une réservation à cette période.");
		   return -1;
		}
		connexion.getConnect().close();
		return 1;
		
	}
	
	public void afficherMenuChiffre(Connect connexion) throws SQLException{
		Scanner sc = new Scanner(System.in);
		int response = -1;
		System.out.println("Choisissez un hôtel parmi la liste : ");
		Hotel h = new Hotel();
		this.listeHotel(connexion);
		while(response == -1){
			response = sc.nextInt();
		}
		h.setId(response);
		System.out.println("Choisissez le mois et l'année à consulter (format mm/aaaa) : ");
		
		String response_date = null;
		sc.nextLine();
		while(response_date == null){
			response_date = sc.nextLine();
		}
		String[] chaine = response_date.split("/");
		h.afficherChiffre(connexion, Integer.parseInt(chaine[0]), Integer.parseInt(chaine[1]));
		
	}
	
	public void afficherMenuChiffreParc(Connect connexion) throws SQLException{
		Scanner sc = new Scanner(System.in);
		int response = -1;
		
		
		System.out.println("Choisissez l'année à consulter (format aaaa) : ");
		
		while(response  == -1){
			response = sc.nextInt();
			
		}
		
		this.afficherChiffreHotel(connexion, response);
		
	}
	
	
	public void afficherChiffreHotel(Connect connexion, int year) throws SQLException{
		connexion.connection();
		String sql = "SELECT SUM(total) FROM facture INNER JOIN reservation ON reservation.facture_id = facture.id WHERE YEAR(reservation.date_fin) = "+year+"";
		Statement state = connexion.getConnect().createStatement();
		ResultSet rs = state.executeQuery(sql);
		while(rs.next()){
			System.out.println("Chiffre d'affaire de l'année "+year+" : "+rs.getInt(1)+"€");
		}
	}
 	
}
