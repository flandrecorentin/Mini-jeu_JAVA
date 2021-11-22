import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Barre extends Brique{
   
    //Constructeurs
    public Barre(int x, int y, int lo, int ha){
        super(x, y,lo,ha);
    }
    
    //Affichage
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    //Déplacement l'algo fonctionne mais l'affichage se met pas à jour
    public void deplaceX(int touche){
        
        if(touche == 37 && super.debutx > 0){ 
            super.debutx = super.debutx - 30;
        }
        if(touche == 39 && super.debutx < 480){
            super.debutx = super.debutx + 30;
        }
    }
    
    //Accesseurs 
    public int getx(){ 
        return super.getx();
	}
    public int gety(){ 
		return super.gety();
	}
    public int getlo(){
        return super.getlo();
    }
    
    public int getha(){
        return super.getha();
    }
    
    public void setx(int x){
		super.setx(x);
	}
	public void sety(int y){
		super.sety(y);
	}	
}

