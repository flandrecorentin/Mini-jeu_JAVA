import java.awt.*;
import java.util.LinkedList; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Font;
import java.util.*;   

public class Snake extends JFrame implements ActionListener, KeyListener{
	protected JPanel snake;
	protected JPanel jeu; 
	protected JPanel infos; 
	protected JLabel titre; 
	protected JButton boutonfacile;
	protected JButton boutonintermediaire;
	protected JButton boutondifficile;
	protected Timer clock;
	protected int intclock;
	protected JLabel labelchronometre;
	protected JLabel labeltaille;
	protected int delai; 
	protected Serpent serpent; 
	protected LinkedList<JLabel> image; 
	protected Point pomme;
	protected boolean etat; 
	protected JLabel labelpomme;
	protected JLabel labeltete;
	protected JButton arreter;
	protected JButton recommencer;
	protected JLabel labelclavier;
	protected JLabel labelclavier2;
	protected JLabel labelclavier3;
	protected JLabel labelfin;
	public Snake(){
		// image = new JLabel (new ImageIcon("./Images/snake/serpent.png"));
		this.setTitle("Snake");
		this.setLayout(null);
		this.setSize(1280,720+47);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		snake = new JPanel();
		snake.setLayout(null);
		snake.setBounds(0,0,1280,720);
		this.add(snake);
		
		infos = new JPanel();
		infos.setLayout(null);	
		infos.setBounds(720,0,560,720);	
		infos.setBackground(new Color(0,0,255));	
		snake.add(infos);
		
		JLabel fond_ecran_infos = new JLabel (new ImageIcon("./Images/snake/fond_infos.png"));
		fond_ecran_infos.setBounds(0,0,560,720);
		infos.add(fond_ecran_infos);
		
		jeu = new JPanel();
		jeu.setBounds(0,0,720,720);
		jeu.setLayout(null);
		//jeu.setBackground(new Color(255,0,0));	
		JLabel fond_ecran = new JLabel (new ImageIcon("./Images/snake/fond_ecran.png"));
		fond_ecran.setBounds(0,0,720,720);
		jeu.add(fond_ecran);
		snake.addKeyListener(this);
		snake.add(jeu);
		
		titre = new JLabel();
		titre.setLayout(null);
		titre.setBounds(80,0,560,200);
		titre.setFont(new Font("Bookman Old Style", Font.BOLD, 120));
		titre.setForeground( new Color(23,150,12));
		titre.setText("Snake");
		infos.add(titre,0);
		/*
		labelclavier = new JLabel();
		labelclavier.setLayout(null);
		labelclavier.setBounds(10,580,540,20);
		labelclavier.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		labelclavier.setForeground( new Color(100,100,100));
		labelclavier.setText("- Clicker sur le terminal puis revenir sur le JFrame");
		infos.add(labelclavier,0);

		labelclavier2 = new JLabel();
		labelclavier2.setLayout(null);
		labelclavier2.setBounds(10,600,540,20);
		labelclavier2.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		labelclavier2.setForeground( new Color(100,100,100));
		labelclavier2.setText("si le clavier n'est pas detecte");
		infos.add(labelclavier2,0);
		*/
		labelclavier3 = new JLabel();
		labelclavier3.setLayout(null);
		labelclavier3.setBounds(10,630,540,20);
		labelclavier3.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		labelclavier3.setForeground( new Color(100,100,100));
		labelclavier3.setText("- Utilisez les fleches directives pour se deplacer ");
		infos.add(labelclavier3,0);

		Font policejeu = new Font("Bookman Old Style", Font.BOLD, 22);
		boutonfacile = new JButton();
		boutonfacile.setLayout(null);
		boutonfacile.setBounds(150,200,260,100);
		JLabel labelfacile = new JLabel();
		labelfacile.setBounds(50,0,200,100);
		labelfacile.setFont(policejeu);
		labelfacile.setText("Vitesse lente");
		boutonfacile.add(labelfacile);
		infos.add(boutonfacile,0);
		boutonfacile.addActionListener(this);
		
		boutonintermediaire = new JButton();
		boutonintermediaire.setLayout(null);
		boutonintermediaire.setBounds(150,330,260,100);
		JLabel labelintermediaire = new JLabel();
		labelintermediaire.setBounds(30,0,200,100);
		labelintermediaire.setFont(policejeu);
		labelintermediaire.setText("Vitesse moyenne");
		boutonintermediaire.add(labelintermediaire);
		infos.add(boutonintermediaire,0);
		boutonintermediaire.addActionListener(this);
		
		boutondifficile = new JButton();
		boutondifficile.setLayout(null);
		boutondifficile.setBounds(150,460,260,100);
		JLabel labeldifficile = new JLabel();
		labeldifficile.setBounds(40,0,200,100);
		labeldifficile.setFont(policejeu);
		labeldifficile.setText("Vitesse rapide");
		boutondifficile.add(labeldifficile);
		infos.add(boutondifficile,0);
		boutondifficile.addActionListener(this);

		
		
		clock = new Timer(100, this);
		intclock=0;
		labelchronometre = new JLabel();
		labelchronometre.setForeground( new Color(100,100,100));
		labelchronometre.setBounds(65,200,560,100);
		labelchronometre.setFont( new Font("Bookman Old Style", Font.BOLD, 30));
		infos.add(labelchronometre,0);
		labeltaille = new JLabel();
		labeltaille.setForeground( new Color(100,100,100));
		labeltaille.setBounds(65,300,255,100);
		labeltaille.setFont( new Font("Bookman Old Style", Font.BOLD, 30));
		infos.add(labeltaille,0);
		labelchronometre.addKeyListener(this);
		labelfin = new JLabel();
		labelfin.setBounds(320,300,260,100);
		labelfin.setFont( new Font("Bookman Old Style", Font.BOLD, 60));
		labelfin.setForeground( new Color(144,0,0));
		labelfin.setText("FIN !");
		
		image = new LinkedList<JLabel>();	
		image.add (new JLabel (new ImageIcon("./Images/snake/serpent.png")));
		image.add( new JLabel (new ImageIcon("./Images/snake/serpent.png")));
		image.add( new JLabel (new ImageIcon("./Images/snake/serpent.png")));
		labelpomme = new JLabel (new ImageIcon("./Images/snake/pomme.png"));
		labeltete = new JLabel (new ImageIcon("./Images/snake_bas/tete.png"));
		jeu.add(labeltete,0);
		jeu.add(labelpomme);
		this.addKeyListener(this) ;
		//this.getContentPane().addKeyListener(this);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icone = kit.getImage("./Images/snake/icon-snake.jpg");
		setIconImage(icone);
		
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == clock){
			etat=true;
			intclock++;
			labelchronometre.setText("Chronometre \n\n "+intclock/10+","+intclock%10+" secondes");
			affichagetemps(intclock);
			if(intclock%delai==0){
				//System.out.println("deplacement/action");
				avancer();
				affichagejeu();
			}
		}
		if(e.getSource()==boutonfacile){
			System.out.println("facile");
			infos.remove(boutonfacile);
			infos.remove(boutonintermediaire);
			infos.remove(boutondifficile);
			infos.setVisible(false);
			infos.setVisible(true);
			creationserpent(3);
			clock.start();
			this.requestFocus();
			
		}
		if(e.getSource()==boutonintermediaire){
			System.out.println("intermediaire");
			infos.remove(boutonfacile);
			infos.remove(boutonintermediaire);
			infos.remove(boutondifficile);
			infos.setVisible(false);
			infos.setVisible(true);
			creationserpent(2);
			clock.start();
			this.requestFocus();
		}
		if(e.getSource()==boutondifficile){
			System.out.println("difficile");
			infos.remove(boutonfacile);
			infos.remove(boutonintermediaire);
			infos.remove(boutondifficile);
			infos.setVisible(false);
			infos.setVisible(true);
			creationserpent(1);
			clock.start();
			this.requestFocus();
			
		}
		if(e.getSource()==recommencer){
			Snake snake = new Snake();
			System.out.println("RECOMMENCER");
			this.setVisible(false);
			this.dispose();
			this.pack();	
		}
		if(e.getSource()==arreter){
			System.out.println("ARRETER");
			this.setVisible(false);
			this.dispose();
			this.pack();	
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		for( Point p: serpent.serp){ // DEBUT PARCOURS FOR EACH
			if(p.ordre==0&&etat){
				if(e.getKeyCode()== KeyEvent.VK_RIGHT){
					//System.out.println("RIGHT");
					if(p.direction==1 || p.direction==3){
						p.direction=2;
						affichagetete();
						etat=false;
					}
				}
				else if(e.getKeyCode()== KeyEvent.VK_DOWN){
					//System.out.println("DOWN");
					if(p.direction==2 || p.direction==4){
						p.direction=3;
						affichagetete();
						etat=false;
					}
				}
				else if(e.getKeyCode()== KeyEvent.VK_LEFT){
					//System.out.println("LEFT");
					if(p.direction==1 || p.direction==3){
						p.direction=4;
						affichagetete();
						etat=false;
					}
				}
				else if(e.getKeyCode()== 38){
					//System.out.println("UP");
					if(p.direction==2 || p.direction==4){
						p.direction=1;
						affichagetete();
						etat=false;
					}
				}
			}
		}// PARCOURS FOR EACH
	}
	public void keyTyped(KeyEvent e){}
	
