package entities;

//import java.sql.Date;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import database.Connect;

public class Facture {
	private int id;
	private static int total;
	private boolean paye=false;
	public int id_client;

	public int id_res;
	public Date date_deb;
	public Date date_fin;
	private int nb_personne;
	private int chambre_id;
	private int facture_id;
	private String duree;
	private String prixtot;
	private int totalrecup;
	private String categorie_id;
	private String classe_id;
	int dejaajoute=0;
	
	Connect connexion = new Connect();
	private int facturefinal;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getTotal() {
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
	
	public Facture(){
		
	}
	
	public Facture(int client_id){
		this.id_client = client_id;
	}
	
	public int createFacture(Connect connexion, Reservation r) throws SQLException{
		
		

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
	
	
	//Consultation de la facture du client, en ajoutant les prestations
	public void consulterFacture(Connect connexion, int client_id)throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		
		//Affichage facture 
		String sql3 ="SELECT * FROM facture WHERE client_id="+client_id;
		ResultSet rs3 =state.executeQuery(sql3);
		
		if(rs3.next()){
			totalrecup =rs3.getInt("total");
			paye =rs3.getBoolean("paye");
			id=rs3.getInt("id");
		}
		else{
			System.out.println("Erreur, la facture n'a pas �t� r�cup�r�e.");
		}

		//Calcul du total des prestations pour une r�servation donn�e
		String sql = "SELECT  SUM(total_prest)FROM tarif GROUP BY facture_id HAVING facture_id="+id;
		ResultSet rs = state.executeQuery(sql);
		int totalprest = 0;
		if(rs.next()){
			totalprest = rs.getInt("SUM(total_prest)");
		}
	
		System.out.println("Montant de la r�servation : "+totalrecup+ "euros.");
		System.out.println("Montant des prestations : "+totalprest+ "euros.");	
		System.out.println("Facture_id : "+id+" client_id"+client_id);	
		
		if(dejaajoute==0){
			//Somme de la facture et des prestations
			facturefinal=totalrecup+totalprest;
			String sql2 = "UPDATE facture SET total="+facturefinal+" WHERE id="+id;
			int rs2=state.executeUpdate(sql2);
			
			this.dejaajoute=1;
		}
		
		//Affichage facture 
		String sql4 ="SELECT * FROM facture WHERE client_id="+client_id;
		ResultSet rs4 =state.executeQuery(sql4);
		
		if(rs4.next()){
			totalrecup =rs4.getInt("total");
			paye =rs4.getBoolean("paye");
		}
		else{
			System.out.println("Erreur, la facture n'a pas �t� r�cup�r�e.");
		}
				
		
		System.out.println("Client num�ro : "+client_id+" Montant total de la facture : "+totalrecup+" Statut facture : "+paye+".");
	}
	
	
	
	//Calcule le montant de la facture
	public void calculFacture(Connect connexion, int client_id) throws SQLException{
		connexion.connection();
		Statement state = connexion.getConnect().createStatement();
		
		//nb �toiles hotel+cat�gorie de chambre+dur�e s�jour + saison
		String facture = "SELECT * FROM reservation WHERE client_id="+client_id+"";
		ResultSet rs =state.executeQuery(facture);
		while(rs.next()){
			id = rs.getInt("id");
			chambre_id = rs.getInt("chambre_id");
			facture_id = rs.getInt("facture_id");
			nb_personne = rs.getInt("nb_pers");
			date_deb = rs.getDate("date_deb");
			date_fin = rs.getDate("date_fin");			
		}
		System.out.println("Date d�but : "+date_deb.toString()+" Date fin : "+date_fin.toString());
		
		//calcul dur�e s�jour
		 String datediff = "SELECT DATEDIFF(date_fin,date_deb) AS diff FROM reservation WHERE client_id="+client_id+"";
		 ResultSet rs2 =state.executeQuery(datediff);
		 while (rs2.next()) {
             duree = rs2.getString("diff");
           }
		 
		
		//calcul saison 01/10 au 30/04 -> hiver 01/05 au 30/09 -> �t�
		 Date date = new Date();
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date_deb);
		 int month = cal.get(Calendar.MONTH);
		 int day = cal.get(Calendar.DAY_OF_MONTH);
	
		 //r�cup�ration classe_id
		 
		 String classe_id_1 = "SELECT * FROM hotel WHERE id=(SELECT hotel_id FROM chambre WHERE id="+chambre_id+")";
		 ResultSet rsclasse_id =state.executeQuery(classe_id_1);
		 while (rsclasse_id.next()) {
             classe_id = rsclasse_id.getString("classe_id");
           }
		 
		 //r�cup�ration categorie_id
		 String categorie_id_1 = "SELECT * FROM chambre WHERE id="+chambre_id+"";
		 ResultSet rscategorie_id =state.executeQuery(categorie_id_1);
		 while (rscategorie_id.next()) {
			 categorie_id = rscategorie_id.getString("categorie_id");
           }
		 
		 //test saison �t�
		 if(month<10 && month>4){
			 if(nb_personne==1){
				 String prixtotal = "SELECT prix_h_1 FROM tarif_chambre WHERE classe_id="+classe_id+" AND categorie_id="+categorie_id;
				 ResultSet rs3 =state.executeQuery(prixtotal);
				 while (rs3.next()) {
		            prixtot = rs3.getString("prix_h_1");
		           }
			 }
			 else{
				 String prixtotal = "SELECT prix_h_2 FROM tarif_chambre WHERE classe_id="+classe_id+" AND categorie_id="+categorie_id;
				 ResultSet rs3 =state.executeQuery(prixtotal);
				 while (rs3.next()) {
		            prixtot = rs3.getString("prix_h_2");
		           }
			 }
		 }
		 else{
			 if(nb_personne==1){
				 String prixtotal = "SELECT * FROM tarif_chambre WHERE classe_id="+classe_id+" AND categorie_id="+categorie_id;
				 ResultSet rs3 =state.executeQuery(prixtotal);
				 while (rs3.next()) {
		            prixtot = rs3.getString("prix_b_1");
		            
		           
		           }
				 System.out.println("PRIX TOTAL = "+prixtot);
			 }
			 else{
				 String prixtotal = "SELECT prix_b_2 FROM tarif_chambre WHERE classe_id="+classe_id+" AND categorie_id="+categorie_id;
				 ResultSet rs3 =state.executeQuery(prixtotal);
				 while (rs3.next()) {
		            prixtot = rs3.getString("prix_b_2");
		           }
			 }
		 }
		 
		 //Montant total de la facture sans les prestations
		 total = Integer.parseInt(prixtot)*Integer.parseInt(duree);
		
		 Statement state1 = connexion.getConnect().createStatement();
			
			String sql ="INSERT into facture (client_id, total, paye) "
					+ "VALUES ("+client_id+", "+this.total+", "+this.paye+")";
			state1.executeUpdate(sql, state1.RETURN_GENERATED_KEYS);
			ResultSet rs1 = state1.getGeneratedKeys();
			
			
			System.out.println("Facture g�n�r�e pour client_id: "+client_id+" pour un total de: "+total+" statut paiement: "+paye);
			
			

	}
	
	public void calculChambre(Connect connexion, Reservation r){
		try {
			connexion.connection();
			Statement state = connexion.getConnect().createStatement();
			String sql = "SELECT MONTH(date_debut) FROM reservation WHERE id = "+r.getId()+"";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}