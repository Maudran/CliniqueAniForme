package fr.eni.aniforme.bo;

import java.util.Date;

public class Rdv {

	private String nomVeterinaire;
	private String nomAnimal;
	private Date dateRdv;

	public String getNomVeterinaire() {
		return nomVeterinaire;
	}

	public void setNomVeterinaire(String nomVeterinaire) {
		this.nomVeterinaire = nomVeterinaire;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	@Override
	public String toString() {
		return "Rdv [nomVeterinaire=" + nomVeterinaire + ", nomAnimal=" + nomAnimal + ", dateRdv=" + dateRdv + "]";
	}

}
