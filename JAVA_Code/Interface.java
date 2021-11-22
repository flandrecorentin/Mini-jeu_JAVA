import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// Interface principale du jeu

public class Interface extends JPanel {

    //objets à afficher
    private Balle b;
    private Brique br;
    private Barre ba;
	
    private int vivant = 1;
    private int casse = 0;
    private int[][] tabbrique;

    
	//constructeur
	public Interface(Balle b, Brique br, Barre ba, int[][] tabbrique){
		this.b = b;
        this.br = br;
        this.ba = ba;
        this.tabbrique = tabbrique;
        
		this.setLayout(null);
		this.setBounds(0,0,580,600); 
        this.setBackground(Color.black); 
        
         
        
	}
    
	//affichage
	public void paintComponent(Graphics g){  
		super.paintComponent(g);
        b.paintComponent(g);
        ba.paintComponent(g);
        
        for(int i=0; i<tabbrique.length; i++){
            for(int j = 0; j < tabbrique[0].length; j++){
                if(tabbrique[i][j] == vivant){
                br.paintComponent(g);
                }
                br.setx(br.getx() +55);
            }
            br.setx(20);
            br.sety(br.gety()+ 25);
		}
        br.sety(20);
	}
}


