package entities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
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
	
	public int createFacture(Connect connexion, Reservation r) throws SQLException, ParseException{
		int diff = (int)((r.getDate_fin().getTime() - r.getDate_deb().getTime()) / (24 * 60 * 60 * 1000));
		
		Calendar cal_deb = Calendar.getInstance();
		cal_deb.setTime(r.getDate_deb());
		
		int year_deb = cal_deb.get(Calendar.YEAR);
		Calendar cal_fin = Calendar.getInstance();
		cal_fin.setTime(r.getDate_fin());

		int year_fin = cal_fin.get(Calendar.YEAR);
		
		Calendar cal_b_d = Calendar.getInstance();
		Calendar cal_b_f = Calendar.getInstance();
		Calendar cal_h_d = Calendar.getInstance();
		Calendar cal_h_f = Calendar.getInstance();
		
		
		cal_b_d.set(year_deb-1, Calendar.OCTOBER, 01);
		cal_b_f.set(year_deb, Calendar.APRIL, 30);
		cal_h_d.set(year_deb, Calendar.MAY, 01);
		cal_h_f.set(year_deb, Calendar.SEPTEMBER, 30);
		String sql_tarif = "";
		
		if(year_deb != year_fin){
			cal_b_d.add(Calendar.YEAR, 1);
			cal_b_f.add(Calendar.YEAR, 1);
			cal_h_d.add(Calendar.YEAR, 1);
			cal_h_f.add(Calendar.YEAR, 1);
			if(cal_deb.compareTo(cal_b_f) <= 0 && cal_fin.compareTo(cal_b_f) <= 0){
				System.out.println("basse saison");
				
				if(r.getNb_pers() == 1){
					sql_tarif = "prix_b_1 * "+diff;
				}
				else{
					sql_tarif = "prix_b_2 *"+diff;
				}
			
				
			}
			else if(cal_deb.compareTo(cal_h_d) >= 0 && cal_fin.compareTo(cal_h_f) <= 0){
				System.out.println("haute saison");
				
				if(r.getNb_pers() == 1){
					sql_tarif = "prix_h_1 * "+diff;
				}
				else{
					sql_tarif = "prix_h_2 * "+diff;
				}
			
			}
			else if(cal_deb.compareTo(cal_b_d) >= 0 && cal_fin.compareTo(cal_b_f) > 0){
				System.out.println("debut bas + haute");
				
				int dif_b = (int)((cal_b_f.getTimeInMillis() - cal_deb.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				int dif_h = (int)((cal_fin.getTimeInMillis() - cal_h_d.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				System.out.println(dif_b);
				System.out.println(dif_h);
				if(r.getNb_pers() == 1){
					sql_tarif = "((prix_b_1 *"+dif_b+")+(prix_h_1 *"+dif_h+")) ";
				}
				else{
					sql_tarif = "((prix_b_2 *"+dif_b+")+(prix_h_2 *"+dif_h+"))";
				}
			
			}
			else if(cal_deb.compareTo(cal_h_d) >= 0 && cal_fin.compareTo(cal_h_f) > 0){
				System.out.println("debut haut + bas");
				int dif_b = (int)((cal_h_f.getTimeInMillis() - cal_deb.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				int dif_h = (int)((cal_fin.getTimeInMillis() - cal_b_d.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				System.out.println(dif_b);
				System.out.println(dif_h);
				if(r.getNb_pers() == 1){
					sql_tarif = "((prix_b_1 *"+dif_b+")+(prix_h_1 *"+dif_h+"))";
				}
				else{
					sql_tarif = "((prix_b_2 *"+dif_b+")+(prix_h_2 *)"+dif_h+"))";
				}
				
			}
		}
		else{
			
			System.out.println(cal_deb.compareTo(cal_h_d));
			System.out.println(cal_fin.compareTo(cal_h_f));
			System.out.println(cal_h_d.get(Calendar.MONTH));
			System.out.println(cal_h_d.get(Calendar.DAY_OF_WEEK));
			System.out.println(cal_h_d.get(Calendar.YEAR));
			System.out.println(cal_deb.get(Calendar.MONTH));
			System.out.println(cal_deb.get(Calendar.DAY_OF_WEEK));
			System.out.println(cal_deb.get(Calendar.YEAR));
			
			
			if(cal_deb.compareTo(cal_b_f) <= 0 && cal_fin.compareTo(cal_b_f) <= 0){
				System.out.println("basse saison");
				if(r.getNb_pers() == 1){
					sql_tarif = "prix_b_1 * "+diff;
				}
				else{
					sql_tarif = "prix_b_2 *"+diff;
				}
			}
			else if(cal_deb.compareTo(cal_h_d) >= 0 && cal_fin.compareTo(cal_h_f) <= 0){
				if(r.getNb_pers() == 1){
					sql_tarif = "prix_h_1 * "+diff;
				}
				else{
					sql_tarif = "prix_h_2 * "+diff;
				}
			
			}
			else if(cal_deb.compareTo(cal_b_d) >= 0 && cal_deb.compareTo(cal_b_f) <= 0 && cal_fin.compareTo(cal_b_f) > 0){

				
				
				int dif_b = (int)((cal_b_f.getTimeInMillis() - cal_deb.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				int dif_h = (int)((cal_fin.getTimeInMillis() - cal_h_d.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				System.out.println(dif_b);
				System.out.println(dif_h);
				if(r.getNb_pers() == 1){
					sql_tarif = "((prix_b_1 *"+dif_b+")+(prix_h_1 *"+dif_h+"))";
				}
				else{
					sql_tarif = "((prix_b_2 *"+dif_b+")+(prix_h_2 *"+dif_h+"))";
				}
			}
			else if(cal_deb.compareTo(cal_h_d) >= 0 && cal_fin.compareTo(cal_h_f) > 0){
				System.out.println("debut haut + bas");
				int dif_b = (int)((cal_h_f.getTimeInMillis() - cal_deb.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				int dif_h = (int)((cal_fin.getTimeInMillis() - cal_b_d.getTimeInMillis()) / (24 * 60 * 60 * 1000));
				System.out.println(dif_b);
				System.out.println(dif_h);
				if(r.getNb_pers() == 1){
					sql_tarif = "((prix_b_1 *"+dif_b+")+(prix_h_1 *"+dif_h+"))";
				}
				else{
					sql_tarif = "((prix_b_2 *"+dif_b+")+(prix_h_2 *"+dif_h+"))";
				}
			}
		}
			
		String sql2 ="SELECT "+sql_tarif+" as total FROM tarif_chambre LEFT JOIN categorie ON categorie.id = tarif_chambre.categorie_id "
				+ "LEFT JOIN classe ON classe.id = tarif_chambre.classe_id "
				+ "LEFT JOIN hotel ON hotel.classe_id = classe.id "
				+ "LEFT JOIN chambre ON chambre.hotel_id = hotel.id "
				+ "WHERE chambre.id="+r.getId_chambre()+" AND tarif_chambre.categorie_id = chambre.categorie_id";
			
		Statement state = connexion.getConnect().prepareStatement(sql2);
		System.out.println(sql2);
		ResultSet rs = state.executeQuery(sql2);
		
		while(rs.next()){
			this.total = rs.getInt(1);
		}
		//calcul saison 01/10 au 30/04 -> hiver 01/05 au 30/09 -> �t�
		
		return this.total;
		 
		//Statement state = connexion.getConnect().createStatement();
		
	
	}

	public void updateFacture(Connect connexion, int tot_pres) throws SQLException{
		
		connexion.connection();
		String sql = "UPDATE facture SET total =total+"+tot_pres+" WHERE id="+this.id;
		
		Statement state = connexion.getConnect().prepareStatement(sql);
		state.executeUpdate(sql);
		
	}
	
	public int insertFacture(Connect connexion) throws SQLException{
		connexion.connection();
		
		String sql ="INSERT into facture (client_id, total, paye) "
				+ "VALUES ("+this.id_client+", "+this.total+", "+this.paye+")";
		
		Statement state = connexion.getConnect().prepareStatement(sql);
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
		 java.util.Date date = new java.util.Date();
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