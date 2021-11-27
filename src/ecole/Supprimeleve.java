package ecole;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import conx.connexion;

public class Supprimeleve extends JFrame {
	private JTextField textid;
	public Supprimeleve() {
		setVisible(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 262);
		getContentPane().add(panel);
		panel.setBorder( BorderFactory. createTitledBorder( "suppression d'elève : ") ) ;
		panel.setLayout(null);
		
		textid = new JTextField();
		textid.setBounds(191, 60, 153, 39);
		panel.add(textid);
		textid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id d'\u00E9leve \u00E0 supprimer");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 64, 171, 26);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id=textid.getText();
				String req="delete from eleve where id ='"+id+"'";
				Statement state;
				try {
					
					state = connexion.getconnect().createStatement();
				int res = state.executeUpdate(req);
				if(res!=0){
					 //state.execute(requete);
						setVisible( false) ;
						JOptionPane.showMessageDialog(null, "eleve effacé", "info", JOptionPane.INFORMATION_MESSAGE);
						}
					 //state.execute(requete);
						setVisible( false) ;
						//res.close();
						state.close();
				} catch (SQLException exp) {
					JOptionPane.showMessageDialog(null, exp.getMessage(), "info", JOptionPane.INFORMATION_MESSAGE);
					exp.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(112, 136, 109, 47);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("annuler");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(247, 136, 109, 47);
		panel.add(btnNewButton_1);
	}

}
