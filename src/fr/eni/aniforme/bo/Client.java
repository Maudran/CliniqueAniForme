package fr.eni.aniforme.bo;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private List<Animal> animaux;
	private Integer codeClient;
	private String nom;
	private String prenom;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private boolean archive;

	public Client() {

		animaux = new ArrayList<>();
	}
	
	public void addAnimal(Animal animal)
	{
		getAnimaux().add(animal);
		animal.setClient(this);
	}

	public Integer getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Integer codeClient) {
		this.codeClient = codeClient;
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

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAssurance() {
		return assurance;
	}

	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(List<Animal> animaux) {
		this.animaux = animaux;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(nom.toUpperCase());
		builder.append(" - ");
		builder.append(prenom);
		builder.append(" - ");
		builder.append(codePostal);
		builder.append(" - ");
		builder.append(ville);
		
		return builder.toString();
	}

//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("Client [CodeClient=");
//		builder.append(codeClient);
//		builder.append(", nom=");
//		builder.append(nom);
//		builder.append(", prenom=");
//		builder.append(prenom);
//		
//		if(animaux.size() > 0) {
//			builder.append(", animaux=[");
//			for(Animal a : animaux) {
//				builder.append("\n\t\t").append(a);
//			}
//		}
//		
//		builder.append("\n]");
//		return builder.toString();
//	}

}
