import java.util.LinkedList; 
public class Point implements Comparable<Point> {
//1 : up
//2 : right 
//3 : down
//4 : left
	protected int direction;
	protected int ordre;
	protected int x;
	protected int y;
	public Point(int x1, int y1, int dir, int o){
		x=x1%12;
		y=y1%12;
		direction=dir;
		ordre=o;
	}
	public Point(int x1, int y1){
		x=x1;
		y=y1;
	}
	public Point (){
		x=(int) (Math.random()*12);
		y = (int) (Math.random()*12);
	}
	public boolean equals(Point A){
		if(this.x == A.x && this.y== A.y) return true;
		else return false; 
	}
	public Point randompoint(LinkedList<Point> suite){
		Point A= new Point((int) (Math.random()*12), (int) (Math.random()*12));
		boolean etat =true;
		while(etat){
			etat=false;
			for(Point B :suite){
				if(B==A) etat=true;
			}
		}
		//System.out.println("Random point"+A.x+" "+A.y);
		return A;
	}
	public String  toString(){
		return "Point: x= "+this.x+" y= "+this.y;
	}
	public int compareTo(Point B){
		if(this.ordre==B.ordre) return 0;
		else if (this.ordre<B.ordre) return -1;
		else  return 1;
	}
}
