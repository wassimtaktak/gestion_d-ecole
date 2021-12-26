package ecole;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import conx.connexion;
import net.proteanit.sql.DbUtils;

public class filiere extends JFrame {
	private JTextField idtext;
	private JTextField libelletext;
	private JTable table;
	public filiere() {
		setVisible(true);
		setSize(800,700);
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 12, 770, 518);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder( BorderFactory. createTitledBorder( "Informations de la filière : ") ) ;
		
		JLabel lblNewLabel = new JLabel("id_filiere");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(34, 59, 117, 41);
		panel.add(lblNewLabel);
		
		idtext = new JTextField();
		idtext.setBounds(182, 62, 201, 37);
		panel.add(idtext);
		idtext.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Libell\u00E9 ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(37, 127, 119, 29);
		panel.add(lblNewLabel_1);
		
		libelletext = new JTextField();
		libelletext.setBounds(184, 123, 202, 37);
		panel.add(libelletext);
		libelletext.setColumns(10);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idtext.getText();
				String libelle = libelletext.getText() ;
				try {
					Statement state;
					state = connexion.getconnect().createStatement();
					String requete = "INSERT INTO filiere VALUES ('"+id+"','"+libelle+"')";
						int res = state.executeUpdate(requete);
						if(res!=0){
								affiche();
								JOptionPane.showMessageDialog(null, "filière ajouté avec succès", "info", JOptionPane.INFORMATION_MESSAGE);
								}
						setVisible( true) ;
						state.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "info", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(filiere.class.getResource("/img/ajouter.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 204, 129, 44);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(412, 33, 284, 140);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {  int i=table.getSelectedRow();
	            deplace(i);}
			catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur selectionne\n"+e1.getMessage()); 
			}}});
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					Statement stmn;
					stmn= connexion.getconnect().createStatement();
					String s;
					
		            if (JOptionPane.showConfirmDialog (null,"confirmer la modification","modification",
		                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

		                stmn.executeUpdate("UPDATE filiere SET libelle='"+libelletext.getText()+
		                        "' WHERE id_fil= "+idtext.getText());
		                affiche();
		            } 
		        } catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de modification!!!!!!!");
		        System.err.println(e1);}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(filiere.class.getResource("/img/modifier.gif")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(149, 204, 143, 44);
		panel.add(btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmn;
					stmn= connexion.getconnect().createStatement();
		             if(JOptionPane.showConfirmDialog(null,"attention vous allez supprimer une filière,est ce que vous etes sure?"
		                     ,"supprimer matiere",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
		         
		            if(idtext.getText().length() != 0){
		        stmn.executeUpdate("Delete From filiere where id_fil = "+idtext.getText());
		        affiche();
		             }
		            else { JOptionPane.showMessageDialog(null,"veuillez remplire le champ id !");}
		        
		        }catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de supprimer \n"+e1.getMessage());} 
		       
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(filiere.class.getResource("/img/supprimer.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(315, 204, 143, 44);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Quitter");
		btnNewButton_3.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_3.setIcon(new ImageIcon(filiere.class.getResource("/img/sortie.gif")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(558, 211, 127, 37);
		panel.add(btnNewButton_3);
		affiche();
		
}
	public void affiche ()
	{
		try {
			String requete ="select * from filiere";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
			
		}catch (Exception exp)
		{
			System.out.println("exception="+exp);
		}
		
	}
	public void deplace(int i){
	       try {     //i represente les ligne 
	     
	          idtext.setText(table.getValueAt (i, 0).toString());
	          libelletext.setText(table.getValueAt (i, 1).toString());
	       
	       }catch (Exception e){ JOptionPane.showMessageDialog(null,"erreur de deplacement de message !!!!! "+e.getMessage());}
	      }
}
