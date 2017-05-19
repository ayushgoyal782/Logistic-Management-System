package View;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.SwingConstants;
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

public class MaintenanceReport extends JFrame {

	private JPanel contentPane;
	private  Choice vno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaintenanceReport frame = new MaintenanceReport();
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
	public MaintenanceReport() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setTitle("Maintenance Report");
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(235, 130, 315, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		vno = new Choice();
		vno.setBounds(88, 49, 140, 20);
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
		panel.add(vno);
		
		JLabel lblVehicleNo = new JLabel("Vehicle Number");
		lblVehicleNo.setBounds(91, 11, 158, 20);
		panel.add(lblVehicleNo);
		lblVehicleNo.setFont(new Font("Sitka Display", Font.BOLD, 20));
		
		JButton btnShow = new JButton("Show");
		btnShow.setBounds(122, 93, 80, 25);
		panel.add(btnShow);
		btnShow.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()).setWidth(50),
					  	col.column("Date",		"date",  type.stringType()).setWidth(110),
					  	col.column("Vehicle No","vno",  type.stringType()).setWidth(125),
					  	col.column("Maintenance Type",	"type", type.stringType()).setWidth(200),
					  	col.column("Odometer Reading",		"odo", type.stringType()),
					  	col.column("Remarks",		"remarks", type.stringType()))
					  .title(Templates.createTitleComponent("Maintenance Report for Vehicle No."+((String)vno.getItem(vno.getSelectedIndex()))))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","vno", "type","odo","remarks");
				
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select sno, date, vno, mtype, odoreading, remarks from maintenance_details WHERE vno='"+((String)vno.getItem(vno.getSelectedIndex()))+"'");
	        		while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
	        			
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
		
		JLabel lblMaintenanceReport = new JLabel("Maintenance Report");
		lblMaintenanceReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaintenanceReport.setFont(new Font("Sitka Heading", Font.BOLD, 25));
		lblMaintenanceReport.setBounds(240, 40, 293, 31);
		contentPane.add(lblMaintenanceReport);
		
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
