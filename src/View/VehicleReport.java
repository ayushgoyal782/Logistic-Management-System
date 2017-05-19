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

import Models.Connector;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class VehicleReport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleReport frame = new VehicleReport();
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
	public VehicleReport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		setTitle("All Vehicle Details");
		setBounds(100, 100, 325, 140);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllVehicleReport = new JLabel("All Vehicle Details");
		lblAllVehicleReport.setBounds(12, 5, 294, 31);
		lblAllVehicleReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllVehicleReport.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblAllVehicleReport);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
					  .columns(
					  	col.column("Sno",			"no",      type.stringType()).setWidth(50),
					  	col.column("Date",			"date",  type.stringType()).setWidth(110),
					  	col.column("Vehicle No",	"vno",  type.stringType()).setWidth(125),
					  	col.column("Owner Name",	"name", type.stringType()),
					  	col.column("CG Tax",		"cg", type.stringType()),
					  	col.column("Cg Tax Upto",	"cgupto", type.stringType()),
					  	col.column("Insurance",		"ins", type.stringType()),
					  	col.column("Insurance Upto","insupto", type.stringType()),
					  	col.column("Authorization",		"autho", type.stringType()),
					  	col.column("Authorization Upto","authoupto", type.stringType()),
					  	col.column("Fitness",			"fitness", type.stringType()),
					  	col.column("Fitness Upto",		"fitnessupto", type.stringType()),
						col.column("National Permit",	"np", type.stringType()),
					  	col.column("National Permit Upto","npupto", type.stringType()),
					  	col.column("P.U.C",		"puc", type.stringType()),
					  	col.column("P.U.C Upto",	"pucupto", type.stringType()))
					  .title(Templates.createTitleComponent("List Of All Vehicle"))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					
					
				} catch (DRException e) {
					e.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","vno", "name", "cg","cgupto","ins","insupto","autho","authoupto","fitness","fitnessupto","np","npupto","puc","pucupto");
				
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		 ResultSet rs = st.executeQuery("Select sno, date, vehicle_no, owner_name, cgtax, cgtaxupto, insurance, insuranceupto, autho, authoupto, fitness, fitnessupto, np, npupto, puc, pucupto from vehicle_details");
					 while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11),rs.getString(12), rs.getString(13), rs.getString(14),rs.getString(15), rs.getString(16));
	        			
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
		btnShow.setBounds(115, 52, 89, 23);
		contentPane.add(btnShow);
		//btnShow.doClick();
		
	}
}
