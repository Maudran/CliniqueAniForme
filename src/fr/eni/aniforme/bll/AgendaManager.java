package fr.eni.aniforme.bll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;

public class AgendaManager {

	private static AgendaManager instance;
	private DAO<Rdv> agendaDAO;
	private DAO<Animal> animalDAO = DAOFactory.getAnimalDAO();
	private DAO<Personnel> personnelDAO = DAOFactory.getPersonnelDAO();
	Calendar calendar = GregorianCalendar.getInstance();

	private AgendaManager() {
		agendaDAO = DAOFactory.getRDVDAO();
	}

	public static AgendaManager getInstance() {
		if (instance == null) {
			instance = new AgendaManager();
		}
		return instance;
	}

	public void insertRdv(Rdv rdv) throws BLLException {

		try {
			validerRdv(rdv);
			agendaDAO.insert(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'insertion d'un RDV dans l'agenda : " + rdv, e);
		}
	}

	public void deleteRdv(Rdv rdv) throws BLLException {
		try {
			agendaDAO.delete(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur à la suppression d'un RDV : " + rdv, e);
		}
	}

	public List<Rdv> getAgendaVeto(String nomVeto, Date date) throws BLLException {
		List<Rdv> agenda;
		List<Rdv> agendaAffichage = new ArrayList<Rdv>();
		try {
			agenda = agendaDAO.selectListByNom(nomVeto);
			calendar.setTime(date);
			int annee = calendar.get(Calendar.YEAR);
			int mois = calendar.get(Calendar.MONTH);
			int jour = calendar.get(Calendar.DAY_OF_MONTH);

			for (Rdv rdv : agenda) {
				calendar.setTime(rdv.getDateRdv());
				if (calendar.get(Calendar.YEAR) == annee && calendar.get(Calendar.MONTH) == mois
						&& calendar.get(Calendar.DAY_OF_MONTH) == jour) {

					agendaAffichage.add(rdv);
				}
			}
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de l'agenda par veto : " + nomVeto, e);
		}
		return agendaAffichage;
	}

	public List<Rdv> getAgendaByDate(Date date) throws BLLException {
		List<Rdv> agenda;
		List<Rdv> agendaAffichage = new ArrayList<Rdv>();
		try {
			agenda = agendaDAO.selectAll();
			calendar.setTime(date);
			int annee = calendar.get(Calendar.YEAR);
			int mois = calendar.get(Calendar.MONTH);
			int jour = calendar.get(Calendar.DAY_OF_MONTH);

			for (Rdv rdv : agenda) {
				calendar.setTime(rdv.getDateRdv());
				if (calendar.get(Calendar.YEAR) == annee && calendar.get(Calendar.MONTH) == mois
						&& calendar.get(Calendar.DAY_OF_MONTH) == jour) {

					agendaAffichage.add(rdv);
				}
			}
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de l'agenda par date : " + date, e);
		}
		return agendaAffichage;
	}

	private void validerRdv(Rdv rdv) throws BLLException {
		try {
			if (personnelDAO.selectById(rdv.getCodeVeterinaire()).getNom() == null
					|| personnelDAO.selectById(rdv.getCodeVeterinaire()).getNom().isEmpty()) {
				throw new BLLException("Le nom du/de la vétérinaire est obligatoire");
			}

			if (rdv.getDateRdv() == null) {
				throw new BLLException("La date du rdv est obligatoire");
			}

			if (animalDAO.selectById(rdv.getCodeAnimal()).getNom() == null
					|| animalDAO.selectById(rdv.getCodeAnimal()).getNom().isEmpty()) {
				throw new BLLException("Le nom de l'animal est obligatoire");
			}

		} catch (DALException e) {
			throw new BLLException("Erreur validation RDV", e);
		}

	}

	public boolean checkDispo(Rdv rdv) throws BLLException {
		try {
			
			calendar.setTime(rdv.getDateRdv());
			int annee = calendar.get(Calendar.YEAR);
			int mois = calendar.get(Calendar.MONTH);
			int jour = calendar.get(Calendar.DAY_OF_MONTH);
			int heures = calendar.get(Calendar.HOUR);
			int minutes = calendar.get(Calendar.MINUTE);
			
			List<Rdv> agenda = agendaDAO.selectAll();

			for (Rdv rdv2 : agenda) {
				calendar.setTime(rdv2.getDateRdv());
				if (calendar.get(Calendar.YEAR) == annee && calendar.get(Calendar.MONTH) == mois && calendar.get(Calendar.DAY_OF_MONTH) == jour
						&& calendar.get(Calendar.HOUR) == heures && calendar.get(Calendar.MINUTE) == minutes) {
					return false;
				}
			}
			return true;

		} catch (DALException e) {
			throw new BLLException("Erreur à la vérification de la disponibilité du/de la vétérinaire", e);
		}

	}

	public String heureDate(Rdv rdv) {

		calendar.setTime(rdv.getDateRdv());
		String heures = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String minutes = Integer.toString(calendar.get(Calendar.MINUTE));

		return heures + "h" + minutes;
	}

	public String nomDuClient(Rdv rdv) throws BLLException {

		try {
			String nom = animalDAO.selectById(rdv.getCodeAnimal()).getClient().getNom().toUpperCase();
			String prenom = animalDAO.selectById(rdv.getCodeAnimal()).getClient().getPrenom();

			return nom + " " + prenom;
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération nom + prénom client pour un RDV", e);
		}

	}

	public String nomAnimal(Rdv rdv) throws BLLException {
		try {
			return animalDAO.selectById(rdv.getCodeAnimal()).getNom();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération nom animal pour un RDV", e);
		}
	}

	public String raceAnimal(Rdv rdv) throws BLLException {
		try {
			return animalDAO.selectById(rdv.getCodeAnimal()).getRace();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération race animal pour un RDV", e);
		}
	}

	public Date getDateDuJour() {
		calendar.setTime(new Date());
		int annee = calendar.get(Calendar.YEAR);
		int mois = calendar.get(Calendar.MONTH);
		int jour = calendar.get(Calendar.DAY_OF_MONTH);

		Date dateDuJour = null;
		calendar.setTime(dateDuJour);
		calendar.set(Calendar.YEAR, annee);
		calendar.set(Calendar.MONTH, mois);
		calendar.set(Calendar.DAY_OF_MONTH, jour);

		return dateDuJour;
	}

}
