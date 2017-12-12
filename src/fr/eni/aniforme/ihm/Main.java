package fr.eni.aniforme.ihm;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				EcranAccueil ecranAccueil = new EcranAccueil();
//				ecranAccueil.addWindowListener(new WindowListener() {
//
//					@Override
//					public void windowOpened(WindowEvent arg0) {
//						JDialog dialogConnexion = new DialogConnexion();
//						dialogConnexion.setVisible(true);
//
//					}
//
//					@Override
//					public void windowIconified(WindowEvent arg0) {
//
//					}
//
//					@Override
//					public void windowDeiconified(WindowEvent arg0) {
//
//					}
//
//					@Override
//					public void windowDeactivated(WindowEvent arg0) {
//
//					}
//
//					@Override
//					public void windowClosing(WindowEvent arg0) {
//
//					}
//
//					@Override
//					public void windowClosed(WindowEvent arg0) {
//
//					}
//
//					@Override
//					public void windowActivated(WindowEvent arg0) {
//					}
//				});
				ecranAccueil.setVisible(true);
			}
		});

	}

}
