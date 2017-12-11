package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bll.AgendaManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bo.RdvAffichage;

public class TableAgendaModel extends AbstractTableModel {

	private List<RdvAffichage> rdvAffichage = new ArrayList<RdvAffichage>();
	private final String[] entetes = { "Heure", "Nom du client", "Animal", "Race" };
	AgendaManager agendaManager = AgendaManager.getInstance();
	Calendar calendar = GregorianCalendar.getInstance();

	public TableAgendaModel() {
		updateData();
	}

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
		Object result = null;

		if (rowIndex < rdvAffichage.size()) {
			switch (columnIndex) {
			case 0:
				result = rdvAffichage.get(rowIndex).getHeure();
				break;
			case 1:
				result = rdvAffichage.get(rowIndex).getNomDuClient();
				break;
			case 2:
				result = rdvAffichage.get(rowIndex).getAnimal();
				break;
			case 3:
				result = rdvAffichage.get(rowIndex).getRace();
			}
		}

		return result;
	}
	
	public RdvAffichage getValueAt(int rowIndex) {
		return rdvAffichage.get(rowIndex);
	}

	public void updateData() {
		
		try {
			
			rdvAffichage = agendaManager.getRdvAffichageDate(new Date());
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}
	
	public void updateVeterinaire(String nom, Date date) 
	{
		try {
			rdvAffichage = agendaManager.getRdvAffichageVet(nom, date);
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDate(Date date)
	{
		try {
			rdvAffichage = agendaManager.getRdvAffichageDate(date);
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
