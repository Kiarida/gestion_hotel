package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Connect;

public class Facture {
	private int id;
	private int total;
	private boolean paye;
	public int id_client;

	public int id_res;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_res() {
		return id_res;
	}

	public void setId_res(int id_res) {
		this.id_res = id_res;
	}

	public Facture(int client_id, int total, boolean paye){
		this.id_client = client_id;
		this.total = total;
		this.paye = paye;
	}
	
	public int createFacture(Connect connexion) throws SQLException{
	

		Statement state = connexion.getConnect().createStatement();
		
		String sql ="INSERT into facture (client_id, total, paye) "
				+ "VALUES ("+this.id_client+", "+this.total+", "+this.paye+")";
		state.executeUpdate(sql, state.RETURN_GENERATED_KEYS);
		
		ResultSet rs = state.getGeneratedKeys();
		
		if(rs.next()){
			
			int id = rs.getInt(1);
			return id;
		}
		else{
			System.out.println("Erreur, la facture n'a pas été créée.");
		}
		return -1;
	}

	
	
}