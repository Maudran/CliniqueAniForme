package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bo.Client;

public class TableRechercheModel extends AbstractTableModel {

	private List<Client> clients = new ArrayList<Client>();
	private String[] entetes = { "Nom", "Prénom", "Code postal", "Ville" };
	ClientManager clientManager = ClientManager.getInstance();

	public void updateData(String saisie) {

		try {
			clients = clientManager.getListByNom(saisie);
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return clients.size();
	}
	
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}
	
	public Client getValueAt(int rowIndex) {
		return clients.get(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object result = null;

		if (rowIndex < clients.size()) {
			switch (columnIndex) {
			case 0:
				result = clients.get(rowIndex).getNom();
				break;
			case 1:
				result = clients.get(rowIndex).getPrenom();
				break;
			case 2:
				result = clients.get(rowIndex).getCodePostal();
				break;
			case 3:
				result = clients.get(rowIndex).getVille();
				break;
			}
		}

		return result;
	}

}
