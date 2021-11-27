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

public class application extends JFrame
{
	public application()
	{
		setSize(700,700);
		setResizable(true);
		this.setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("nouveau");
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("nouveau eleve");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleve el=new eleve();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("nouveau enseignant");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enseignant ens =new Enseignant();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("nouvelle matiere");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("exit");
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		mnNewMenu_1.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("suppression");
		mnNewMenu_2.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("supprimer un eleve ");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion d'eleves");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menueleve m=new Menueleve();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(73, 54, 142, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestion de matiere");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBounds(73, 159, 142, 64);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Gestion d'enseignants");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setBounds(61, 272, 154, 64);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\java\\eclipse\\developpement\\workspace\\essaiapp\\tsawer\\e.jpg"));
		lblNewLabel.setBounds(245, 54, 420, 285);
		getContentPane().add(lblNewLabel);
	}	
	public static void main(String[]args) 
	{
		application app=new application();

	}
	}

