package fr.eni.aniforme.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fr.eni.aniforme.bll.BLLException;
import fr.eni.aniforme.bll.PersonnelManager;
import fr.eni.aniforme.bo.Personnel;
import fr.eni.aniforme.ihm.DialogConnexion.ConnexionListener;

import javax.swing.JMenu;

public class EcranAccueil extends JFrame implements ConnexionListener {

	private JMenuBar menuBar;
	private JMenu menuFichier, menuGestionRdv, menuAgenda, menuGestionPersonnel;
	private JMenuItem itemDeconnexion, itemFermer, itemPriseRdv, itemGestionClients, itemAgenda, itemGestionPersonnel;
	private PanelClients panelClients;
	private PanelGestionPersonnel panelGestionPersonnel;
	private PanelPriseRDV panelPriseRDV;
	private PanelAgenda panelAgenda;
	private DialogConnexion dialogConnexion;

	PersonnelManager personnelManager = PersonnelManager.getInstance();

	public EcranAccueil() {
		initialize();
	}

	private void initialize() {

		setTitle("Clinique AniForme");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				openDialogConnexion();
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});

		getMenuFichier().add(getItemDeconnexion());
		getMenuFichier().add(getItemFermer());
		getMenubar().add(getMenuFichier());

		getMenuGestionRdv().add(getItemPriseRdv());
		getMenuGestionRdv().add(getItemGestionClients());

		getMenubar().add(getMenuGestionRdv());

		getMenubar().add(getMenuAgenda());
		getMenubar().add(getMenuGestionPersonnel());

		setJMenuBar(getMenubar());

	}

	public JMenuBar getMenubar() {

		if (menuBar == null) {
			menuBar = new JMenuBar();
		}
		return menuBar;
	}

	public JMenu getMenuFichier() {

		if (menuFichier == null) {
			menuFichier = new JMenu("Fichier");
		}
		return menuFichier;
	}

	public JMenu getMenuGestionRdv() {
		if (menuGestionRdv == null) {
			menuGestionRdv = new JMenu("Gestion des RDV");
		}
		return menuGestionRdv;
	}

	public JMenu getMenuAgenda() {
		if (menuAgenda == null) {
			menuAgenda = new JMenu("Agenda");
			menuAgenda.add(getItemAgenda());
		}
		return menuAgenda;
	}

	public JMenu getMenuGestionPersonnel() {
		if (menuGestionPersonnel == null) {
			menuGestionPersonnel = new JMenu("Gestion du personnel");
			menuGestionPersonnel.add(getItemGestionPersonnel());
		}
		return menuGestionPersonnel;
	}

	public JMenuItem getItemDeconnexion() {
		if (itemDeconnexion == null) {
			itemDeconnexion = new JMenuItem("Déconnexion");
			itemDeconnexion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					getContentPane().revalidate();
					repaint();
					getDialogConnexion().setVisible(true);

				}
			});
		}
		return itemDeconnexion;
	}

	public JMenuItem getItemFermer() {
		if (itemFermer == null) {
			itemFermer = new JMenuItem("Fermer");
			itemFermer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return itemFermer;
	}

	public JMenuItem getItemPriseRdv() {
		if (itemPriseRdv == null) {
			itemPriseRdv = new JMenuItem("Prise de RDV");
			itemPriseRdv.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					getContentPane().add(getPanelPriseRDV());
					getContentPane().revalidate();
					repaint();
				}
			});
		}
		return itemPriseRdv;
	}

	public JMenuItem getItemGestionClients() {
		if (itemGestionClients == null) {
			itemGestionClients = new JMenuItem("Gestion des clients");
			itemGestionClients.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					getContentPane().removeAll();
					getContentPane().add(getPanelClients());
					getContentPane().revalidate();
					repaint();
				}
			});
		}
		return itemGestionClients;
	}

	public JMenuItem getItemAgenda() {
		if (itemAgenda == null) {
			itemAgenda = new JMenuItem("Agenda");
			itemAgenda.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					List<Personnel> veterinaires;
					try {
						veterinaires = personnelManager.getVeterinaires();
						getContentPane().removeAll();
						getContentPane().add(getPanelAgenda(veterinaires.get(0).getNom()));
						getContentPane().revalidate();
						repaint();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
		return itemAgenda;
	}

	public JMenuItem getItemGestionPersonnel() {
		if (itemGestionPersonnel == null) {
			itemGestionPersonnel = new JMenuItem("Gestion du personnel");
			itemGestionPersonnel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getContentPane().removeAll();
					getContentPane().add(getPanelGestionPersonnel());
					revalidate();
					repaint();
				}
			});
		}
		return itemGestionPersonnel;
	}

	public PanelClients getPanelClients() {
		if (panelClients == null) {
			panelClients = new PanelClients(this);
			this.setTitle("Gestion des clients");
		}
		return panelClients;
	}

	public PanelAgenda getPanelAgenda(String nomVeto) {
		if (panelAgenda == null) {
			panelAgenda = new PanelAgenda(nomVeto);
			this.setTitle("Agenda");
		}
		return panelAgenda;
	}

	public PanelPriseRDV getPanelPriseRDV() {
		if (panelPriseRDV == null) {
			panelPriseRDV = new PanelPriseRDV(EcranAccueil.this);
			this.setTitle("Prise de RDV");
		}
		return panelPriseRDV;
	}

	public PanelGestionPersonnel getPanelGestionPersonnel() {
		if (panelGestionPersonnel == null) {
			panelGestionPersonnel = new PanelGestionPersonnel(EcranAccueil.this);
			this.setTitle("Gestion du personnel");
		}
		return panelGestionPersonnel;
	}
	
	public DialogConnexion getDialogConnexion()
	{
		if (dialogConnexion == null) {
			dialogConnexion = new DialogConnexion(EcranAccueil.this);
			dialogConnexion.setVisible(true);
		}
		return dialogConnexion;
	}

	private void openDialogConnexion() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				getDialogConnexion();
			}
		});

	}

	@Override
	public void checkConnexion(String nom, String motPasse) {

		try {
			Personnel employe = personnelManager.connexionEmploye(nom, motPasse);

			if (employe != null && dialogConnexion != null) {

				setEnabled(true);
				getDialogConnexion().getTextNom().setText("");
				getDialogConnexion().getTextMotPasse().setText("");
				getDialogConnexion().setVisible(false);
				

				if (employe.getRole().equalsIgnoreCase("vet")) {
					getContentPane().removeAll();
					getContentPane().add(getPanelAgenda(employe.getNom()));
					getContentPane().revalidate();
					repaint();

					getMenuGestionPersonnel().setEnabled(false);
					getMenuGestionRdv().setEnabled(false);
				} else if (employe.getRole().equalsIgnoreCase("sec")) {
					getContentPane().removeAll();
					getContentPane().add(getPanelPriseRDV());
					getContentPane().revalidate();
					repaint();

					getMenuGestionPersonnel().setEnabled(false);

				} else if (employe.getRole().equalsIgnoreCase("adm")) {
					getContentPane().removeAll();
					getContentPane().add(getPanelGestionPersonnel());
					getContentPane().revalidate();
					repaint();
				}
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deconnexion() {
		setEnabled(false);

	}

}
