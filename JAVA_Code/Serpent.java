import java.util.LinkedList; 
import java.awt.*;
import java.util.LinkedList; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
//1 : up
//2 : right 
//3 : down
//4 : left


public class Serpent  {
	protected LinkedList<Point> serp;
	public Serpent(){
		serp = new LinkedList<Point>();
		Point tete = new Point(1,2,3,0);
		Point A = new Point(1,1,3,1);
		Point B = new Point(1,0,3,2);
		serp.add(B);
		serp.add(A);
		serp.add(tete);
	}
	public String toString(){
		String s = " Le serpent est (taille "+ serp.size()+") : ";
		for(int i=0; i<serp.size(); i++){
			s = s+ " ("+serp.get(i).x+";"+serp.get(i).y+") ";
		}
		return s;
	}
}
