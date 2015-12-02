package core;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import database.Connect;
import entities.Chambre;
import entities.Client;
import entities.Hotel;
import entities.Parc;
import entities.Reservation;

public class Gestion_hotel {

	public static void main(String[] args) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		
		
		
		
		Connect connexion = new Connect();
		Parc parc = new Parc();
		
		Scanner input = new Scanner(System.in);
		int reponse = 0;
		//while(reponse != 10){
		while(reponse != 1 && reponse != 2 && reponse != 3 && reponse != 4 && reponse != 5 && reponse != 6){
			System.out.println(reponse);
			System.out.println("Bienvenue dans le système. \n\n");
			System.out.println("1. Consulter la liste des hôtels.");
			System.out.println("2. Faire une réservation.");
			System.out.println("3. Consulter les clients.");
			System.out.println("4. Création fichier client.");
			System.out.println("5. Création d'un hôtel.");
			System.out.println("6. Edition d'un hôtel");
			System.out.println("7. Modification ou annulation d'une réservation");
			System.out.println("10. Quitter");
			reponse = input.nextInt();
			
			
		}
		switch(reponse){
		case 1:
			System.out.println("Liste des hôtels : ");
			//Client c2 = new Client("bla", "blab", "bla","bla");
			//c2.testFunction(connexion);
			break;
		case 2:
			System.out.println("Réservation");
			demandeReservation(connexion, parc);
			break;
		case 3:
			System.out.println("Consultation des clients");
			parc.consultClients(connexion);
			break;
		case 4:
			System.out.println("Affichage des clients");
			demandeclient(connexion);
			break;
		case 5:
			System.out.println("Création d'un hôtel");
			demandecreatehotel(connexion, parc);
			break;
		case 6: 
			System.out.println("Edition d'un hôtel");
			demandeedithotel(connexion, parc);
			break;
		case 7:
			System.out.println("Edition d'une réservation");
			demandemodifreservation(connexion);
		}
		
		
		
	}
	
	public static void demandemodifreservation(Connect connexion){
		Scanner sc = new Scanner(System.in);
		String response = null;
		System.out.println("Possédez-vous votre numéro de réservation ? Oui (O)/Non (N)");
		while(response != "O" && response != "N"){
			response = sc.nextLine();
		}
		if(response == "O"){
			response = null;
			System.out.println("Entrez votre numéro de réservation");
			
		}
		else if(response == "N"){
			response = null;
			System.out.println("Entrez votre nom, votre prénom et votre date de naissance (format : nom;prenom;jj/mm/aaaa");
			
		}
	}
	
	
	//Interroge l'utilisateur pour la création d'un hôtel
	public static void demandecreatehotel(Connect connexion, Parc parc) throws SQLException{
		Scanner sc = new Scanner(System.in);
		String reponse = null;
		System.out.println("Veuillez entrer la classe de l'hôtel (1/2/3/4)");
		while(reponse == null){
			reponse = sc.nextLine();
		}
		int classe = Integer.parseInt(reponse);
		
		System.out.println("Veuillez entrer le nom de l'hôtel");
		reponse = null;
		while(reponse == null){
			reponse = sc.nextLine();
		}
		String nom = reponse;
		
		System.out.println("Veuillez entrer l'adresse de l'hôtel");
		reponse = null;
		while(reponse == null){
			reponse = sc.nextLine();
		}
		String adresse = reponse;
		
		Hotel hotel = new Hotel(classe,nom,adresse);
		parc.creationHotel(connexion,hotel);
		sc.close();
		
	}
	public static void demandeedithotel(Connect connexion, Parc parc) throws SQLException{
		Scanner sc = new Scanner(System.in);
		String reponse = null;
		parc.listeHotel(connexion);
		System.out.println("Choisissez le numéro de l'hôtel à éditer.");
		while(reponse == null){
			reponse = sc.nextLine();
		}
		String id_hotel = reponse;
		reponse = null;
		System.out.println("Entrez le nombre d'étoiles, le nom et l'adresse de l'hôtel séparés par un /.");
		while(reponse == null){
			reponse = sc.nextLine();
		}
		String[] reponses = reponse.split("/");
		sc.close();
		Hotel hotel = new Hotel(Integer.parseInt(id_hotel), Integer.parseInt(reponses[0]),reponses[1],reponses[2]);
		hotel.getIdClasseEtoiles(connexion, hotel.getClasse());
		parc.modifierHotel(connexion, hotel);
	}
	
	public static void demandeclient(Connect connexion) throws SQLException{
		Scanner sc2 = new Scanner(System.in);
		String reponse2 = null;
		System.out.println("Veuillez entrer votre nom :");
		while(reponse2 == null){
			
			reponse2 = sc2.nextLine();
		}
		String nom = reponse2;

		
		System.out.println("Veuillez entrer votre prénom : ");
		reponse2 = null;
		while(reponse2 == null){
			reponse2 = sc2.nextLine();
		}
		String prenom = reponse2;
		
		System.out.println("Veuillez entrer votre adresse (numéro et rue) : ");
		reponse2 = null;
		while(reponse2 == null){
			reponse2 = sc2.nextLine();
		}
		String adresse = reponse2;
		
		System.out.println("Veuillez entrer la ville où vous résidez : ");
		reponse2 = null;
		while(reponse2 == null){
			reponse2 = sc2.nextLine();
		}
		String ville = reponse2;
		
		System.out.println("Veuillez entrer votre date de naissance (format jj/mm/aaaa) : ");
		reponse2 = null;
		while(reponse2 == null){
			reponse2 = sc2.nextLine();
		}
		String naissance = reponse2;
		
		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(adresse);
		System.out.println(ville);
		
		Client c = new Client(nom, prenom, adresse, ville, naissance);
		c.createClient(connexion);
		sc2.close();
	}
	
	public static void demandeReservation(Connect connexion, Parc parc) throws SQLException, ParseException{
		Scanner sc_res = new Scanner(System.in);
		int response = -1;
		System.out.println("Veuillez entrer votre num. client : ");
		while(response == -1){
			response = sc_res.nextInt();
		}
		int id_client = response;
		sc_res.nextLine();
		String response_date = null;
		System.out.println("Entrez la date de début de votre séjour (format jj/mm/aaaa) : ");
		
	
		while(response_date == null){
			response_date = sc_res.nextLine();
		}
		String date = response_date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		java.util.Date date_debut_temp= sdf.parse(date);
		Date date_debut = new Date(date_debut_temp.getTime());
		
		System.out.println("Entrez la date de fin de votre séjour (format jj/mm/aaaa) : ");
		response_date = null;
		while(response_date == null){
			response_date = sc_res.nextLine();
		}
		
		date = response_date;
		java.util.Date date_fin_temp= sdf.parse(date);
		
		Date date_fin = new Date(date_fin_temp.getTime());
		
		
		response = -1;
		System.out.println("Veuillez entrer le nombre de personnes (1 ou 2) : ");
		while(response == -1){
			response = sc_res.nextInt();
		}
		int nb_pers = response;
		
		
		
		System.out.println("Choisissez un hôtel parmi la liste : ");
		parc.listeHotel(connexion);
		
		response = -1;
		while(response == -1){
			response = sc_res.nextInt();
			
		}
		int id_hotel =  response;
		System.out.println("Choisissez une chambre parmi celles disponibles : ");
		
		Hotel h =  new Hotel();
		h.listeChambresDispo(connexion, id_hotel, date_debut, date_fin);
		
		response = -1;
		while(response == -1){
			response = sc_res.nextInt();
			
		}
		
		int num_chambre = response;
		
		//int id_client = 0;
		Chambre c = new Chambre();
		int id_chambre = c.getIdChambre(connexion, num_chambre, id_hotel);
		
		Reservation reservation = new Reservation(id_client, nb_pers, date_debut, date_fin, true, id_chambre );
		reservation.createReservation(connexion);
		sc_res.close();
	}

}
