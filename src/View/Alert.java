package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Alert extends JFrame {

	private JPanel contentPane;
	private JXDatePicker date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alert frame = new Alert();
					
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
	public Alert() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setAlwaysOnTop(true);
		setTitle("Alerts");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 404);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlerts = new JLabel("Alerts");
		lblAlerts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerts.setFont(new Font("Calibri", Font.BOLD, 25));
		lblAlerts.setBounds(10, 11, 524, 31);
		contentPane.add(lblAlerts);
		
		date = new JXDatePicker();
		date.setBounds(0, 0, 0, 0);
		date.setDate(Calendar.getInstance().getTime());
		contentPane.add(date);
		
		TextArea textArea = new TextArea();
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			Connection con=Connector.getConnection();
			
	    	Statement st = con.createStatement();			            
	    	ResultSet rs = st.executeQuery("Select vehicle_no,cgtaxupto,insuranceupto,authoupto,fitnessupto,npupto,pucupto from vehicle_details");
	    		ArrayList<String> vno= new ArrayList<String>();
	    		ArrayList<String> taxupto= new ArrayList<String>();
	    		ArrayList<String> insuranceupto= new ArrayList<String>();
	    		ArrayList<String> authoupto= new ArrayList<String>();
	    		ArrayList<String> fitnessupto= new ArrayList<String>();
	    		ArrayList<String> npupto= new ArrayList<String>();
	    		ArrayList<String> pucupto= new ArrayList<String>();
	    		
	    	// while loop and with while loop code use for print the data
	    	while (rs.next()) 
	    	{
	    		vno.add(rs.getString(1));
	    		taxupto.add(rs.getString(2));
	    		insuranceupto.add(rs.getString(3));
	    		authoupto.add(rs.getString(4));
	    		fitnessupto.add(rs.getString(5));
	    		npupto.add(rs.getString(6));
	    		pucupto.add(rs.getString(7));
	    	}
	    	st.close();
	    	con.close();
			for(int x=0;x<vno.size();x++)
			{	Date tax = formater.parse(taxupto.get(x));
				Date ins=formater.parse(insuranceupto.get(x));
				Date autho=formater.parse(authoupto.get(x));
				Date fit=formater.parse(fitnessupto.get(x));
				Date np=formater.parse(npupto.get(x));
				Date puc=formater.parse(pucupto.get(x));
				Date today=date.getDate();
				long diff = tax.getTime()- today.getTime();//in milliseconds
				int c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nCgtax expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				diff = ins.getTime()- today.getTime();//in milliseconds
				c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nInsurance expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				diff = autho.getTime()- today.getTime();//in milliseconds
				c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nAuthorization expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				diff = fit.getTime()- today.getTime();//in milliseconds
				c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nFitness expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				diff = np.getTime()- today.getTime();//in milliseconds
				c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nNational Permit expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				diff = puc.getTime()- today.getTime();//in milliseconds
				c= (int) (diff/(1000*60*60*24));
				if(c<=7 && c>=0)
				{
					textArea.append("\nPUC expiring soon for vehicle no "+vno.get(x)+" in "+c+" days\n");
				}
				
			}
		} catch (ParseException e2) {
			e2.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textArea.setBounds(10, 48, 524, 308);
		contentPane.add(textArea);
	}
}
