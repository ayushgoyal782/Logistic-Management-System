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

public class TyreReport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TyreReport frame = new TyreReport();
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
	public TyreReport() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setResizable(false);
		setTitle("Tyre Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(239, 130, 315, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVehicleNumber = new JLabel("Vehicle Number");
		lblVehicleNumber.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblVehicleNumber.setBounds(78, 11, 158, 20);
		panel.add(lblVehicleNumber);
		
		final Choice vno = new Choice();
		vno.setBounds(87, 49, 140, 20);
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
		
		JButton button = new JButton("Show");
		button.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		button.setBounds(117, 93, 80, 25);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()).setWidth(50),
					  	col.column("Date",		"date",  type.stringType()).setWidth(110),
					  	col.column("Vehicle No","vno",  type.stringType()).setWidth(125),
					  	col.column("Tyre Position",	"position", type.stringType()).setWidth(200),
					  	col.column("Tyre No",	"tno", type.stringType()).setWidth(200),
					  	col.column("Odometer Reading",		"odo", type.stringType()),
					  	col.column("Remarks",		"remarks", type.stringType()))
					  .title(Templates.createTitleComponent("Tyre Report for Vehicle No."+((String)vno.getItem(vno.getSelectedIndex()))))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","vno", "position","tno","odo","remarks");
				
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select sno, date, vehicle_no, tyre_position, tyre_no, odoreading, remarks from tyre WHERE vehicle_no='"+((String)vno.getItem(vno.getSelectedIndex()))+"'");
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
		
		JLabel lblTyreReport = new JLabel("Tyre Report");
		lblTyreReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblTyreReport.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTyreReport.setBounds(261, 39, 271, 31);
		contentPane.add(lblTyreReport);
		
		
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
