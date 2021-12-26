package ecole;

import javax.swing.*;

import conx.connexion;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Note extends JFrame 
{
	private JTextField note;
	private JTable table;
	private JTable table_1;
	
	public Note() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		setSize(1019,746);
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 985, 677);
		getContentPane().add(panel);
		panel.setLayout(null);
		int i;
		panel.setBorder( BorderFactory. createTitledBorder( "Gestion des notes  ") ) ;
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 71, 120, 26);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("matiere :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(58, 69, 91, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Note : \r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(74, 136, 97, 20);
		panel.add(lblNewLabel_2);
		
		note = new JTextField();
		note.setBounds(158, 136, 127, 29);
		panel.add(note);
		note.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 55, 490, 226);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		//ki tkliki yselecty filiere f box w yzidlk matieret lfiliere edhyka f box
				int j=table.getSelectedRow();
				note.setText("");
				String f=table.getValueAt(j,3).toString();
				comboBox.removeAllItems();
				int k;
				for(k=0;k<colmat(f).size();k++)
				{comboBox.addItem(colmat(f).get(k));}

			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("s\u00E9lectionner un \u00E9l\u00E8ve pour lui affecter une note :");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(448, 10, 500, 35);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("affecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=table.getSelectedRow();
				String m=comboBox.getSelectedItem().toString();//matiere
				String id_mat=idlib(m);//id matiere ml box
				String n = note.getText();
				String f =table.getValueAt(i,3).toString();
				String id_fil=idlibfil(f);//idfiliere
				String id_el=table.getValueAt(i,0).toString();
				String requete="INSERT INTO `note` values(NULL, '"+n+"', '"+id_el+"', '"+id_fil+"', '"+id_mat+"')";
				Statement state;
				try {
					
					state = connexion.getconnect().createStatement();
				int res = state.executeUpdate(requete);
				if(res!=0){
					 //state.execute(requete);
						//setVisible( false) ;
						JOptionPane.showMessageDialog(null, "note affecté avec succès", "info", JOptionPane.INFORMATION_MESSAGE);
						affiche();
						affichenote();
						}
						//setVisible( false) ;
						state.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "info", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(Note.class.getResource("/img/ajouter.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(25, 197, 161, 42);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int l=table_1.getSelectedRow();
				try { 
					
					Statement stmn;
					stmn= connexion.getconnect().createStatement();
					String s=note.getText();
					String no = table_1.getValueAt(l, 0).toString();
		            if (JOptionPane.showConfirmDialog (null,"confirmer la modification","modification",
		                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

		                stmn.executeUpdate("UPDATE note SET note='"+s+"' where id_note='"+no+"'");
		                affichenote ();
		            } 
		        } catch (Exception e1){JOptionPane.showMessageDialog(null,"erreur de modification!!!!!!!");
		        System.err.println(e1);}
			}
			{
			}});
		btnNewButton_1.setIcon(new ImageIcon("D:\\java\\eclipse\\developpement\\workspace\\gestion_d'ecole\\images\\img\\modifier.gif"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(221, 197, 158, 43);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 312, 884, 269);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int r=table_1.getSelectedRow();
				comboBox.removeAllItems();
				comboBox.addItem(table_1.getValueAt(r,3).toString());
				note.setText(table_1.getValueAt (r,4).toString());
				
				
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel = new JLabel("s\u00E9lectionner une note \u00E0 modifier :\r\n");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(44, 269, 350, 35);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("D:\\java\\eclipse\\developpement\\workspace\\gestion_d'ecole\\images\\img\\sortie.gif"));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_2.setBounds(252, 610, 172, 42);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Moyennes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Moyennestat m= new Moyennestat();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Note.class.getResource("/img/stats.gif")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_3.setBounds(41, 610, 172, 42);
		panel.add(btnNewButton_3);
		affiche();
		affichenote();
	}
	public void affiche ()
	{
		try {
			String requete ="SELECT  id,`nom`, `prenom`, filiere.libelle FROM `eleve`,filiere WHERE eleve.id_f =filiere.id_fil";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
	}
	public void affichenote()
	{
		try {
			String requete ="SELECT DISTINCT note.id_note ,`nom`, `prenom`, matiere.libelle,note.note FROM `eleve`,matiere,note WHERE matiere.id_mat=note.id_matiere and eleve.id=note.id_eleve ORDER BY eleve.id ";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(res));
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
		
	}
	public ArrayList colmat(String filiererempli)
	{
		String query = "SELECT distinct matiere.libelle FROM `matierefiliere` , matiere,filiere WHERE matierefiliere.idmat=matiere.id_mat AND matierefiliere.idfil=filiere.id_fil AND filiere.libelle='"+filiererempli+"'";
		ArrayList<String> list = new ArrayList<String>();

		try {
			Statement state  =connexion.getconnect().createStatement();
			ResultSet res =state.executeQuery(query);
			while (res.next())
			{
				list.add(res.getString("libelle"));
			}
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  list;
	}
	public String idlib(String s)
	{
		String query = " SELECT `id_mat` FROM `matiere` WHERE matiere.libelle='"+s+"' ";
		ArrayList<String> list = new ArrayList<String>();

		try {
			Statement state  =connexion.getconnect().createStatement();
			ResultSet res =state.executeQuery(query);
			while (res.next())
			{
				list.add(res.getString("id_mat"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}return list.get(0);}
	public String idlibfil(String s)
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
