package View;


import java.awt.Choice;
import java.awt.EventQueue;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;

import Models.Connector;

import javax.swing.border.BevelBorder;

public class TripReport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripReport frame = new TripReport();
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
	public TripReport() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setTitle("Trip Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setOpaque(false);
		panel.setBounds(239, 130, 315, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(101, 93, 113, 25);
		panel.add(btnNewButton);
		
		JLabel lblVehicleNo = new JLabel("For Specific Vehicle Number");
		lblVehicleNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleNo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVehicleNo.setBounds(10, 11, 295, 25);
		panel.add(lblVehicleNo);
		
		final Choice vno = new Choice();
		vno.setBounds(87, 49, 140, 20);
		panel.add(vno);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
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
					  .title(Templates.createTitleComponent("Trip Report for Vehicle No."+((String)vno.getItem(vno.getSelectedIndex()))))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e) {
					e.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","vno", "from", "to","weight","rate","freight","advance","balance","transporter","terms","remarks");
				
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select Sno, Date, Vehicle_no, Fr, Tpo, Weight, Rate, Freight, Advance, Balance, Transporter, Terms, Remarks from trip WHERE Vehicle_no='"+((String)vno.getItem(vno.getSelectedIndex()))+"'");
	        		while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11),rs.getString(12), rs.getString(13));
	        			
	        		}
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
		
		JLabel lblTripReport = new JLabel("Trip Report");
		lblTripReport.setBounds(312, 47, 169, 31);
		lblTripReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripReport.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblTripReport);
		try{
			Connection con=Connector.getConnection();
		
	    	Statement st = con.createStatement();			            
	    	ResultSet rs = st.executeQuery("Select DISTINCT vehicle_no from vehicle_details");
	    		
	    	// while loop and with while loop code use for print the data
	    	while (rs.next()) 
	    	{
	    		vno.add(rs.getString(1));
	    	}
	    	st.close();
	    	con.close();
    	}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("rep.jpg");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		lblNewLabel.setBounds(0, 0, 794, 572);
		contentPane.add(lblNewLabel);
		
	}
}
