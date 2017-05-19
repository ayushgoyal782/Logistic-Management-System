package View;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import Models.Connector;

public class DriverPayReport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverPayReport frame = new DriverPayReport();
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
	public DriverPayReport() {
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
		setTitle("Driver/Helper Pay Report");
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
		
		JLabel lblDriverhelperName = new JLabel("Driver/helper Name");
		lblDriverhelperName.setBounds(74, 11, 191, 26);
		panel.add(lblDriverhelperName);
		lblDriverhelperName.setFont(new Font("Sitka Display", Font.BOLD, 20));
		
		final Choice name = new Choice();
		name.setBounds(88, 49, 140, 20);
		panel.add(name);
		
		JButton button = new JButton("Show");
		button.setBounds(112, 93, 80, 25);
		panel.add(button);
		button.setFont(new Font("Sitka Banner", Font.BOLD, 16));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					report()
					  .setTemplate(Templates.reportTemplate)
					  .columns(
					  	col.column("Sno",		"no",      type.stringType()).setWidth(50),
					  	col.column("Name","name",  type.stringType()).setWidth(125),
					  	col.column("Date",		"date",  type.stringType()).setWidth(110),					  	
					  	col.column("Amount",	"amount", type.stringType()).setWidth(200),
					  	col.column("Remarks",		"remarks", type.stringType()))
					  .title(Templates.createTitleComponent("Payment Report for Driver/Helper Name:- "+((String)name.getItem(name.getSelectedIndex()))))
					  .pageFooter(Templates.footerComponent)
					  .setDataSource(createDataSource())
					  .show(false);
					 

					
				} catch (DRException e1) {
					e1.printStackTrace();
				}
			}

			private JRDataSource createDataSource() {
				DRDataSource dataSource = new DRDataSource("no", "name","date", "amount","remarks");
				
				try {
	            	double total=0,amount=0;
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select sno, name, date, amount, remarks from pay WHERE name='"+((String)name.getItem(name.getSelectedIndex()))+"'");
	        		while (rs.next()) 
	        		{  
	        			dataSource.add(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5));
	        			amount=Double.parseDouble(rs.getString(4));
	        			total=total+amount;
	        		}
	        		dataSource.add("","","","Total Amount",""+amount,"");
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
		
		JLabel lblDriverPayReport = new JLabel("Driver/Helper Pay Report");
		lblDriverPayReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblDriverPayReport.setFont(new Font("Sitka Heading", Font.BOLD, 25));
		lblDriverPayReport.setBounds(250, 40, 305, 31);
		contentPane.add(lblDriverPayReport);
		try{
	        Connection con=Connector.getConnection();
	    	Statement st = con.createStatement();			            
	    	ResultSet rs = st.executeQuery("Select DISTINCT name from driver_details");
	    		
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
