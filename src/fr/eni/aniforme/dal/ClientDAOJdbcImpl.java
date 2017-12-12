package fr.eni.aniforme.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;

public class ClientDAOJdbcImpl implements DAO<Client> {

	Connection connection = null;

	@Override
	public void insert(Client client) throws DALException {
		openConnection();

		String sql = "INSERT INTO [Clients]([NomClient],[PrenomClient],[Adresse1],[Adresse2],[CodePostal],[Ville], "
				+ "[NumTel],[Assurance],[Email],[Remarque],[Archive]) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			fillStatementFromClient(statement, client);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				client.setCodeClient(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un client : " + client, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un client : " + client, e);
			}
		}

	}

	@Override
	public void update(Client client) throws DALException {
		openConnection();

		String sql = "UPDATE Clients SET nomclient=?,prenomclient=?,adresse1=?"
				+ ",adresse2=?,codepostal=?,ville=?,numtel=?,assurance=?,email=?,remarque=?,archive=? WHERE codeclient=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			fillStatementFromClient(statement, client);
			statement.setInt(12, client.getCodeClient());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un animal : " + client, e);
		}

	}

	@Override
	public void delete(int id) throws DALException {
		openConnection();

		String sql = "UPDATE Clients SET archive = ? WHERE codeclient = ? "
				+ "UPDATE Animaux SET archive = ? WHERE codeclient = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, id);
			statement.setInt(3, 1);
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un client et ses animaux : " + id, e);
		}

	}

	@Override
	public Client selectById(int id) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients where codeclient=? AND archive = 0";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getClientFromResultset(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un client : " + id, e);
		}
	}

	@Override
	public List<Client> selectByRole(String role) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> selectListByNom(String saisie) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients WHERE nomclient=? OR prenomclient = ? OR nomclient like '%'+?+'%' "
				+ "OR prenomclient like '%'+?+'%' AND archive = 0";
		List<Client> clients = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, saisie);
			statement.setString(2, saisie);
			statement.setString(3, saisie);
			statement.setString(4, saisie);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clients.add(getClientFromResultset(resultSet));
			}
			return clients;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des clients par nom", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des clients par nom", e);
			}
		}
	}

	@Override
	public List<Animal> selectAnimaux(Client client) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Animaux WHERE codeclient = ? AND archive = 0";
		List<Animal> animaux = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, client.getCodeClient());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				animaux.add(AnimalDAOJdbcImpl.getAnimalFromResultset(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des animaux d'un client", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des animaux d'un client", e);
			}
		}
	}

	@Override
	public Client selectByNom(String nom) throws DALException {
		return null;
	}

	@Override
	public List<Client> selectByDate(Date date) throws DALException {

		return null;
	}

	@Override
	public List<String> selectRaces() throws DALException {

		return null;
	}

	@Override
	public List<String> selectEspeces() throws DALException {

		return null;
	}

	private void openConnection() throws DALException {
		try {
			if (connection == null)
				connection = JdbcTools.getConnection();
		} catch (SQLException e) {
			throw new DALException("Erreur à l'ouverture de la connexion", e);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
		}
		super.finalize();
	}

	public static Client getClientFromResultset(ResultSet resultSet) throws SQLException {
		Client client = new Client();

		client.setNom(resultSet.getString("nomclient"));
		client.setPrenom(resultSet.getString("prenomclient"));
		client.setAdresse1(resultSet.getString("adresse1"));
		client.setAdresse2(resultSet.getString("adresse2"));
		client.setCodePostal(resultSet.getString("codepostal"));
		client.setVille(resultSet.getString("ville"));
		client.setCodeClient(resultSet.getInt("codeclient"));
		client.setNumTel(resultSet.getString("numtel"));
		client.setAssurance(resultSet.getString("assurance"));
		client.setEmail(resultSet.getString("email"));
		client.setRemarque(resultSet.getString("remarque"));
		client.setArchive(false);

		return client;
	}

	private void fillStatementFromClient(PreparedStatement statement, Client client) throws SQLException {

		statement.setString(1, client.getNom());
		statement.setString(2, client.getPrenom());
		statement.setString(3, client.getAdresse1());
		statement.setString(4, client.getAdresse2());
		statement.setString(5, client.getCodePostal());
		statement.setString(6, client.getVille());
		statement.setString(7, client.getNumTel());
		statement.setString(8, client.getAssurance());
		statement.setString(9, client.getEmail());
		statement.setString(10, client.getRemarque());
		statement.setInt(11, 0);
	}

	@Override
	public List<Client> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients WHERE archive = 0";
		List<Client> clients = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				clients.add(getClientFromResultset(resultSet));
			}
			return clients;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les clients", e);
		}
	}

	@Override
	public ArrayList<Client> selectAllWithAnimals() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Clients c INNER JOIN Animaux a ON c.codeClient = a.codeClient";
		ArrayList<Client> clients = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int codeClientPrecedent = -1;
			Client newClient = null;
			while (rs.next()) {

				int code = rs.getInt("codeClient");

				if (code != codeClientPrecedent) {

					if (newClient != null) {
						clients.add(newClient);
					}
					newClient = getClientFromResultset(rs);

					codeClientPrecedent = code;
				}
				newClient.addAnimal(AnimalDAOJdbcImpl.getAnimalFromResultset(rs));
			}
			clients.add(newClient);
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les clients et leurs animaux", e);
		}
		return clients;
	}

	@Override
	public void delete(Rdv rdv) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rdv> selectAgendaVet(String nom, Date date) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> selectByClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel connexionPersonnel(String nom, String motPasse) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
