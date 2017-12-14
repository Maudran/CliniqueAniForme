package fr.eni.aniforme.ihm;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Personnel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EcranAjoutPersonnel extends JFrame {

	private JTextField txtNom, txtRole;
	private JPasswordField txtPassword;
	private JLabel lblNom, lblRole, lblPassword;
	private JButton btnEnregistrer;

	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public interface EmployeListener {
		void refreshTable();
	}

	private EmployeListener listener;

	public EcranAjoutPersonnel(EmployeListener listener) {

		this.listener = listener;

		setSize(300, 300);
		setLocationRelativeTo(null);
		setTitle("Création d'un employé");
		getContentPane().setLayout(null);

		getLblNom().setBounds(25, 60, 46, 14);
		getContentPane().add(getLblNom());

		getLblRole().setBounds(25, 101, 67, 14);
		getContentPane().add(getLblRole());

		getLblPassword().setBounds(25, 143, 84, 14);
		getContentPane().add(getLblPassword());

		getTxtNom().setBounds(119, 57, 111, 20);
		getContentPane().add(getTxtNom());
		getTxtNom().setColumns(10);

		getTxtRole().setBounds(119, 98, 111, 20);
		getContentPane().add(getTxtRole());
		getTxtRole().setColumns(10);

		getTxtPassword().setBounds(119, 143, 111, 20);
		getContentPane().add(getTxtPassword());
		getTxtPassword().setColumns(10);

		getBtnEnregistrer().setBounds(95, 207, 105, 23);

		getContentPane().add(getBtnEnregistrer());

	}

	public JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
		}
		return txtNom;
	}

	public JTextField getTxtRole() {
		if (txtRole == null) {
			txtRole = new JTextField();
		}
		return txtRole;
	}

	public JPasswordField getTxtPassword() {
		if (txtPassword == null) {
			txtPassword = new JPasswordField();
		}
		return txtPassword;
	}

	public JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom : ");
		}
		return lblNom;
	}

	public JLabel getLblRole() {
		if (lblRole == null) {
			lblRole = new JLabel("Role : ");
		}
		return lblRole;
	}

	public JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Mot de passe : ");
		}
		return lblPassword;
	}

	public JButton getBtnEnregistrer() {
		if (btnEnregistrer == null) {
			btnEnregistrer = new JButton(new ImageIcon("ic_save_black_24dp/web/ic_save_black_24dp_1x.png"));
			btnEnregistrer.setContentAreaFilled(false);
			btnEnregistrer.setToolTipText("Enregistrer");
			btnEnregistrer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (!checkEmploye(getEmployeFromChamps())) {
						try {
							personnelManager.insertPersonnel(getEmployeFromChamps());
							listener.refreshTable();
							setVisible(false);
						} catch (BLLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(EcranAjoutPersonnel.this,
								"Le nom est obligatoire et le mot de passe ne doit pas dépasser 10 caractères"
										+ System.lineSeparator() + "Le role doit être sec, adm ou vet");
					}

				}
			});
		}
		return btnEnregistrer;
	}

	private boolean checkEmploye(Personnel employe) {
		if (isNullOrEmpty(employe.getNom()) || isNullOrEmpty(employe.getRole()) || isNullOrEmpty(employe.getMotPasse())
				|| employe.getMotPasse().length() > 10 || !employe.getRole().equalsIgnoreCase("sec")
				|| !employe.getRole().equalsIgnoreCase("adm") || !employe.getRole().equalsIgnoreCase("vet")) {
			return false;
		}
		return true;
	}

	public Personnel getEmployeFromChamps() {
		Personnel employe = new Personnel();

		String password = new String(getTxtPassword().getPassword());
		employe.setNom(getTxtNom().getText());
		employe.setRole(getTxtRole().getText());
		employe.setMotPasse(password);

		return employe;
	}

	private boolean isNullOrEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
}
