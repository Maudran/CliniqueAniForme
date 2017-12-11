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

public class PersonnelDAOJdbcImpl implements DAO<Personnel> {

	Connection connection = null;

	@Override
	public void insert(Personnel personnel) throws DALException {

		openConnection();

		String sql = "INSERT INTO [Personnels]([Nom],[MotPasse],[Role],[Archive]) VALUES(?,?,?,?)";

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			fillStatementFromPersonnel(statement, personnel);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				personnel.setCodePers(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un personnel : " + personnel, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un personnel : " + personnel, e);
			}
		}

	}

	@Override
	public void update(Personnel personnel) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET nom=?,motpasse=?,role=?,archive=? WHERE codepers=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			fillStatementFromPersonnel(statement, personnel);
			statement.setInt(5, personnel.getCodePers());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'update d'un personnel : " + personnel, e);
		}

	}

	@Override
	public void delete(int id) throws DALException {
		openConnection();

		String sql = "UPDATE Personnels SET archive = ? WHERE codepers=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'archivage d'un personnel : " + id, e);
		}

	}

	@Override
	public Personnel selectById(int id) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels where codepers=? and archive = 0";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getPersonnelFromResultset(resultSet);
			return null;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un personnel : " + id, e);
		}
	}

	@Override
	public List<Personnel> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels WHERE archive = 0";
		List<Personnel> personnels = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				personnels.add(getPersonnelFromResultset(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les personnels", e);
		}
	}

	@Override
	public List<Personnel> selectByRole(String role) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels WHERE role =? AND archive = 0";
		List<Personnel> personnels = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, role);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personnels.add(getPersonnelFromResultset(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des personnels par role", e);
		}
	}

	@Override
	public List<Personnel> selectListByNom(String saisie) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels WHERE nom=? OR nom like '%'+?+'%' AND archive = 0";
		List<Personnel> personnels = new LinkedList<>();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, saisie);
			statement.setString(2, saisie);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personnels.add(getPersonnelFromResultset(resultSet));
			}
			return personnels;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des employes par nom", e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à la récupération des employes par nom", e);
			}
		}
	}

	@Override
	public Personnel selectByNom(String nom) throws DALException {
		openConnection();

		String sql = "SELECT * FROM Personnels where nom=? AND archive = 0";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nom);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next())
				return getPersonnelFromResultset(resultSet);
			else {
				return null;
			}

		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération d'un personnel : " + nom, e);
		}
	}

	@Override
	public List<Personnel> selectByDate(Date date) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectRaces() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> selectEspeces() throws DALException {
		// TODO Auto-generated method stub
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

	public static Personnel getPersonnelFromResultset(ResultSet resultSet) throws SQLException {

		Personnel personnel = new Personnel();

		personnel.setNom(resultSet.getString("nom"));
		personnel.setMotPasse(resultSet.getString("motpasse"));
		personnel.setRole(resultSet.getString("role"));
		personnel.setCodePers(resultSet.getInt("codepers"));

		return personnel;
	}

	private void fillStatementFromPersonnel(PreparedStatement statement, Personnel personnel) throws SQLException {

		statement.setString(1, personnel.getNom());
		statement.setString(2, personnel.getMotPasse());
		statement.setString(3, personnel.getRole());
		statement.setInt(4, 0);
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

}
