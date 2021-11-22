import java.util.*;

public class Explosion{
	
    // déclaration variables
	private int scoreJoueur;
	private int scoreOrdi;
	private String choix;
	private String choixAI;
	private int gagne;

    //constructeur pour initialiser variables
	public Explosion(){
		scoreJoueur=0;
		scoreOrdi=0;
        choix=null;
		choixAI=null;
		gagne=0;
	}
	
    //méthode qui renvoie variable indiquant le gagnant 
    //utilise méthode comparaison, finDePartie et affichageScore
	public int joue(String choix1){
		String[] ts= {"Bouclier","BouleDeFeu","Laser"};
		int resultat=0;
		if ((scoreJoueur!=3 && scoreOrdi!=3)){
			this.choixAI=ts[(int) (3*Math.random())];
			System.out.println("Mon choix : "+choix1);
			System.out.println("Choix de l'ordinateur : "+this.choixAI);
			resultat=comparaison(choix1,this.choixAI);
			if(resultat==1){
				this.scoreJoueur++;
			}else if(resultat==-1){
				this.scoreOrdi++;
			}else if(resultat==0){
				System.out.println("Il y a egalite personne n'a gagne");
			}
			affichageScore(scoreJoueur,scoreOrdi);
			if (scoreJoueur==3 || scoreOrdi==3){
				finDePartie();
			}
		}
		return resultat;
	}
    
    // méthode qui réinitialise la partie pour rejouer
	public void finDePartie(){
		if (scoreJoueur==3){
			System.out.println("Vous avez gagne");
			this.gagne=1;
            this.scoreJoueur = 0;
            this.scoreOrdi = 0;
		}else if (scoreOrdi==3){
			System.out.println("L'ordinateur a gagne");
			this.gagne=2;
            this.scoreJoueur = 0;
            this.scoreOrdi = 0;
		}
	}
    
    //méthode pour relancer le jeu à zéro
    public void rejouer(){
        this.gagne=0;
    }

    //méthode qui permet de comparer les attaques et définir gagnant
	public int comparaison(String s,String res){
		int x=0;
		if(s.equals("Bouclier")){
			if(res.equals("BouleDeFeu")){
				x=1;
			}else if(res.equals("Laser")){
				x=-1;
			}else if(res.equals("Bouclier")){
				x=0;
			}
		}
		else if(s.equals("BouleDeFeu")){
			if(res.equals("Laser")){
				x=1;
			}else if(res.equals("Bouclier")){
				x=-1;
			}else if(res.equals("BouleDeFeu1")){
				x=0;
			}
		}
		else if(s.equals("Laser")){
			if(res.equals("Bouclier")){
				x=1;
			}else if(res.equals("BouleDeFeu")){
				x=-1;
			}else if(res.equals("Laser")){
				x=0;
			}
		}
		return x;
	}
    
    //méthode qui affiche les scores dans le terminal
	public void affichageScore(int x, int y){
		System.out.println("Ton score est de : "+x);
		System.out.println("Le score de l'ordinateur est : "+y); 
        System.out.println(); 
	}
    
    //méthodes d'accès et de modifications aux/des variables 
    
	public void setChoix (String s){
		this.choix=s;
	}
	public String getchoixAI(){
		return choixAI;
	}

	public int getGagne(){
		return gagne;
	}
	
	public int getscoreOrdi(){
		return scoreOrdi;
	}
	
	public int getscoreJoueur(){
		return scoreJoueur;
	}
}


