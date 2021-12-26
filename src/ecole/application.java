package ecole;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.UIManager;



public class application extends JFrame
{
	public application()
	{
		setSize(636,657);
		setResizable(false);
		this.setVisible(true);
		JButton btnNewButton = new JButton("Gestion des eleves");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setFont(new Font("Colored Crayons", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleve ele=new eleve();
			}
		});
		
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(43, 54, 254, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestion des matieres");
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matiere m=new matiere();
			}
		});
		btnNewButton_1.setFont(new Font("Colored Crayons", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(334, 54, 223, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Gestion des enseignants");
		btnNewButton_2.setBackground(new Color(0, 128, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enseignant ens = new Enseignant();
			}
		});
		btnNewButton_2.setFont(new Font("Colored Crayons", Font.BOLD, 18));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(43, 154, 254, 64);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("gestion des filieres");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filiere f=new filiere();
			}
		});
		btnNewButton_3.setFont(new Font("Colored Crayons", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(0, 128, 0));
		btnNewButton_3.setBounds(334, 155, 223, 62);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("matiere/filiere");
		btnNewButton_4.setBackground(new Color(0, 128, 0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				matiereparfiliere matfil= new matiereparfiliere();
			}
		});
		btnNewButton_4.setFont(new Font("Colored Crayons", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBounds(43, 272, 254, 58);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("gestion des notes");
		btnNewButton_5.setBackground(new Color(0, 128, 0));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Note n=new Note();
			}
		});
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setFont(new Font("Colored Crayons", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_5.setBounds(334, 269, 223, 64);
		getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon(application.class.getResource("/img/background1.jpg")));
		getContentPane().add(lblNewLabel);
		lblNewLabel.setBounds(0, 0, 622, 620);
		getContentPane().add(lblNewLabel);
		
	}	
	public static void main(String[]args) 
	{
		application app=new application();

	}
	}

