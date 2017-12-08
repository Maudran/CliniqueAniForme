package fr.eni.aniforme.ihm;

public class RdvAffichage {
	
	private String heure;
	private String nomDuClient;
	private String animal;
	private String race;
	
	public RdvAffichage(String heure, String nomDuClient, String animal, String race) {
		setHeure(heure);
		setNomDuClient(nomDuClient);
		setAnimal(animal);
		setRace(race);
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getNomDuClient() {
		return nomDuClient;
	}

	public void setNomDuClient(String nomDuClient) {
		this.nomDuClient = nomDuClient;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

}
