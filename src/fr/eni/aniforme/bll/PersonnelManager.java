package fr.eni.aniforme.bll;

import java.util.List;

import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;

public class PersonnelManager {

	private static PersonnelManager instance;
	private DAO<Personnel> personnelDAO;

	private PersonnelManager() {
		personnelDAO = DAOFactory.getPersonnelDAO();
	}

	public static PersonnelManager getInstance() {
		if (instance == null) {
			instance = new PersonnelManager();
		}
		return instance;
	}
	
	public Personnel getEmployeById(int id) throws BLLException
	{
		try {
			return personnelDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration d'un employ� par id : " + id, e);
		}
	}
	
	public Personnel getEmployeByNom(String nom) throws BLLException
	{
		try {
			return personnelDAO.selectByNom(nom);
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration d'un employ� par nom : " + nom, e);
		}
	}

	public List<Personnel> getEmployes() throws BLLException {
		try {
			return personnelDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration des employ�s", e);
		}
	}

	public List<Personnel> getVeterinaires() throws BLLException {
		try {
			return personnelDAO.selectByRole("vet");
		} catch (DALException e) {
			throw new BLLException("Erreur � la r�cup�ration de la liste des v�t�rinaires", e);
		}
	}

	public int insertPersonnel(Personnel personnel) throws BLLException {
		try {
			validerEmploye(personnel);
			return personnelDAO.insert(personnel);
		} catch (DALException e) {
			throw new BLLException("Erreur � l'ajout d'un employ� : " + personnel, e);
		}
	}

	public void updatePersonnel(Personnel personnel) throws BLLException {
		try {
			validerEmploye(personnel);
			personnelDAO.update(personnel);
		} catch (DALException e) {
			throw new BLLException("Erreur � la mise � jour d'un employ� : " + personnel, e);
		}
	}

	public void archiverEmploye(int codePers) throws BLLException {
		try {
			personnelDAO.delete(codePers);
		} catch (DALException e) {
			throw new BLLException("Erreur � l'archivage d'un employ�, id : " + codePers, e);
		}
	}
	
	public Personnel connexionEmploye(String nom, String motPasse) throws BLLException
	{
		try {
			return personnelDAO.connexionPersonnel(nom, motPasse);
		} catch (DALException e) {
			throw new BLLException("Erreur � la connexion d'un employ�, nom : " + nom, e);
		}
	}

	public void validerEmploye(Personnel personnel) throws BLLException {
		if (isNullOrEmpty(personnel.getNom())) {
			throw new BLLException("Le nom d'un employe est obligatoire");
		}

		if (isNullOrEmpty(personnel.getMotPasse())) {
			throw new BLLException("Le mot de passe d'un employe est obligatoire");
		}

		if (isNullOrEmpty(personnel.getRole())) {
			throw new BLLException("Le role d'un employe est obligatoire");
		}

	}

	private static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
