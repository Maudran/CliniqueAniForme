package fr.eni.aniforme.bll;

import java.util.Date;
import java.util.List;

import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;

public class AgendaManager {

	private static AgendaManager instance;
	private DAO<Rdv> agendaDAO;

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

	public List<Rdv> getAgendaVeto(String nomVeto) throws BLLException {
		try {
			return agendaDAO.selectListByNom(nomVeto);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de l'agenda d'un vétérinaire : " + nomVeto, e);
		}
	}
	
	public List<Rdv> getAgendaByDate(Date date) throws BLLException
	{
		try {
			return agendaDAO.selectByDate(date);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de l'agenda par date : " + date, e);
		}
	}

	private void validerRdv(Rdv rdv) throws BLLException {
		if (rdv.getNomVeterinaire() == null) {
			throw new BLLException("Le nom du/de la vétérinaire est obligatoire");
		}

		if (rdv.getDateRdv() == null) {
			throw new BLLException("La date du rdv est obligatoire");
		}

		if (rdv.getNomAnimal() == null) {
			throw new BLLException("Le nom de l'animal est obligatoire");
		}

	}
	
	public boolean checkDispo(Rdv rdv) throws BLLException
	{
		try {
			List<Rdv> agenda = agendaDAO.selectAll();
			
			for (Rdv rdv2 : agenda) {
				if (rdv.getDateRdv() == rdv2.getDateRdv() && rdv.getNomVeterinaire().equals(rdv2.getNomVeterinaire())) {
					return false;
				}
			}
			return true;
			
		} catch (DALException e) {
			throw new BLLException("Erreur à la vérification de la disponibilité du/de la vétérinaire", e);
		}
		
	}

}
