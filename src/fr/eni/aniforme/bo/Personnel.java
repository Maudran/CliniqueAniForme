package fr.eni.aniforme.bo;

public class Personnel {

	private Integer codePers;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;


	public Integer getCodePers() {
		return codePers;
	}

	public void setCodePers(Integer codePers) {
		this.codePers = codePers;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Personnels [codePers=" + codePers + ", nom=" + nom + ", motPasse=" + motPasse + ", role=" + role + "]";
	}

}
