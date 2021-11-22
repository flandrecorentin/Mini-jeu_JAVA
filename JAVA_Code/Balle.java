import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Balle extends JPanel {
	
    //Attributs
    private int centrex, centrey, rayon, x,y ;
    
    //Constructeur
	public Balle( int cx, int cy, int r) {
        
        centrex = cx;
        centrey = cy;
        rayon = r;
                    
        //7 et 7 sont des bonnes vitesses            
        
        x = 7  + this.random();
        y = 9 + (this.random());
        System.out.println("Vitesse x :" +x+" y :"+y);
    	
	}
    
    // Accesseurs 
    public int getx(){ 
		return centrex;
	}
    public int gety(){ 
		return centrey;
	}
    
    public int getr(){ 
		return rayon;
	}
    
    public void setx(int x){
		this.centrex=x;
	}
	public void sety(int y){
		this.centrey=y;
	}	
    public void setr(int r){
		this.rayon=r;
	}	
    
    //Affichage
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(236,236,236));
        g.fillOval(centrex-rayon, centrey - rayon ,2*rayon,2*rayon);
    }
    
    //Déplacement de la balle
    public void seDeplacer(){
		centrex = centrex + x;
        centrey = centrey + y;
	}	
    
    
    public  void collisionMur(int width, int height) {
    //Si la balle touche un mur, elle rebondit
        if(centrey < 10){
            y = y*-1;
        }
        if(centrex + rayon> 580 || centrex < 10){
            x = x*-1;
        }
    
    }
    
    //Tirage de la vitesse de début au sort
    public int random(){
    int x = (int)(Math.random()*5);
        
    return x;
    
    }
    
    //Si la balle touche une brique, la brique disparait
    public void collisionBrique(Brique brique, int[][] tabbrique){
        for(int i = 0; i<tabbrique.length; i++){
            for(int j = 0;j<tabbrique[0].length; j++){
                if(tabbrique[i][j]==1){
                    if( centrex  > brique.getx() && centrex < (brique.getx()+ brique.getlo()) ) {
                        if(centrey > brique.gety() && centrey < (brique.gety() + brique.getha()) ){
                            tabbrique[i][j]=0;
                            y = y * -1 ;
                        }
                    }
                }
                brique.setx(brique.getx() +55);
            }
            brique.setx(20);
            brique.sety(brique.gety()+ 25);
        }
        brique.sety(20);
    } 
    
    //Si la balle touche la barre, elle rebondit
    public void collisionBarre(Barre barre){
        if( centrex > barre.getx() && centrex < barre.getx()+ barre.getlo() ) {
            if( (centrey + rayon) > barre.gety() && (centrey + rayon) < barre.gety() + barre.getha() ){
                y = y * -1 ;
            }
        }
    } 
}




