package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EcranAjoutPersonnel extends JFrame {

	private JTextField TextNom;
	private JTextField TextPrenom;
	private JTextField TextPassword;

	public EcranAjoutPersonnel() {
		setSize(600, 300);
		setLocationRelativeTo(null);
		setTitle("Création d'un employé");
		getContentPane().setLayout(null);

		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setBounds(25, 80, 46, 14);
		getContentPane().add(lblNom);

		JLabel lblPrenom = new JLabel("Prénom:");
		lblPrenom.setBounds(25, 121, 67, 14);
		getContentPane().add(lblPrenom);

		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(25, 163, 84, 14);
		getContentPane().add(lblMotDePasse);

		TextNom = new JTextField();
		TextNom.setBounds(119, 77, 111, 20);
		getContentPane().add(TextNom);
		TextNom.setColumns(10);

		TextPrenom = new JTextField();
		TextPrenom.setBounds(119, 118, 111, 20);
		getContentPane().add(TextPrenom);
		TextPrenom.setColumns(10);

		TextPassword = new JTextField();
		TextPassword.setBounds(119, 163, 111, 20);
		getContentPane().add(TextPassword);
		TextPassword.setColumns(10);

		JButton btnEnregsitrer = new JButton("Enregistrer");
		btnEnregsitrer.setBounds(289, 227, 105, 23);
		getContentPane().add(btnEnregsitrer);

	}

}
