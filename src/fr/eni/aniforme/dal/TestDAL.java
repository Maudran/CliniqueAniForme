package fr.eni.aniforme.dal;

import java.util.Date;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;

public class TestDAL {
	

	public static void main(String[] args) {


		DAO<Animal> animalDAO = DAOFactory.getAnimalDAO();
		DAO<Client> clientDAO = DAOFactory.getClientDAO();
		DAO<Personnel> personnelDAO = DAOFactory.getPersonnelDAO();
		DAO<Rdv> rdvDAO = DAOFactory.getRDVDAO();
		Date date = new Date();

		
		Client client1 = new Client();
		client1.setNom("Audran");
		client1.setPrenom("Maryanne");
		client1.setAdresse1("4 rue Fr");
		client1.setAdresse2("Apt 42");
		client1.setCodePostal("56400");
		client1.setVille("Le Bono");
		
		Animal animal1 = new Animal();
		animal1.setNom("Fluffy");
		animal1.setSexe("F");
		animal1.setCouleur("Gris");
		animal1.setRace("Siamois");
		animal1.setEspece("Chat");
		animal1.setTatouage("AUD1234");
		animal1.setClient(client1);
		
		Personnel veterinaire = new Personnel();
		
		veterinaire.setNom("Veto");
		veterinaire.setMotPasse("password");
		veterinaire.setRole("vet");
		
		try {
			
			
			clientDAO.delete(8);
//			
//			personnelDAO.insert(veterinaire);
//			
//			System.out.println(animalDAO.selectById(4));
//			System.out.println(clientDAO.selectById(2));
//			System.out.println(personnelDAO.selectById(3));
//			
//			System.out.println(animalDAO.selectByNom("Milou"));
//			
//			System.out.println("--------------------------------------------");
//			System.lineSeparator();
//			
//			System.out.println(animalDAO.selectRaces());
//			System.lineSeparator();
//			System.out.println(animalDAO.selectEspeces());
//			
//			Animal animal2 = animalDAO.selectByNom("Milou");
//			
//			animal2.setCouleur("Blanc");
//			animal2.setTatouage("HADOCK");
//			
//			animalDAO.update(animal2);
//			
//			System.lineSeparator();
//			System.out.println("Modification de Milou");
//			System.out.println(animalDAO.selectByNom("Milou"));
//			
//			System.lineSeparator();
//			System.out.println("Archivage de Milou");
//			animalDAO.delete(animal2.getCodeAnimal());
//			System.out.println(animalDAO.selectByNom("Milou"));
//			System.lineSeparator();
//			
//			System.out.println(animalDAO.selectAll());
//			
//			System.lineSeparator();
//			
//			System.out.println(clientDAO.selectListByNom("Dup"));
//			
//			System.out.println("--------------------------------------------");
//			System.lineSeparator();
//			System.lineSeparator();
//			
//			
//			Client client2 = clientDAO.selectById(1009);
//			
//			client2.setAssurance("pouet");
//			client2.setEmail("pouet@pouet.fr");
//			
//			
//			
//			
//			System.out.println("Modification de client");
//			System.out.println(clientDAO.selectById(1009));
//			System.lineSeparator();
//			clientDAO.update(client2);
//			System.out.println(clientDAO.selectById(1009));
//			
//			System.lineSeparator();
//			System.out.println("Archivage de client");
//			clientDAO.delete(client2.getCodeClient());
//			System.out.println(clientDAO.selectById(3));
//			System.out.println(clientDAO.selectAllWithAnimals());
//			System.lineSeparator();
//			System.out.println(clientDAO.selectAll());
//			
//			System.out.println(personnelDAO.selectByNom("Veto"));
//			
//			System.out.println("--------------------------------------------");
//			System.lineSeparator();
//			
//			System.out.println(personnelDAO.selectByRole("vet"));
//			System.lineSeparator();
//			System.out.println(personnelDAO.selectAll());
//			
//			Personnel personnel2 = personnelDAO.selectByNom("Veto");
//			
//			personnel2.setMotPasse("coucou");
//			personnel2.setRole("sec");
//			
//			
//			
//			System.lineSeparator();
//			System.out.println("Modification de Veto");
//			System.out.println(personnelDAO.selectByNom("Veto"));
//			personnelDAO.update(personnel2);
//			System.out.println(personnelDAO.selectByNom("Veto"));
//			
//			System.lineSeparator();
//			System.out.println("Archivage de Veto");
//			personnelDAO.delete(personnel2.getCodePers());
//			System.out.println(personnelDAO.selectByNom("Veto"));
			
//			String string = "Décembre 6, 2017 14:15";
//			String string2 = "Janvier 2, 2018 16:30";
//			DateFormat format = new SimpleDateFormat("MMMM d, yyyy hh:mm", Locale.FRANCE);
//			
//			
//			
//			try {
//				Date date1 = format.parse(string);
//				Date date2 = format.parse(string2);
//				System.out.println(date1);
//				
//				String dateFormat = new SimpleDateFormat("hh:mm").format(date1);
//				
//				System.out.println(dateFormat);
//				Rdv rdv = new Rdv();
//				
//				rdv.setNomVeterinaire("MALALANICH Mélanie");
//				rdv.setNomAnimal("Kiki");
//				rdv.setDateRdv(date2);
//				
//				rdvDAO.insert(rdv);
//				System.out.println(rdv);
//				
//				System.out.println(rdvDAO.selectByDate(date1));
//				System.lineSeparator();
//				System.out.println(rdvDAO.selectAll());
//				
//				rdvDAO.delete(rdv);
//				
//				
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			
			
			
		} catch (DALException e1) {
			
			e1.printStackTrace();
		}
		

	}

}
