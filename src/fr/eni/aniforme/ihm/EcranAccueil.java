package fr.eni.aniforme.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JMenu;

public class EcranAccueil extends JFrame {

	private JMenuBar menuBar;
	private JMenu menuFichier, menuGestionRdv, menuAgenda, menuGestionPersonnel;
	private JMenuItem itemDeconnexion, itemFermer, itemPriseRdv, itemGestionClients;
	private PanelClients panelClients;
	private PanelGestionPersonnel panelGestionPersonnel;
	private PanelPriseRDV panelPriseRDV;
	private PanelAgenda panelAgenda;

	public EcranAccueil() {
		initialize();
	}

	private void initialize() {

		setTitle("Clinique AniForme");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

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
			menuAgenda.addMenuListener(new MenuListener() {

				@Override
				public void menuSelected(MenuEvent e) {
					getContentPane().removeAll();
					getContentPane().add(getPanelAgenda());
					getContentPane().revalidate();
				}

				@Override
				public void menuDeselected(MenuEvent e) {
				}

				@Override
				public void menuCanceled(MenuEvent e) {
				}
			});
			menuAgenda.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Hellllowwwww!!!");
				}
			});
		}
		return menuAgenda;
	}

	public JMenu getMenuGestionPersonnel() {
		if (menuGestionPersonnel == null) {
			menuGestionPersonnel = new JMenu("Gestion du personnel");
			menuGestionPersonnel.addMenuListener(new MenuListener() {

				@Override
				public void menuSelected(MenuEvent e) {
					getContentPane().removeAll();
					getContentPane().add(getPanelGestionPersonnel());
					revalidate();
					repaint();
				}

				@Override
				public void menuDeselected(MenuEvent e) {
				}

				@Override
				public void menuCanceled(MenuEvent e) {
				}
			});
		}
		return menuGestionPersonnel;
	}

	public JMenuItem getItemDeconnexion() {
		if (itemDeconnexion == null) {
			itemDeconnexion = new JMenuItem("Déconnexion");
			itemDeconnexion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Bye bye");

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
				}
			});
		}
		return itemGestionClients;
	}

	public PanelClients getPanelClients() {
		if (panelClients == null) {
			panelClients = new PanelClients();
			this.setTitle("Gestion des clients");
		}
		return panelClients;
	}

	public PanelAgenda getPanelAgenda() {
		if (panelAgenda == null) {
			panelAgenda = new PanelAgenda();
			this.setTitle("Agenda");
		}
		return panelAgenda;
	}

	public PanelPriseRDV getPanelPriseRDV() {
		if (panelPriseRDV == null) {
			panelPriseRDV = new PanelPriseRDV();
			this.setTitle("Prise de RDV");
		}
		return panelPriseRDV;
	}

	public PanelGestionPersonnel getPanelGestionPersonnel() {
		if (panelGestionPersonnel == null) {
			panelGestionPersonnel = new PanelGestionPersonnel();
			this.setTitle("Gestion du personnel");
		}
		return panelGestionPersonnel;
	}

}
