package fr.eni.aniforme.bo;

import java.util.Date;

public class Rdv {

	private int codeVeterinaire;
	private int codeAnimal;
	private Date dateRdv;

	public int getCodeVeterinaire() {
		return codeVeterinaire;
	}

	public void setCodeVeterinaire(int codeVeterinaire) {
		this.codeVeterinaire = codeVeterinaire;
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	@Override
	public String toString() {
		return "Rdv [nomVeterinaire=" + codeVeterinaire + ", nomAnimal=" + codeAnimal + ", dateRdv=" + dateRdv + "]";
	}

}
