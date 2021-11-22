import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fin extends JFrame implements ActionListener {
    
    private JPanel affichage;
    private JLabel textWinLose;
    private JLabel continuer;
    private JButton yes;
    public JButton no;
    
    public Fin(String texte){
        
        this.setTitle("IHM - Final");
        this.setLayout(null);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        affichage = new JPanel();
        affichage.setLayout(null);
        affichage.setBounds(0,0,900,900);
        affichage.setBackground(Color.gray);
        
        textWinLose = new JLabel(texte);
        textWinLose.setFont(new Font("SansSerif", Font.BOLD, 60));
        textWinLose.setForeground(Color.white);
		textWinLose.setBounds(150,100,900,100);
        affichage.add(textWinLose);
        
        continuer = new JLabel("Continuer ?");
        continuer.setFont(new Font("SansSerif", Font.BOLD, 40));
        continuer.setBounds(340,200,900,200);
		affichage.add(continuer);
        
        no = new JButton("NO");
		no.setBounds(500,400,400,200);
        no.setFont(new Font("SansSerif", Font.BOLD, 30));
        no.setForeground(Color.WHITE);
		no.setOpaque(false);
		no.setContentAreaFilled(false);
		no.addActionListener(this);
		affichage.add(no);
        
        yes= new JButton("YES");
		yes.setBounds(20,400,400,200);
        yes.setFont(new Font("SansSerif", Font.BOLD, 30));
        yes.setForeground(Color.WHITE);
		yes.setOpaque(false);
		yes.setContentAreaFilled(false);
		yes.addActionListener(this);
		affichage.add(yes);
        
        this.add(affichage);
        
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource()== no) {
            this.setVisible(false);
			this.dispose();
			this.pack();	
			
		}
        if (e.getSource()== yes) {
           this.setVisible(false);
			this.dispose();
			this.pack();	
			
            CasseBrique jeu = new CasseBrique();
            jeu.rejouer();
		}
    }  
}
