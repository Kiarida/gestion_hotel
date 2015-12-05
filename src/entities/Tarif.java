package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Connect;

public class Tarif {
	private int id;
	private int nb_jours;
	private int total_prest;
	public int id_prestation;
	public int id_facture;
	Connect connexion = new Connect();
	private int facture_id;
	private int facture;
	
	public Tarif(int id, int nb_jours, int total_prest, int id_prest, int id_facture){
		this.id = id;
		this.nb_jours = nb_jours;
		this.total_prest = total_prest;
		this.id_prestation = id_prestation;
		this.id_facture = id_facture;
	}
	
	public Tarif(){
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNb_jours() {
		return nb_jours;
	}
	public void setNb_jours(int nb_jours) {
		this.nb_jours = nb_jours;
	}
	public int getTotal_prest() {
		return total_prest;
	}
	public void setTotal_prest(int total_prest) {
		this.total_prest = total_prest;
	}
	public int getId_prestation() {
		return id_prestation;
	}
	public void setId_prestation(int id_prestation) {
		this.id_prestation = id_prestation;
	}
	public int getId_facture() {
		return id_facture;
	}
	public void setId_facture(int id_facture) {
		this.id_facture = id_facture;
	}

	public void createTarif(Connect connexion) throws SQLException{
		connexion.connection();
		Statement state =  connexion.getConnect().createStatement();
		String sql = "INSERT INTO tarif (nb_jours, total_prest, prestation_id, facture_id) "
				+ "SELECT ("+this.nb_jours+", "+this.total_prest+", "+this.id_prestation+", "+this.id_facture+") FROM tarif "
				+ "WHERE NOT EXISTS (SELECT * FROM tarif WHERE prestation_id ="+this.id_prestation+" AND facture_id = "+this.id_facture+" LIMIT 1";
		try{
			state.executeUpdate(sql);
			System.out.println("Vous disposez maintenant de ce service.\n");
		}catch(SQLException e){

			System.out.println("Une erreur s'est produite. Veuillez réessayer.");
		}
		
		
		connexion.getConnect().close();
		
		//return this;
	}
	

}