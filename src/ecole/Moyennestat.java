package ecole;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import conx.connexion;
import demo.orsoncharts.BarChart3D1;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Moyennestat extends JFrame
{
	private JTable table;
	public Moyennestat() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		setSize(826,700);
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 744, 606);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 54, 619, 404);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Moyennes");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(202, 10, 202, 33);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Quitter\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Moyennestat.class.getResource("/img/sortie.gif")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(226, 508, 228, 39);
		panel.add(btnNewButton_1);
		affiche();
		
	}
	public void affiche ()
	{
		try {
			String requete ="SELECT * FROM  moyenne";
			PreparedStatement  ps =connexion.getconnect().prepareStatement(requete);
			ResultSet res=ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
		}catch (Exception exp) {
			System.out.println("exception="+exp);
		}
		
	}
}
