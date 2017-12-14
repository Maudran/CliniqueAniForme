package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bo.Animal;
import fr.eni.aniforme.bo.Client;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EcranAnimaux extends JFrame {

	private JTextField textNomAnimal, textCouleur, textTatouage;
	private JLabel nomClient, labelCode, codeAnimal, labelNomAnimal, labelCouleur, labelEspece, labelRace,
			labelTatouage;
	private JComboBox<String> sexeCB, especeCB, raceCB;
	private JButton btnValider, btnAnnuler;
	private JPanel panelButtons, panelClient, panelAnimal, panelEspeceRace, panelCenter;

	private AnimalManager animalManager = AnimalManager.getInstance();

	public interface AnimalListener {
		void afficherAnimal(Animal animal);
	}

	private AnimalListener listener;

	public EcranAnimaux(AnimalListener listener, Client client, Animal animal) {

		this.listener = listener;

		setLayout(new BorderLayout());

		this.setTitle("Animaux");
		this.setSize(800, 500);
		add(getPanelClient(), BorderLayout.NORTH);
		add(getPanelCenter(), BorderLayout.CENTER);
		add(getPanelButtons(client), BorderLayout.SOUTH);

		afficherAnimal(client, animal);

	}

	public EcranAnimaux(AnimalListener listener, Client client) {

		this.listener = listener;

		setLayout(new BorderLayout());
		this.setTitle("Animaux");
		this.setSize(500, 500);

		add(getPanelClient(), BorderLayout.NORTH);
		add(getPanelCenter(), BorderLayout.CENTER);

		getEspeceCB().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<String> races = new ArrayList<>();

				switch ((String) getEspeceCB().getSelectedItem()) {
				case "Chien":
					races.add("Batard");
					races.add("Setter");
					break;
				case "Escargot":
					races.add("Bourgogne");
					break;
				case "Oiseau":
					races.add("Canari");
					break;
				case "Tortue":
					races.add("Marine");
					break;
				case "Chat":
					races.add("Siamois");
					break;
				}

				String[] racesArray = races.toArray(new String[0]);
				getRaceCB().setModel(new DefaultComboBoxModel<String>(racesArray));

			}
		});
		add(getPanelButtons(client), BorderLayout.SOUTH);

		afficherAnimal(client);

	}

	public JPanel getPanelButtons(Client client) {
		if (panelButtons == null) {
			panelButtons = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(4, 4, 4, 4);
			gbc.gridx = 5;
			gbc.gridy = 0;
			panelButtons.add(getBtnValider(client), gbc);
			gbc.gridx = 6;
			gbc.gridy = 0;
			panelButtons.add(getBtnAnnuler(client), gbc);
		}
		return panelButtons;
	}

	public JPanel getPanelClient() {
		if (panelClient == null)
			panelClient = new JPanel(new GridBagLayout());
		panelClient.setBorder(BorderFactory.createTitledBorder("Client :"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelClient.add(getNomClient(), gbc);
		return panelClient;
	}

	public JPanel getPanelAnimal() {
		if (panelAnimal == null) {
			panelAnimal = new JPanel(new GridBagLayout());
			panelAnimal.setAlignmentY(LEFT_ALIGNMENT);
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.insets = new Insets(6, 6, 6, 6);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			panelAnimal.add(getLabelCode(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panelAnimal.add(getCodeAnimal(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.anchor = GridBagConstraints.WEST;
			panelAnimal.add(getLabelNomAnimal(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			panelAnimal.add(getTextNomAnimal(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.anchor = GridBagConstraints.WEST;
			panelAnimal.add(getLabelCouleur(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;
			panelAnimal.add(getTextCouleur(), gbc);
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.anchor = GridBagConstraints.WEST;
			panelAnimal.add(getLabelTatouage(), gbc);
			gbc.gridx = 1;
			gbc.gridy = 5;
			panelAnimal.add(getTextTatouage(), gbc);

		}
		return panelAnimal;
	}

	public JPanel getPanelEspeceRace() {
		if (panelEspeceRace == null) {
			panelEspeceRace = new JPanel(new GridBagLayout());
			panelEspeceRace.setAlignmentY(LEFT_ALIGNMENT);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(10, 10, 10, 10);
			panelEspeceRace.add(getLabelEspece(), gbc);
			panelEspeceRace.add(getEspeceCB(), gbc);
			panelEspeceRace.add(getLabelRace(), gbc);
			panelEspeceRace.add(getRaceCB(), gbc);
			panelEspeceRace.add(getSexeCB(), gbc);
		}
		return panelEspeceRace;
	}

	public JPanel getPanelCenter() {
		if (panelCenter == null) {
			panelCenter = new JPanel(new GridLayout(2, 1));
			panelCenter.setAlignmentY(LEFT_ALIGNMENT);
			panelCenter.add(getPanelAnimal());
			panelCenter.add(getPanelEspeceRace());
		}
		return panelCenter;
	}

	public JLabel getNomClient() {
		if (nomClient == null) {
			nomClient = new JLabel("");
		}
		return nomClient;
	}

	public JLabel getCodeAnimal() {
		if (codeAnimal == null) {
			codeAnimal = new JLabel("");
		}
		return codeAnimal;
	}

	public JTextField getTextNomAnimal() {
		if (textNomAnimal == null) {
			textNomAnimal = new JTextField(20);
		}
		return textNomAnimal;
	}

	public JTextField getTextCouleur() {
		if (textCouleur == null) {
			textCouleur = new JTextField(20);
		}
		return textCouleur;
	}

	public JTextField getTextTatouage() {
		if (textTatouage == null) {
			textTatouage = new JTextField(20);
		}
		return textTatouage;
	}

	public JLabel getLabelCode() {
		if (labelCode == null) {
			labelCode = new JLabel("Code");
		}
		return labelCode;
	}

	public JLabel getLabelNomAnimal() {
		if (labelNomAnimal == null) {
			labelNomAnimal = new JLabel("Nom");
		}
		return labelNomAnimal;
	}

	public JLabel getLabelCouleur() {
		if (labelCouleur == null) {
			labelCouleur = new JLabel("Couleur");
		}
		return labelCouleur;
	}

	public JLabel getLabelEspece() {
		if (labelEspece == null) {
			labelEspece = new JLabel("Espece");
		}
		return labelEspece;
	}

	public JLabel getLabelRace() {
		if (labelRace == null) {
			labelRace = new JLabel("Race");
		}
		return labelRace;
	}

	public JLabel getLabelTatouage() {
		if (labelTatouage == null) {
			labelTatouage = new JLabel("Tatouage");
		}
		return labelTatouage;
	}

	public JComboBox<String> getSexeCB() {
		if (sexeCB == null) {
			sexeCB = new JComboBox<String>(new String[] { "Femelle", "Mâle", "Hermaphrodite" });
		}
		return sexeCB;
	}

	public JComboBox<String> getEspeceCB() {
		List<String> especes = null;
		if (especeCB == null) {
			try {
				especes = animalManager.getEspeces();

			} catch (BLLException e) {
				e.printStackTrace();
			}
			String[] especesArray = especes.toArray(new String[0]);
			especeCB = new JComboBox<String>(especesArray);
		}
		return especeCB;
	}

	public JComboBox<String> getRaceCB() {
		List<String> races = new ArrayList<String>();
		if (raceCB == null) {

			try {
				switch (animalManager.getEspeces().get(0)) {
				case "Chien":
					races.add("Batard");
					races.add("Setter");
					break;
				case "Escargot":
					races.add("Bourgogne");
					break;
				case "Oiseau":
					races.add("Canari");
					break;
				case "Tortue":
					races.add("Marine");
					break;
				case "Chat":
					races.add("Siamois");
					break;
				}

			} catch (BLLException e) {
				e.printStackTrace();
			}
			String[] racesArray = races.toArray(new String[0]);
			raceCB = new JComboBox<String>(racesArray);

		}
		return raceCB;
	}

	public JButton getBtnValider(Client client) {
		if (btnValider == null) {
			btnValider = new JButton(new ImageIcon("ic_done_black_24dp/web/ic_done_black_24dp_1x.png"));
			btnValider.setContentAreaFilled(false);
			btnValider.setToolTipText("Valider");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (checkAnimal(getAnimalFromChamps(client))) {
						try {
							if (isNullOrEmpty(getCodeAnimal().getText())) {
								int code = animalManager.insertAnimal(getAnimalFromChamps(client));
								listener.afficherAnimal(animalManager.getAnimalById(code));
								setVisible(false);
							} else {
								if (JOptionPane.showConfirmDialog(EcranAnimaux.this,
										"Voulez-vous enregistrer les modifications?", "Demande de confrmation",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

									animalManager.updateAnimal(getAnimalFromChamps(client));
									listener.afficherAnimal(
											animalManager.getAnimalById(Integer.valueOf(getCodeAnimal().getText())));
									setVisible(false);
								}

							}

						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(EcranAnimaux.this,
								"Les champs nom, sexe, race et especes sont obligatoires");
					}

				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler(Client client) {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton(new ImageIcon("web/ic_undo_black_24dp_1x.png"));
			btnAnnuler.setContentAreaFilled(false);
			btnAnnuler.setToolTipText("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (isNullOrEmpty(getCodeAnimal().getText())) {
						effacerChamps();
					} else {
						afficherAnimal(client, getAnimalFromCode(getCodeAnimal().getText()));
					}

				}
			});
		}
		return btnAnnuler;
	}

	public Animal getAnimalFromChamps(Client client) {
		Animal animal = new Animal();

		if (!isNullOrEmpty(getCodeAnimal().getText())) {
			animal.setCodeAnimal(Integer.valueOf(getCodeAnimal().getText()));
		}

		animal.setNom(getTextNomAnimal().getText());
		animal.setClient(client);
		animal.setCouleur(getTextCouleur().getText());
		animal.setSexe((String) getSexeCB().getSelectedItem());
		animal.setRace((String) getRaceCB().getSelectedItem());
		animal.setEspece((String) getEspeceCB().getSelectedItem());
		animal.setTatouage(getTextTatouage().getText());

		return animal;
	}

	private void effacerChamps() {
		getTextNomAnimal().setText(null);
		getTextCouleur().setText(null);
		getTextTatouage().setText(null);
	}

	private void afficherAnimal(Client client, Animal animal) {

		getCodeAnimal().setText(Integer.toString(animal.getCodeAnimal()));
		getNomClient().setText(client.getNom() + " " + client.getPrenom());
		getTextNomAnimal().setText(animal.getNom());
		getTextCouleur().setText(animal.getCouleur());
		getSexeCB().setSelectedItem(animal.getSexe());
		getRaceCB().setSelectedItem(animal.getRace());
		getEspeceCB().setSelectedItem(animal.getEspece());
		getTextTatouage().setText(animal.getTatouage());

	}

	private void afficherAnimal(Client client) {
		getNomClient().setText(client.getNom() + " " + client.getPrenom());
	}

	private boolean checkAnimal(Animal animal) {
		if (isNullOrEmpty(animal.getNom()) || isNullOrEmpty(animal.getSexe()) || isNullOrEmpty(animal.getRace())
				|| isNullOrEmpty(animal.getEspece()) || animal.getClient() == null) {
			return false;
		}
		return true;
	}

	public Animal getAnimalFromCode(String code) {
		try {
			return animalManager.getAnimalById(Integer.valueOf(code));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}

}
