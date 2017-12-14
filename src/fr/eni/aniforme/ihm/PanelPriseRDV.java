package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.AgendaManager;
import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.bo.Rdv;
import fr.eni.aniforme.ihm.EcranAnimaux.AnimalListener;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelPriseRDV extends JPanel implements ClientListener, AnimalListener {
	private JLabel lblClient, lblAnimal, lblVeterinaire, lblDate, lblHeure;
	private JButton btnSupprimer, btnValider, btnAjouterClient, btnAjouterAnimal, btnRechercherClient;;
	private JComboBox<String> cboVeterinaire, cboHeure, cboMinute;
	private JComboBox<Client> cboClient;
	private JComboBox<Animal> cboAnimal;
	private JScrollPane agendaScrollPane;
	private JTable tableau;
	private TableAgendaModel model;
	private EcranAjoutClient ecranAjoutClient;
	private EcranAnimaux ecranAnimaux;
	private EcranRechercheClient ecranRecherche;
	private UtilDateModel modelDp;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl dpCalendar;
	private JPanel panelClient, panelVeterinaire, panelDate, panelBtns, panelNord, panelHeure;

	PersonnelManager personnelManager = PersonnelManager.getInstance();
	ClientManager clientManager = ClientManager.getInstance();
	AgendaManager agendaManager = AgendaManager.getInstance();
	AnimalManager animalManager = AnimalManager.getInstance();
	

	public PanelPriseRDV(JFrame frame) {
		setLayout(new BorderLayout());

		add(getPanelNord(), BorderLayout.NORTH);

		getCboClient().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Animal> choixAnimal;

				try {
					if (!animalManager.getAnimauxClient((Client) getCboClient().getSelectedItem()).isEmpty()) {
						getCboAnimal().setEnabled(true);
						choixAnimal = animalManager.getAnimauxClient((Client) getCboClient().getSelectedItem());
						Animal[] animauxArray = choixAnimal.toArray(new Animal[0]);

						getCboAnimal().setModel(new DefaultComboBoxModel<Animal>(animauxArray));
					} else {
						getCboAnimal().setEnabled(false);
					}
				} catch (BLLException e1) {
					e1.printStackTrace();
				}

			}
		});

		getCboVeterinaire().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
						(Date) getDpCalendar().getModel().getValue());
			}
		});

		add(getAgendaScrollPane(), BorderLayout.CENTER);
		add(getPanelBtns(frame), BorderLayout.SOUTH);

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

	public JButton getBtnSupprimer(JFrame frame) {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton(new ImageIcon("ic_delete_black_24dp/web/ic_delete_black_24dp_1x.png"));
			btnSupprimer.setContentAreaFilled(false);
			btnSupprimer.setToolTipText("Supprimer RDV");
			btnSupprimer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (getTableau().getSelectedRow() != -1) {
						if (JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment supprimer ce rendez-vous?",
								"Confirmation suppression", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							try {
								agendaManager.deleteRdv(getModel().getValueAt(getTableau().getSelectedRow()));
								getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
										getRdvFromChamps().getDateRdv());
							} catch (BLLException e1) {
								e1.printStackTrace();
							}
						}
					}

				}
			});
		}
		return btnSupprimer;
	}

	public JButton getBtnValider(JFrame frame) {
		if (btnValider == null) {
			btnValider = new JButton(new ImageIcon("ic_done_black_24dp/web/ic_done_black_24dp_1x.png"));
			btnValider.setContentAreaFilled(false);
			btnValider.setToolTipText("Valider RDV");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (checkRDV() || getCboAnimal().isEnabled()) {
							if (agendaManager.checkDispo(getRdvFromChamps())) {
								if (JOptionPane.showConfirmDialog(frame,
										"Voulez-vous vraiment enregistrer ce rendez-vous?", "Confirmation validation",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

									agendaManager.insertRdv(getRdvFromChamps());
									getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
											getRdvFromChamps().getDateRdv());

								}
							}
							else
							{
								JOptionPane.showMessageDialog(frame, "Vétérinaire non disponible à la date du RDV");
							}

						} else {
							JOptionPane.showMessageDialog(frame, "Tous les champs sont obligatoires");
						}

					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});

		}
		return btnValider;
	}
	
	public JButton getBtnRechercherClient() {
		if (btnRechercherClient == null) {
			btnRechercherClient = new JButton(new ImageIcon("ic_search_black_24dp/web/ic_search_black_24dp_1x.png"));
			btnRechercherClient.setContentAreaFilled(false);
			btnRechercherClient.setToolTipText("Rechercher client");
			btnRechercherClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							getEcranRecherche().setVisible(true);
						}
					});
				}
			});
		}
		return btnRechercherClient;
	}

	public JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton(new ImageIcon("web/ic_add_black_24dp_1x.png"));
			btnAjouterClient.setContentAreaFilled(false);
			btnAjouterClient.setToolTipText("Ajouter client");
			btnAjouterClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getEcranAjoutClient().setVisible(true);

				}
			});
		}
		return btnAjouterClient;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton(new ImageIcon("web/ic_add_black_24dp_1x.png"));
			btnAjouterAnimal.setContentAreaFilled(false);
			btnAjouterAnimal.setToolTipText("Ajouter animal");
			btnAjouterAnimal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getAjouterAnimal().setVisible(true);

				}
			});
		}
		return btnAjouterAnimal;
	}

	public JComboBox<String> getCboVeterinaire() {
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

	public JComboBox<Client> getCboClient() {
		List<Client> choixClient = null;
		if (cboClient == null) {
			try {
				choixClient = clientManager.getClients();

			} catch (BLLException e) {
			}
			Client[] clientsArray = choixClient.toArray(new Client[0]);
			cboClient = new JComboBox<Client>(clientsArray);

		}
		return cboClient;
	}

	public JComboBox<Animal> getCboAnimal() {
		if (cboAnimal == null) {

			try {
				List<Client> clients = clientManager.getClientsWithAnimals();
				List<Animal> choixAnimal;
				choixAnimal = clients.get(0).getAnimaux();
				Animal[] animauxArray = choixAnimal.toArray(new Animal[0]);

				if (!animalManager.getAnimauxClient(clientManager.getClients().get(0)).isEmpty()) {
					cboAnimal = new JComboBox<Animal>(animauxArray);
				} else {
					cboAnimal = new JComboBox<Animal>();
					cboAnimal.setEnabled(false);
				}

			} catch (BLLException e) {
				e.printStackTrace();
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

	public JScrollPane getAgendaScrollPane() {
		if (agendaScrollPane == null) {
			agendaScrollPane = new JScrollPane(getTableau());
		}
		return agendaScrollPane;
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

	public EcranAjoutClient getEcranAjoutClient() {
		if (ecranAjoutClient == null) {
			ecranAjoutClient = new EcranAjoutClient(this);
		}
		return ecranAjoutClient;
	}

	public EcranAnimaux getEcranAnimaux() {
		if (ecranAnimaux == null) {
			ecranAnimaux = new EcranAnimaux(this, (Client) getCboClient().getSelectedItem(),
					(Animal) getCboAnimal().getSelectedItem());
		}
		return ecranAnimaux;
	}
	
	public EcranRechercheClient getEcranRecherche()
	{
		if (ecranRecherche == null) {
			ecranRecherche = new EcranRechercheClient(this);
		}
		return ecranRecherche;
	}

	public EcranAnimaux getAjouterAnimal() {
		if (ecranAnimaux == null) {
			ecranAnimaux = new EcranAnimaux(this, (Client) getCboClient().getSelectedItem());
		}
		return ecranAnimaux;
	}

	public UtilDateModel getModelDp() {
		if (modelDp == null) {
			modelDp = new UtilDateModel();
			modelDp.setValue(new Date());
		}
		return modelDp;
	}

	public JDatePanelImpl getDatePanel() {
		if (datePanel == null) {
			datePanel = new JDatePanelImpl(getModelDp());
		}
		return datePanel;
	}

	public JDatePickerImpl getDpCalendar() {
		if (dpCalendar == null) {
			dpCalendar = new JDatePickerImpl(getDatePanel());
			dpCalendar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getModel().updateVeterinaire((String) getCboVeterinaire().getSelectedItem(),
							(Date) getDpCalendar().getModel().getValue());

				}
			});

		}
		return dpCalendar;
	}

	public JPanel getPanelClient() {
		if (panelClient == null) {
			panelClient = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelClient.add(getBtnRechercherClient(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panelClient.add(getLblClient(), gbc);
			gbc.gridx = 2;
			panelClient.add(getCboClient(), gbc);
			gbc.gridx = 3;
			panelClient.add(getBtnAjouterClient(), gbc);
			gbc.gridy = 1;
			gbc.gridx = 1;
			panelClient.add(getLblAnimal(), gbc);
			gbc.gridx = 2;
			panelClient.add(getCboAnimal(), gbc);
			gbc.gridx = 3;
			panelClient.add(getBtnAjouterAnimal(), gbc);
		}
		return panelClient;
	}

	public JPanel getPanelVeterinaire() {
		if (panelVeterinaire == null) {
			panelVeterinaire = new JPanel();
			Insets insets = new Insets(5, 5, 5, 5);
			panelVeterinaire.add(getLblVeterinaire(), insets);
			panelVeterinaire.add(getCboVeterinaire(), insets);
		}
		return panelVeterinaire;
	}

	public JPanel getPanelDate() {
		if (panelDate == null) {
			panelDate = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelDate.add(getLblDate(), gbc);
			gbc.gridx = 1;
			panelDate.add(getDpCalendar(), gbc);
			gbc.gridy = 1;
			gbc.gridx = 0;
			panelDate.add(getLblHeure(), gbc);
			gbc.gridx = 1;
			panelDate.add(getPanelHeure(), gbc);
		}
		return panelDate;
	}

	public JPanel getPanelBtns(JFrame frame) {
		if (panelBtns == null) {
			panelBtns = new JPanel();
			panelBtns.add(getBtnValider(frame));
			panelBtns.add(getBtnSupprimer(frame));
		}
		return panelBtns;
	}

	public JPanel getPanelNord() {
		if (panelNord == null) {
			panelNord = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelNord.add(getPanelClient(), gbc);
			gbc.gridx = 1;
			panelNord.add(getPanelVeterinaire(), gbc);
			gbc.gridx = 2;
			panelNord.add(getPanelDate(), gbc);
		}
		return panelNord;
	}

	public JPanel getPanelHeure() {
		if (panelHeure == null) {
			panelHeure = new JPanel();
			Insets insets = new Insets(5, 5, 5, 5);
			panelHeure.add(getCboHeure(), insets);
			panelHeure.add(getCboMinute(), insets);
		}
		return panelHeure;
	}

	private Rdv getRdvFromChamps() {
		Rdv rdv = new Rdv();

		try {

			rdv.setCodeAnimal(((Animal) getCboAnimal().getSelectedItem()).getCodeAnimal());

			rdv.setCodeVeterinaire(
					personnelManager.getEmployeByNom((String) getCboVeterinaire().getSelectedItem()).getCodePers());
			rdv.setDateRdv(TableAgendaModel.getDateFromChamps((Date) getDpCalendar().getModel().getValue(),
					(String) getCboHeure().getSelectedItem(), (String) getCboMinute().getSelectedItem()));
		} catch (BLLException e) {
			e.printStackTrace();
		}

		return rdv;
	}

	private boolean checkRDV() {
		if (getCboClient().getSelectedItem() == null || getCboAnimal().getSelectedItem() == null
				|| getCboVeterinaire().getSelectedItem() == null || getDpCalendar().getModel().getValue() == null
				|| getCboHeure().getSelectedItem() == null || getCboMinute().getSelectedItem() == null) {
			return false;
		}
		return true;
	}

	@Override
	public void afficherClient(Client client) {
		Client[] clients;
		try {
			clients = clientManager.getClients().toArray(new Client[0]);
			getCboClient().setModel(new DefaultComboBoxModel<>(clients));
			getCboClient().getModel().setSelectedItem(client);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void afficherAnimal(Animal animal) {

		Animal[] animauxArray;
		try {
			getCboAnimal().setEnabled(true);
			animauxArray = animalManager.getAnimauxClient((Client) getCboClient().getSelectedItem())
					.toArray(new Animal[0]);
			getCboAnimal().setModel(new DefaultComboBoxModel<Animal>(animauxArray));
			getCboAnimal().setSelectedItem(animal);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}
}
