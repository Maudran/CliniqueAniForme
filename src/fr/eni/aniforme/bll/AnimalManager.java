package fr.eni.aniforme.bll;

import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;

public class AnimalManager {

	private static AnimalManager instance;
	private DAO<Animal> animalDAO;

	private AnimalManager() {
		animalDAO = DAOFactory.getAnimalDAO();
	}

	public static AnimalManager getInstance() {
		if (instance == null) {
			instance = new AnimalManager();
		}
		return instance;
	}

	public List<Animal> getAnimaux() throws BLLException {
		try {
			return animalDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de la liste des animaux", e);
		}
	}
	
	public List<Animal> getAnimauxClient(Client client) throws BLLException
	{
		try {
			return animalDAO.selectByClient(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération des animaux d'un client", e);
		}
	}

	public void insertAnimal(Animal animal) throws BLLException {
		try {
			validerAnimal(animal);
			if (animal.getSexe().equalsIgnoreCase("Femelle")) {
				animal.setSexe("F");
			} else if (animal.getSexe().equalsIgnoreCase("Mâle")) {
				animal.setSexe("M");
			} else {
				animal.setSexe("H");
			}
			animalDAO.insert(animal);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'ajout d'un animal : " + animal, e);
		}
	}

	public void updateAnimal(Animal animal) throws BLLException {
		try {
			validerAnimal(animal);
			if (animal.getSexe().equalsIgnoreCase("Femelle")) {
				animal.setSexe("F");
			} else if (animal.getSexe().equalsIgnoreCase("Mâle")) {
				animal.setSexe("M");
			} else {
				animal.setSexe("H");
			}
			animalDAO.update(animal);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour d'un animal : " + animal, e);
		}
	}

	public void archiverAnimal(int codeAnimal) throws BLLException {
		try {
			animalDAO.delete(codeAnimal);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'arhivage d'un animal, id : " + codeAnimal, e);
		}
	}

	public List<String> getRaces() throws BLLException {
		try {
			return animalDAO.selectRaces();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de la liste des races", e);
		}
	}

	public List<String> getEspeces() throws BLLException {
		try {
			return animalDAO.selectEspeces();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de la liste des especes", e);
		}
	}

	public void validerAnimal(Animal animal) throws BLLException {
		if (isNullOrEmpty(animal.getNom())) {
			throw new BLLException("Le nom d'un animal est obligatoire");
		}

		if (isNullOrEmpty(animal.getSexe())) {
			throw new BLLException("Le sexe d'un animal est obligatoire");
		}

		if (isNullOrEmpty(animal.getRace())) {
			throw new BLLException("La race d'un animal est obligatoire");
		}

		if (isNullOrEmpty(animal.getEspece())) {
			throw new BLLException("L'espèce d'un animal est obligatoire");
		}

		if (animal.getClient() == null) {
			throw new BLLException("Le client d'un animal est obligatoire");
		}

	}

	private static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
