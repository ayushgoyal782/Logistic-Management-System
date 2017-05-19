package View;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;

import Models.Connector;

import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.awt.Color;

public class BalanceAmountReport extends JFrame {

	private JPanel contentPane;
	private JLabel lblPartyName;
	private Choice name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BalanceAmountReport frame = new BalanceAmountReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BalanceAmountReport() {
		setResizable(false);
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setTitle("Balance Amount Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.inactiveCaptionBorder);
		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(235, 130, 315, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnShow = new JButton("Show");
		btnShow.setBounds(112, 93, 80, 25);
		Font bt2 = new Font("Sitka Display", Font.ITALIC,12);
		btnShow.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		panel.add(btnShow);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()).setWidth(50),
					  	col.column("Date",		"date",  type.stringType()).setWidth(110),
					  	col.column("Vehicle No","vno",  type.stringType()).setWidth(125),
					  	col.column("From",		"from", type.stringType()),
					  	col.column("To",		"to", type.stringType()),
					  	col.column("Weight","weight", type.stringType()),
					  	col.column("Rate",	"rate", type.stringType()),
					  	col.column("Freight",		"freight", type.stringType()),
					  	col.column("Advance",		"advance", type.stringType()),
					  	col.column("Balance",		"balance", type.stringType()),
					  	col.column("transporter",		"transporter", type.stringType()),
					  	col.column("Payment Terms",		"terms", type.stringType()),
					  	col.column("Remarks",		"remarks", type.stringType()))
					  .title(Templates.createTitleComponent("Balance Report for Transporter :-"+((String)name.getItem(name.getSelectedIndex()))))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","vno", "from", "to","weight","rate","freight","advance","balance","transporter","terms","remarks");
				
				try {
	            	double total=0,balance=0;
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select Sno, Date, Vehicle_no, Fr, Tpo, Weight, Rate, Freight, Advance, Balance, Transporter, Terms, Remarks from trip WHERE Transporter='"+((String)name.getItem(name.getSelectedIndex()))+"'");
	        		while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11),rs.getString(12), rs.getString(13));
	        			balance=Double.parseDouble(rs.getString(10));
	        			total=balance+total;
	        		}
	        		dataSource.add("","","", "","","","","","Total",""+total,"","","");
        			st.close();
	        		con.close();
					} 
					catch (Exception ex)
                     {
							System.err.print("Exception: ");
							System.err.println(ex.getMessage());
                     }
				
				return dataSource;
				
			}
		});
		
		lblPartyName = new JLabel("Transporter Name");
		lblPartyName.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblPartyName.setBounds(74, 11, 180, 25);
		panel.add(lblPartyName);
		
		name = new Choice();
		name.setForeground(Color.BLACK);
		name.setBackground(Color.WHITE);
		name.setBounds(88, 49, 140, 20);
		panel.add(name);
		
		JLabel lblBalanceAmountReport = new JLabel("Balance Amount Report");
		lblBalanceAmountReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanceAmountReport.setFont(new Font("Calibri", Font.BOLD, 25));
		lblBalanceAmountReport.setBounds(221, 43, 347, 31);
		contentPane.add(lblBalanceAmountReport);
		try{
			Connection con=Connector.getConnection();
		
	    	Statement st = con.createStatement();			            
	    	ResultSet rs = st.executeQuery("Select DISTINCT Transporter from trip");
	    		
	    	// while loop and with while loop code use for print the data
	    	while (rs.next()) 
	    	{
	    		name.add(rs.getString(1));
	    	}
	    	st.close();
	    	con.close();
    	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel label = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("rep.jpg");
		try {
			label.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		label.setBounds(0, 0, 794, 572);
		contentPane.add(label);
	}
}
