public class mondetest {
	protected boolean [][] mapbombes;
	protected int [][] mapvoisins;
	protected boolean [][] mapdecouverte;
	protected int[][] mappaquetbombes;
	protected boolean[][] mapdrapeau;
	protected int nombre_drapeaux_posees;
	public mondetest(int lignes, int colonnes, int bombes, int xdeb, int ydeb){
		commencer(lignes,colonnes,bombes,xdeb, ydeb);  //generer map
	}
	
	//GENERE MAP & CREER MAP VOISINS & CREER MAP PAQUET & DECOUVRIR
	public boolean[][] generermap(int lignes, int colonnes, int bombes, int xdeb, int ydeb){
		boolean [][] m= new boolean [lignes][colonnes];
		int nbbombes=bombes;
		while(nbbombes!=0){
			int i=  (int) (Math.random()*lignes);
			int j =  (int) (Math.random()*colonnes);	
			if(m[i][j]==false&&!(i==xdeb&&j==ydeb)&&!(i-1==xdeb&&j-1==ydeb)&&!(i-1==xdeb&&j==ydeb)&&!(i-1==xdeb&&j+1==ydeb)&&!(i==xdeb&&j-1==ydeb)&&!(i==xdeb&&j+1==ydeb)&&!(i+1==xdeb&&j-1==ydeb)&&!(i+1==xdeb&&j==ydeb)&&!(i+1==xdeb&&j+1==ydeb)){
				m[i][j]=true;
				nbbombes=nbbombes-1;
			}
		}
		return m;
	}
	public int[][] creermapvoisins(boolean[][] mb){
		int[][] mi = new int[mb.length][mb[0].length];
		boolean[][]mbe=elargir(mb);
		for(int i=0;i<mi.length; i++){
			for(int j=0; j<mi[0].length ; j++){
				if(mb[i][j]==true) mi[i][j]=-1; // 9 = il y a bombe
				else{
					int nbvoisins=0;
					if(mbe[i][j]==true) nbvoisins++;
					if(mbe[i+1][j]==true)nbvoisins++;
					if(mbe[i+2][j]==true) nbvoisins++;
					if(mbe[i][j+1]==true) nbvoisins++;
					if(mbe[i+2][j+1]==true) nbvoisins++;
					if(mbe[i][j+2]==true) nbvoisins++;
					if(mbe[i+1][j+2]==true) nbvoisins++;
					if(mbe[i+2][j+2]==true) nbvoisins++;
					mi[i][j]=nbvoisins;
				}
			}
		}
		return mi;
	}
	/*public int[][] creermappaquet(int[][]mi){
		int[][] m = new int[mi.length][mi[0].length];
		int[][]me = new
		int indice=1;
		for(int i=0; i<m.length; i++){
			for(int j=0; j<m[0].length; j++){
				if(mi[i][j]==-1) m[i][j]=0;
				else{
					if(mie[i+1][j]==0)m[i][j]=indice
					if 
					if
					if
					else {
						indice++;
						m[i][j]=indice;
					}
				}
			}
		}
		
		return m;
	}*/
	public boolean[][] decouvrir(int l, int c,int x, int y){
		boolean [][] m = new boolean[l][c];
		m[x][y]=true;
		return m;
	}
	public void commencer(int l, int c, int b, int xdeb, int ydeb){
		mapbombes=generermap(l,c,b,xdeb,ydeb);
		mapvoisins=creermapvoisins(this.mapbombes);
		mapdecouverte=decouvrir(l,c,xdeb,ydeb);
		//mappaquetbombes=creermappaquet(this.mapvoisins);
		mapdrapeau = new boolean[l][c];
		nombre_drapeaux_posees=b;
	}
	// ELARGIR UNE MAP 
	public boolean[][] elargir(boolean [][] m){
		boolean[][] me= new boolean[m.length+2][m[0].length+2];
		for(int i=1; i<me.length-1; i++){
			for(int j=1; j<me[0].length-1;j++){
				me[i][j]=m[i-1][j-1];
			}
		}
		return  me;
	}
	public int[][] elargirint(int [][] m){
		int [][] me= new int [m.length+2][m[0].length+2];
		for(int i=1; i<me.length-1; i++){
			for(int j=1; j<me[0].length-1;j++){
				me[i][j]=m[i-1][j-1];
			}
		}
		for(int i=0; i<me.length; i++){
			me[i][0]=1;		
		}
		for(int i=0; i<me.length; i++){
			me[i][me[0].length-1]=1;		
		}
		for(int i=0; i<me[0].length; i++){
			me[0][i]=1;		
		}
		for(int i=0; i<me[0].length; i++){
			me[me.length-1][i]=1;		
		}
		return  me;
	}
	public boolean[][] retrecir(boolean[][]m){
		boolean[][] mr= new boolean [m.length-2][m[0].length-2];
		for(int i=1; i<m.length-1 ; i++){
			for(int j=1; j<m[0].length-1;j++){
				mr[i-1][j-1]=m[i][j];
			}
		}
		return mr;
	}
	//EXPLORATION AUTOMATIQUE
	public void explorationautomatique(){
		boolean continuer=true;
		boolean[][] m = elargir(mapdecouverte);
		int[][] v = elargirint(mapvoisins);
		while(continuer){
			continuer=false;
			for(int i=1; i<m.length-1;i++){
				for(int j=1; j<m[0].length-1; j++){
					if(m[i][j]&&v[i][j]==0){
						if(m[i-1][j]==false && v[i-1][j]==0){
							m[i-1][j]=true;
							continuer=true;
						}
						if(m[i-1][j-1]==false && v[i-1][j-1]==0){
							m[i-1][j-1]=true;
							continuer=true;
						}
						if(m[i-1][j+1]==false && v[i-1][j+1]==0){
							m[i-1][j+1]=true;
							continuer=true;
						}
						if(m[i+1][j-1]==false && v[i+1][j-1]==0){
							m[i+1][j-1]=true;
							continuer=true;
						}
						if(m[i+1][j+1]==false && v[i+1][j+1]==0){
							m[i+1][j+1]=true;
							continuer=true;
						}
						if(m[i+1][j]==false && v[i+1][j]==0){
							m[i+1][j]=true;
							continuer=true;
						}
						if(m[i][j+1]==false && v[i][j+1]==0){
							m[i][j+1]=true;
							continuer=true;
						}
						if(m[i][j-1]==false && v[i][j-1]==0){
							m[i][j-1]=true;
							continuer=true;
						}
					}
				}
			}
		}
		boolean [][] mavant =  new boolean[m.length][m[0].length];
		for(int i=0; i<mavant.length; i++){
			for(int j=0; j<mavant[0].length; j++){
				mavant[i][j]=m[i][j];
			}
		}
		for(int i=1; i<m.length-1;i++){
				for(int j=1; j<m[0].length-1; j++){
					if(mavant[i][j]&&v[i][j]==0){
						m[i][j-1]=true;
						m[i][j+1]=true;
						m[i+1][j]=true;
						m[i+1][j+1]=true;
						m[i+1][j-1]=true;
						m[i-1][j+1]=true;
						m[i-1][j]=true;
						m[i-1][j-1]=true;
				}
			}
		}
		mapdecouverte=retrecir(m);
	}
	//AFFICHER MAP (pour des verifications)
	public void affichermapint(int[][]m){
		for(int i=0; i<m.length;i++){
			for(int j=0; j<m[0].length; j++){
				System.out.print(m[i][j]);
				if(m[i][j]!=-1) System.out.print(" ");
			}
			System.out.println("");
		}
	}
	public void affichermapboolean(boolean[][]m){
		for(int i=0; i<m.length;i++){
			for(int j=0; j<m[0].length; j++){
				System.out.print(m[i][j]);
				if(m[i][j]==true) System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
