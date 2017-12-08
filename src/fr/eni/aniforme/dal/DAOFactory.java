package fr.eni.aniforme.dal;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;

public class DAOFactory {

	public static DAO<Animal> getAnimalDAO() {
		return new AnimalDAOJdbcImpl();
	}

	public static DAO<Client> getClientDAO() {
		return new ClientDAOJdbcImpl();
	}

	public static DAO<Personnel> getPersonnelDAO() {
		return new PersonnelDAOJdbcImpl();
	}

	public static DAO<Rdv> getRDVDAO() {
		return new RDVDAOJdbcImpl();
	}
}
