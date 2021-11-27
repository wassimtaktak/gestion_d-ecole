package ecole;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Enseignant extends JFrame
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public Enseignant() {
		setSize(700,700);
		getContentPane().setLayout(null);
		this.setVisible(true);
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBounds(10, 10, 600, 419);
		getContentPane().add(panel);
		panel.setBorder( BorderFactory. createTitledBorder( "Informations d'enseignant : ") ) ;
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(234, 38, 187, 37);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id enseignant:");
		lblNewLabel.setBounds(67, 34, 165, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(234, 99, 187, 37);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(160, 102, 72, 22);
		panel.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(234, 162, 187, 37);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Prenom:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(131, 164, 103, 25);
		panel.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(234, 234, 187, 39);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("date de naissance:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(31, 224, 201, 51);
		panel.add(lblNewLabel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("masculin",true);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton.setBounds(234, 320, 103, 21);
		ButtonGroup sexe = new ButtonGroup();
		sexe.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("feminin",false);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNewRadioButton_1.setBounds(363, 322, 103, 21);
		sexe.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("sexe:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(160, 310, 62, 37);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(193, 377, 122, 37);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(344, 377, 122, 37);
		panel.add(btnNewButton_1);
	}
}
