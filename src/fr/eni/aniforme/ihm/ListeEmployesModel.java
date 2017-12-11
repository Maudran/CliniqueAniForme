package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Personnel;

public class ListeEmployesModel extends AbstractListModel {
	
	private List<Personnel> employes = new ArrayList<Personnel>();
	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public ListeEmployesModel() {
		updateData();
	}
	
	private void updateData() {
		try {
			employes = personnelManager.getEmployes();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Personnel getElementAt(int index) {
		return employes.get(index);
	}

	@Override
	public int getSize() {
		return employes.size();
	}

}
