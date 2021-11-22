import java.awt.*;

public class listefont
{
	public static void main(String[] args)
	{
	     //Création d'une chaîne
	    String[] fontNames = GraphicsEnvironment
                         .getLocalGraphicsEnvironment()
                         .getAvailableFontFamilyNames() ;
			
		//afficher la liste
		for(int i=0; i<fontNames.length; i++) 
		   System.out.println(fontNames[i]) ;
	}
}
