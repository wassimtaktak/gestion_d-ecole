package ecole;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import conx.connexion;
import net.proteanit.sql.DbUtils;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class eleve extends JFrame
{
	private JTextField id;
	private JTextField nom;
	private JTextField prenom;

	private JTable table;
	public eleve() {
		setSize(826,700);
		int i;
		getContentPane().setLayout(null);
		this.setVisible(true);
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBounds(10, 10, 851, 643);
		getContentPane().add(panel);
		panel.setBorder( BorderFactory. createTitledBorder( "Informations d'eleve: : ") ) ;
		panel.setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 377, 135, 25);
		for(i=0;i<colmat().size();i++)
		{comboBox.addItem(colmat().get(i));}
		
		id = new JTextField();
		id.setBounds(125, 38, 162, 37);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id :");
		lblNewLabel.setBounds(39, 34, 56, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		nom = new JTextField();
		nom.setBounds(125, 99, 162, 37);
		panel.add(nom);
		nom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(23, 102, 72, 22);
		panel.add(lblNewLabel_1);
		
		prenom = new JTextField();
		prenom.setBounds(111, 162, 187, 37);
		panel.add(prenom);
		prenom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 164, 85, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("date de naissance :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(10, 224, 196, 51);
		panel.add(lblNewLabel_4);
		
		 JRadioButton rdbtnNewRadioButton = new JRadioButton("masculin",true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(125, 322, 103, 21);
		ButtonGroup sexe = new ButtonGroup();
		sexe.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("feminin",false);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBounds(247, 322, 103, 21);
		sexe.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("sexe :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(23, 312, 62, 37);
		panel.add(lblNewLabel_3);
		
		  JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(216, 237, 162, 35);
		panel.add(dateChooser);


		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 38, 467, 290);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {  int i=table.getSelectedRow();
	            deplace(i);
	            String gender=table.getValueAt(i, 3).toString();
	            comboBox.setSelectedItem(table.getValueAt (i,5).toString());
	            if(gender.equals("Masculin"))
	            		{
	            		rdbtnNewRadioButton.setSelected(true);
	            		}
	            else {
	            	rdbtnNewRadioButton_1.setSelected(true);
	            }
	            		
	            dateChooser.setDate( new SimpleDateFormat("yyyy-MM-dd").parse(table.getValueAt (i,3).toString()));
	            
	        }
	        catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur selectionne\n"+e1.getMessage());          }
			}
		});
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = id.getText();
				String no = nom.getText() ;
				String preno = prenom.getText();
				String filiere=comboBox.getSelectedItem().toString();
				String id_mat=idlib(filiere);
				
				java.util.Date date=dateChooser.getDate();
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
				String s;
				if (rdbtnNewRadioButton.isSelected())
					s="Masculin";
				else 
					s="Feminin";
				String requete = "INSERT INTO eleve VALUES ('"+num+"','"+no+"','"+preno+"','"+s+"','"+sqldate+"','"+id_mat+"')";
			      Statement state;
				try {
					
					state = connexion.getconnect().createStatement();
				int res = state.executeUpdate(requete);
				if(res!=0){
					 //state.execute(requete);
						//setVisible( false) ;
						JOptionPane.showMessageDialog(null, "élève ajouté avec succès", "info", JOptionPane.INFORMATION_MESSAGE);
						affiche();
						}
						//setVisible( false) ;
						state.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "info", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(eleve.class.getResource("/img/nouveau.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(28, 444, 151, 37);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmn;
					stmn= connexion.getconnect().createStatement();
		             if(JOptionPane.showConfirmDialog(null,"attention vous allez supprimer un élève,est ce que vous etes sure?"
		                     ,"supprimer etudient",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
		         
		            if(id.getText().length() != 0){
		        stmn.executeUpdate("Delete From eleve where id = "+id.getText());
		        affiche();
		             }//ca est pour recharger la list des stagiaire
		            else { JOptionPane.showMessageDialog(null,"veuillez remplire le champ id !");}
		        
		        }catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de supprimer \n"+e1.getMessage());} 
		       
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(eleve.class.getResource("/img/supprimer.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(388, 444, 154, 37);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					Statement stmn;
					stmn= connexion.getconnect().createStatement();
					java.util.Date date=dateChooser.getDate();
					java.sql.Date sqldate = new java.sql.Date(date.getTime());
					String s;
					if (rdbtnNewRadioButton.isSelected())
						s="Masculin";
					else 
						s="Feminin";
		            if (JOptionPane.showConfirmDialog (null,"confirmer la modification","modification",
		                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

		                stmn.executeUpdate("UPDATE eleve SET Nom='"+nom.getText()+"',prenom='"+prenom.getText()+"',sexe='"+s+"',date='"+sqldate+"',id_f='"+idlib(comboBox.getSelectedItem().toString())+
		                        "' WHERE id= "+id.getText());
		                affiche();
		                //afficher ();
		            } 
		        } catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de modification!!!!!!!");
		        System.err.println(e1);}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(eleve.class.getResource("/img/modifier.gif")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(199, 444, 151, 37);
		panel.add(btnNewButton_2);
		panel.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("filiere :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(71, 368, 91, 35);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton_3 = new JButton("Quitter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(eleve.class.getResource("/img/sortie.gif")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setBounds(594, 446, 154, 37);
		panel.add(btnNewButton_3);
		affiche();
		
		
		
	}
	public void affiche ()
	{
		try {
			String requete ="SELECT eleve.id, eleve.nom, eleve.prenom,eleve.date,eleve.sexe,filiere.libelle FROM `filiere`,`eleve` WHERE filiere.id_fil=eleve.id_f";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
	}
	//matiere
    private void deplace(int i)
    {
       try {     //i represente les ligne 
     
         id.setText(table.getValueAt (i, 0).toString());
      // txttp.setSelectedItem(dt.getValueAt (i, 5).toString());
        nom.setText(table.getValueAt (i, 1).toString());
        // txtb.setSelectedItem(dt.getValueAt (i, 2).toString());
         prenom.setText(table.getValueAt (i, 2).toString());
         
         
      
       
       }catch (Exception e){ JOptionPane.showMessageDialog(null,"erreur de deplacement de message !!!!! "+e.getMessage());}
      }
    public ArrayList colmat()
	{
		String query = " select libelle from filiere  ";
		ArrayList<String> list = new ArrayList<String>();

		try {
			Statement state  =connexion.getconnect().createStatement();
			ResultSet res =state.executeQuery(query);
			while (res.next())
			{
				list.add(res.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  list;
	}
    public String idlib(String s)
   	{
   		String query = " SELECT `id_fil` FROM `filiere` WHERE filiere.libelle='"+s+"' ";
   		ArrayList<String> list = new ArrayList<String>();

   		try {
   			Statement state  =connexion.getconnect().createStatement();
   			ResultSet res =state.executeQuery(query);
   			while (res.next())
   			{
   				list.add(res.getString("id_fil"));
   			}
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}return list.get(0);
   }
    }