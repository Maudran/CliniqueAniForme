package fr.eni.aniforme.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bo.Client;

public class ListeRechercheModel extends AbstractListModel {

	private List<Client> clients = new ArrayList<Client>();
	ClientManager clientManager = ClientManager.getInstance();

	public void updateData(String saisie) {

		try {
			clients = clientManager.getListByNom(saisie);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Client getElementAt(int index) {
		clients.get(index);
		return null;
	}

	@Override
	public int getSize() {
		return clients.size();
	}

}
