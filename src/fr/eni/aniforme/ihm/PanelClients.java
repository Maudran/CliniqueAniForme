package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.ClientManager;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;
import fr.eni.aniforme.ihm.EcranAnimaux.AnimalListener;

public class PanelClients extends JPanel implements ClientListener, AnimalListener {

	private JButton btnRechercherClient, btnAjouterClient, btnSupprimerClient, btnValider, btnAnnuler, btnAjouterAnimal,
			btnSupprimerAnimal, btnEditerAnimal;
	private JLabel lblCode, code, lblNom, lblPrenom, lblAdresse1, lblCodePostal, lblVille;
	private JTextField txtNom, txtPrenom, txtAdresse1, txtAdresse2, txtCodePostal, txtVille;
	private JPanel panelButtons, panelCoordonneesClients, panelButtonsAnimaux;

	private EcranAjoutClient ecranAjout;
	private EcranRechercheClient ecranRecherche;
	private EcranAnimaux ajoutAnimal, modifAnimal;

	private JTable tableauAnimaux;
	private JScrollPane animauxScrollPane;
	private TableAnimauxModel model;

	ClientManager clientManager = ClientManager.getInstance();
	AnimalManager animalManager = AnimalManager.getInstance();

	public PanelClients(JFrame frame) {
		setLayout(new BorderLayout());

		add(getPanelButtons(frame), BorderLayout.NORTH);
		add(getPanelCoordonneesClients(), BorderLayout.WEST);
		add(getAnimauxScrollPane(), BorderLayout.CENTER);
		add(getPanelButtonsAnimaux(frame), BorderLayout.SOUTH);

		List<Client> clients;
		try {
			clients = clientManager.getClientsWithAnimals();
			afficherClient(clients.get(0));
		} catch (BLLException e) {
			e.printStackTrace();
		}

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
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							getEcranAjout().setVisible(true);
						}
					});

				}
			});
		}

		return btnAjouterClient;
	}

	public JButton getBtnSupprimerClient(JFrame frame) {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton(new ImageIcon("ic_delete_black_24dp/web/ic_delete_black_24dp_1x.png"));
			btnSupprimerClient.setContentAreaFilled(false);
			btnSupprimerClient.setToolTipText("Supprimer client");
			btnSupprimerClient.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (!getCode().getText().isEmpty()) {
						if (JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment archiver ce client?",
								"Demande de confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

							try {
								clientManager.archivageClient(Integer.valueOf(getCode().getText()));
								effacerChamps();
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							} catch (BLLException e1) {
								e1.printStackTrace();
							}
						}
					}

				}
			});
		}
		return btnSupprimerClient;
	}

	public JButton getBtnValider(JFrame frame) {
		if (btnValider == null) {
			btnValider = new JButton(new ImageIcon("ic_done_black_24dp/web/ic_done_black_24dp_1x.png"));
			btnValider.setContentAreaFilled(false);
			btnValider.setToolTipText("Valider modifications client");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (!getCode().getText().isEmpty()) {
						if (JOptionPane.showConfirmDialog(frame,
								"Voulez-vous enregistrer les modifications de ce client?", "Demande de confirmation",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							try {
								clientManager.updateClient(getClientFromChamps());
								afficherClient(getClientFromCode(getCode().getText()));
							} catch (BLLException e1) {
								e1.printStackTrace();
							}
						}
					}

				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton(new ImageIcon("web/ic_undo_black_24dp_1x.png"));
			btnAnnuler.setContentAreaFilled(false);
			btnAnnuler.setToolTipText("Annuler modifications client");
			btnAnnuler.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					afficherClient(getClientFromCode(getCode().getText()));

				}
			});
		}
		return btnAnnuler;
	}

	public JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton(new ImageIcon("web/ic_add_black_24dp_1x.png"));
			btnAjouterAnimal.setContentAreaFilled(false);
			btnAjouterAnimal.setToolTipText("Ajouter animal");
			btnAjouterAnimal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							getAjoutAnimal().setVisible(true);
						}
					});
				}
			});
		}
		return btnAjouterAnimal;
	}

	public JButton getBtnSupprimerAnimal(JFrame frame) {
		if (btnSupprimerAnimal == null) {
			btnSupprimerAnimal = new JButton(new ImageIcon("ic_delete_black_24dp/web/ic_delete_black_24dp_1x.png"));
			btnSupprimerAnimal.setContentAreaFilled(false);
			btnSupprimerAnimal.setToolTipText("Supprimer animal");
			btnSupprimerAnimal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (getTableauAnimaux().getSelectedRow() != -1) {
						if (JOptionPane.showConfirmDialog(frame, "Voulez-vous archiver cet animal?",
								"Demande de confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							try {
								animalManager.archiverAnimal(
										getModel().getValueAt(getTableauAnimaux().getSelectedRow()).getCodeAnimal());
								getModel().updateClient(getClientFromCode(getCode().getText()));
							} catch (BLLException e1) {
								e1.printStackTrace();
							}
						}

					}

				}
			});
		}
		return btnSupprimerAnimal;
	}

	public JButton getBtnEditerAnimal() {
		if (btnEditerAnimal == null) {
			btnEditerAnimal = new JButton(new ImageIcon("ic_border_color_black_24dp/web/ic_border_color_black_24dp_1x.png"));
			btnEditerAnimal.setContentAreaFilled(false);
			btnEditerAnimal.setToolTipText("Modifier animal");
			btnEditerAnimal.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (getTableauAnimaux().getSelectedRow() != -1) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								getModifAnimal().setVisible(true);
							}
						});
					}

				}
			});
		}
		return btnEditerAnimal;
	}

	public JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
		}
		return lblCode;
	}

	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
		}
		return lblNom;
	}

	public JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prenom");
		}
		return lblPrenom;
	}

	public JLabel getLblAdresse1() {
		if (lblAdresse1 == null) {
			lblAdresse1 = new JLabel("Adresse");
		}
		return lblAdresse1;
	}

	public JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code Postal");
		}
		return lblCodePostal;
	}

	public JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
		}
		return lblVille;
	}

	public JLabel getCode() {
		if (code == null) {
			code = new JLabel();
		}
		return code;
	}

	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField(20);
		}
		return txtNom;
	}

	public JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField(20);
		}
		return txtPrenom;
	}

	public JTextField getTxtAdresse1() {
		if (txtAdresse1 == null) {
			txtAdresse1 = new JTextField(20);
		}
		return txtAdresse1;
	}

	public JTextField getTxtAdresse2() {
		if (txtAdresse2 == null) {
			txtAdresse2 = new JTextField(20);
		}
		return txtAdresse2;
	}

	public JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField(20);
		}
		return txtCodePostal;
	}

	public JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField(20);
		}
		return txtVille;
	}

	public JTable getTableauAnimaux() {
		if (tableauAnimaux == null) {
			tableauAnimaux = new JTable(getModel());
		}
		return tableauAnimaux;
	}

	public JScrollPane getAnimauxScrollPane() {
		if (animauxScrollPane == null) {
			animauxScrollPane = new JScrollPane(getTableauAnimaux());
		}
		return animauxScrollPane;
	}

	public TableAnimauxModel getModel() {
		if (model == null) {
			model = new TableAnimauxModel();
		}
		return model;
	}

	public JPanel getPanelButtons(JFrame frame) {
		if (panelButtons == null) {
			panelButtons = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(4, 4, 4, 4);
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelButtons.add(getBtnRechercherClient(), gbc);
			gbc.gridx = 2;
			panelButtons.add(getBtnAjouterClient(), gbc);
			gbc.gridx = 3;
			panelButtons.add(getBtnSupprimerClient(frame), gbc);
			gbc.gridx = 6;
			panelButtons.add(getBtnValider(frame), gbc);
			gbc.gridx = 7;
			panelButtons.add(getBtnAnnuler(), gbc);
			gbc.gridx = 8;
		}
		return panelButtons;
	}

	public JPanel getPanelCoordonneesClients() {
		if (panelCoordonneesClients == null) {
			panelCoordonneesClients = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(8, 8, 8, 8);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			panelCoordonneesClients.add(getLblCode(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panelCoordonneesClients.add(getCode(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			panelCoordonneesClients.add(getLblNom(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			panelCoordonneesClients.add(getTxtNom(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			panelCoordonneesClients.add(getLblPrenom(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;

			panelCoordonneesClients.add(getTxtPrenom(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 3;

			panelCoordonneesClients.add(getLblAdresse1(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 3;

			panelCoordonneesClients.add(getTxtAdresse1(), gbc);

			gbc.gridy = 4;
			panelCoordonneesClients.add(getTxtAdresse2(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 5;

			panelCoordonneesClients.add(getLblCodePostal(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 5;
			panelCoordonneesClients.add(getTxtCodePostal(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 6;
			panelCoordonneesClients.add(getLblVille(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 6;

			panelCoordonneesClients.add(getTxtVille(), gbc);

		}
		return panelCoordonneesClients;
	}

	public JPanel getPanelButtonsAnimaux(JFrame frame) {
		if (panelButtonsAnimaux == null) {
			panelButtonsAnimaux = new JPanel();
			panelButtonsAnimaux.add(getBtnAjouterAnimal());
			panelButtonsAnimaux.add(getBtnSupprimerAnimal(frame));
			panelButtonsAnimaux.add(getBtnEditerAnimal());
		}
		return panelButtonsAnimaux;
	}

	public EcranAjoutClient getEcranAjout() {
		if (ecranAjout == null) {
			ecranAjout = new EcranAjoutClient(this);
		}
		return ecranAjout;
	}

	public EcranRechercheClient getEcranRecherche() {
		if (ecranRecherche == null) {
			ecranRecherche = new EcranRechercheClient(this);
		}
		return ecranRecherche;
	}

	public EcranAnimaux getAjoutAnimal() {
		if (ajoutAnimal == null) {
			ajoutAnimal = new EcranAnimaux(this, getClientFromCode(getCode().getText()));
		}
		return ajoutAnimal;
	}

	public EcranAnimaux getModifAnimal() {
		if (modifAnimal == null) {
			modifAnimal = new EcranAnimaux(this, getClientFromCode(getCode().getText()),
					getModel().getValueAt(getTableauAnimaux().getSelectedRow()));
		}
		return modifAnimal;
	}

	public Client getClientFromChamps() {
		Client client = new Client();

		client.setCodeClient(Integer.valueOf(getCode().getText()));
		client.setNom(getTxtNom().getText());
		client.setPrenom(getTxtPrenom().getText());
		client.setAdresse1(getTxtAdresse1().getText());
		client.setAdresse2(getTxtAdresse2().getText());
		client.setCodePostal(getTxtCodePostal().getText());
		client.setVille(getTxtVille().getText());

		return client;
	}

	public Client getClientFromCode(String code) {
		try {
			return clientManager.getClientById(Integer.valueOf(code));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void effacerChamps() {
		getCode().setText(null);
		getTxtNom().setText(null);
		getTxtPrenom().setText(null);
		getTxtAdresse1().setText(null);
		getTxtAdresse2().setText(null);
		getTxtCodePostal().setText(null);
		getTxtVille().setText(null);
		getModel().clearData();

	}

	@Override
	public void afficherClient(Client client) {
		getCode().setText(Integer.toString(client.getCodeClient()));
		getTxtNom().setText(client.getNom());
		getTxtPrenom().setText(client.getPrenom());
		getTxtAdresse1().setText(client.getAdresse1());
		getTxtAdresse2().setText(client.getAdresse2());
		getTxtCodePostal().setText(client.getCodePostal());
		getTxtVille().setText(client.getVille());

		if (client.getAnimaux() != null) {
			getModel().updateClient(client);
		}

	}

	@Override
	public void afficherAnimal(Animal animal) {
		getModel().updateClient(animal.getClient());

	}

}
