package fr.eni.aniforme.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import fr.eni.aniforme.bo.Animal;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class EcranDossierMedical extends JFrame {

	private JPanel panelBouton;
	private JPanel panelInfoClient;
	private JPanel panelInfoAnimal;
	private JTextArea panelZoneDeSaisie;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JTextField textClient;
	private JLabel lblCodeAnimal, lblNomAnimal,lblCouleurAnimal, lblSexeAnimal,lblEspeceAnimal, lblRaceAnimal, lblTatouageAnimal;

	

	public EcranDossierMedical(Animal animal) {
		setSize(800, 500);
		setLocationRelativeTo(null);
		setTitle("Dossier Médical");
		setLayout(new BorderLayout());

		add(getPanelBouton(), BorderLayout.NORTH);
		add(getPanelInfoClient(), BorderLayout.WEST);
		add(getPanelInfoAnimal(), BorderLayout.CENTER);
		add(getPanelZoneDeSaisie(), BorderLayout.EAST);
		panelZoneDeSaisie.setBorder(BorderFactory.createTitledBorder("Antécédents/ Consultations :"));
		panelInfoClient.setBorder(BorderFactory.createTitledBorder("Client :"));
		panelInfoAnimal.setBorder(BorderFactory.createTitledBorder("Animal :"));
		// panelInfoAnimal.setLayout(new BoxLayout(panelInfoAnimal,
		// BoxLayout.PAGE_AXIS));

	}

	public JPanel getPanelBouton() {
		if (panelBouton == null) {
			panelBouton = new JPanel();
			panelBouton.add(getBtnValider());
			panelBouton.add(getBtnAnnuler());
		}
		return panelBouton;
	}

	public JPanel getPanelInfoClient() {
		if (panelInfoClient == null) {
			panelInfoClient = new JPanel();
			panelInfoClient.add(getTextClient());

		}
		return panelInfoClient;
	}

	public JPanel getPanelInfoAnimal() {
		if (panelInfoAnimal == null) {
			panelInfoAnimal = new JPanel();
			panelInfoAnimal.add(getLblCodeAnimal());
			panelInfoAnimal.add(getLblNomAnimal());
			panelInfoAnimal.add(getLblCouleurAnimal());
			panelInfoAnimal.add(getLblSexeAnimal());
			panelInfoAnimal.add(getLblEspeceAnimal());
			panelInfoAnimal.add(getLblRaceAnimal());
			panelInfoAnimal.add(getLblTatouageAnimal());
							
			
		}
		return panelInfoAnimal;
	}

	
	public JTextArea getPanelZoneDeSaisie() {

		if (panelZoneDeSaisie == null) {
			panelZoneDeSaisie = new JTextArea(2, 35);

		}
		return panelZoneDeSaisie;
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

	public JTextField getTextClient() {
		if (textClient == null) {
			textClient = new JTextField(20);
		}
		return textClient;
	}
	public JLabel getLblCodeAnimal() {
		if (lblCodeAnimal == null) {
			lblCodeAnimal = new JLabel();
		}
		return lblCodeAnimal;
	}

	public JLabel getLblNomAnimal() {
		if (lblNomAnimal == null) {
			lblNomAnimal = new JLabel();
		}
		return lblNomAnimal;
	}

	public JLabel getLblCouleurAnimal() {
		if (lblCouleurAnimal == null) {
			lblCouleurAnimal = new JLabel();
		}
		return lblCouleurAnimal;
	}

	public JLabel getLblSexeAnimal() {
		if (lblSexeAnimal == null) {
			lblSexeAnimal = new JLabel();
		}
		return lblSexeAnimal;
	}

	public JLabel getLblEspeceAnimal() {
		if (lblEspeceAnimal == null) {
			lblEspeceAnimal = new JLabel();
		}
		return lblEspeceAnimal;
	}

	public JLabel getLblRaceAnimal() {
		if (lblRaceAnimal == null) {
			lblRaceAnimal = new JLabel();
		}
		return lblRaceAnimal;
	}

	public JLabel getLblTatouageAnimal() {
		if (lblTatouageAnimal == null) {
			lblTatouageAnimal = new JLabel();
		}
		return lblTatouageAnimal;
	}

	

}
