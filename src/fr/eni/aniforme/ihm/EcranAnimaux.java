package fr.eni.aniforme.ihm;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import fr.eni.aniforme.bll.AnimalManager;
import fr.eni.aniforme.bll.BLLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EcranAnimaux extends JFrame {

	private JTextField textNomAnimal, textCouleur, textTatouage;
	private JLabel labelClient, labelCode, codeAnimal, nomClient, labelNomAnimal, labelCouleur, labelEspece, labelRace, labelTatouage;
	private JComboBox<String> sexeCB, especeCB, raceCB;
	private JButton btnValider, btnAnnuler;
	private JPanel panelButtons;

	
	private AnimalManager animalManager = AnimalManager.getInstance();

	public EcranAnimaux(String nomClient, int codeAnimal) {
		
		this.setTitle("Animaux");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new GridLayout());
		
		Box boxClient = Box.createHorizontalBox();
		boxClient.add(getLabelClient());
		getNomClient().setText(nomClient);
		boxClient.add(getNomClient());
		getContentPane().add(boxClient);
		
		JPanel panelChamps = new JPanel();
		panelChamps.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(2, 5, 2, 5);

		gbc.gridy = 0;
		gbc.gridx = 0;
		panelChamps.add(getLabelCode());
		
		gbc.gridx = 1;
		getCodeAnimal().setText(Integer.toString(codeAnimal));
		panelChamps.add(getCodeAnimal());
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelChamps.add(getLabelNomAnimal());
		
		gbc.gridx = 1;
		panelChamps.add(getTextNomAnimal());
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelChamps.add(getLabelCouleur());
		
		gbc.gridx = 1;
		panelChamps.add(getTextCouleur());
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		Box boxEspece = Box.createHorizontalBox();
		boxEspece.add(getLabelEspece());
		boxEspece.add(getEspeceCB());
		panelChamps.add(boxEspece);
		
		gbc.gridx = 1;
		Box boxRace = Box.createHorizontalBox();
		boxRace.add(getLabelRace());
		boxRace.add(getRaceCB());
		panelChamps.add(boxRace);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		panelChamps.add(getLabelTatouage());
		
		gbc.gridx = 1;
		panelChamps.add(getTextTatouage());
		
		getContentPane().add(panelChamps);
		
		getContentPane().add(getPanelButtons());

	}


	public JTextField getTextNomAnimal() {
		if (textNomAnimal == null) {
			textNomAnimal = new JTextField();
		}
		return textNomAnimal;
	}

	public JTextField getTextCouleur() {
		if (textCouleur == null) {
			textCouleur = new JTextField();
		}
		return textCouleur;
	}

	public JTextField getTextTatouage() {
		if (textTatouage == null) {
			textTatouage = new JTextField();
		}
		return textTatouage;
	}

	public JLabel getLabelClient() {
		if (labelClient == null) {
			labelClient = new JLabel("Client");
		}
		return labelClient;
	}

	public JLabel getLabelCode() {
		if (labelCode == null) {
			labelCode = new JLabel("Code");
		}
		return labelCode;
	}

	public JLabel getCodeAnimal() {
		if (codeAnimal == null) {
			codeAnimal = new JLabel();
		}
		return codeAnimal;
	}

	public JLabel getNomClient() {
		if (nomClient == null) {
			nomClient = new JLabel();
		}
		return nomClient;
	}
	
	public JLabel getLabelNomAnimal()
	{
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
			sexeCB = new JComboBox<String>(new String[] { "Femelle", "Mâle", "Hermaphrodite"});
		}
		return sexeCB;
	}

	public JComboBox<String> getEspeceCB() {
		if (especeCB == null) {
			especeCB = new JComboBox<String>();
		}
		return especeCB;
	}

	public JComboBox<String> getRaceCB() {
		if (raceCB == null) {
			raceCB = new JComboBox<String>();
		}
		return raceCB;
	}

	public JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
		}
		return btnValider;
	}

	public JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
		}
		return btnAnnuler;
	}
	
	public JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.add(getBtnValider());
			panelButtons.add(getBtnAnnuler());
		}
		return panelButtons;
	}

}
