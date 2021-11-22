import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class fenetre extends JFrame implements ActionListener{
    
    //Déclaration widjets
    private JPanel total;
    
    private JLabel presentation;
    private JLabel titre;
    private JLabel nom;
    private JTextField nomJoueur;
    private JLabel partieGagne;
    private JLabel partiePerdu;
    private JLabel partieEgalite;
    private JLabel manche;

    private JPanel principal;
    private JPanel principal2;
    
    private JPanel jeu;
	private Bouton [][] lesBoutons;
	
	private int [] tableau ;
	private int a;
	private int x;
	private int y;
	private int [][] etat;
	private finGagnanteMorpion affichageGagne;
    private finPerdantMorpion affichagePerdu;
    private finEgaliteMorpion affichageEgalite;
    private int numManche;
    private int mancheJoueur;
    private int mancheOrdi;
    private int mancheEgale;
    
    public fenetre(){
		this.setTitle("principal");
		this.setSize(1600,850);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        affichageGagne = new finGagnanteMorpion();
		affichagePerdu = new finPerdantMorpion();
		affichageEgalite = new finEgaliteMorpion();

		lesBoutons = new Bouton[4][4];
		etat = new int [4][4];
        numManche = 0;
        mancheJoueur = 0;
        mancheOrdi = 0;
        mancheEgale = 0;
        
        // présentation
        total = new JPanel();
        total.setLayout(null);
        total.setBounds(0,0,2000,2000);
        
        // Panneau principal
        principal = new JPanel();
        principal.setLayout(null);
        principal.setBounds(0,0,900,900);
        
        // Panneau 
        presentation = new JLabel(new ImageIcon ("./Images/morpion/FlammeFeu.jpg"));
		presentation.setLayout(null);
		getContentPane().setLayout(new BorderLayout ());
        getContentPane().add(presentation);
		presentation.setBounds(0,0,800,850);
        
        titre = new JLabel();
		titre.setText("Morpion");
		titre.setFont(new Font("Serif", Font.BOLD, 60));
        titre.setForeground(Color.WHITE);
		titre.setBounds(280, 20, 300, 70);
		presentation.add(titre);
        
        nom= new JLabel("Nom joueur : ");
		nom.setBounds(40,100,200,100);
		nom.setFont(new Font("Serif", Font.BOLD, 30));
        nom.setForeground(Color.WHITE);
		presentation.add(nom);
        
        nomJoueur = new JTextField();
		nomJoueur.setBounds(290, 125, 200, 50);
		nomJoueur.setFont(new Font("Contour", Font.BOLD, 30));
		nomJoueur.setForeground(Color.BLACK);
		presentation.add(nomJoueur);
		
		partieGagne= new JLabel("Nombre de partie gagnee : 0");
		partieGagne.setBounds(25,300,550,50);
		partieGagne.setFont(new Font("Serif", Font.BOLD, 25));
        partieGagne.setForeground(Color.WHITE);
		presentation.add(partieGagne);
		
		partiePerdu= new JLabel("Nombre de partie gagnne par l'ordinateur : 0");
		partiePerdu.setBounds(25,350,550,50);
		partiePerdu.setFont(new Font("Serif", Font.BOLD, 25));
        partiePerdu.setForeground(Color.WHITE);
		presentation.add(partiePerdu);
        
        partieEgalite= new JLabel("Nombre de partie egalite : 0 ");
		partieEgalite.setBounds(25,400,550,50);
		partieEgalite.setFont(new Font("Serif", Font.BOLD, 25));
        partieEgalite.setForeground(Color.WHITE);
		presentation.add(partieEgalite);
        
        manche = new JLabel("Nombre de manche : 0");
		manche.setBounds(25,700,350,50);
		manche.setFont(new Font("Serif", Font.BOLD, 25));
        manche.setForeground(Color.WHITE);
		presentation.add(manche);
        
        principal.add(presentation);
        
        //panneau jeu
        principal2 = new JPanel();
        principal2.setLayout(null);
        principal2.setBounds(800,20,950,900);

		jeu = new JPanel();
        jeu.setLayout(null);
        jeu.setBounds(0,0,900,900);
        jeu.setBackground(new Color(156,132,132));
        jeu.setOpaque(false);
        
        for (int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				lesBoutons[i][j] = new Bouton(i,j);
				lesBoutons[i][j].bouton.addActionListener(this);
				jeu.add(lesBoutons[i][j].bouton);
				etat[i][j]=0;
			}
		}
        
        principal2.add(jeu);
        
        total.add(presentation);
        total.add(principal2);
        this.add(total);
        this.setVisible(true);
        
	}
	
	public void actionPerformed (ActionEvent e){
        //Demande au joueur de rentrer son nom
        while (nomJoueur.getText().equals("")){
            JOptionPane.showMessageDialog(this, "  Balance ton blaze frerot");
            return;
        }
		boolean JoueurGagne = false;
		boolean OrdiGagne = false; 
        
		for (int i=0; i<4; i++){
			for(int j=0; j<4; j++){
                
                //Informe le joueur que la case est déjà remplie 
                if(e.getSource() == lesBoutons[i][j].bouton && etat[i][j]!=0){
                    JOptionPane.showMessageDialog(this, "Choisis une autre case");
                    return;
                }
                
				if (e.getSource() == lesBoutons[i][j].bouton && etat[i][j]==0){
					lesBoutons[i][j].setBouton();
					etat[i][j]=1;
					JoueurGagne = gagneJoueur();
                    
					tableau = caseOrdi();
					lesBoutons[tableau[0]][tableau[1]].setBoutonOrdi();
					etat[tableau[0]][tableau[1]]=2;
					OrdiGagne = gagneOrdi();
                    
                    //Différents codes en fonction du gagnant
					if (JoueurGagne){
						affichageGagne.setVisible(true);
						remiseZero();
						reset();
                        numManche+=1;
                        manche.setText("Nombre de manche jouee: " + numManche);
                        mancheJoueur+=1;
                        partieGagne.setText("Nombre de partie gagnee par "+ nomJoueur.getText() + " : " + mancheJoueur);
					}
                    else if(OrdiGagne){
						affichagePerdu.setVisible(true);
						remiseZero();
						reset();
                        numManche+=1;
                        manche.setText("Nombre de manche jouee: " + numManche);
                        mancheOrdi+=1;
                        partiePerdu.setText("Nombre de partie gagnee par l'ordinateur : " + mancheOrdi);
					}
                    else if (compterFin ()==16){
						affichageEgalite.setVisible(true);
						remiseZero();
						reset();
                        numManche+=1;
						manche.setText("Nombre de manche jouee: " + numManche);
                        mancheEgale+=1;
                        partieEgalite.setText("Nombre de partie egalite : " + mancheEgale);
					}
				}
			}
		}
	}
	
    //Méthode test Joueur gagnant
	public boolean gagneJoueur(){
		boolean gagneJoueurV=false;
		if(((etat[0][0]==etat[1][1]) && (etat[0][0]==etat[2][2]) && (etat[0][0]==etat[3][3])&&(etat[0][0]==1))
				||((etat[0][3]==etat[1][2]) && (etat[0][3]==etat[2][1]) && (etat[0][3]==etat[3][0])&&(etat[0][3]==1))
				||((etat[0][0]==etat[1][0]) && (etat[0][0]==etat[2][0]) && (etat[0][0]==etat[3][0])&&(etat[3][0]==1))
				||((etat[0][1]==etat[1][1]) && (etat[0][1]==etat[2][1]) && (etat[0][1]==etat[3][1])&&(etat[3][1]==1))
				||((etat[0][2]==etat[1][2]) && (etat[0][2]==etat[2][2]) && (etat[0][2]==etat[3][2])&&(etat[3][2]==1))
				||((etat[0][3]==etat[1][3]) && (etat[0][3]==etat[2][3]) && (etat[0][3]==etat[3][3])&&(etat[3][3]==1))
				||((etat[0][0]==etat[0][1]) && (etat[0][0]==etat[0][2]) && (etat[0][0]==etat[0][3])&&(etat[0][3]==1))
				||((etat[1][0]==etat[1][1]) && (etat[1][0]==etat[1][2]) && (etat[1][0]==etat[1][3])&&(etat[1][3]==1))
				||((etat[2][0]==etat[2][1]) && (etat[2][0]==etat[2][2]) && (etat[2][0]==etat[2][3])&&(etat[2][3]==1))
				||((etat[3][0]==etat[3][1]) && (etat[3][0]==etat[3][2]) && (etat[3][0]==etat[3][3])&&(etat[3][3]==1))){
			gagneJoueurV=true;
		}
		return gagneJoueurV;
	}

    //Méthode test ordi gagnant	
	public boolean gagneOrdi(){
		boolean gagneOrdiV=false;
		if(((etat[0][0]==etat[1][1]) && (etat[0][0]==etat[2][2]) && (etat[0][0]==etat[3][3])&&(etat[0][0]==2))
				||((etat[0][3]==etat[1][2]) && (etat[0][3]==etat[2][1]) && (etat[0][3]==etat[3][0])&&(etat[0][3]==2))
				||((etat[0][0]==etat[1][0]) && (etat[0][0]==etat[2][0]) && (etat[0][0]==etat[3][0])&&(etat[3][0]==2))
				||((etat[0][1]==etat[1][1]) && (etat[0][1]==etat[2][1]) && (etat[0][1]==etat[3][1])&&(etat[3][1]==2))
				||((etat[0][2]==etat[1][2]) && (etat[0][2]==etat[2][2]) && (etat[0][2]==etat[3][2])&&(etat[3][2]==2))
				||((etat[0][3]==etat[1][3]) && (etat[0][3]==etat[2][3]) && (etat[0][3]==etat[3][3])&&(etat[3][3]==2))
				||((etat[0][0]==etat[0][1]) && (etat[0][0]==etat[0][2]) && (etat[0][0]==etat[0][3])&&(etat[0][3]==2))
				||((etat[1][0]==etat[1][1]) && (etat[1][0]==etat[1][2]) && (etat[1][0]==etat[1][3])&&(etat[1][3]==2))
				||((etat[2][0]==etat[2][1]) && (etat[2][0]==etat[2][2]) && (etat[2][0]==etat[2][3])&&(etat[2][3]==2))
				||((etat[3][0]==etat[3][1]) && (etat[3][0]==etat[3][2]) && (etat[3][0]==etat[3][3])&&(etat[3][3]==2))){
					gagneOrdiV=true;
		}
		return gagneOrdiV;
	}
	
    //Méthode qui permet de choisir bouton de l'ordi
	public int [] caseOrdi (){
		int [] choix = new int[2];
		choix[0] = (int) (Math.random()*4);
		choix[1] = (int) (Math.random()*4);
		while (etat[choix[0]][choix[1]]!=0){
			choix[0] = (int) (Math.random()*4);
			choix[1] = (int) (Math.random()*4);
		}
		return choix;
	}
	
    //Méthode qui permet de savoir si toutes les cases sont remplies
	public int compterFin (){
		int compter = 0;
		for (int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (etat[i][j] != 0){
					compter = compter +1;
				}
			}
		}
		return compter;
	}
	
    //Méthode qui permet de réinitialiser le tableau état
	public void remiseZero (){
		for (int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				etat[i][j] = 0;
			}
		}
	}
	
    //Méthode qui permet de réinitialiser les boutons sans image
	public void reset(){
		for(int y =0; y<4; y++){
			for(int z =0; z<4; z++){
				lesBoutons[y][z].reSetBouton();
			}
		}
	}
}





