import java.awt.*;
import java.util.LinkedList; 
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.Font;
public class SelectionJeu extends JFrame implements ActionListener {	
	private JButton jeu1;
	private JButton jeu2;
	private JButton jeu3;
	private JButton jeu4;
	private JButton jeu5;
	private JPanel fenetreprincipale;
	private JPanel pointeurtest;
	private JLabel fondecran;
	private JLabel titre; 
	
	public SelectionJeu () {
		this.setTitle("Menu de Jeu");
		this.setSize(1280,720);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println();
		fenetreprincipale = new JPanel();
		fenetreprincipale.setBounds(0,0,this.getSize().width, this.getSize().height);
		fenetreprincipale.setBackground(Color.orange);
		fenetreprincipale.setLayout(null);
		
		
		Font policetitre = new Font("Bookman Old Style", Font.BOLD, 100);
		titre = new JLabel();
		titre.setBounds(295,0,690,200);
		titre.setFont(policetitre);
		titre.setForeground(new Color(120,120,120));
		titre.setText("EXPLOSION !");
		fenetreprincipale.add(titre,0);

		pointeurtest = new JPanel();
		pointeurtest.setBounds(2,2,10,10);
		pointeurtest.setBackground(Color.black);
		fenetreprincipale.add(pointeurtest);
		
		jeu1= new JButton();
		jeu1.setLayout(null);
		jeu1.setBounds(170,250,200,100);
		//Color grey = new Color( 140,140,140);
		JLabel label1 = new JLabel();
		label1.setBounds(25,0,200,100);
		Font policejeu = new Font("Bookman Old Style", Font.BOLD, 25);
		jeu1.add(label1);
		label1.setFont(policejeu);	
		label1.setText("Demineur");
		//jeu1.setBackground(grey);
		jeu1.addActionListener(this);
		fenetreprincipale.add(jeu1,1);
		
		
		jeu2= new JButton();
		jeu2.setLayout(null);
		jeu2.setBounds(540,250,200,100);
		//jeu2.setBackground(grey);
		JLabel label2 = new JLabel();
		policejeu = new Font("Bookman Old Style", Font.BOLD, 20);
		label2.setFont(policejeu);
		label2.setText("Casse-briques");
		label2.setBounds(25,0,200,100);
		jeu2.add(label2);
		jeu2.addActionListener(this);
		jeu2.setLayout(null);
		//jeu2.requestFocus();
		fenetreprincipale.add(jeu2,2);
		
		jeu3= new JButton();
		jeu3.setLayout(null);
		JLabel label3 = new JLabel();
		label3.setBounds(5,0,200,100);
		policejeu = new Font("Bookman Old Style", Font.BOLD, 25);
		label3.setFont(policejeu);
		label3.setText("Snake");
		jeu3.add(label3);
		jeu3.setBounds(910,250,200,100);
		//jeu3.setBackground(grey);
		jeu3.addActionListener(this);
		jeu3.setLayout(null);
		jeu3.requestFocus();
		fenetreprincipale.add(jeu3,3);
		
		jeu4= new JButton();
		jeu4.setLayout(null);
		JLabel label4 = new JLabel();
		label4.setBounds(25,0,200,100);
		policejeu = new Font("Bookman Old Style", Font.BOLD, 25);
		label4.setFont(policejeu);
		label4.setText("Snake");
		//jeu4.add(label4);
		jeu4.setBounds(170+85*2,450,200,100);
		//jeu4.setBackground(grey);
		jeu4.addActionListener(this);
		jeu4.setLayout(null);
		jeu4.requestFocus();
		//fenetreprincipale.add(jeu4,4);
		
		jeu5= new JButton();
		jeu5.setLayout(null);
		JLabel label5 = new JLabel();
		label5.setBounds(25,0,200,100);
		policejeu = new Font("Bookman Old Style", Font.BOLD, 25);
		label5.setFont(policejeu);
		label5.setText("Morpion");
		//jeu5.add(label5);
		jeu5.setBounds(540+85*2,450,200,100);
		//jeu5.setBackground(grey);
		jeu5.addActionListener(this);
		jeu5.setLayout(null);
		jeu5.requestFocus();
		//fenetreprincipale.add(jeu5,5);
		
		fondecran=new JLabel (new ImageIcon("./Images/selectionjeu/background.jpg"));
		fondecran.setBounds(0,0,1280,720);
		fenetreprincipale.add(fondecran,4);
		
		this.add(fenetreprincipale);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icone = kit.getImage("./Images/selectionjeu/icon-selectionjeu.png");
		setIconImage(icone);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==jeu1) {		
			Demineur demineur = new Demineur();
			//this.setVisible(false);
		}
		if(e.getSource()==jeu2) {
			CasseBrique cassebrique = new CasseBrique();
			//this.setVisible(false);
		}
		if(e.getSource()==jeu3) {
			Snake snake = new Snake();	
			//this.setVisible(false);
		}
		
	}
}
