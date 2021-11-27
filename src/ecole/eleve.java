package ecole;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import javax.swing.*;

import conx.connexion;

import java.awt.*;
import java.awt.event.*;
public class eleve extends JFrame
{
	private JLabel nomLabel,numele,prenoml,datel,sexel;
	private JButton b01 = new JButton();
	private JButton b02 = new JButton();
	
	public eleve()
	{
		this.setVisible(true);
		this. setTitle( "ajout_eleve") ;
		this.setAlwaysOnTop(true);
		this. setSize( 450, 300) ;
		this.setResizable(true);
		this. setLocationRelativeTo( null) ;
		this. getContentPane( ).setLayout( new FlowLayout( )) ;
		JPanel pan = new JPanel( ) ;
		pan. setBackground( Color.blue) ;
		pan. setLayout( new BorderLayout( ) ) ;
		getContentPane().add(pan);
		JPanel panNom = new JPanel( ) ;
		panNom. setBackground( Color. white) ;
		panNom. setPreferredSize( new Dimension( 420, 250) ) ;
		
		
		final JTextField numeleve  = new JTextField();
		numeleve.setPreferredSize(new Dimension(150, 25));
		
	
		final JTextField nom  = new JTextField();
		nom.setPreferredSize(new Dimension(150, 25));
		
		
		final JTextField prenom  = new JTextField();
		prenom.setPreferredSize(new Dimension(150, 25));
		
		
		final JTextField date  = new JTextField();
		date.setPreferredSize(new Dimension(150, 25));
		
		
		ButtonGroup sexe = new ButtonGroup();  
		final JRadioButton radio1 = new JRadioButton("masculin", true);
        JRadioButton radio2 = new JRadioButton("feminin", false);
        sexe.add(radio1);
        sexe.add(radio2);
		
		panNom.setBorder( BorderFactory. createTitledBorder( "Information d'elève : ") ) ;
		numele = new JLabel( "Numero d'elève : ") ;
		numele.setPreferredSize(new Dimension( 150, 25));
		panNom.add(numele);
		panNom.add(numeleve);
		nomLabel = new JLabel("Nom d'elève :    ") ;
		nomLabel.setPreferredSize(new Dimension( 150, 25));
		panNom. add( nomLabel) ;
		panNom. add( nom) ;
		prenoml = new JLabel("Prenom d'elève");
		prenoml.setPreferredSize(new Dimension( 150, 25));
		panNom.add(prenoml);
		panNom.add(prenom);
		datel = new JLabel("Date de Naissance :");
		datel.setPreferredSize(new Dimension( 150, 25));
		panNom.add(datel);
		panNom.add(date);
		sexel=new JLabel("sexe :");
		sexel.setPreferredSize(new Dimension( 150, 25));
		panNom.add(sexel);
		panNom.add(radio1);
		panNom.add(radio2);
		b01 = new JButton("valider");
		b01.setBackground(Color.GREEN);
		b01.setPreferredSize(new Dimension( 100, 25));
		b01.addActionListener( new ActionListener( ) 
		{
			public void actionPerformed( ActionEvent arg0) 
			 {
				
				String num = numeleve.getText();
				String no = nom.getText() ;
				String preno = prenom.getText();
				String dn = date.getText();
				char s;
				if (radio1.isSelected())
					s='M';
				else 
					s='F';
				//requete
				String requete = "INSERT INTO eleve VALUES ('"+num+"','"+no+"','"+preno+"','"+dn+"','"+s+"')";
			      Statement state;
				try {
					
					state = connexion.getconnect().createStatement();
				int res = state.executeUpdate(requete);
				if(res!=0){
					 //state.execute(requete);
						setVisible( false) ;
						JOptionPane.showMessageDialog(null, "Eneregistrement ajouté avec succès", "info", JOptionPane.INFORMATION_MESSAGE);
						}
					 //state.execute(requete);
						setVisible( false) ;
						//res.close();
						state.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "info", JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}
			 //On exécute la requête
			     
			  }
		});
//aaaaaaaaaaaaaaaaaaaaaa
//tttttttttttttt
//jjjjjjjjjjjjjjjjjjjjjjjj
		b02 = new JButton();
		b02.setText("Annuler");
		b02.setPreferredSize(new Dimension( 100, 25));
		b02.setBackground(Color.RED);
		b02.addActionListener( new ActionListener( ) {
		public void actionPerformed( ActionEvent arg0) {
			 setVisible(false);
		     
		}});
		panNom.add(b01);
		panNom.add(b02);
		getContentPane().add(panNom);
	}
	
}
