package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Choice;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class DriverPay extends JFrame {

	private JPanel contentPane;
	private JTextField amount;
	private JTextField remarks;
	private JTable table;
	private JTextField sno;
	private static Choice name;
	private JButton btnSave;
	private JButton button_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverPay frame = new DriverPay();
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
	public DriverPay() {
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
		setTitle("Driver or Helper Pay");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(01, 83, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLocation(28, -53);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDriverOrHelper = new JLabel("Driver or Helper Pay");
		lblDriverOrHelper.setBounds(163, 11, 464, 31);
		lblDriverOrHelper.setHorizontalAlignment(SwingConstants.CENTER);
		lblDriverOrHelper.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblDriverOrHelper);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(173, 53, 439, 135);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDriverhelperName = new JLabel("Driver/Helper Name");
		lblDriverhelperName.setBounds(85, 35, 109, 14);
		panel.add(lblDriverhelperName);
		
		name = new Choice();
		name.setBounds(200, 33, 160, 20);
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
		panel.add(name);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(85, 60, 58, 14);
		panel.add(lblDate);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(200, 56, 160, 22);
		panel.add(date);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(85, 85, 46, 14);
		panel.add(lblAmount);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(85, 110, 46, 14);
		panel.add(lblRemarks);
		
		amount = new JTextField();
		amount.setBounds(200, 82, 160, 20);
		panel.add(amount);
		amount.setColumns(10);
		
		remarks = new JTextField();
		remarks.setBounds(200, 107, 160, 20);
		panel.add(remarks);
		remarks.setColumns(10);
		
		JLabel lblSno = new JLabel("S.No.");
		lblSno.setBounds(85, 14, 46, 14);
		panel.add(lblSno);
		
		sno = new JTextField();
		sno.setColumns(10);
		sno.setBounds(200, 11, 160, 20);
		panel.add(sno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(163, 229, 464, 115);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Driver/Helper Name", "Date", "Amount", "Remarks"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int s=0;
				try{
				
				Connection con=Connector.getConnection();
        		Statement st = con.createStatement();			            
        		ResultSet rs = st.executeQuery("Select max(sno) from pay");
        		rs.next();
        		s=rs.getInt(1);
        		s=s+1;
        		st.close();
        		con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				finally{}	
				
				sno.setText(""+s);
				amount.setText("");
				remarks.setText("");
				date.setDate(Calendar.getInstance().getTime());
				btnSave.setEnabled(true);
				button_4.doClick();
				
				
			}
		});
		btnNew.setBounds(173, 358, 70, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   PreparedStatement pst = con.prepareStatement("INSERT INTO pay(sno, name, date, amount, remarks)VALUES (?, ?, ?, ?, ?)");

					   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
						
					   pst.setInt(1,Integer.parseInt(sno.getText()));
					   pst.setString(2,name.getItem(name.getSelectedIndex()));
					   pst.setString(3,formater.format(date.getDate()));
					   pst.setString(4,amount.getText());
					   pst.setString(5,remarks.getText());
					   pst.executeUpdate();
			           pst.close();
					  
					   
			           button_4.doClick();
		        		con.close();
		        		sno.setText("");
						amount.setText("");
						remarks.setText("");
						date.setDate(Calendar.getInstance().getTime());
		        		remarks.setText("");
		        		btnSave.setEnabled(false);
				   } 
				    catch (SQLException e1) {
					e1.printStackTrace();
				}
				    finally{}
			
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(299, 358, 70, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection con=Connector.getConnection();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel) table.getModel();	        		
					st.executeUpdate("delete from pay where sno="+((String) model.getValueAt(table.getSelectedRow(), 0)));
					con.close();
		        	st.close();
		        	button_4.doClick();
		        			
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnDelete.setBounds(421, 358, 70, 23);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(538, 358, 70, 23);
		contentPane.add(btnExit);
		
		JLabel label = new JLabel("Search Records");
		label.setBounds(488, 199, 85, 14);
		contentPane.add(label);
		
		button_4 = new JButton(".....");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   Statement st = con.createStatement();
					   ResultSet rs = st.executeQuery("Select sno, name, date, amount, remarks from pay");
					   DefaultTableModel model = (DefaultTableModel) table.getModel();
					   model.setRowCount(0);
		        		// while loop and with while loop code use for print the data
		        		while (rs.next()) 
		        		{  
		        			
		        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5)};
		        			model.addRow(row);  
	     			    
		        		}
		        		con.close();
		        		st.close();
		        		rs.close();
		    	   } 
				    catch (SQLException e1) {
					e1.printStackTrace();
				}
				    finally{}
				
			
				
				
			}
		});
		button_4.setBounds(583, 195, 44, 23);
		contentPane.add(button_4);
		JLabel lblNewLabel = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("vehicledetails.png");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(0, 0, 784, 562);
		contentPane.add(lblNewLabel);
	
	}
}
