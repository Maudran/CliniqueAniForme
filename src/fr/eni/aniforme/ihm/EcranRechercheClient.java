package fr.eni.aniforme.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EcranRechercheClient extends JFrame {
	
	private JTextField txtRecherche;
	private JButton btnRechercher;
	private JScrollPane rechercheScrollPane;
	private JTable tableau;
	private TableRechercheModel model;
	private JPanel panelBarre;
	
	
	private ClientListener listener;
	
	public EcranRechercheClient(ClientListener listener) {
		
		this.listener = listener;
		
		setTitle("Recherche d'un client");
		setSize(500, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(getPanelBarre(), BorderLayout.NORTH);
		add(getRechercheScrollPane(), BorderLayout.CENTER);
		
	}
	
	public JTextField getTxtRecherche() {
		if (txtRecherche == null) {
			txtRecherche = new JTextField(40);
		}
		return txtRecherche;
	}

	public JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton(new ImageIcon("ic_search_black_24dp/web/ic_search_black_24dp_1x.png"));
			btnRechercher.setContentAreaFilled(false);
			btnRechercher.setToolTipText("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!getTxtRecherche().getText().isEmpty()) {
						getModel().updateData(getTxtRecherche().getText());
					}
				}
			});
		}
		return btnRechercher;
	}

	public JScrollPane getRechercheScrollPane() {
		if (rechercheScrollPane == null) {
			rechercheScrollPane = new JScrollPane(getTableau());
		}
		return rechercheScrollPane;
	}

	public TableRechercheModel getModel() {
		if (model == null) {
			model = new TableRechercheModel();
		}
		return model;
	}

	//je dirais plutôt MouseListener sur toute la JTable et dans le mouseClicked utiliser les méthodes rowAtPoint et columnAtPoint pour déterminer la cellule, puis ouvrir une JFrame
	
	public JTable getTableau() {
		if (tableau == null) {
			tableau = new JTable(getModel());
			tableau.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {	
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (getTableau().getSelectedRow() != -1) {
						listener.afficherClient(getModel().getValueAt(tableau.getSelectedRow()));
					}
					
					
				}
			});
		}
		return tableau;
	}

	public JPanel getPanelBarre() {
		if (panelBarre == null) {
			panelBarre = new JPanel();
			panelBarre.add(getTxtRecherche());
			panelBarre.add(getBtnRechercher());
		}
		return panelBarre;
	}
}
