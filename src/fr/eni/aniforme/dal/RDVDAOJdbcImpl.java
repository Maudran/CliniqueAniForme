package fr.eni.aniforme.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.ihm.RdvAffichage;

public class RDVDAOJdbcImpl implements DAO<Rdv> {

	Connection connection = null;

	@Override
	public void insert(Rdv rdv) throws DALException {
		openConnection();

		String sql = "INSERT INTO [Agendas]([CodeVeto],[DateRdv],[CodeAnimal]) VALUES(?,?,?)";

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			fillStatementFromRdv(statement, rdv);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Erreur à l'insertion d'un rdv : " + rdv, e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				throw new DALException("Erreur à l'insertion d'un rdv : " + rdv, e);
			}
		}

	}

	@Override
	public void update(Rdv rdv) throws DALException {

	}

	@Override
	public void delete(int id) throws DALException {

	}

	@Override
	public void delete(Rdv rdv) throws DALException {
		openConnection();

		String sql = "DELETE FROM Agendas WHERE codeveto = ? and daterdv = ? and codeanimal = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			fillStatementFromRdv(statement, rdv);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Erreur lors de la suppression d'un rdv", e);
		}

	}

	@Override
	public Rdv selectById(int id) throws DALException {
		return null;
	}

	@Override
	public List<Rdv> selectByRole(String role) throws DALException {

		return null;
	}

	@Override
	public List<Rdv> selectListByNom(String saisie) throws DALException {
		openConnection();

		String sql = "SELECT codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)) AS remarque,c.archive "
				+ "FROM Agendas ag INNER JOIN Personnels p ON ag.codeveto = p.codepers "
				+ "INNER JOIN Animaux a ON ag.codeanimal = a.codeanimal "
				+ "INNER JOIN Clients c ON a.codeclient = c.codeclient "
				+ "WHERE nom = ? AND p.archive = 0 AND a.archive = 0 AND c.archive = 0 "
				+ "GROUP BY codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)),c.archive";

		List<Rdv> agenda = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, saisie);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				agenda.add(getRdvFromResultset(resultSet));
			}
			return agenda;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des rdv par nom", e);
		}
	}

	@Override
	public Rdv selectByNom(String nom) throws DALException {
		return null;
	}

	@Override
	public List<Rdv> selectAll() throws DALException {
		openConnection();

		String sql = "SELECT codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)) AS remarque,c.archive "
				+ "FROM Agendas ag INNER JOIN Personnels p ON ag.codeveto = p.codepers "
				+ "INNER JOIN Animaux a ON ag.codeanimal = a.codeanimal "
				+ "INNER JOIN Clients c ON a.codeclient = c.codeclient "
				+ "WHERE p.archive = 0 AND a.archive = 0 AND c.archive = 0"
				+ "GROUP BY codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)),c.archive";

		List<Rdv> agenda = new LinkedList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				agenda.add(getRdvFromResultset(resultSet));
			}
			return agenda;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération de tous les rdv", e);
		}
	}

	@Override
	public List<Rdv> selectByDate(Date date) throws DALException {

		openConnection();

		String sql = "SELECT codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)) as antecedents,a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)) AS remarque,c.archive "
				+ "FROM Agendas ag INNER JOIN Personnels p ON ag.codeveto = p.codepers "
				+ "INNER JOIN Animaux a ON ag.codeanimal = a.codeanimal "
				+ "INNER JOIN Clients c ON a.codeclient = c.codeclient "
				+ "WHERE daterdv = ? AND p.archive = 0 AND a.archive = 0 AND c.archive = 0 "
				+ "GROUP BY codeveto,daterdv,ag.codeanimal,codepers,nom,motpasse,role,p.archive,"
				+ "nomanimal,sexe,couleur,race,espece,a.codeclient,tatouage,CAST(antecedents AS varchar(max)),a.archive,"
				+ "nomclient,prenomclient,adresse1,adresse2,codepostal,ville,numtel,assurance,email,CAST(remarque AS varchar(max)),c.archive";

		List<Rdv> agenda = new LinkedList<>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setTimestamp(1, new Timestamp(date.getTime()));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				agenda.add(getRdvFromResultset(resultSet));
			}
			return agenda;
		} catch (SQLException e) {
			throw new DALException("Erreur à la récupération des rdv par date", e);
		}
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

	public static Rdv getRdvFromResultset(ResultSet resultSet) throws SQLException {

		Personnel personnel = PersonnelDAOJdbcImpl.getPersonnelFromResultset(resultSet);

		Animal animal = AnimalDAOJdbcImpl.getAnimalFromResultset(resultSet);

		Rdv rdv = new Rdv();

		rdv.setCodeVeterinaire(personnel.getCodePers());
		rdv.setCodeAnimal(animal.getCodeAnimal());
		rdv.setDateRdv(resultSet.getTimestamp("daterdv"));

		return rdv;
	}

	private void fillStatementFromRdv(PreparedStatement statement, Rdv rdv) throws SQLException, DALException {

		DAO<Personnel> personnelDAO = DAOFactory.getPersonnelDAO();
		DAO<Animal> animalDAO = DAOFactory.getAnimalDAO();

		int codePers = personnelDAO.selectById(rdv.getCodeVeterinaire()).getCodePers();
		int codeAnimal = animalDAO.selectById(rdv.getCodeAnimal()).getCodeAnimal();

		statement.setInt(1, codePers);
		statement.setTimestamp(2, new Timestamp(rdv.getDateRdv().getTime()));
		statement.setInt(3, codeAnimal);
	}

	@Override
	public List<Animal> selectAnimaux(Client client) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Client> selectAllWithAnimals() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
