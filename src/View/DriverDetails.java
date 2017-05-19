package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.UIManager;

public class DriverDetails extends JFrame {

	private JPanel contentPane;
	private JTextField sno;
	private JTextField name;
	private JTextField vno;
	private JTextField licenseno;
	private JTextField salary;
	private JTextField reference;
	private JTextField remarks;
	private JTable table;
	private JButton btnSave;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverDetails frame = new DriverDetails();
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
	public DriverDetails() {
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
		setTitle("Driver or Helper Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDriverOrHelper = new JLabel("Driver or Helper Details");
		lblDriverOrHelper.setBounds(10, 11, 664, 31);
		lblDriverOrHelper.setHorizontalAlignment(SwingConstants.CENTER);
		lblDriverOrHelper.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblDriverOrHelper);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 72, 601, 185);
		contentPane.add(panel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(128, 21, 20, 152);
		panel.add(verticalStrut);
		
		JLabel label = new JLabel("S.No.");
		label.setBounds(10, 22, 108, 14);
		panel.add(label);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(10, 34, 275, 14);
		panel.add(horizontalStrut);
		
		JLabel lblDriverhelperName = new JLabel("Driver/Helper Name");
		lblDriverhelperName.setBounds(10, 46, 108, 14);
		panel.add(lblDriverhelperName);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(10, 59, 275, 14);
		panel.add(horizontalStrut_1);
		
		JLabel lblVehicleNo = new JLabel("Vehicle No");
		lblVehicleNo.setBounds(10, 71, 108, 14);
		panel.add(lblVehicleNo);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(10, 84, 68, 14);
		panel.add(horizontalStrut_2);
		
		JLabel lblJoiningDate = new JLabel("Joining Date");
		lblJoiningDate.setBounds(10, 96, 108, 14);
		panel.add(lblJoiningDate);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setBounds(10, 109, 68, 14);
		panel.add(horizontalStrut_3);
		
		JLabel lblLicenseNumber = new JLabel("License Number");
		lblLicenseNumber.setBounds(10, 121, 108, 14);
		panel.add(lblLicenseNumber);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setBounds(10, 134, 68, 14);
		panel.add(horizontalStrut_4);
		
		JLabel lblReferencedBy = new JLabel("Referenced By");
		lblReferencedBy.setBounds(10, 146, 108, 14);
		panel.add(lblReferencedBy);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setBounds(10, 159, 68, 14);
		panel.add(horizontalStrut_5);
		
		JLabel label_8 = new JLabel("Date");
		label_8.setBounds(314, 21, 140, 14);
		panel.add(label_8);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setBounds(314, 33, 68, 14);
		panel.add(horizontalStrut_7);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(314, 45, 108, 14);
		panel.add(lblAddress);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setBounds(314, 58, 68, 14);
		panel.add(horizontalStrut_8);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setBounds(314, 83, 68, 14);
		panel.add(horizontalStrut_9);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalStrut_10.setBounds(314, 108, 68, 14);
		panel.add(horizontalStrut_10);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(314, 120, 140, 14);
		panel.add(lblSalary);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalStrut_11.setBounds(314, 133, 68, 14);
		panel.add(horizontalStrut_11);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(314, 145, 140, 14);
		panel.add(lblRemarks);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalStrut_12.setBounds(314, 158, 68, 14);
		panel.add(horizontalStrut_12);
		
		sno = new JTextField();
		sno.setColumns(10);
		sno.setBounds(145, 21, 140, 20);
		panel.add(sno);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(145, 43, 140, 20);
		panel.add(name);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(284, 21, 20, 152);
		panel.add(verticalStrut_1);
		
		final JXDatePicker jdate = new JXDatePicker();
		jdate.setBounds(145, 92, 140, 22);
		panel.add(jdate);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(451, 21, 140, 22);
		panel.add(date);
		
		final JTextArea address = new JTextArea();
		address.setToolTipText("Address");
		address.setBounds(451, 51, 140, 59);
		panel.add(address);
		
		vno = new JTextField();
		vno.setBounds(145, 68, 140, 20);
		panel.add(vno);
		vno.setColumns(10);
		
		licenseno = new JTextField();
		licenseno.setBounds(145, 118, 140, 20);
		panel.add(licenseno);
		licenseno.setColumns(10);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(451, 118, 140, 20);
		panel.add(salary);
		
		reference = new JTextField();
		reference.setColumns(10);
		reference.setBounds(145, 143, 140, 20);
		panel.add(reference);
		
		remarks = new JTextField();
		remarks.setColumns(10);
		remarks.setBounds(451, 143, 140, 20);
		panel.add(remarks);
		
		JLabel label_1 = new JLabel("Search Records");
		label_1.setBounds(492, 272, 97, 14);
		contentPane.add(label_1);
		
		button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   Statement st = con.createStatement();
					   ResultSet rs = st.executeQuery("Select sno, date, name, address, vno, joining_date, license_no, salary, referenced_by, remarks from driver_details");
					   DefaultTableModel model = (DefaultTableModel) table.getModel();
					   model.setRowCount(0);
		        		// while loop and with while loop code use for print the data
		        		while (rs.next()) 
		        		{  
		        			
		        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10)};
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
		button.setBounds(599, 268, 44, 23);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(42, 299, 601, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Date", "Driver/Helper Name", "Address", "Vehicle No.", "Joining Date", "License Number", "Salary", "Referenced By", "Remarks"
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
        		ResultSet rs = st.executeQuery("Select max(sno) from driver_details");
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
				vno.setText("");
				name.setText("");
				date.setDate(Calendar.getInstance().getTime());
				jdate.setDate(Calendar.getInstance().getTime());
 				address.setText("");
 				licenseno.setText("");
 				salary.setText("");
 				reference.setText("");
 				remarks.setText("");
				btnSave.setEnabled(true);
				button.doClick();
        		
								
		
				
				
			}
		});
		btnNew.setBounds(42, 510, 89, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   PreparedStatement pst = con.prepareStatement("INSERT INTO driver_details(sno, date, name, address, vno, joining_date, license_no, salary, referenced_by, remarks)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
						
					   pst.setInt(1,Integer.parseInt(sno.getText()));
					   pst.setString(2,formater.format(date.getDate()));
					   pst.setString(3,name.getText());
					   pst.setString(4,address.getText());
					   pst.setString(5,vno.getText());
					   pst.setString(6,formater.format(jdate.getDate()));
					   pst.setString(7,licenseno.getText());
					   pst.setString(8,salary.getText());
					   pst.setString(9,reference.getText());
					   pst.setString(10,remarks.getText());
					   pst.executeUpdate();
			           pst.close();
					  
					   
					 
		        		con.close();
		        		sno.setText("");
						vno.setText("");
						name.setText("");
						date.setDate(Calendar.getInstance().getTime());
						jdate.setDate(Calendar.getInstance().getTime());
		 				address.setText("");
		 				licenseno.setText("");
		 				salary.setText("");
		 				reference.setText("");
		 				remarks.setText("");
		 				button.doClick();
		        		btnSave.setEnabled(false);
				   } 
				    catch (SQLException e1) {
					e1.printStackTrace();
				}
				    finally{}

				
				
				
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(214, 510, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con=Connector.getConnection();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel) table.getModel();	        		
					st.executeUpdate("delete from driver_details where sno="+((String) model.getValueAt(table.getSelectedRow(), 0)));
					con.close();
		        	st.close();
		        	button.doClick();
	        						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(374, 510, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(554, 510, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("vehicledetails.png");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(0, 0, 694, 572);
		contentPane.add(lblNewLabel);
	}
}
