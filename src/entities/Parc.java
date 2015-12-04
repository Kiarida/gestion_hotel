package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 	
}
