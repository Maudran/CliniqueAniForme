package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;

public class TableAnimauxModel extends AbstractTableModel {

	private List<Animal> animaux = new ArrayList<Animal>();
	private final String[] entetes = { "Numero", "Nom", "Sexe", "Couleur", "Race", "Tatouage" };
	AnimalManager animalManager = AnimalManager.getInstance();
	ClientManager clientManager = ClientManager.getInstance();
	Calendar calendar = GregorianCalendar.getInstance();

	public TableAnimauxModel() {
		updateData();
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return animaux.size();
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;

		if (rowIndex < animaux.size()) {
			switch (columnIndex) {
			case 0:
				result = animaux.get(rowIndex).getCodeAnimal();
				break;
			case 1:
				result = animaux.get(rowIndex).getNom();
				break;
			case 2:
				result = animaux.get(rowIndex).getSexe();
				break;
			case 3:
				result = animaux.get(rowIndex).getCouleur();
				break;
			case 4:
				result = animaux.get(rowIndex).getRace();
				break;
			case 5:
				result = animaux.get(rowIndex).getTatouage();
				break;
			}
		}

		return result;
	}

	public Animal getValueAt(int rowIndex) {
		return animaux.get(rowIndex);
	}

	public void updateData() {

		try {

			List<Client> clients = clientManager.getClients();
			animaux = clientManager.getClientWithAnimals(clients.get(0).getCodeClient()).getAnimaux();
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	public void updateClient(Client client) {
		try {
			animaux = animalManager.getAnimauxClient(client);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
