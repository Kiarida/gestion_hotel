package entities;





import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import database.Connect;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private String naissance;
	
	
	public Client(String nom, String prenom, String adresse, String ville, String naissance){
		this.nom=nom;
		this.prenom=prenom;
		this.ville = ville;
		this.adresse = adresse;
		this.naissance = naissance;
	}
	
	public Client(String nom, String prenom, String naissance){
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	
	public String getNaissance(){
		return naissance;
	}
	
	public void setNaissance(String naissance){
		this.naissance = naissance;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void createClient(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		String sql ="INSERT into client (nom, prenom, adresse, ville) VALUES ('"+this.nom+"', '"+this.prenom+"', '"+this.adresse+"', '"+this.ville+"', '"+this.naissance+"')";

		state.executeUpdate(sql, state.RETURN_GENERATED_KEYS);
		
		ResultSet rs = state.getGeneratedKeys();
		
		if(rs.next()){
			
			int id = rs.getInt(1);
			System.out.println("Client créé. Veuillez noter votre numéro client : "+id);
		}
		else{
			System.out.println("Erreur, le client n'a pas été créé.");
		}
		connexion.getConnect().close();
	}
	
	public void findAllReservationByClient(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String sql = "SELECT * FROM reservation WHERE client_id = "+this.getId();
		ResultSet rs = state.executeQuery(sql);
		while(rs.next()){
			
			System.out.println("Numéro de réservation : "+rs.getInt("id"));
			
		}
		connexion.getConnect().close();
		//return id;
	}
	
	public HashMap<Integer, ArrayList<Object>> findReservationByClient(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String[][] array = null;
		String sql = "SELECT * FROM reservation  LEFT JOIN chambre on reservation.chambre_id = chambre.id LEFT JOIN hotel ON chambre.hotel_id = hotel.id WHERE client_id = "+this.getId()+" AND date_deb > NOW()";
		ResultSet rs = state.executeQuery(sql);
	
		HashMap<Integer, ArrayList<Object>> hashmap = new HashMap<Integer, ArrayList<Object>>();
		while(rs.next()){
			//array[rs.getInt("id")].
			ArrayList<Object> array2 = new ArrayList<>();
			array2.add(0, rs.getDate("date_deb"));
			array2.add(1, rs.getDate("date_fin"));
			array2.add(2, rs.getString("hotel_id"));
			array2.add(3, rs.getString("classe_id"));
			array2.add(4, rs.getString("categorie_id"));
			array2.add(5, rs.getString("facture_id"));
			hashmap.put(rs.getInt("id"), array2);
			
			System.out.println("Réservation n°"+rs.getInt("id")+" - "+rs.getString("nom")+" - du "+rs.getString("date_deb")+" au "+rs.getString("date_fin"));
			
		}
		connexion.getConnect().close();
		return hashmap;
	}
	
	
	public HashMap<Integer, ArrayList<Object>> findReservationsSejoursByClient(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String[][] array = null;
		String sql = "SELECT * FROM reservation  LEFT JOIN chambre on reservation.chambre_id = chambre.id LEFT JOIN hotel ON chambre.hotel_id = hotel.id WHERE client_id = "+this.getId()+" AND date_fin >= NOW()";
		ResultSet rs = state.executeQuery(sql);
	
		HashMap<Integer, ArrayList<Object>> hashmap = new HashMap<Integer, ArrayList<Object>>();
		while(rs.next()){
			//array[rs.getInt("id")].
			ArrayList<Object> array2 = new ArrayList<>();
			array2.add(0, rs.getDate("date_deb"));
			array2.add(1, rs.getDate("date_fin"));
			array2.add(2, rs.getInt("hotel_id"));
			array2.add(3, rs.getInt("classe_id"));
			array2.add(4, rs.getInt("categorie_id"));
			array2.add(5, rs.getInt("facture_id"));
			
			hashmap.put(rs.getInt("id"), array2);
		
			System.out.println("Réservation n°"+rs.getInt("id")+" - "+rs.getString("nom")+" - du "+rs.getString("date_deb")+" au "+rs.getString("date_fin"));
			
		}
		
		connexion.getConnect().close();
		return hashmap;
	}
	
	public Client findClientByParams(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String sql = "SELECT * FROM client WHERE nom ='"+this.getNom()+"' AND prenom ='"+this.getPrenom()+"' AND naissance = '"+this.getNaissance()+"'";
		ResultSet rs = state.executeQuery(sql);
		
		while(rs.next()){
			this.setId(rs.getInt("id"));
		}
		connexion.getConnect().close();
		return this;
		
	}
	
	public void afficheDeleteClient(Connect connexion) throws SQLException{
		Scanner sc = new Scanner(System.in);
		String response = null;
		Reservation reservation = new Reservation();
		System.out.println("Possédez-vous le numéro de réservation ? Oui(O)/Non(N)");
		while(response == null){
			response = sc.nextLine();
		}
		if(response.equals("O")){
			int response_res = -1;
			System.out.println("Entrez votre numéro de réservation");
			
			while(response_res == -1){
				response_res = sc.nextInt();
			}
			reservation.setId(response_res);
			
		}
		else if(response.equals("N")){
			
			response = null;
			System.out.println("Entrez votre numéro de client OU votre nom, votre prénom et votre date de naissance (format : nom;prenom;jj/mm/aaaa)");
			while(response == null){
				response = sc.nextLine();
			}
			String[] chaine = response.split(";");
			if(chaine.length > 1){
			
				this.setNom(chaine[0]);
				this.setPrenom(chaine[1]);
				this.setNaissance(chaine[2]);
				this.findClientByParams(connexion);
				HashMap<Integer, ArrayList<Object>> hash = this.findReservationByClient(connexion);
				int response_res = -1;
				System.out.println("Taper le numéro de la réservation que vous souhaitez supprimer : ");
				while(response_res == -1){
					response_res =sc.nextInt();
				}
				ArrayList<Object> array = hash.get(response_res);
				reservation.setId(response_res);
				reservation.deleteReservation(connexion);
				
			}
			else{
				this.id = Integer.parseInt(response);
				HashMap<Integer, ArrayList<Object>> hash = this.findReservationByClient(connexion);
				int response_res = -1;
				System.out.println("Taper le numéro de la réservation que vous souhaitez supprimer : ");
				while(response_res == -1){
					response_res =sc.nextInt();
				}
				ArrayList<Object> array = hash.get(response_res);
				reservation.setId(response_res);
				reservation.deleteReservation(connexion);
			}
		}
	
			
	}
	public void achatPrestation(Connect connexion, Facture f, Prestation p, int nb_jours) throws SQLException{
		Tarif tarif = new Tarif();
		tarif.setId_facture(f.getId());
		tarif.setId_prestation(p.getId());
		tarif.setNb_jours(nb_jours);
		int total_prestation = p.getTarif_jour() * nb_jours;
		tarif.setTotal_prest(total_prestation);
		int update = tarif.createTarif(connexion);
		if(update != 0){
			
			f.updateFacture(connexion, tarif.getTotal_prest());
		}
		
	}

	
	
	

}