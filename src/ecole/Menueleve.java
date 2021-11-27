package ecole;
import conx.connexion;
import net.proteanit.sql.DbUtils;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import java.sql.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menueleve extends JFrame 
{
	private JTable table;
	public Menueleve() {
		setVisible(true);
		setSize(700,500);
		getContentPane().setLayout(null);
		connexion.getconnect();
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 493, 341);
		panel.setBorder( BorderFactory. createTitledBorder( "MENU ELEVE: ") ) ;
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleve e1 = new eleve();
			}
		});
		btnNewButton.setBounds(78, 210, 137, 36);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Supprimeleve S= new Supprimeleve(); 
			}
		});
		btnNewButton_1.setBounds(261, 210, 137, 36);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.setBounds(181, 256, 137, 36);
		panel.add(btnNewButton_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(42, 27, 416, 164);
		affiche();
		panel.add(table);
		
	}
	public void affiche ()
	{
		try {
			String requete ="select * from eleve";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
			
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
	}
}
