package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Choice;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class MaintenanceDetails extends JFrame {

	private JPanel contentPane;
	private JTextField odoreading;
	private JTextField remarks;
	private JTextField sno;
	private static Choice vno;
	private JTable table;
	private JButton btnSave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						MaintenanceDetails frame = new MaintenanceDetails();
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
	public MaintenanceDetails() {
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
		setTitle("Maintenance Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMaintenanceDetails = new JLabel("Maintenance Details");
		lblMaintenanceDetails.setBounds(163, 11, 464, 31);
		lblMaintenanceDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaintenanceDetails.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblMaintenanceDetails);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(173, 53, 439, 163);
		contentPane.add(panel);
		
		JLabel lblMaintenanceType = new JLabel("Maintenance Type");
		lblMaintenanceType.setBounds(73, 86, 121, 14);
		panel.add(lblMaintenanceType);
		
		final Choice mtype = new Choice();
		mtype.setBounds(200, 84, 160, 20);
		mtype.add("Engine Oil");
		mtype.add("Crown Oil");
		mtype.add("Gear Oil");
		mtype.add("Hub Greasing");
		mtype.add("Other..");
		panel.add(mtype);
		
		JLabel lblOdometerReading = new JLabel("Odometer Reading");
		lblOdometerReading.setBounds(73, 110, 117, 14);
		panel.add(lblOdometerReading);
		
		JLabel label_3 = new JLabel("Remarks");
		label_3.setBounds(73, 135, 58, 14);
		panel.add(label_3);
		
		odoreading = new JTextField();
		odoreading.setColumns(10);
		odoreading.setBounds(200, 107, 160, 20);
		panel.add(odoreading);
		
		remarks = new JTextField();
		remarks.setColumns(10);
		remarks.setBounds(200, 132, 160, 20);
		panel.add(remarks);
		
		JLabel label_4 = new JLabel("S.No.");
		label_4.setBounds(73, 14, 58, 14);
		panel.add(label_4);
		
		sno = new JTextField();
		sno.setColumns(10);
		sno.setBounds(200, 11, 160, 20);
		panel.add(sno);
		
		JLabel label_1 = new JLabel("Date");
		label_1.setBounds(73, 38, 70, 14);
		panel.add(label_1);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(200, 34, 160, 22);
		panel.add(date);
		
		JLabel lblVehicleNo = new JLabel("Vehicle No.");
		lblVehicleNo.setBounds(73, 62, 121, 14);
		panel.add(lblVehicleNo);
		
		vno = new Choice();
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
		vno.setBounds(200, 60, 160, 20);
		panel.add(vno);
		
		JLabel label = new JLabel("Search Records");
		label.setBounds(451, 222, 107, 14);
		contentPane.add(label);
		
		final JButton button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   Statement st = con.createStatement();
					   ResultSet rs = st.executeQuery("Select sno, date, vno, mtype, odoreading, remarks from maintenance_details");
					   DefaultTableModel model = (DefaultTableModel) table.getModel();
					   model.setRowCount(0);
		        		// while loop and with while loop code use for print the data
		        		while (rs.next()) 
		        		{  
		        			
		        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)};
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
		button.setBounds(568, 218, 44, 23);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(163, 247, 464, 115);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Date", "Vehicle No.", "Maintenance Type", "Odometer Reading", "Remarks"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(51);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(104);
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int s=0;
				try{
				
				Connection con=Connector.getConnection();
        		Statement st = con.createStatement();			            
        		ResultSet rs = st.executeQuery("Select max(sno) from maintenance_details");
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
				odoreading.setText("");
				remarks.setText("");
				date.setDate(Calendar.getInstance().getTime());
				btnSave.setEnabled(true);
				button.doClick();
				
				
			}
		});
		btnNew.setBounds(177, 382, 70, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   PreparedStatement pst = con.prepareStatement("INSERT INTO maintenance_details(sno, date, vno, mtype, odoreading, remarks)VALUES (?, ?, ?, ?, ?, ?)");

					   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
						
					   pst.setInt(1,Integer.parseInt(sno.getText()));
					   pst.setString(2,formater.format(date.getDate()));
					   pst.setString(3,vno.getItem(vno.getSelectedIndex()));
					   pst.setString(4,mtype.getItem(mtype.getSelectedIndex()));
					   pst.setString(5,odoreading.getText());
					   pst.setString(6,remarks.getText());
					   pst.executeUpdate();
			           pst.close();
					   con.close();
		        		sno.setText("");
						odoreading.setText("");
						remarks.setText("");
						date.setDate(Calendar.getInstance().getTime());
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
		btnSave.setBounds(303, 382, 70, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con=Connector.getConnection();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel) table.getModel();	        		
					st.executeUpdate("delete from maintenance_details where sno="+((String) model.getValueAt(table.getSelectedRow(), 0)));
					button.doClick();
					
		        		con.close();
		        		st.close();
		        		
	        		
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(425, 382, 70, 23);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(542, 382, 70, 23);
		contentPane.add(btnExit);
		
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
