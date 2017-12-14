package fr.eni.aniforme.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bo.Animal;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EcranDossierMedical extends JFrame {

	private JPanel panelBouton;
	private JPanel panelInfoClient;
	private JPanel panelInfoAnimal;
	private JTextArea panelZoneDeSaisie;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JLabel lblCodeAnimal, lblNomAnimal, lblCouleurAnimal, lblSexeAnimal, lblEspeceAnimal, lblRaceAnimal,
			lblTatouageAnimal, lblClient;

	AnimalManager animalManager = AnimalManager.getInstance();

	public EcranDossierMedical(Animal animal) {
		setSize(500, 300);
		setLocationRelativeTo(null);
		setTitle("Dossier Médical");
		setLayout(new BorderLayout());

		add(getPanelBouton(this, animal), BorderLayout.NORTH);
		add(getPanelInfoClient(animal), BorderLayout.WEST);
		add(getPanelInfoAnimal(animal), BorderLayout.CENTER);
		add(getPanelZoneDeSaisie(animal), BorderLayout.EAST);
		panelZoneDeSaisie.setBorder(BorderFactory.createTitledBorder("Antécédents/ Consultations :"));
		panelInfoClient.setBorder(BorderFactory.createTitledBorder("Client :"));
		panelInfoAnimal.setBorder(BorderFactory.createTitledBorder("Animal :"));

	}

	public JPanel getPanelBouton(JFrame frame, Animal animal) {
		if (panelBouton == null) {
			panelBouton = new JPanel();
			panelBouton.add(getBtnValider(frame, animal));
			panelBouton.add(getBtnAnnuler(animal));
		}
		return panelBouton;
	}

	public JPanel getPanelInfoClient(Animal animal) {
		if (panelInfoClient == null) {
			panelInfoClient = new JPanel();
			panelInfoClient.add(getLblClient(animal));

		}
		return panelInfoClient;
	}

	public JPanel getPanelInfoAnimal(Animal animal) {
		if (panelInfoAnimal == null) {
			panelInfoAnimal = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(6, 6, 6, 6);
			gbc.gridx = 0;
			gbc.gridy = 0;
			panelInfoAnimal.add(getLblCodeAnimal(animal), gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			panelInfoAnimal.add(getLblNomAnimal(animal), gbc);
			gbc.gridx = 0;
			gbc.gridy = 2;
			panelInfoAnimal.add(getLblCouleurAnimal(animal), gbc);
			gbc.gridx = 1;
			gbc.gridy = 2;
			panelInfoAnimal.add(getLblSexeAnimal(animal), gbc);
			gbc.gridx = 0;
			gbc.gridy = 3;
			panelInfoAnimal.add(getLblEspeceAnimal(animal), gbc);
			gbc.gridx = 1;
			gbc.gridy = 3;
			panelInfoAnimal.add(getLblRaceAnimal(animal), gbc);
			gbc.gridx = 0;
			gbc.gridy = 4;
			panelInfoAnimal.add(getLblTatouageAnimal(animal), gbc);

		}
		return panelInfoAnimal;
	}

	public JTextArea getPanelZoneDeSaisie(Animal animal) {

		if (panelZoneDeSaisie == null) {
			panelZoneDeSaisie = new JTextArea(2, 35);
			panelZoneDeSaisie.setText(animal.getAntecedents());
		}
		return panelZoneDeSaisie;
	}

	public JButton getBtnValider(JFrame frame, Animal animal) {
		if (btnValider == null) {
			btnValider = new JButton(new ImageIcon("ic_done_black_24dp/web/ic_done_black_24dp_1x.png"));
			btnValider.setContentAreaFilled(false);
			btnValider.setToolTipText("Valider");
			btnValider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Animal newAnimal = new Animal();
					
					if (JOptionPane.showConfirmDialog(frame, "Voulez-vous enregistrer les modifications de cet animal?",
							"Demande de confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						try {
							newAnimal = animal;
							animal.setAntecedents(getPanelZoneDeSaisie(animal).getText());
							animalManager.updateAnimal(animal);
							dispose();
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					}

				}
			});
		}
		return btnValider;
	}

	public JButton getBtnAnnuler(Animal animal) {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton(new ImageIcon("web/ic_undo_black_24dp_1x.png"));
			btnAnnuler.setContentAreaFilled(false);
			btnAnnuler.setToolTipText("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					getPanelZoneDeSaisie(animal).setText(animal.getAntecedents());
					
				}
			});
		}
		return btnAnnuler;
	}

	public JLabel getLblClient(Animal animal) {
		if (lblClient == null) {
			lblClient = new JLabel(animal.getClient().getNom() + " " + animal.getClient().getPrenom());
		}
		return lblClient;
	}

	public JLabel getLblCodeAnimal(Animal animal) {
		if (lblCodeAnimal == null) {
			lblCodeAnimal = new JLabel(Integer.toString(animal.getCodeAnimal()));
		}
		return lblCodeAnimal;
	}

	public JLabel getLblNomAnimal(Animal animal) {
		if (lblNomAnimal == null) {
			lblNomAnimal = new JLabel(animal.getNom());
		}
		return lblNomAnimal;
	}

	public JLabel getLblCouleurAnimal(Animal animal) {
		if (lblCouleurAnimal == null) {
			lblCouleurAnimal = new JLabel(animal.getCouleur());
		}
		return lblCouleurAnimal;
	}

	public JLabel getLblSexeAnimal(Animal animal) {
		if (lblSexeAnimal == null) {
			lblSexeAnimal = new JLabel(animal.getSexe());
		}
		return lblSexeAnimal;
	}

	public JLabel getLblEspeceAnimal(Animal animal) {
		if (lblEspeceAnimal == null) {
			lblEspeceAnimal = new JLabel(animal.getEspece());
		}
		return lblEspeceAnimal;
	}

	public JLabel getLblRaceAnimal(Animal animal) {
		if (lblRaceAnimal == null) {
			lblRaceAnimal = new JLabel(animal.getRace());
		}
		return lblRaceAnimal;
	}

	public JLabel getLblTatouageAnimal(Animal animal) {
		if (lblTatouageAnimal == null) {
			lblTatouageAnimal = new JLabel(animal.getTatouage());
		}
		return lblTatouageAnimal;
	}

}
