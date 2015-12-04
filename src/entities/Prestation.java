package entities;

public class Prestation {
	private int id;
	private String libelle;
	private int tarif_jour;
	public int id_categorie;
	public int id_classe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getTarif_jour() {
		return tarif_jour;
	}
	public void setTarif_jour(int tarif_jour) {
		this.tarif_jour = tarif_jour;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public int getId_classe() {
		return id_classe;
	}
	public void setId_classe(int id_classe) {
		this.id_classe = id_classe;
	}
	
	public Prestation getPrestationFromLibelle(){
		return this;
	}
	
	
	
}