	public void creationserpent(int t){
		delai=t;
		pomme = new Point();
		//System.out.println("POMMMMMMMMMMME"+pomme.x+""+pomme.y);
		serpent = new Serpent();
		labeltaille.setText("Taille: "+Integer.toString(serpent.serp.size()));
	}
	public void affichagetete(){
		//System.out.println(serpent);
		//System.out.println("TETE : "+serpent.serp.get(0));
		jeu.remove(labeltete);
		if(serpent.serp.get(0).direction==1) labeltete = new JLabel (new ImageIcon("./Images/snake/tete_haut.png"));
		else if(serpent.serp.get(0).direction==2)   labeltete = new JLabel (new ImageIcon("./Images/snake/tete_droite.png"));
		else if(serpent.serp.get(0).direction==3) labeltete = new JLabel (new ImageIcon("./Images/snake/tete_bas.png"));
		else if(serpent.serp.get(0).direction==4)   labeltete = new JLabel (new ImageIcon("./Images/snake/tete_gauche.png"));		
		labeltete.setBounds(serpent.serp.get(0).x*60, serpent.serp.get(0).y*60,60,60);
		jeu.add(labeltete,0);
		snake.setVisible(false);
		snake.setVisible(true);
	}
	public void affichagejeu(){
		affichagetete();
		affichagepomme();
		for(int i=1; i<serpent.serp.size(); i++){
			image.get(i).setBounds(serpent.serp.get(i).x*60,serpent.serp.get(i).y*60,60,60);
			jeu.add(image.get(i),0);
			snake.setVisible(false);
			snake.setVisible(true);
		}
	}
	public void affichagepomme(){
		labelpomme.setBounds(pomme.x*60, pomme.y*60,60,60);
		jeu.add(labelpomme,1);
	}
	public void avancer(){
		Collections.sort(serpent.serp);	
		if(serpent.serp.get(0).equals(pomme)) actionpomme();
		LinkedList<Point> memo = new LinkedList<Point>();
		for(Point p: serpent.serp){
			Point A = new Point(p.x,p.y,p.direction,p.ordre ); 
			memo.add(A);		
		}
		//System.out.println("MEMO "+memo);
		if(serpent.serp.get(0).direction==1){
				if(serpent.serp.get(0).y !=0) serpent.serp.get(0).y--;
				else if (serpent.serp.get(0).y ==0) serpent.serp.get(0).y=11;
			}
			if(serpent.serp.get(0).direction==2){
				if(serpent.serp.get(0).x !=11) serpent.serp.get(0).x++;
				else if (serpent.serp.get(0).x ==11) serpent.serp.get(0).x=0;
			}
			if(serpent.serp.get(0).direction==3){
				if(serpent.serp.get(0).y !=11) serpent.serp.get(0).y++;
				else if (serpent.serp.get(0).y ==11) serpent.serp.get(0).y=0;
			}
			if(serpent.serp.get(0).direction==4){
				if(serpent.serp.get(0).x !=0) serpent.serp.get(0).x--;
				else if (serpent.serp.get(0).x ==0) serpent.serp.get(0).x=11;
			}
		for(int i = 1; i<serpent.serp.size() ; i++){
			serpent.serp.get(i).x=memo.get(i-1).x;
			serpent.serp.get(i).y=memo.get(i-1).y;
			serpent.serp.get(i).direction=memo.get(i-1).direction;
		}
		//System.out.println(serpent);
		if(lose()){
			clock.stop();
			System.out.println("Perdu");
			infos.add(labelfin,0);
			demanderrestart();
		}
	}
	public boolean lose(){
		for(int i=1; i<serpent.serp.size(); i++){
			for(int j=0; j<i; j++){
					if(serpent.serp.get(i).equals(serpent.serp.get(j))){
						System.out.println(serpent.serp.get(i));
						return true;
				}
			}
		}
		return false;
	}
	public void actionpomme(){	
		//System.out.println("POMME");
		pomme = pomme.randompoint(serpent.serp);
		int avancementx=0;
		int avancementy=0;
		if(serpent.serp.get(serpent.serp.size()-1).direction == 1){ avancementx=0;avancementy=1;}
		if(serpent.serp.get(serpent.serp.size()-1).direction == 2){ avancementx=1;avancementy=0;}
		if(serpent.serp.get(serpent.serp.size()-1).direction == 3){ avancementx=0;avancementy=-1;}
		if(serpent.serp.get(serpent.serp.size()-1).direction == 4){ avancementx=-1;avancementy=0;}
		Point memoire = new Point(serpent.serp.get(serpent.serp.size()-1).x + avancementx,serpent.serp.get(serpent.serp.size()-1).y+avancementy,serpent.serp.get(serpent.serp.size()-1).direction,serpent.serp.get(serpent.serp.size()-1).ordre+1);
		serpent.serp.add(memoire);
		image.add( new JLabel (new ImageIcon("./Images/snake/serpent.png")));
		//System.out.println("Taille image "+image.size());
		labeltaille.setText("Taille: "+Integer.toString(serpent.serp.size()));
	}
	public void affichagetemps(int t){
		//System.out.println (t/10+" secondes");
		//labelchronometre.setText(intclock/10+","+intclock%10+" secondes");
	}
	public void demanderrestart(){
		recommencer = new JButton();
		Font policejeu = new Font("Bookman Old Style", Font.BOLD, 18);
		JLabel labelrecommencer = new JLabel();
		labelrecommencer.setBounds(5,0,200,100);
		labelrecommencer.setFont(policejeu);
		labelrecommencer.setText("Recommencer ?");
		recommencer.add(labelrecommencer);
		recommencer.setBounds(180,475,200,100);
		Color couleur = new Color (171,121,121);
		recommencer.setBackground(couleur);
		recommencer.addActionListener(this);
		
		arreter = new JButton();
		JLabel labelarreter = new JLabel();
		labelarreter.setBounds(5,0,200,100);
		labelarreter.setFont(policejeu);
		labelarreter.setText("Arreter ?");
		arreter.add(labelarreter);
		arreter.setBounds(180,600,200,100);
		couleur = new Color (171,121,121);
		arreter.setBackground(couleur);
		arreter.addActionListener(this);
		
		/*infos.remove(labelclavier);
		infos.remove(labelclavier2);*/
		infos.remove(labelclavier3);
		
		infos.add(arreter,0);
		infos.add(recommencer,0);
		infos.setVisible(false);
		infos.setVisible(true);
	}
}
