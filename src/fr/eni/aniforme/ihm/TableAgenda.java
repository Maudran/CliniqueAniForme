package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bo.Rdv;

public class TableAgenda extends AbstractTableModel {
	
	private List<RdvAffichage> rdvAffichage = new ArrayList<RdvAffichage>();
	private final String[] entetes = {"Heure", "Nom du client", "Animal", "Race"};

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return rdvAffichage.size();
	}
	
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
		}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
	
	public void addRdv(Rdv rdv)
	{
		
	}

}
