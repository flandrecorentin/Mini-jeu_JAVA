import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.Font;


public class CasseBrique extends JFrame implements ActionListener, KeyListener{
    
    //attributs
    private int hauteur = 600;
    private int largeur = 900;
    private JPanel panneauDroite;
    private JLabel titre;
    private JLabel scoreTxt;
    private JLabel nbPartie;
    private JPanel panneauWinLose;
    private JLabel winLose;
    
    int score = 0;
    int partie = 0;
    int gagne = 0;
    
    //objets
    
    //matrice "squelette" des briques
    static int [][] tabbrique ={
		{1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1},
    };
    
    //matrice test pour le winOrLose
    /*
    static int [][] tabbrique ={
		{0,0,0,0,0,0,0,0,0},
    };
    */ 
        
    static String message = new String("test");
    static Balle b = new Balle(250, 250, 5);
    static Brique brique = new Brique(20, 20, 50,20);
    static Barre barre = new Barre(250, 450, 100, 15);
    static Interface i = new Interface(b, brique, barre, tabbrique);
    static Fin affichageFin;
    
    //Timer
    private int time;
    private Timer t=new Timer(80,this);;
    
    
    //Constructeur 
    public CasseBrique (){
        
        t.start(); //on lance le timer quand jeu s'ouvre
        
        panneauDroite = new JPanel();
		panneauDroite.setLayout(null);
		panneauDroite.setBounds(600,0,300,300);
        panneauDroite.setBackground(Color.gray);
        
        titre = new JLabel();
		titre.setText("CASSE-BRIQUE");
		titre.setFont(new Font("SansSerif", Font.BOLD, 30));
        titre.setForeground(Color.white);
		titre.setBounds(620, 00, 300, 400);
        panneauDroite.add(titre);
        
        //Affichage Score
        scoreTxt = new JLabel();
		scoreTxt.setText("SCORE : " + score);
		scoreTxt.setFont(new Font("SansSerif", Font.BOLD, 20));
        scoreTxt.setForeground(Color.white);
		scoreTxt.setBounds(680, 200, 200, 200);
        panneauDroite.add(scoreTxt);
        
        //Affichage NbParties
        nbPartie = new JLabel();
        nbPartie.setText("Nombre de parties : " + partie);
		nbPartie.setFont(new Font("SansSerif", Font.BOLD, 20));
        nbPartie.setForeground(Color.white);
		nbPartie.setBounds(625, 250, 300, 200);
        panneauDroite.add(nbPartie);
        
        //Affichage Win/Lose   
        panneauWinLose = new JPanel();
        winLose = new JLabel();
        winLose.setFont(new Font("SansSerif", Font.BOLD, 20));
        winLose.setForeground(Color.white);
        winLose.setBounds(680, 200, 200, 200);
        
        //Fenetre de jeu
        this.setSize(largeur, hauteur);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icone = kit.getImage("./Images/casse-brique/icon-casse-brique.jfif");
		setIconImage(icone);
        this.setVisible(true);
        this.setTitle("Explosion");
        this.add(i);
        this.add(panneauDroite);
        addKeyListener (this) ;	
    }
	
    
	public static void main (String[] args) {
		CasseBrique jouer = new CasseBrique();
	}
    
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource()==t){ // gérer le timer pour le déplacement de la balle
            time=time+100;
            
            if(time%50==0) {

                 b.seDeplacer();
                 b.collisionMur(hauteur, largeur);
                 b.collisionBrique(brique, tabbrique);
                 b.collisionBarre(barre);
                 winOrLose(tabbrique);
            }
		}	
    
    // on remet l'affichage à jour
    
		i.revalidate();
		this.revalidate();
		i.repaint();
		this.repaint();
		

    }
    
    public void keyPressed(KeyEvent e){
        
        //On déplace la barre à l'aide des touches gauche et droite
        int touche = e.getKeyCode();
        barre.deplaceX(touche);
        
    }
    
    public void keyReleased(KeyEvent e) {
        // cette méthode ne fait rien
	}
    
    public void keyTyped(KeyEvent evt){
         // cette méthode ne fait rien	
    }
    
    
    //Méthode pour savoir si on a gagné ou perdu
    public void winOrLose(int[][] tabbrique){
    
        //On augmente le score à chaque brique cassée
        for(int i = 0; i< tabbrique.length; i++){
            for(int j = 0; j < tabbrique[0].length; j++){
                if(tabbrique[i][j] == 0){
                    score = score + 10;
                    scoreTxt.setText("Score : " + score);
                }
            }
        }
        
        //Si toutes les briques sont cassées, c'est gagné
        if(score == tabbrique.length * tabbrique[0].length *10){
            t.stop();
            time =0;
            gagne = 2;
            message = ("VOUS AVEZ GAGNE");
            affichageFinal(gagne, partie);
            
        //Si la balle sort de l'écran, c'est perdu
        } else if ( b.gety() > 600){
            t.stop();
            time = 0;
            gagne = 1;
            message = ("VOUS AVEZ PERDU");
            affichageFinal(gagne, partie);
        }
        score = 0;
        
    }
    
    public int affichageFinal(int test, int r){
        
        affichageFin = new Fin(message);
        affichageFin.setVisible(true);
		this.dispose();
        
        return r;
    }
    
    //Méthode remettant les paramètres à l'origine pour recommencer une partie
    public void rejouer(){
        t.start();
        gagne = 0;
        b.setx(250);
        b.sety(250);
        barre.setx(250);
        barre.sety(450);
        for(int i = 0; i< tabbrique.length; i++){
            for(int j = 0; j < tabbrique[0].length; j++){
                    tabbrique[i][j] = 1;
                }
            }
        partie += 1;
        nbPartie.setText("Nombre de parties : " + partie);
        
    }
    
    

}

