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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class matiereparfiliere extends JFrame 
{
	private JTable table;
	private int i;
	public matiereparfiliere() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		setSize(826,700);
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 652, 438);
		getContentPane().add(panel);
		panel.setBorder( BorderFactory. createTitledBorder( "matière par filière: ") ) ;
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(155, 68, 126, 21);
		for(i=0;i<colmat().size();i++)
		{comboBox.addItem(colmat().get(i));}
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("matiere :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(60, 54, 110, 40);
		panel.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(155, 163, 126, 21);
		for(i=0;i<colfil().size();i++)
		{comboBox_1.addItem(colfil().get(i));}
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("fili\u00E8re :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1.setBounds(60, 155, 85, 28);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 33, 300, 270);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=table.getSelectedRow();
				comboBox_1.setSelectedItem(table.getValueAt (j,0).toString());
				comboBox.setSelectedItem(table.getValueAt (j,1).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Affecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matiere=comboBox.getSelectedItem().toString();
				String id_mat=idlib(matiere);
				String filiere=comboBox_1.getSelectedItem().toString();
				String id_fil=idlibfil(filiere);
				String requete ="INSERT INTO `matierefiliere` (`idmatfil`, `idfil`, `idmat`) VALUES (NULL, '"+id_fil+"', '"+id_mat+"')";
				Statement state;
				try {
					
					state = connexion.getconnect().createStatement();
				int res = state.executeUpdate(requete);
				if(res!=0){
					 //state.execute(requete);
						//setVisible( false) ;
						JOptionPane.showMessageDialog(null, "matiere affecté à une filière avec succès", "info", JOptionPane.INFORMATION_MESSAGE);
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
		btnNewButton.setIcon(new ImageIcon(matiereparfiliere.class.getResource("/img/ajouter.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(43, 243, 232, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quitter\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setIcon(new ImageIcon(matiereparfiliere.class.getResource("/img/sortie.gif")));
		btnNewButton_1.setBounds(481, 325, 139, 35);
		panel.add(btnNewButton_1);
		affiche();
	}
	public void affiche ()
	{
		try {
			String requete ="SELECT distinct filiere.libelle , matiere.libelle FROM `matierefiliere`,matiere ,filiere WHERE matierefiliere.idmat=matiere.id_mat AND matierefiliere.idfil=filiere.id_fil";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
	}
	 public ArrayList colmat()
		{
			String query = " select libelle from matiere  ";
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
	 public ArrayList colfil()
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
