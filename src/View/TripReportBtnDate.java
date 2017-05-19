package View;

import java.awt.BorderLayout;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
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

public class TripReportBtnDate extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripReportBtnDate frame = new TripReportBtnDate();
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
	public TripReportBtnDate() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setTitle("Trip Report Between Dates");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnShow = new JButton("Show");
		btnShow.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		btnShow.setBounds(128, 93, 80, 25);
		panel.add(btnShow);
		
		final JXDatePicker to = new JXDatePicker();
		to.setBounds(128, 44, 140, 20);
		panel.add(to);
		
		final JXDatePicker from = new JXDatePicker();
		from.setBounds(128, 11, 140, 20);
		panel.add(from);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Sitka Display", Font.BOLD, 20));
		lblTo.setBounds(39, 38, 32, 26);
		panel.add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Sitka Display", Font.BOLD, 20));
		lblFrom.setBounds(39, 5, 55, 26);
		panel.add(lblFrom);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
        		

				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()),
					  	col.column("Date",		"date",  type.stringType()),
					  	col.column("Vehicle No","vno",  type.stringType()),
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
					  .title(Templates.createTitleComponent("Trip Report between "+formater.format(from.getDate()) +" and "+formater.format(to.getDate())))
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
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();	
	        		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");	        		
	        		ResultSet rs = st.executeQuery("Select Sno, Date, Vehicle_no, Fr, Tpo, Weight, Rate, Freight, Advance, Balance, Transporter, Terms, Remarks from trip WHERE date>='"+formater.format(from.getDate())+"' and date<='"+formater.format(to.getDate())+"'");
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
		
		JLabel lblTripReportBetween = new JLabel("Trip Report Between Dates");
		lblTripReportBetween.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripReportBetween.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTripReportBetween.setBounds(250, 40, 287, 31);
		contentPane.add(lblTripReportBetween);
		
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
