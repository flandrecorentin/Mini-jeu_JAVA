public class Monde extends Demineur{
	protected boolean [][] mapbombes;
	protected int [][] mapvoisins;
	protected boolean [][] mapdecouverte;
	protected int[][] mappaquetbombes;
	public Monde(int lignes, int colonnes, int bombes){
		mapbombes = generermap(lignes,colonnes,bombes);  //generer map
		affichermapboolean(mapbombes);
	}
	
	//GENERE MAP
	public boolean[][] generermap(int lignes, int colonnes, int bombes){
		boolean [][] m= new boolean [lignes][colonnes];
		int nbbombes=bombes;
		while(nbbombes!=0){
			int i= (int) Math.random()*lignes;
			int j = (int) Math.random()*colonnes;		
			if(m[i][j]==false){
				m[i][j]=true;
				nbbombes=nbbombes-1;
			}
		}
		return m;
	}
	
	//AFFICHER MAP (pour des verifications)
	public void affichermapint(int[][]m){
		for(int i=0; i<m.length;i++){
			for(int j=0; j<m[0].length; j++){
				System.out.print(m[i][j]);
			}
			System.out.println("");
		}
	}
	public void affichermapboolean(boolean[][]m){
		for(int i=0; i<m.length;i++){
			for(int j=0; j<m[0].length; j++){
				System.out.print(m[i][j]);
			}
			System.out.println("");
		}
	}
}
