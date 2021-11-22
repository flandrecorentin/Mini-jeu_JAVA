import java.awt.*;
import java.util.LinkedList; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Font;
//import java.awt.Graphics;
public class Demineur extends JFrame implements ActionListener,  MouseListener {
	protected JPanel demineur; 
	protected JPanel choixdifficulte; 
	protected JPanel plateau;
	protected JPanel touches;
	protected JPanel informations;
	protected int difficulte;
	protected JButton boutonfacile;
	protected JButton boutonintermediaire;
	protected JButton boutondifficile;
	protected JLabel[][] boutons;
	protected Timer clock;
	protected int secondes;
	protected JLabel temps_de_jeu; 
	protected JLabel bombe;
	protected JLabel victoiredefaite;
	protected mondetest maps;
	protected boolean debut;
	protected int l;
	protected int c;
	protected int b;
	protected int xdebut;
	protected int ydebut;
	protected int x;
	protected int y; 
	protected int taillecase;
	protected String imagebombe;
	protected String imagecase; 
	protected String imagedrapeau;
	protected String image0;
	protected String image1;
	protected String image2;
	protected String image3;
	protected String image4;
	protected String image5;
	protected String image6;
	protected JButton recommencer; 
	protected JButton arreter;
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public Demineur (){
		this.setTitle("Demineur");
		this.setSize(1280,720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		debut=false;
		demineur = new JPanel();
		demineur.setBounds(0,0,getWidth(),getHeight());
		Color couleur= new Color(100,100,100);
		demineur.setBackground(couleur);
		demineur.setLayout(null);
		//choixdifficulte
		difficulte=0;
		choixdifficulte= new JPanel();
		choixdifficulte.setBounds(10,10,800,655);
		couleur= new Color(200,200,200);
		choixdifficulte.setBackground(couleur); 
		choixdifficulte.setLayout(null);
		Font policejeu = new Font("Bookman Old Style", Font.BOLD, 35);
		boutonfacile = new JButton();
		boutonintermediaire = new JButton();
		boutondifficile = new JButton();
		JLabel labelniveau1 = new JLabel();
		labelniveau1.setBounds(100,0,200,150);
		labelniveau1.setFont(policejeu);
		labelniveau1.setText("Facile");
		boutonfacile.add(labelniveau1);
		JLabel labelniveau2 = new JLabel();
		labelniveau2.setBounds(100,0,200,150);
		labelniveau2.setFont(policejeu);
		labelniveau2.setText("Intermediaire");
		boutonintermediaire.add(labelniveau2);
		JLabel labelniveau3 = new JLabel();
		labelniveau3.setBounds(100,0,200,150);
		labelniveau3.setFont(policejeu);
		labelniveau3.setText("Difficile");
		boutondifficile.add(labelniveau3);
		boutonfacile.setBounds(250,50,300,150);
		couleur= new Color(129,206,15);
		boutonfacile.setBackground(couleur);
		boutonintermediaire.setBounds(250,250,300,150);
		couleur= new Color(218,201,15);
		boutonintermediaire.setBackground(couleur);
		boutondifficile.setBounds(250,450,300,150);
		couleur= new Color(197,32,21);
		boutondifficile.setBackground(couleur);
		choixdifficulte.add(boutonfacile);
		choixdifficulte.add(boutonintermediaire);
		choixdifficulte.add(boutondifficile);
		boutonfacile.addActionListener(this);
		boutonintermediaire.addActionListener(this);
		boutondifficile.addActionListener(this);
		
		demineur.add(choixdifficulte);
		//informations
		informations = new JPanel();
		informations.setBounds(10+800+10,10,450-8-10,300);
		couleur = new Color(200,200,200);
		informations.setBackground(couleur);
		informations.setLayout(null);
		clock = new Timer(100, this);
		temps_de_jeu= new JLabel();
		//temps_de_jeu.setLocationRelativeTo(null);
		temps_de_jeu.setLayout(null);
		temps_de_jeu.setBounds(10,10,432-10-10,135);
		Font police=new Font("Arial",Font.BOLD,30);
		temps_de_jeu.setFont(police);
		temps_de_jeu.setForeground(Color.BLACK);
		temps_de_jeu.setBackground(Color.RED);
		temps_de_jeu.setText("0 secondes");
		informations.add(temps_de_jeu);
		JLabel imagebombe = new JLabel(new ImageIcon("./Images/demineur/bombe.png"));
		imagebombe.setBounds(10,155,100,135);
		imagebombe.setLayout(null);
		informations.add(imagebombe);
		bombe = new JLabel();
		bombe.setBounds(10+100,155,100,135);
		bombe.setFont(police);
		bombe.setForeground(Color.BLACK);
		informations.add(bombe);
		victoiredefaite= new JLabel();
		victoiredefaite.setBounds(10+200,155,212,135);
		victoiredefaite.setFont(police);
		informations.add(victoiredefaite);
		demineur.add(informations);
		//touches/regles
		touches = new JPanel();
		touches.setBounds(10+800+10,10+300+10,442-10,345);
		couleur = new Color(200,200,200);
		touches.setBackground(couleur);
		touches.setLayout(null);
		demineur.add(touches); 	
		this.add(demineur);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icone = kit.getImage("./Images/demineur/icon-demineur.gif");
		setIconImage(icone);
		this.setVisible(true);
		
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void placerplateau(){
		//plateau
		bombe.setText(Integer.toString(this.b));
		System.out.println("PLACER PLATEAU");
		plateau= new JPanel();
		plateau.setBounds(10,10,800,655);
		Color couleur= new Color(200,200,200);
		plateau.setBackground(couleur); 
		plateau.setLayout(null);
		boutons = new JLabel [this.c][this.l];
		taillecase = minimum((int)800/c,(int)655/l);
		System.out.print("TAILLE CASE" + taillecase);
		couleur= new Color(100,100,100);
		for(int i=0; i<boutons.length; i++){
			for(int j=0; j< boutons[0].length; j++){
				boutons[i][j]= new JLabel(new ImageIcon(imagecase)); 
				boutons[i][j].setBounds(i*taillecase,j*taillecase,taillecase,taillecase);				
				plateau.add(boutons[i][j]);
			}
		}
		demineur.add(plateau);
		addMouseListener (this) ;
		this.setVisible(false);
		this.setVisible(true);    
	}
	public String affichagetemps(int s){
		int minutes = s/600;
		int secondes = (s/10)%60;
		int dixieme= s%10;
		if(minutes!=0)return minutes +" minutes "+secondes+","+dixieme+" secondes";
		else return secondes+","+dixieme+" secondes";
	}
	public void toutdecouvrir(){
		for(int i=0; i<l; i++){
			for(int j=0; j<c; j++){
				 if(maps.mapbombes[i][j]) boutons[j][i].setIcon(new ImageIcon(imagebombe));
				if(maps.mapvoisins[i][j]==0) boutons[j][i].setIcon(new ImageIcon(image0));
				if(maps.mapvoisins[i][j]==1) boutons[j][i].setIcon(new ImageIcon(image1));
				if(maps.mapvoisins[i][j]==2) boutons[j][i].setIcon(new ImageIcon(image2));
				if(maps.mapvoisins[i][j]==3) boutons[j][i].setIcon(new ImageIcon(image3));
				if(maps.mapvoisins[i][j]==4) boutons[j][i].setIcon(new ImageIcon(image4));
				if(maps.mapvoisins[i][j]==5) boutons[j][i].setIcon(new ImageIcon(image5));
				if(maps.mapvoisins[i][j]==6) boutons[j][i].setIcon(new ImageIcon(image6));
			}
		}
		plateau.setVisible(false);
		plateau.setVisible(true);
	}
	public void explorationautomatique(){
		maps.explorationautomatique();
		afficherimages();
	}
	public void afficherimages(){
		for(int i=0; i<c; i++){
			for(int j=0; j<l; j++){
				if(maps.mapdecouverte[j][i]){
					if(maps.mapvoisins[j][i]==0){
						boutons[i][j].setIcon(new ImageIcon(image0));
					}
					if(maps.mapvoisins[j][i]==1){
						boutons[i][j].setIcon(new ImageIcon(image1));
					}
					if(maps.mapvoisins[j][i]==2){
						boutons[i][j].setIcon(new ImageIcon(image2));
					}
					if(maps.mapvoisins[j][i]==3){
						boutons[i][j].setIcon(new ImageIcon(image3));
					}
					if(maps.mapvoisins[j][i]==4){
						boutons[i][j].setIcon(new ImageIcon(image4));
					}
					if(maps.mapvoisins[j][i]==5){
						boutons[i][j].setIcon(new ImageIcon(image5));
					}
					if(maps.mapvoisins[j][i]==6){
						boutons[i][j].setIcon(new ImageIcon(image6));
					}
				}
			}
		}
	}
	public boolean testgagner(){
		if(maps.nombre_drapeaux_posees!=0) { return false; }
		else{
			boolean win= true;
			for(int i=0; i<this.l; i++){
				for(int j=0; j<this.c; j++){
					if( maps.mapdecouverte[i][j] &&  maps.mapdrapeau[i][j]!=maps.mapbombes[i][j]) {win=false;}
					
				}
			}
			return win;
		}
	}
	// ACTION PERFORMED
	 public void actionPerformed (ActionEvent e){
		 
		repet: { //voir si le break fonctionne bien 
		if(e.getSource()==clock){
			secondes++;
			temps_de_jeu.setText(affichagetemps(secondes));
			if(secondes%10==0)System.out.println(secondes/10);
			break repet;
		}
		if(e.getSource()==boutonfacile){
			System.out.println("demineur facile");
			demineur.remove(choixdifficulte);
			l=9;
			c=9;
			b=10;
			imagecase ="./Images/demineur/casefacile.png";
			imagedrapeau="./Images/demineur/drapeaufacile.png";
			image0="./Images/demineur/casefacile0.png";
			image1="./Images/demineur/casefacile1.png";
			image2="./Images/demineur/casefacile2.png";
			image3="./Images/demineur/casefacile3.png";
			image4="./Images/demineur/casefacile4.png";
			image5="./Images/demineur/casefacile5.png";
			image6="./Images/demineur/casefacile6.png";
			imagebombe="./Images/demineur/bombefacile.png";
			placerplateau();
			
			break repet;
		}
		if(e.getSource()==boutonintermediaire){
			System.out.println("demineur intermediaire");
			demineur.remove(choixdifficulte);
			l=16;
			c=16;
			b=40;
			imagecase ="./Images/demineur/caseintermediaire.png";
			imagedrapeau="./Images/demineur/drapeauintermediaire.png";
			image0="./Images/demineur/caseintermediaire0.png";
			image1="./Images/demineur/caseintermediaire1.png";
			image2="./Images/demineur/caseintermediaire2.png";
			image3="./Images/demineur/caseintermediaire3.png";
			image4="./Images/demineur/caseintermediaire4.png";
			image5="./Images/demineur/caseintermediaire5.png";
			image6="./Images/demineur/caseintermediaire6.png";
			imagebombe="./Images/demineur/bombeintermediaire.png";
			placerplateau();
			break repet;
		}
		if(e.getSource()==boutondifficile){
			System.out.println("demineur difficile");
			demineur.remove(choixdifficulte);
			l=16;
			c=30;
			b=100;
			imagecase ="./Images/demineur/casedifficile.png";
			imagedrapeau="./Images/demineur/drapeaudifficile.png";
			image0="./Images/demineur/casedifficile0.png";
			image1="./Images/demineur/casedifficile1.png";
			image2="./Images/demineur/casedifficile2.png";
			image3="./Images/demineur/casedifficile3.png";
			image4="./Images/demineur/casedifficile4.png";
			image5="./Images/demineur/casedifficile5.png";
			image6="./Images/demineur/casedifficile6.png";
			imagebombe="./Images/demineur/bombedifficile.png";
			placerplateau();
			break repet;
		}
		if(e.getSource()==recommencer){
			Demineur demineur = new Demineur();
			System.out.println("RECOMMENCER");
			this.setVisible(false);
			this.dispose();
			this.pack();	
			break repet;
		}
		if(e.getSource()==arreter){
			System.out.println("ARRETER");
			this.setVisible(false);
			this.dispose();
			this.pack();	
			break repet;
		}
	}
	}
	public void mouseExited(MouseEvent m){}
	public void mouseEntered(MouseEvent m){}
	public void mouseReleased(MouseEvent m){}
	public void mousePressed(MouseEvent m){
		if ((m.getModifiers() & InputEvent.BUTTON3_MASK) != 0) System.out.print ("droit "+m.getX() + " " + m.getY()) ;
		if ((m.getModifiers() & InputEvent.BUTTON1_MASK) != 0) System.out.print ("gauche "+m.getX() + " " + m.getY()) ;
        x=((m.getX()-19)/taillecase);
        y=((m.getY()-48)/taillecase);
        System.out.println("X"+x+"Y"+y);
        if(x<=c && y<=l){
			if(debut){
				if(maps.mapbombes[y][x]&&(m.getModifiers() & InputEvent.BUTTON1_MASK) != 0){   // DEFAITE: click gauche sur bombe
					clock.stop();	
					victoiredefaite.setForeground(Color.red);
					victoiredefaite.setText("PERDU !");
					toutdecouvrir();		
					demanderrestart();			
				}
				if((m.getModifiers() & InputEvent.BUTTON3_MASK) != 0 && maps.mapdecouverte[y][x]==false){ // DRAPEAU : click droit sur case non explore
					
					if(!maps.mapdrapeau[y][x]) {
						boutons[x][y].setIcon(new ImageIcon(imagedrapeau));
						maps.mapdrapeau[y][x]=true;
						System.out.println("DRAPEAU");
						maps.nombre_drapeaux_posees--;
						bombe.setText(Integer.toString(maps.nombre_drapeaux_posees));
						if(testgagner()){
							clock.stop();
							victoiredefaite.setForeground(new Color(0,171,0));
							victoiredefaite.setText("VICTOIRE !");
							demanderrestart();
							toutdecouvrir();
						}

					}
					else{
						boutons[x][y].setIcon(new ImageIcon(imagecase));
						maps.mapdrapeau[y][x]=false;
						System.out.println("ENLEVER DRAPEAU");
						maps.nombre_drapeaux_posees++;
						bombe.setText(Integer.toString(maps.nombre_drapeaux_posees));
					}		
				}
				if(!maps.mapdecouverte[y][x]&&!maps.mapbombes[y][x] && (m.getModifiers() & InputEvent.BUTTON1_MASK) != 0){
					maps.mapdecouverte[y][x]=true;
					if(maps.mapdrapeau[y][x]) {
						System.out.println("ENTER IF");
						maps.mapdrapeau[y][x]=false;
						maps.nombre_drapeaux_posees++;
						bombe.setText(Integer.toString(maps.nombre_drapeaux_posees));
					}
					if(maps.mapvoisins[y][x]==0){
						boutons[x][y].setIcon(new ImageIcon(image0));
						explorationautomatique();
					}
					if(maps.mapvoisins[y][x]==1){
						boutons[x][y].setIcon(new ImageIcon(image1));
					}
					if(maps.mapvoisins[y][x]==2){
						boutons[x][y].setIcon(new ImageIcon(image2));
					}
					if(maps.mapvoisins[y][x]==3){
						boutons[x][y].setIcon(new ImageIcon(image3));
					}
					if(maps.mapvoisins[y][x]==4){
						boutons[x][y].setIcon(new ImageIcon(image4));
					}
					if(maps.mapvoisins[y][x]==5){
						boutons[x][y].setIcon(new ImageIcon(image5));
					}
					if(maps.mapvoisins[y][x]==6){
						boutons[x][y].setIcon(new ImageIcon(image6));
					}
					
					
				}
			}
			if(!debut){
				xdebut=x;
				ydebut=y;				
				maps= new mondetest(this.l,this.c,this.b, this.ydebut, this.xdebut);
				boutons[x][y].setIcon(new ImageIcon(image0));
				maps.mapdecouverte[y][x]=true;
				explorationautomatique();
				debut=true;
				clock.start(); 
				secondes=0;
			}
		}
	}
	public void mouseClicked(MouseEvent m){}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public void demanderrestart(){
		recommencer = new JButton();
		Font policejeu = new Font("Bookman Old Style", Font.BOLD, 18);
		JLabel labelrecommencer = new JLabel();
		labelrecommencer.setBounds(5,0,200,100);
		labelrecommencer.setFont(policejeu);
		labelrecommencer.setText("Recommencer ?");
		recommencer.add(labelrecommencer);
		recommencer.setBounds(116,48,200,100);
		Color couleur = new Color (171,121,121);
		recommencer.setBackground(couleur);
		recommencer.addActionListener(this);
		
		arreter = new JButton();
		JLabel labelarreter = new JLabel();
		labelarreter.setBounds(5,0,200,100);
		labelarreter.setFont(policejeu);
		labelarreter.setText("Arreter ?");
		arreter.add(labelarreter);
		arreter.setBounds(116,197,200,100);
		couleur = new Color (171,121,121);
		arreter.setBackground(couleur);
		arreter.addActionListener(this);
		
		touches.add(arreter);
		touches.add(recommencer);
		touches.setVisible(false);
		touches.setVisible(true);
	}
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	public int minimum(int a, int b){
		if(a<=b) return a;
		else return b;
	}
}
