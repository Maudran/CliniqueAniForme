package fr.eni.aniforme.bll;

import java.util.List;

import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.DAO;
import fr.eni.aniforme.dal.DAOFactory;

public class ClientManager {

	private static ClientManager instance;
	private DAO<Client> clientDAO;

	private ClientManager() {
		clientDAO = DAOFactory.getClientDAO();
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}

	public List<Client> getClients() throws BLLException {
		try {
			return clientDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération de la liste des clients", e);
		}
	}

	public int insertClient(Client client) throws BLLException {
		try {
			validerClient(client);
			return clientDAO.insert(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'ajout d'un client : " + client, e);
		}
	}

	public void updateClient(Client client) throws BLLException {
		try {
			validerClient(client);
			clientDAO.update(client);
		} catch (DALException e) {
			throw new BLLException("Erreur à la mise à jour d'un client : " + client, e);
		}
	}

	public void archivageClient(int codeClient) throws BLLException {
		try {
			clientDAO.delete(codeClient);
		} catch (DALException e) {
			throw new BLLException("Erreur à l'archivage d'un client, id : " + codeClient, e);
		}
	}

	public Client getClientById(int id) throws BLLException {
		try {
			return clientDAO.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un client par id : " + id, e);
		}
	}

	public List<Client> getListByNom(String saisie) throws BLLException {
		try {
			return clientDAO.selectListByNom(saisie);
		} catch (DALException e) {
			throw new BLLException("Erreur à la recherche d'un client : ", e);
		}
	}
	
	public Client getClientWithAnimals(int id) throws BLLException
	{
		try {
			return clientDAO.selectClientWithAnimals(id);
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération d'un client et ses animaux : " + id, e);
		}
	}
	
	public List<Client> getClientsWithAnimals() throws BLLException
	{
		try {
			return clientDAO.selectAllWithAnimals();
		} catch (DALException e) {
			throw new BLLException("Erreur à la récupération des client et leurs animaux", e);
		}
	}

	public void validerClient(Client client) throws BLLException {
		if (isNullOrEmpty(client.getNom())) {
			throw new BLLException("Le nom d'un client est obligatoire");
		}

		if (isNullOrEmpty(client.getPrenom())) {
			throw new BLLException("Le prénom d'un client est obligatoire");
		}

	}

	private static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
