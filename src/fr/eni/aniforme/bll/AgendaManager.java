package fr.eni.aniforme.bll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;
import fr.eni.aniforme.dal.RDVDAOJdbcImpl;
import fr.eni.aniforme.ihm.RdvAffichage;

public class AgendaManager {

	private static AgendaManager instance;
	private DAO<Rdv> agendaDAO;
	private DAO<Animal> animalDAO = DAOFactory.getAnimalDAO();
	private DAO<Personnel> personnelDAO = DAOFactory.getPersonnelDAO();
	private DAO<Client> clientDAO = DAOFactory.getClientDAO();
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
			throw new BLLException("Erreur � l'insertion d'un RDV dans l'agenda : " + rdv, e);
		}
	}

	public void deleteRdv(Rdv rdv) throws BLLException {
		try {
			agendaDAO.delete(rdv);
		} catch (DALException e) {
			throw new BLLException("Erreur � la suppression d'un RDV : " + rdv, e);
		}
	}

	public List<Rdv> getAgendaVeto(String nomVeto) throws BLLException {
		try {
			return agendaDAO.selectListByNom(nomVeto);
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration de l'agenda d'un v�t�rinaire : " + nomVeto, e);
		}
	}

	public List<Rdv> getAgendaByDate(Date date) throws BLLException {
		try {
			return agendaDAO.selectByDate(date);
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration de l'agenda par date : " + date, e);
		}
	}

	public List<RdvAffichage> getRdvAffichageDate(Date date) throws BLLException
	{
		List<Rdv> agenda;
		List<RdvAffichage> agendaAffichage = new ArrayList<RdvAffichage>();
		try {
			 agenda = agendaDAO.selectByDate(date);
			 
			 for (Rdv rdv : agenda) {
				agendaAffichage.add(new RdvAffichage(heureDate(rdv), nomDuClient(rdv), nomAnimal(rdv), raceAnimal(rdv)));
			}
			 
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration des rdv � afficher",e);
		}
		return agendaAffichage;
	}

	private void validerRdv(Rdv rdv) throws BLLException {
		try {
			if (personnelDAO.selectById(rdv.getCodeVeterinaire()).getNom() == null
					|| personnelDAO.selectById(rdv.getCodeVeterinaire()).getNom().isEmpty()) {
				throw new BLLException("Le nom du/de la v�t�rinaire est obligatoire");
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
			List<Rdv> agenda = agendaDAO.selectAll();

			for (Rdv rdv2 : agenda) {
				if (rdv.getDateRdv() == rdv2.getDateRdv() && rdv.getCodeVeterinaire() == rdv2.getCodeVeterinaire()) {
					return false;
				}
			}
			return true;

		} catch (DALException e) {
			throw new BLLException("Erreur � la v�rification de la disponibilit� du/de la v�t�rinaire", e);
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
			throw new BLLException("Erreur � la r�cup�ration nom + pr�nom client pour un RDV", e);
		}

	}

	public String nomAnimal(Rdv rdv) throws BLLException {
		try {
			return animalDAO.selectById(rdv.getCodeAnimal()).getNom();
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration nom animal pour un RDV", e);
		}
	}

	public String raceAnimal(Rdv rdv) throws BLLException {
		try {
			return animalDAO.selectById(rdv.getCodeAnimal()).getRace();
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration race animal pour un RDV", e);
		}
	}

}
