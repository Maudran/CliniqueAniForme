package fr.eni.aniforme.ihm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import fr.eni.aniforme.bll.AgendaManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.bo.RdvAffichage;

public class TableAgendaModel extends AbstractTableModel {

	private List<Rdv> rdvAffichage = new ArrayList<Rdv>();
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

		try {
		
		if (rowIndex < rdvAffichage.size()) {
			switch (columnIndex) {
			case 0:
				result = agendaManager.heureDate(rdvAffichage.get(rowIndex));
				break;
			case 1:
				
					result = agendaManager.nomDuClient(rdvAffichage.get(rowIndex));
				
				break;
			case 2:
				result = agendaManager.nomAnimal(rdvAffichage.get(rowIndex));
				break;
			case 3:
				result = agendaManager.raceAnimal(rdvAffichage.get(rowIndex));
				break;
			}
		}
		
		} catch (BLLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Rdv getValueAt(int rowIndex) {
		return rdvAffichage.get(rowIndex);
	}

	public void updateData() {
		
		try {
			
			rdvAffichage = agendaManager.getAgendaByDate(new Date());
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}
	
	public void updateVeterinaire(String nom, Date date) 
	{
		try {
			rdvAffichage = agendaManager.getAgendaVeto(nom, date);
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDate(Date date)
	{
		try {
			rdvAffichage = agendaManager.getAgendaByDate(date);
			fireTableDataChanged();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	public static Date getDateFromChamps(Date date, String heures, String minutes)
	{
		Calendar calendar = GregorianCalendar.getInstance();
		
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(heures));
		calendar.set(Calendar.MINUTE, Integer.valueOf(minutes));
		
		
		return calendar.getTime();
	}

}
