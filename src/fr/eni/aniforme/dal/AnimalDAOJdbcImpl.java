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

import fr.eni.aniforme.dal.DALException;
import fr.eni.aniforme.dal.JdbcTools;
import fr.eni.aniforme.bo.*;

public class AnimalDAOJdbcImpl implements DAO<Animal> {

	Connection connection = null;

	@Override
	public int insert(Animal animal) throws DALException {

		openConnection();

		String sql = "INSERT INTO [Animaux]([NomAnimal],[Sexe],[Couleur],[Race],[Espece],[CodeClient],"
				+ "[Tatouage],[Antecedents],[Archive]) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			fillStatementFromAnimal(statement, animal);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				animal.setCodeAnimal(rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un animal : " + animal, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un animal : " + animal, e);
			}
		}
		return 0;
	}

	@Override
	public void update(Animal animal) throws DALException {
		openConnection();

		String sql = "UPDATE Animaux SET nomanimal=?,sexe=?,couleur=?"
				+ ",race=?,espece=?,codeclient=?,tatouage=?,antecedents=?,archive=? WHERE codeanimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			fillStatementFromAnimal(statement, animal);
			statement.setInt(10, animal.getCodeAnimal());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un animal : " + animal, e);
		}

	}

	@Override
	public void delete(int id) throws DALException {
		openConnection();

		String sql = "UPDATE Animaux SET archive = ? WHERE codeanimal=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un animal : " + id, e);
		}

	}

	@Override
	public Animal selectById(int id) throws DALException {

		openConnection();

		String sql = "SELECT codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)) as remarque,c.archive "
				+ "FROM Animaux a INNER JOIN Clients c ON a.codeclient = c.codeclient where codeanimal=? and a.archive = 0 and c.archive = 0 "
				+ "GROUP BY codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)),c.archive";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getAnimalFromResultset(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un animal : " + id, e);
		}
	}
	
	@Override
	public List<Animal> selectByClient(Client client) throws DALException
	{
		openConnection();

		String sql = "SELECT codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)) as remarque,c.archive "
				+ "FROM Animaux a INNER JOIN Clients c ON a.codeclient = c.codeclient where a.codeclient=? and a.archive = 0 and c.archive = 0 "
				+ "GROUP BY codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)),c.archive";
		
		List<Animal> animaux = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, client.getCodeClient());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				animaux.add(getAnimalFromResultset(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les animaux d'un client", e);
		}
	}

	@Override
	public List<Animal> selectByRole(String role) throws DALException {

		return null;
	}

	@Override
	public List<Animal> selectListByNom(String saisie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Animal selectByNom(String nom) throws DALException {
		openConnection();

		String sql = "SELECT codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)) as remarque,c.archive "
				+ "FROM Animaux a INNER JOIN Clients c ON a.codeclient = c.codeclient where nomanimal=? and a.archive = 0 and c.archive = 0 "
				+ "GROUP BY codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)),c.archive";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nom);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getAnimalFromResultset(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un animal : " + nom, e);
		}
	}

	@Override
	public List<Animal> selectByDate(Date date) throws DALException {

		return null;
	}

	@Override
	public List<String> selectRaces() throws DALException {

		openConnection();

		String sql = "SELECT race FROM Races";
		List<String> races = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				races.add(resultSet.getString("race"));
			}
			return races;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de toutes les races", e);
		}
	}

	@Override
	public List<String> selectEspeces() throws DALException {

		openConnection();

		String sql = "SELECT espece FROM Races";
		List<String> especes = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				especes.add(resultSet.getString("espece"));
			}
			return especes;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de toutes les especes", e);
		}
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

	public static Animal getAnimalFromResultset(ResultSet resultSet) throws SQLException {

		Client client = ClientDAOJdbcImpl.getClientFromResultset(resultSet);
		Animal animal = new Animal();

		animal.setNom(resultSet.getString("nomanimal"));
		animal.setSexe(resultSet.getString("sexe"));
		animal.setCouleur(resultSet.getString("couleur"));
		animal.setRace(resultSet.getString("race"));
		animal.setEspece(resultSet.getString("espece"));
		animal.setTatouage(resultSet.getString("tatouage"));
		animal.setAntecedents(resultSet.getString("antecedents"));
		animal.setClient(client);
		animal.setCodeAnimal(resultSet.getInt("codeanimal"));
		return animal;
	}

	private void fillStatementFromAnimal(PreparedStatement statement, Animal animal) throws SQLException {

		statement.setString(1, animal.getNom());
		statement.setString(2, animal.getSexe());
		statement.setString(3, animal.getCouleur());
		statement.setString(4, animal.getRace());
		statement.setString(5, animal.getEspece());
		statement.setInt(6, animal.getClient().getCodeClient());
		statement.setString(7, animal.getTatouage());
		statement.setString(8, animal.getAntecedents());
		statement.setInt(9, 0);
	}

	@Override
	public List<Animal> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)) as remarque,c.archive "
				+ "FROM Animaux a INNER JOIN Clients c ON a.codeclient = c.codeclient where a.archive = 0 and c.archive = 0 "
				+ "GROUP BY codeanimal,nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage, CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email, CAST(remarque AS varchar(max)),c.archive";
		List<Animal> animaux = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				animaux.add(getAnimalFromResultset(resultSet));
			}
			return animaux;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les animaux", e);
		}
	}

	@Override
	public List<Animal> selectAnimaux(Client client) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Rdv rdv) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Client> selectAllWithAnimals() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rdv> selectAgendaVet(String nom, Date date) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel connexionPersonnel(String nom, String motPasse) throws DALException {
		return null;
	}

	@Override
	public Client selectClientWithAnimals(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
