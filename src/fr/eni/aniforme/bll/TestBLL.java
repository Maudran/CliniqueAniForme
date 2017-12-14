package fr.eni.aniforme.bll;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Personnel;

public class TestBLL {

	public static void main(String[] args) {
		PersonnelManager personnelManager = PersonnelManager.getInstance();
		AgendaManager agenda = AgendaManager.getInstance();
		AnimalManager animalManager = AnimalManager.getInstance();
		ClientManager clientManager = ClientManager.getInstance();

		Animal animal = new Animal();

		try {
			
			Personnel admin = new Personnel();
			
			admin.setMotPasse("x");
			admin.setNom("admin");
			admin.setRole("adm");
			
			personnelManager.insertPersonnel(admin);

			// animal.setNom("Fluffy");
			// animal.setSexe("Mâle");
			// animal.setRace("Siamois");
			// animal.setEspece("Chat");
			//
			// animal.setClient(clientManager.getClientById(2));
			//
			// animal.setArchive(false);
			//
			// System.out.println("Ajout d'un employé");
			//
			// animalManager.insertAnimal(animal);
			// System.out.println(agenda.getAgendaVeto("DE CAJOU Benoît"));
			// System.out.println(personnelManager.getVeterinaires());
			// System.out.println(animalManager.getAnimaux());

			String string = "Décembre 06 2017";
			DateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.FRANCE);

			Date date = format.parse(string);
			
			System.out.println(date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDuJour = sdf.parse(sdf.format(new Date()));
			
			System.out.println(dateDuJour);

			System.out.println(agenda.getAgendaByDate(dateDuJour));

		} catch (BLLException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

}
