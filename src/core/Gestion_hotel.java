package core;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
		afficherMenu(connexion, parc);
		
		//while(reponse != 10){
		
		
		
	}
	
	public static void afficherMenu(Connect connexion, Parc parc) throws SQLException, ParseException{
		Scanner input = new Scanner(System.in);
		int reponse = 0;

		
		boolean shouldbreak = false;
		//while(reponse != 1 && reponse != 2 && reponse != 3 && reponse != 4 && reponse != 5 && reponse != 6 && reponse != 7 && reponse != 10){
		while(shouldbreak == false){
			System.out.println("\n\n Bienvenue dans le système. \n");
			System.out.println("1. Consulter la liste des hôtels.");
			System.out.println("2. Faire une réservation.");
			System.out.println("3. Consulter les clients.");
			System.out.println("4. Création fichier client.");
			System.out.println("5. Création d'un hôtel.");
			System.out.println("6. Edition d'un hôtel");
			System.out.println("7. Modification ou annulation d'une réservation");
			System.out.println("8. Ajout d'une chambre dans un hôtel");
			System.out.println("9. Modification d'une chambre dans un hôtel");
			System.out.println("10. Suppression d'une réservation.");
			
			System.out.println("12. Quitter");
			reponse = input.nextInt();
			shouldbreak=true;
		}
	
		switch(reponse){
		case 1:
			System.out.println("Liste des hôtels : ");
			//Client c2 = new Client("bla", "blab", "bla","bla");
			//c2.testFunction(connexion);
			afficherMenu(connexion, parc);
			//break;
		case 2:
			System.out.println(reponse);
			System.out.println("Réservation");
			demandeReservation(connexion, parc);
			afficherMenu(connexion, parc);
			//break;
		case 3:
			System.out.println("Consultation des clients");
			parc.consultClients(connexion);
			afficherMenu(connexion, parc);
			//break;
		case 4:
			System.out.println("REPONSE "+reponse);
			System.out.println("Création client");
			demandeclient(connexion);
			afficherMenu(connexion, parc);
			//break;
		case 5:
			System.out.println("Création d'un hôtel");
			demandecreatehotel(connexion, parc);
			afficherMenu(connexion, parc);
			//break;
		case 6: 
			System.out.println("Edition d'un hôtel");
			demandeedithotel(connexion, parc);
			afficherMenu(connexion, parc);
			//break;
		case 7:
			System.out.println("Edition d'une réservation");
			demandemodifreservation(connexion, parc);
			afficherMenu(connexion, parc);
			//break;
		case 8:
			System.out.println("Ajout d'une chambre dans un hôtel");
			Hotel hotel = new Hotel();
			hotel.ajoutChambre(connexion, parc);
			afficherMenu(connexion, parc);
		case 9:
			System.out.println("Edition d'une chambre dans un hôtel");
			Hotel hotel2 = new Hotel();
			hotel2.editChambre(connexion, parc);
			afficherMenu(connexion, parc);
		case 10:
			System.out.println("Supression d'une réservation");
			Client c = new Client();
			c.afficheDeleteClient(connexion);
			afficherMenu(connexion, parc);
		case 11:
			System.out.println("Vérifier l'occupation des chambres pour un jour ou une période.");
			parc.afficherOccupationChambre(connexion);
			afficherMenu(connexion, parc);
		case 12:
			System.out.println("Merci d'avoir utilisé l'interface de gestion hotelière.");
			shouldbreak =true;
			System.exit(0);
			break;
		default:
			break;
		}
		shouldbreak=true;
		
		reponse = -1;
		
	}
	
	public static void demandemodifreservation(Connect connexion, Parc parc) throws SQLException, ParseException{
		Scanner sc = new Scanner(System.in);
		String response = "";
		Client c = new Client();
		Reservation reservation = new Reservation();
		System.out.println("Possédez-vous votre numéro de réservation ? Oui (O)/Non (N)");
		while(!response.equals("O") && !response.equals("N")){
			response = sc.nextLine();
		}
		if(response.equals("O")){
			int response_res = -1;
			System.out.println("Entrez votre numéro de réservation");
			
			while(response_res == -1){
				response_res = sc.nextInt();
			}
			reservation.setId(response_res);
			reservation.getReservationById(connexion);
			System.out.println(reservation.getDate_deb());
			
		}
		else if(response.equals("N")){
			
			response = null;
			System.out.println("Entrez votre nom, votre prénom et votre date de naissance (format : nom;prenom;jj/mm/aaaa");
			while(response == null){
				response = sc.nextLine();
			}
			
			String[] chaine = response.split(";");
			c.setNom(chaine[0]);
			c.setPrenom(chaine[1]);
			c.setNaissance(chaine[2]);
			c.findClientByParams(connexion);
			HashMap<Integer, ArrayList<Date>> hash = c.findReservationByClient(connexion);
			int response_res = -1;
			System.out.println("Taper le numéro de la réservation que vous souhaitez éditer : ");
			while(response_res == -1){
				response_res =sc.nextInt();
			}
			ArrayList<Date> array = hash.get(response_res);
			reservation.setId(response_res);
			reservation.setDate_deb(array.get(0));
			reservation.setDate_fin(array.get(1));
			
		}
		
		menuEditReservation(connexion, parc, reservation);
	}
	
	
	
	public static void menuEditReservation(Connect connexion, Parc parc, Reservation reservation) throws SQLException, ParseException{
		Scanner sc = new Scanner(System.in);
		int response = -1;
		System.out.println("Choisissez la propriété à éditer : ");
		System.out.println("1. Dates de la réservation");
		System.out.println("2. Lieu de la réservation");
		System.out.println("3. Nombre de personnes");
		System.out.println("4. Revenir au menu précédent");
		while(response != 1 && response != 2 && response != 3 && response != 4){
			response = sc.nextInt();
			
			
		}
		
		switch(response){
		case 1:
			System.out.println("Dates de réservation. Entrez la date de début du séjour et la date de fin du séjour au format dd/mm/aaaa-dd/mm/aaaa :");
			String response_date=null;
			sc.nextLine();
			//System.out.println()
			while(response_date == null){
				response_date=sc.nextLine();
			}
			String[] date = response_date.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			java.util.Date date_debut_temp= sdf.parse(date[0]);
			java.util.Date date_fin_temp= sdf.parse(date[1]);
			Date date_debut = new Date(date_debut_temp.getTime());
			Date date_fin = new Date(date_fin_temp.getTime());
			if(date_debut.after(date_fin)){
				System.out.println("Erreur : la date de début doit être antérieure à la date de fin. Retour au menu.");
				afficherMenu(connexion, parc);
				
			}
			reservation.setDate_deb(date_debut);
			reservation.setDate_fin(date_fin);
			reservation.editReservation(connexion, "dates");
			menuEditReservation(connexion, parc, reservation);
		
		case 2:
			System.out.println("Lieu de réservation :");
			System.out.println("Choisissez un hôtel :");
			parc.listeHotel(connexion);
			int response_lieu=0;
			while(response_lieu == 0){
				response_lieu=sc.nextInt();
			}
			int id_hotel = response_lieu;
			Hotel h = new Hotel();
			//h.listeChambresDispo(connexion, id_hotel, date_deb, date_fin);
			System.out.println("Choisissez une chambre :");
			h.listeChambresDispo(connexion, id_hotel, reservation.getDate_deb(), reservation.getDate_fin());
			response_lieu=0;
			while(response_lieu == 0){
				response_lieu=sc.nextInt();
			}
			Chambre chambre = new Chambre();
			int id_chambre =chambre.getIdChambre(connexion, response_lieu, id_hotel);
			reservation.setId_chambre(id_chambre);
			reservation.editReservation(connexion, "lieu");
			menuEditReservation(connexion, parc, reservation);
		case 3:
			System.out.println("Nombre de personnes : entrez le nombre de personnes (1 ou 2) :");
			int response_personne=0;
			while(response_personne == 0){
				response_personne=sc.nextInt();
			}
			reservation.setNb_pers(response_personne);
			reservation.editReservation(connexion, "personnes");
			menuEditReservation(connexion, parc, reservation);
		case 4:
			break;
		}
		
		
		//sc.close();
	}
	
	//Interroge l'utilisateur pour la création d'un hôtel
	public static void demandecreatehotel(Connect connexion, Parc parc) throws SQLException{
		Scanner sc = new Scanner(System.in);
		String reponse = null;
		System.out.println("Veuillez entrer la classe de l'hôtel (2/3/4)");
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
		//sc.close();
		
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
		//sc.close();
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
		//sc2.close();
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
		if(date_debut.after(date_fin)){
			System.out.println("Erreur : la date de début doit être antérieure à la date de fin. Retour au menu.");
			afficherMenu(connexion, parc);
			
		}
		
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
		//sc_res.close();
	}

}
