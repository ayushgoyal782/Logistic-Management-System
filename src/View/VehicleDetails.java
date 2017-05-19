package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
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

public class VehicleDetails extends JFrame {

	private JPanel contentPane;
	private JTextField sno;
	private JTextField vno;
	private JTextField ownername;
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
					VehicleDetails frame = new VehicleDetails();
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
	public VehicleDetails() {
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
		setTitle("Vehicle Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 626);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(85, 84, 601, 231);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(128, 21, 20, 199);
		panel.add(verticalStrut);
		
		JLabel lblSno = new JLabel("S.No.");
		lblSno.setBounds(10, 22, 108, 14);
		panel.add(lblSno);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(10, 34, 275, 14);
		panel.add(horizontalStrut);
		
		JLabel lblVehicleNo = new JLabel("Vehicle No.");
		lblVehicleNo.setBounds(10, 46, 108, 14);
		panel.add(lblVehicleNo);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(10, 59, 275, 14);
		panel.add(horizontalStrut_1);
		
		JLabel lblCgQuaterlyTax = new JLabel("CG Quaterly Tax Date");
		lblCgQuaterlyTax.setBounds(10, 71, 108, 14);
		panel.add(lblCgQuaterlyTax);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(10, 84, 68, 14);
		panel.add(horizontalStrut_2);
		
		JLabel lblInsuranceDtae = new JLabel("Insurance Date");
		lblInsuranceDtae.setBounds(10, 96, 108, 14);
		panel.add(lblInsuranceDtae);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setBounds(10, 109, 68, 14);
		panel.add(horizontalStrut_3);
		
		JLabel lblAuthorisationDate = new JLabel("Authorization Date");
		lblAuthorisationDate.setBounds(10, 121, 108, 14);
		panel.add(lblAuthorisationDate);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setBounds(10, 134, 68, 14);
		panel.add(horizontalStrut_4);
		
		JLabel lblFitnessDate = new JLabel("Fitness Date");
		lblFitnessDate.setBounds(10, 146, 108, 14);
		panel.add(lblFitnessDate);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setBounds(10, 159, 68, 14);
		panel.add(horizontalStrut_5);
		
		JLabel lblNationalPermitDate = new JLabel("National Permit Date");
		lblNationalPermitDate.setBounds(10, 171, 108, 14);
		panel.add(lblNationalPermitDate);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setBounds(10, 184, 68, 14);
		panel.add(horizontalStrut_6);
		
		JLabel lblPucDate = new JLabel("PUC Date");
		lblPucDate.setBounds(10, 196, 46, 14);
		panel.add(lblPucDate);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(314, 21, 140, 14);
		panel.add(lblDate);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setBounds(314, 33, 68, 14);
		panel.add(horizontalStrut_7);
		
		JLabel lblOwnerName = new JLabel("Owner Name");
		lblOwnerName.setBounds(314, 45, 108, 14);
		panel.add(lblOwnerName);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setBounds(314, 58, 68, 14);
		panel.add(horizontalStrut_8);
		
		JLabel lblCgQuaterlyTax_1 = new JLabel("CG Quaterly Tax Upto Date");
		lblCgQuaterlyTax_1.setBounds(314, 70, 140, 14);
		panel.add(lblCgQuaterlyTax_1);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setBounds(314, 83, 68, 14);
		panel.add(horizontalStrut_9);
		
		JLabel lblInsuranceUptoDate = new JLabel("Insurance Upto Date");
		lblInsuranceUptoDate.setBounds(314, 95, 140, 14);
		panel.add(lblInsuranceUptoDate);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalStrut_10.setBounds(314, 108, 68, 14);
		panel.add(horizontalStrut_10);
		
		JLabel lblAuthorizationUptoDate = new JLabel("Authorization Upto Date");
		lblAuthorizationUptoDate.setBounds(314, 120, 140, 14);
		panel.add(lblAuthorizationUptoDate);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalStrut_11.setBounds(314, 133, 68, 14);
		panel.add(horizontalStrut_11);
		
		JLabel lblFitnessUptoDate = new JLabel("Fitness Upto Date");
		lblFitnessUptoDate.setBounds(314, 145, 140, 14);
		panel.add(lblFitnessUptoDate);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalStrut_12.setBounds(314, 158, 68, 14);
		panel.add(horizontalStrut_12);
		
		JLabel lblNationalPermitUpto = new JLabel("National Permit Upto Date");
		lblNationalPermitUpto.setBounds(314, 170, 140, 14);
		panel.add(lblNationalPermitUpto);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalStrut_13.setBounds(314, 183, 68, 14);
		panel.add(horizontalStrut_13);
		
		JLabel lblPucUptoDate = new JLabel("PUC Upto Date");
		lblPucUptoDate.setBounds(314, 195, 140, 14);
		panel.add(lblPucUptoDate);
		
		sno = new JTextField();
		sno.setBounds(145, 21, 140, 20);
		panel.add(sno);
		sno.setColumns(10);
		
		vno = new JTextField();
		vno.setBounds(145, 43, 140, 20);
		panel.add(vno);
		vno.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(284, 21, 20, 199);
		panel.add(verticalStrut_1);
		
		final JXDatePicker cgqt = new JXDatePicker();
		cgqt.setBounds(145, 67, 140, 22);
		panel.add(cgqt);
		
		final JXDatePicker insurance = new JXDatePicker();
		insurance.setBounds(145, 92, 140, 22);
		panel.add(insurance);
		
		final JXDatePicker authorization = new JXDatePicker();
		authorization.setBounds(145, 117, 140, 22);
		panel.add(authorization);
		
		final JXDatePicker fitness = new JXDatePicker();
		fitness.setBounds(145, 142, 140, 22);
		panel.add(fitness);
		
		final JXDatePicker np = new JXDatePicker();
		np.setBounds(145, 167, 140, 22);
		panel.add(np);
		
		final JXDatePicker puc = new JXDatePicker();
		puc.setBounds(145, 192, 140, 22);
		panel.add(puc);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(451, 21, 140, 22);
		panel.add(date);
		
		final JXDatePicker pucupto = new JXDatePicker();
		pucupto.setBounds(451, 192, 140, 22);
		panel.add(pucupto);
		
		final JXDatePicker npupto = new JXDatePicker();
		npupto.setBounds(451, 167, 140, 22);
		panel.add(npupto);
		
		final JXDatePicker fitnessupto = new JXDatePicker();
		fitnessupto.setBounds(451, 142, 140, 22);
		panel.add(fitnessupto);
		
		final JXDatePicker authorizationupto = new JXDatePicker();
		authorizationupto.setBounds(451, 117, 140, 22);
		panel.add(authorizationupto);
		
		final JXDatePicker insuranceupto = new JXDatePicker();
		insuranceupto.setBounds(451, 92, 140, 22);
		panel.add(insuranceupto);
		
		final JXDatePicker cgqtupto = new JXDatePicker();
		cgqtupto.setBounds(451, 67, 140, 22);
		panel.add(cgqtupto);
		
		ownername = new JTextField();
		ownername.setColumns(10);
		ownername.setBounds(451, 44, 140, 20);
		panel.add(ownername);
		
		JLabel lblVehicleDetails = new JLabel("Vehicle Details");
		lblVehicleDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleDetails.setFont(new Font("Calibri", Font.BOLD, 25));
		lblVehicleDetails.setBounds(55, 11, 664, 50);
		contentPane.add(lblVehicleDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(85, 350, 601, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Date", "Vehicle No.", "Owner Name", "Cg Tax Date", "Cg Tax Upto Date", "Insurance  Date", "Insurance Upto Date", "Authorization  Date", "Authorization Upto Date", "Fitness Date", "Fitness Upto Date", "National Permit Date", "National Permit Upto Date", "Puc Date", "PUC Upto Date"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(110);
		table.getColumnModel().getColumn(8).setPreferredWidth(110);
		table.getColumnModel().getColumn(9).setPreferredWidth(134);
		table.getColumnModel().getColumn(11).setPreferredWidth(106);
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int s=0;
				try{
				
				Connection con=Connector.getConnection();
        		Statement st = con.createStatement();			            
        		ResultSet rs = st.executeQuery("Select max(sno) from vehicle_details");
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
				ownername.setText("");
				date.setDate(Calendar.getInstance().getTime());
				cgqt.setDate(Calendar.getInstance().getTime());
 				cgqtupto.setDate(Calendar.getInstance().getTime());
 				insurance.setDate(Calendar.getInstance().getTime());
 				insuranceupto.setDate(Calendar.getInstance().getTime());
 				authorization.setDate(Calendar.getInstance().getTime());
 				authorizationupto.setDate(Calendar.getInstance().getTime());
 				fitness.setDate(Calendar.getInstance().getTime());
 				fitnessupto.setDate(Calendar.getInstance().getTime());
 				np.setDate(Calendar.getInstance().getTime());
 				npupto.setDate(Calendar.getInstance().getTime());
 				puc.setDate(Calendar.getInstance().getTime());
 				pucupto.setDate(Calendar.getInstance().getTime());
 				button.doClick();
				btnSave.setEnabled(true);
								
		
				
			}
		});
		btnNew.setBounds(85, 548, 89, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
				 try {
		            	
			           Connection con=Connector.getConnection();
					   PreparedStatement pst = con.prepareStatement("INSERT INTO vehicle_details(sno, date, vehicle_no, owner_name, cgtax, cgtaxupto, insurance, insuranceupto, autho, authoupto, fitness, fitnessupto, np, npupto, puc, pucupto)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
						
					   pst.setInt(1,Integer.parseInt(sno.getText()));
					   pst.setString(2,formater.format(date.getDate()));
					   pst.setString(3,vno.getText());
					   pst.setString(4,ownername.getText());
					   pst.setString(5,formater.format(cgqt.getDate()));
					   pst.setString(6,formater.format(cgqtupto.getDate()));
					   pst.setString(7,formater.format(insurance.getDate()));
					   pst.setString(8,formater.format(insuranceupto.getDate()));
					   pst.setString(9,formater.format(authorization.getDate()));
					   pst.setString(10,formater.format(authorizationupto.getDate()));
					   pst.setString(11,formater.format(fitness.getDate()));
					   pst.setString(12,formater.format(fitnessupto.getDate()));
					   pst.setString(13,formater.format(np.getDate()));
					   pst.setString(14,formater.format(npupto.getDate()));
					   pst.setString(15,formater.format(puc.getDate()));
					   pst.setString(16,formater.format(pucupto.getDate()));
					   pst.executeUpdate();
			           pst.close();
					  
					   
					   Statement st = con.createStatement();
					  
		        		con.close();
		        		st.close();
		        		button.doClick();
		        		sno.setText("");
		 				vno.setText("");
		 				ownername.setText("");
		 				date.setDate(Calendar.getInstance().getTime());
		 				cgqt.setDate(Calendar.getInstance().getTime());
		 				cgqtupto.setDate(Calendar.getInstance().getTime());
		 				insurance.setDate(Calendar.getInstance().getTime());
		 				insuranceupto.setDate(Calendar.getInstance().getTime());
		 				authorization.setDate(Calendar.getInstance().getTime());
		 				authorizationupto.setDate(Calendar.getInstance().getTime());
		 				fitness.setDate(Calendar.getInstance().getTime());
		 				fitnessupto.setDate(Calendar.getInstance().getTime());
		 				np.setDate(Calendar.getInstance().getTime());
		 				npupto.setDate(Calendar.getInstance().getTime());
		 				puc.setDate(Calendar.getInstance().getTime());
		 				pucupto.setDate(Calendar.getInstance().getTime());
		 				btnSave.setEnabled(false);
				   } 
				    catch (SQLException e1) {
					e1.printStackTrace();
				}
				    finally{}
			
			
			
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(257, 548, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con=Connector.getConnection();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel) table.getModel();	        		
					st.executeUpdate("delete from vehicle_details where sno="+((String) model.getValueAt(table.getSelectedRow(), 0)));
					con.close();
	        		st.close();  
	        		button.doClick();
	        	
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(417, 548, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnExit.setBounds(597, 548, 89, 23);
		contentPane.add(btnExit);
		
		button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Connection con;
				try {
					con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Acer/Documents/Database1.accdb");
					 Statement st = con.createStatement();
					   ResultSet rs = st.executeQuery("Select sno, date, vehicle_no, owner_name, cgtax, cgtaxupto, insurance, insuranceupto, autho, authoupto, fitness, fitnessupto, np, npupto, puc, pucupto from vehicle_details");
					   DefaultTableModel model = (DefaultTableModel) table.getModel();
					   model.setRowCount(0);
		        		// while loop and with while loop code use for print the data
		        		while (rs.next()) 
		        		{  
		        			
		        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11),rs.getString(12), rs.getString(13),rs.getString(14), rs.getString(15),rs.getString(16)};
		        			model.addRow(row);  
	   			    
		        		}
		        		con.close();
		        		st.close();rs.close();
		        		
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		button.setBounds(637, 316, 44, 23);
		contentPane.add(button);
		
		JLabel lblSearchRecords = new JLabel("Search Records");
		lblSearchRecords.setBounds(542, 320, 85, 14);
		contentPane.add(lblSearchRecords);
		
		JLabel lblNewLabel = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("vehicledetails.png");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}lblNewLabel.setBounds(0, 0, 794, 598);
		contentPane.add(lblNewLabel);
	}
}
