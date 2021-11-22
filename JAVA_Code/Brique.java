import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.*;

public class Brique extends JPanel{
	
    //Attributs
    int hauteur;
    int longueur;
    int debutx;
    int debuty;
    
    //Constructeur
    public Brique (int x, int y, int lo, int ha){
        super();
		debutx = x;
        debuty = y;
		longueur = lo;
        hauteur = ha;
    }

    //Affichage
    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(debutx, debuty, longueur, hauteur);
	}
    
    //Accesseurs 
    public int getx(){ 
		return debutx;
	}
    public int gety(){ 
		return debuty;
	}
    
    public int getlo(){
        return longueur;
    }
    
    public int getha(){
        return hauteur;
    }
    
    public void setx(int x){
		debutx=x;
	}
	public void sety(int y){
		debuty=y;
	}	

}
