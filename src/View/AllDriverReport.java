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
import javax.swing.SwingConstants;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class AllDriverReport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllDriverReport frame = new AllDriverReport();
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
	public AllDriverReport() {
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
		setTitle("All Driver Details Report");
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
		panel.setBounds(235, 130, 315, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("To");
		label_2.setBounds(39, 45, 46, 26);
		panel.add(label_2);
		label_2.setFont(new Font("Sitka Banner", Font.BOLD, 20));
		
		final JXDatePicker to = new JXDatePicker();
		to.setBounds(128, 48, 140, 20);
		panel.add(to);
		
		final JXDatePicker from = new JXDatePicker();
		from.setBounds(128, 14, 140, 20);
		panel.add(from);
		
		JLabel label_1 = new JLabel("From");
		label_1.setBounds(39, 11, 59, 26);
		panel.add(label_1);
		label_1.setFont(new Font("Sitka Display", Font.BOLD, 20));
		
		JButton button = new JButton("Show");
		button.setBounds(128, 93, 80, 25);
		panel.add(button);
		button.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()),
					  	col.column("Date",		"date",  type.stringType()),
					  	col.column("Name",		"name", type.stringType()),
					  	col.column("Address",	"address", type.stringType()),
					  	col.column("Vehicle No","vno",  type.stringType()),
					  	col.column("Joining Date",	"jdate", type.stringType()),
					  	col.column("License No.","lno", type.stringType()),
					  	col.column("Salary","salary", type.stringType()),
					  	col.column("Referenced by",		"ref", type.stringType()),
					  	col.column("Remarks",		"remarks", type.stringType()))
					  .title(Templates.createTitleComponent("All Driver Details between "+formater.format(from.getDate()) +" and "+formater.format(to.getDate())))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "date","name","address","vno","jdate","lno","salary","ref","remarks");
				
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();	
	        		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");	
	        		ResultSet rs = st.executeQuery("Select sno, date, name, address, vno, joining_date, license_no, salary, referenced_by, remarks from driver_details WHERE date>='"+formater.format(from.getDate())+"' and date<='"+formater.format(to.getDate())+"'");
	        		while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10));
	        			
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
		
		JLabel lblAllDriverReport = new JLabel("All Driver Details Report");
		lblAllDriverReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllDriverReport.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAllDriverReport.setBounds(250, 40, 271, 31);
		contentPane.add(lblAllDriverReport);
		
		JLabel lblNewLabel = new JLabel("");
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("rep.jpg");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		lblNewLabel.setBounds(0, 0,794, 572);
		contentPane.add(lblNewLabel);
	}

}
