package fr.eni.aniforme.dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Rdv;

public interface DAO<T> {

	public void insert(T data) throws DALException;

	public void update(T data) throws DALException;

	public void delete(int id) throws DALException;

	public T selectById(int id) throws DALException;

	public List<T> selectByRole(String role) throws DALException;

	public List<T> selectListByNom(String saisie) throws DALException;

	public T selectByNom(String nom) throws DALException;

	public List<T> selectByDate(Date date) throws DALException;

	public List<String> selectRaces() throws DALException;

	public List<String> selectEspeces() throws DALException;

	public List<T> selectAll() throws DALException;

	public List<Animal> selectAnimaux(Client client) throws DALException;

	public void delete(Rdv rdv) throws DALException;

	public ArrayList<Client> selectAllWithAnimals() throws DALException;

	List<Rdv> selectAgendaVet(String nom, Date date) throws DALException;

	List<Animal> selectByClient(Client client) throws DALException;

}
