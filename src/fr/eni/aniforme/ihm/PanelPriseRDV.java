package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelPriseRDV extends JPanel {
	private JLabel lblClient, lblAnimal, lblVeterinaire, lblDate, lblHeure, lblMinute;
	private JButton btnSupprimer, btnValider, btnAjouter;
	private JComboBox<String> cboVeterinaire, cboClient, cboHeure, cboMinute;
	private JComboBox<Animal> cboAnimal;
	private JScrollPane tableAgenda;
	private JTable tableau;
	private TableAgendaModel model;

	PersonnelManager personnelManager = PersonnelManager.getInstance();
	ClientManager clientManager = ClientManager.getInstance();

	public PanelPriseRDV() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 0;
		gbc.gridx = 0;
		add(getLblClient(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(getCboClient(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 1;
		add(getBtnAjouter(), gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		add(getLblAnimal(), gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(getCboAnimal(), gbc);
		gbc.gridy = 3;
		gbc.gridx = 1;
		add(getBtnAjouter(), gbc);
		gbc.gridy = 0;
		gbc.gridx = 2;
		add(getLblVeterinaire(), gbc);
		gbc.gridy = 1;
		gbc.gridx = 2;
		add(getCboVeterinaire(), gbc);
		gbc.gridy = 0;
		gbc.gridx = 3;
		add(getLblDate(), gbc);
		// gbc.gridy = 1;
		// gbc.gridx = 3;
		// add(dpCalendar, gbc);
		gbc.gridy = 2;
		gbc.gridx = 3;
		add(getLblHeure(), gbc);
		gbc.gridy = 2;
		gbc.gridx = 4;
		add(getLblMinute(), gbc);

		UtilDateModel model = new UtilDateModel();
		model.setValue(new Date());
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl dpCalendar = new JDatePickerImpl(datePanel);
	}

	public JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
		}
		return lblClient;
	}

	public JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal");
		}
		return lblAnimal;
	}

	public JLabel getLblVeterinaire() {
		if (lblVeterinaire == null) {
			lblVeterinaire = new JLabel("Veterinaire");
		}
		return lblVeterinaire;
	}

	public JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
		}
		return lblDate;
	}

	public JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel("Heure");
		}
		return lblHeure;
	}

	public JLabel getLblMinute() {
		if (lblMinute == null) {
			lblMinute = new JLabel("Minutes");
		}
		return lblMinute;
	}

	public JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
		}
		return btnSupprimer;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
		}
		return btnValider;
	}

	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("+");
		}
		return btnAjouter;
	}

	public JComboBox getCboVeterinaire() {
		List<String> veterinaires = new ArrayList<String>();
		List<Personnel> personnels;

		if (cboVeterinaire == null) {

			try {
				personnels = personnelManager.getVeterinaires();
				for (Personnel personnel : personnels) {
					veterinaires.add(personnel.getNom());
				}
			} catch (BLLException e) {

			}
			String[] veterinairesArray = veterinaires.toArray(new String[0]);
			cboVeterinaire = new JComboBox<String>(veterinairesArray);
		}
		return cboVeterinaire;
	}

	public JComboBox<String> getCboClient() {
		List<String> clients = new ArrayList<String>();
		List<Client> choixClient;
		if (cboClient == null) {
			try {
				choixClient = clientManager.getClients();
				clients.add(null);
				for (Client client : choixClient) {
					clients.add(client.getNom());
				}
			} catch (BLLException e) {
			}
			String[] clientsArray = clients.toArray(new String[0]);
			cboClient = new JComboBox<String>(clientsArray);
			while (cboClient.getSelectedItem() == null) {
				getCboAnimal().setEnabled(false);
			}
		}
		return cboClient;
	}

	public JComboBox<Animal> getCboAnimal() {
		List<Animal> choixAnimal;
		if (cboAnimal == null) {
			if (getCboClient().getSelectedItem() != null) {
				cboAnimal.setEnabled(true);
				choixAnimal = ((Client) getCboClient().getSelectedItem()).getAnimaux();
				Animal[] animauxArray = choixAnimal.toArray(new Animal[0]);
				cboAnimal = new JComboBox<Animal>(animauxArray);
			}

		}
		return cboAnimal;
	}

	public JComboBox<String> getCboHeure() {
		if (cboHeure == null) {
			cboHeure = new JComboBox<String>(new String[] { "09", "10", "11", "13", "14", "15", "16", "17" });
		}
		return cboHeure;
	}

	public JComboBox<String> getCboMinute() {
		if (cboMinute == null) {
			cboMinute = new JComboBox<String>(new String[] { "00", "15", "30", "45" });
		}
		return cboMinute;
	}

	public JScrollPane getTableAgenda() {
		if (tableAgenda == null) {
			tableAgenda = new JScrollPane(tableau);
		}
		return tableAgenda;
	}

	public TableAgendaModel getModel() {
		if (model == null) {
			model = new TableAgendaModel();
		}
		return model;
	}

	public JTable getTableau() {
		if (tableau == null) {
			tableau = new JTable(getModel());
		}
		return tableau;
	}
}
