import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Bouton extends JFrame{
	
	public JButton bouton;
	
	//constructeur du bouton
	public Bouton(int y, int z) {
		bouton = new JButton();
		bouton.setOpaque(false);
		bouton.setContentAreaFilled(false);
		bouton.setBounds(0+200*y, 0+200*z, 200, 200); 
        //Les coordonnées permettent de créer une grille de bouton
	}
    
    //Rempli un bouton avec une image rond
	public void setBouton(){
		bouton.setIcon(new ImageIcon("./Images/morpion/RondNoir.png"));
	}
    
    //Rempli un bouton avec une image croix
	public void setBoutonOrdi(){
		bouton.setIcon(new ImageIcon("./Images/morpion/CroixRouge.jpg"));
	}
    
    //Vide la case de l'image
	public void reSetBouton(){
		bouton.setIcon(null);
	}
}

