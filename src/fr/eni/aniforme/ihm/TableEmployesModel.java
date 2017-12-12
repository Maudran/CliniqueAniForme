package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Personnel;

public class TableEmployesModel extends AbstractTableModel {

	private List<Personnel> employes = new ArrayList<Personnel>();
	private final String[] entetes = { "Nom", "Rôle", "Mot de passe" };
	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public TableEmployesModel() {
		updateData();
	}

	public void updateData() {
		try {
			employes = personnelManager.getEmployes();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	public Personnel getValueAt(int rowIndex) {
		return employes.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return employes.size();
	}
	
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;

		if (rowIndex < employes.size()) {
			switch (columnIndex) {
			case 0:
				result = employes.get(rowIndex).getNom();
				break;
			case 1:
				result = employes.get(rowIndex).getRole();
				break;
			case 2:
				result = employes.get(rowIndex).getMotPasse();
				break;
			}
		}

		return result;
	}

}
