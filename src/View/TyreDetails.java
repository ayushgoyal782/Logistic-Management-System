package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Choice;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class TyreDetails extends JFrame {

	private JPanel contentPane;
	private JTextField tno;
	private JTextField remarks;
	private JTextField sno;
	private JTextField odoreading;
	private JTable table;
	private JButton btnSave;
	private static Choice vno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TyreDetails frame = new TyreDetails();
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
	public TyreDetails() {
		setTitle("Tyre Change Details");
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLocation(-20, -51);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTyreChangeDetails = new JLabel("Tyre Change Details");
		lblTyreChangeDetails.setBounds(247, 11, 283, 31);
		lblTyreChangeDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTyreChangeDetails.setFont(new Font("Calibri", Font.BOLD, 25));
		contentPane.add(lblTyreChangeDetails);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(154, 68, 464, 186);
		contentPane.add(panel);
		
		JLabel lblTyrePosition = new JLabel("Tyre Position");
		lblTyrePosition.setBounds(73, 86, 121, 14);
		panel.add(lblTyrePosition);
		
		final Choice tp = new Choice();
		tp.setBounds(200, 84, 160, 20);
		tp.add("Horse Front.Right");
		tp.add("Horse Front.Left");
		tp.add("Horse Rear.Right.OUTER");
		tp.add("Horse Rear.Right.INNER");
		tp.add("Horse Rear.Left.OUTER");
		tp.add("Horse Rear.Left.Inner");
		tp.add("Trailor Front.Left.Inner");
		tp.add("Trailor Front.Left.Outer");
		tp.add("Trailor Front.Right.Inner");
		tp.add("Trailor Front.Right.Outer");
		tp.add("Trailor Middle.Left.Inner");
		tp.add("Trailor Middle.Left.Outer");
		tp.add("Trailor Middle.Right.Inner");
		tp.add("Trailor Middle.Right.Outer");
		tp.add("Trailor Rear.Left.Inner");
		tp.add("Trailor Rear.Left.Outer");
		tp.add("Trailor Rear.Right.Inner");
		tp.add("Trailor Rear.Right.Outer");
		panel.add(tp);
		
		JLabel lblTyreNo = new JLabel("Tyre No.");
		lblTyreNo.setBounds(73, 110, 117, 14);
		panel.add(lblTyreNo);
		
		JLabel label_2 = new JLabel("Remarks");
		label_2.setBounds(73, 158, 58, 14);
		panel.add(label_2);
		
		tno = new JTextField();
		tno.setColumns(10);
		tno.setBounds(200, 107, 160, 20);
		panel.add(tno);
		
		remarks = new JTextField();
		remarks.setColumns(10);
		remarks.setBounds(200, 155, 160, 20);
		panel.add(remarks);
		
		JLabel label_3 = new JLabel("S.No.");
		label_3.setBounds(73, 14, 58, 14);
		panel.add(label_3);
		
		sno = new JTextField();
		sno.setColumns(10);
		sno.setBounds(200, 11, 160, 20);
		panel.add(sno);
		
		JLabel label_4 = new JLabel("Date");
		label_4.setBounds(73, 38, 70, 14);
		panel.add(label_4);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(200, 34, 160, 22);
		panel.add(date);
		
		JLabel label_5 = new JLabel("Vehicle No.");
		label_5.setBounds(73, 62, 121, 14);
		panel.add(label_5);
		
		vno = new Choice();
		vno.setBounds(200, 60, 160, 20);
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
		
		JLabel label = new JLabel("Odometer Reading");
		label.setBounds(73, 134, 117, 14);
		panel.add(label);
		
		odoreading = new JTextField();
		odoreading.setColumns(10);
		odoreading.setBounds(200, 131, 160, 20);
		panel.add(odoreading);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(154, 305, 464, 128);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Date", "Vehicle No.", "Tyre Position", "Tyre No.", "Odometer Reading", "Remarks"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("Search Records");
		label_1.setBounds(472, 263, 93, 14);
		contentPane.add(label_1);
		
		final JButton button = new JButton(".....");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 
			           Connection con=Connector.getConnection();
					   Statement st = con.createStatement();
					   ResultSet rs = st.executeQuery("Select sno, date, vehicle_no, tyre_position, tyre_no, odoreading, remarks from tyre");
					   DefaultTableModel model = (DefaultTableModel) table.getModel();
					   model.setRowCount(0);
		        		// while loop and with while loop code use for print the data
		        		while (rs.next()) 
		        		{  
		        			
		        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
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
		button.setBounds(574, 259, 44, 23);
		contentPane.add(button);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int s=0;
				try{
				
				Connection con=Connector.getConnection();
        		Statement st = con.createStatement();			            
        		ResultSet rs = st.executeQuery("Select max(sno) from tyre");
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
				tno.setText("");
				date.setDate(Calendar.getInstance().getTime());
				button.doClick();
				btnSave.setEnabled(true);
			}
		});
		btnNew.setBounds(164, 469, 70, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
	            	
			           Connection con=Connector.getConnection();
					   PreparedStatement pst = con.prepareStatement("INSERT INTO tyre(sno, date, vehicle_no, tyre_position, tyre_no, odoreading, remarks)VALUES (?, ?, ?, ?, ?, ?, ?)");

					   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
						
					   pst.setInt(1,Integer.parseInt(sno.getText()));
					   pst.setString(2,formater.format(date.getDate()));
					   pst.setString(3,vno.getItem(vno.getSelectedIndex()));
					   pst.setString(4,tp.getItem(tp.getSelectedIndex()));
					   pst.setString(5,tno.getText());
					   pst.setString(6,odoreading.getText());
					   pst.setString(7,remarks.getText());
					   pst.executeUpdate();
			           pst.close();
					   con.close();
					   sno.setText("");
						odoreading.setText("");
						remarks.setText("");
						tno.setText("");
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
		btnSave.setBounds(290, 469, 70, 23);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
					
					Connection con=Connector.getConnection();
					Statement st = con.createStatement();
					DefaultTableModel model = (DefaultTableModel) table.getModel();	        		
					st.executeUpdate("delete from tyre where sno="+((String) model.getValueAt(table.getSelectedRow(), 0)));
					
		        		con.close();
		        		st.close();
		        		button.doClick();
						
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnDelete.setBounds(412, 469, 70, 23);
		contentPane.add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(529, 469, 70, 23);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("vehicledetails.png");
		try {
			lblNewLabel.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel.setBounds(0, 0, 784, 574);
		contentPane.add(lblNewLabel);
	
	}

}
