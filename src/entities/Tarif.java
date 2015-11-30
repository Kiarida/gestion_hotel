package entities;

public class Tarif {
	private int id;
	private int nb_jours;
	private int total_prest;
	public int id_prestation;
	public int id_facture;
	
	
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

	
	

}