package entities;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	
	

}