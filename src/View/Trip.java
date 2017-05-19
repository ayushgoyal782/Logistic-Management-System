package View;

import Models.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

import Models.Connector;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class Trip extends JFrame {

	private JPanel contentPane;
	private JTextField sno;
	private JTextField to;
	private JTextField rate;
	private JTextField advance;
	private JTextField from;
	private JTextField weight;
	private JTextField freight;
	private JTextField remarks;
	private JTable table;
	private JTextField transporter;
	private JButton btnSave;
	private JButton btnModify;
	private JButton btnDelete;
	private JXDatePicker date;
	private static Choice vno1;
	private JTextField balance;
	private JTextField terms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trip frame = new Trip();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Trip() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Trip Details");
		setResizable(false);
		setBounds(100, 100, 800, 620);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(144, 86, 521, 228);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JXDatePicker date = new JXDatePicker();
		date.setBounds(371, 16, 140, 20);
		date.setDate(Calendar.getInstance().getTime());
		date.setFormats(new SimpleDateFormat("MM/dd/yyyy"));
		panel.add(date);
		
		JLabel lblSno = new JLabel("S.No.");
		lblSno.setBounds(6, 19, 46, 14);
		panel.add(lblSno);
		
		sno = new JTextField();
		sno.setEnabled(false);
		sno.setBounds(82, 19, 140, 20);
		panel.add(sno);
		sno.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Vehicle No.");
		lblNewLabel.setBounds(6, 48, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(6, 79, 46, 14);
		panel.add(lblTo);
		
		to = new JTextField();
		to.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				weight.requestFocusInWindow();
			}
		});
		to.setBounds(82, 79, 140, 20);
		panel.add(to);
		to.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(6, 112, 53, 14);
		panel.add(lblRate);
		
		rate = new JTextField();
		rate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					   if(  Double.parseDouble(rate.getText())==0){}
						 else{
							 double w=Double.parseDouble(weight.getText());
								double r=Double.parseDouble(rate.getText());
								double f=w*r;
								freight.setText(""+f);
								advance.requestFocusInWindow();
						 }
				   }
				   catch(Exception e2){
					   
					   JOptionPane.showMessageDialog(null, "please enter number", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
					   rate.setText("");
					   rate.requestFocusInWindow();
					   e2.printStackTrace();
				   }
				
			}
		});
		rate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			
				double w=Double.parseDouble(weight.getText());
				double r=Double.parseDouble(rate.getText());
				double f=w*r;
				freight.setText(""+f);
				advance.requestFocusInWindow();			
				
			}
		});
		rate.setBounds(82, 110, 140, 20);
		panel.add(rate);
		rate.setColumns(10);
		
		JLabel lblAdvance = new JLabel("Advance");
		lblAdvance.setBounds(6, 143, 53, 14);
		panel.add(lblAdvance);
		
		advance = new JTextField();
		advance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					   if(  Double.parseDouble(advance.getText())==0){}
						 else{
							 double f=Double.parseDouble(freight.getText());
							 double a=Double.parseDouble(advance.getText());
							 if(a>f){
								 JOptionPane.showMessageDialog(null, "Advance Cannot be Greater than Freight", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
								 advance.requestFocusInWindow();
								 }
							 else{ balance.setText(""+(f-a));
							 transporter.requestFocusInWindow();}
						 }
				   }
				   catch(Exception e2){
					   
					   JOptionPane.showMessageDialog(null, "please enter number", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
					   advance.setText("");
					   advance.requestFocusInWindow();
					   e2.printStackTrace();
				   }
				
				
			}
		});
		advance.setBounds(82, 141, 140, 20);
		panel.add(advance);
		advance.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(295, 16, 46, 14);
		panel.add(lblDate);
		
		
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(295, 47, 53, 14);
		panel.add(lblFrom);
		
		from = new JTextField();
		from.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				to.requestFocusInWindow();
			}
		});
		from.setBounds(371, 44, 140, 20);
		panel.add(from);
		from.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(295, 76, 46, 14);
		panel.add(lblWeight);
		
		weight = new JTextField();
		weight.addActionListener(new ActionListener() {
			   @Override
			    public void actionPerformed(ActionEvent e) {
				   try{
					   if(  Double.parseDouble(weight.getText())==0){}
						 else{
							 rate.requestFocusInWindow();
						 }
				   }
				   catch(Exception e2){
					   
					   JOptionPane.showMessageDialog(null, "please enter number", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
					  weight.setText("");
					   weight.requestFocusInWindow();
					   e2.printStackTrace();
				   }
			      
			   }
			}); 
		weight.setBounds(371, 73, 140, 20);
		panel.add(weight);
		weight.setColumns(10);
		
		JLabel lblFrieght = new JLabel("Freight");
		lblFrieght.setBounds(295, 109, 53, 14);
		panel.add(lblFrieght);
		
		freight = new JTextField();
		freight.setBounds(371, 104, 140, 20);
		panel.add(freight);
		freight.setColumns(10);
		
		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(6, 200, 53, 14);
		panel.add(lblRemarks);
		
		remarks = new JTextField();
		remarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.requestFocusInWindow();
			}
		});
		remarks.setBounds(82, 198, 431, 20);
		panel.add(remarks);
		remarks.setColumns(10);
		
		
		JLabel lblTransporter = new JLabel("Transporter");
		lblTransporter.setBounds(6, 172, 77, 14);
		panel.add(lblTransporter);
		
		
		
		transporter = new JTextField();
		transporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				terms.requestFocusInWindow();
			}
		});
		transporter.setBounds(82, 169, 140, 20);
		panel.add(transporter);
		transporter.setColumns(10);
		
		vno1 = new Choice();
		vno1.setBounds(82, 48, 140, 20);
		try{
			Connection con=Connector.getConnection();
		
	    	Statement st = con.createStatement();			            
	    	ResultSet rs = st.executeQuery("Select DISTINCT vehicle_no from vehicle_details");
	    		
	    	// while loop and with while loop code use for print the data
	    	while (rs.next()) 
	    	{
	    		vno1.add(rs.getString(1));
	    	}
	    	st.close();
	    	con.close();
    	}
		catch(Exception e){
			e.printStackTrace();
		}
		panel.add(vno1);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(295, 138, 53, 14);
		panel.add(lblBalance);
		
		balance = new JTextField();
		balance.setColumns(10);
		balance.setBounds(371, 134, 140, 20);
		panel.add(balance);
		
		JLabel lblPaymentTerms = new JLabel("Payment Terms");
		lblPaymentTerms.setBounds(295, 169, 77, 14);
		panel.add(lblPaymentTerms);
		
		terms = new JTextField();
		terms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remarks.requestFocusInWindow();
			}
		});
		terms.setColumns(10);
		terms.setBounds(371, 166, 140, 20);
		panel.add(terms);
		
		
				
		JLabel lblSearchRecords = new JLabel("Search Records");
		lblSearchRecords.setBounds(514, 320, 94, 14);
		contentPane.add(lblSearchRecords);
		
		
		final JButton button = new JButton("......");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	            	
	            	Connection con=Connector.getConnection();
	        		Statement st = con.createStatement();			            
	        		ResultSet rs = st.executeQuery("Select Sno, Date, Vehicle_no, Fr, Tpo, Weight, Rate, Freight, Advance, Balance, Transporter, Terms, Remarks from trip");
	        		DefaultTableModel model = (DefaultTableModel) table.getModel();
	        		model.setRowCount(0);
	        		while (rs.next()) 
	        		{  
	        			
	        			Object[] row = { rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11),rs.getString(12), rs.getString(13)};
	        			model.addRow(row);  
      			    
	        		}
	        		st.close();
	        		con.close();
					} 
					catch (Exception ex)
                     {
							System.err.print("Exception: ");
							System.err.println(ex.getMessage());
                     }
				

			}
		});
		button.setBounds(618, 315, 46, 23);
		contentPane.add(button);
		
		JButton btnNew = new JButton("New");
		btnNew.setBackground(SystemColor.inactiveCaptionBorder);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int s=0;
				try{
				
				Connection con=Connector.getConnection();
        		Statement st = con.createStatement();			            
        		ResultSet rs = st.executeQuery("Select max(Sno) from trip");
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
				from.setText("");
				to.setText("");
				weight.setText("");
				rate.setText("");
				freight.setText("");
				advance.setText("");
				transporter.setText("");
				balance.setText("");
 				terms.setText("");
				remarks.setText("");
				btnSave.setEnabled(true);
				button.doClick();
				
				
			}
		});
		
		btnNew.setBounds(147, 550, 85, 23);
		contentPane.add(btnNew);
		
		btnSave = new JButton("Save");
		btnSave.setBackground(SystemColor.inactiveCaptionBorder);
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				double w=Double.parseDouble(weight.getText());
				double r=Double.parseDouble(rate.getText());
				double f=w*r;
			
				
				

			    
			    try {
	            	
		            Connection con=Connector.getConnection();
				    PreparedStatement pst = con.prepareStatement("INSERT INTO trip(Sno, Date, Vehicle_no, Fr, Tpo, Weight, Rate, Freight, Advance, Balance, Transporter, Terms, Remarks)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

				    SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
					 String d=formater.format(date.getDate());
				   pst.setInt(1,Integer.parseInt(sno.getText()));
				   pst.setString(2,d);
				   pst.setString(3,vno1.getItem(vno1.getSelectedIndex()));
				   pst.setString(4,from.getText());
				   pst.setString(5,to.getText());
				   pst.setDouble(6, w);
				   pst.setDouble(7, r);
				   pst.setDouble(8, f);
				   pst.setDouble(9, Double.parseDouble(advance.getText()));
				   pst.setDouble(10,Double.parseDouble(balance.getText()));
				   pst.setString(11,transporter.getText());
				   pst.setString(12,terms.getText());
				   pst.setString(13,remarks.getText());
				   pst.executeUpdate();
		           pst.close();
				  
				   
				  button.doClick();
	        		 con.close();
	        		
	        		 sno.setText("");
	 				from.setText("");
	 				to.setText("");
	 				weight.setText("");
	 				rate.setText("");
	 				freight.setText("");
	 				advance.setText("");
	 				balance.setText("");
	 				terms.setText("");
	 				transporter.setText("");
	 				remarks.setText("");
	        	btnSave.setEnabled(false);
			   } 
			    catch (SQLException e) {
				e.printStackTrace();
			}
			    finally{}
			 
				
			}
		});
		btnSave.setBounds(297, 550, 85, 23);
		contentPane.add(btnSave);
		
		btnModify = new JButton("Modify");
		btnModify.setBackground(SystemColor.inactiveCaptionBorder);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double w=Double.parseDouble(weight.getText());
				double r=Double.parseDouble(rate.getText());
				double f=w*r;
				
				

			    
			    try {
	            	
		            Connection con=Connector.getConnection();
				    PreparedStatement pst = con.prepareStatement("update trip SET Sno=?, Date=?, Vehicle_no=?, Fr=?, Tpo=?, Weight=?, Rate=?, Freight=?, Advance=?, Balance=?, Transporter=?, Terms=?, Remarks=? where Sno=?");

				   SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
				   String d=formater.format(date.getDate());
				   pst.setInt(1,Integer.parseInt(sno.getText()));
				   pst.setString(2,d);
				   pst.setString(3,vno1.getItem(vno1.getSelectedIndex()));
				   pst.setString(4,from.getText());
				   pst.setString(5,to.getText());
				   pst.setDouble(6, w);
				   pst.setDouble(7, r);
				   pst.setDouble(8, f);
				   pst.setDouble(9, Double.parseDouble(advance.getText()));
				   pst.setDouble(10,Double.parseDouble(balance.getText()));
				   pst.setString(11,transporter.getText());
				   pst.setString(12,terms.getText());
				   pst.setString(13,remarks.getText());
				   pst.setString(14,sno.getText());
				   pst.executeUpdate();
		           pst.close();
				  
				   
				   button.doClick();
	        		 con.close();
	        		
	        		 sno.setText("");
	 				from.setText("");
	 				to.setText("");
	 				weight.setText("");
	 				rate.setText("");
	 				freight.setText("");
	 				advance.setText("");
	 				transporter.setText("");
	 				balance.setText("");
	 				terms.setText("");
	 				remarks.setText("");
	        	btnSave.setEnabled(false);
	        	btnModify.setEnabled(false);
			   } 
			    catch (SQLException el) {
				el.printStackTrace();
			}
			    finally{}
			 	
				
			}
		});
		btnModify.setEnabled(false);
		btnModify.setBounds(445, 550, 85, 23);
		contentPane.add(btnModify);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(SystemColor.inactiveCaptionBorder);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(580, 550, 85, 23);
		contentPane.add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(144, 343, 520, 187);
		contentPane.add(scrollPane);
		
		table=new JTable();
		table.setOpaque(false);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				sno.setText((String) model.getValueAt(table.getSelectedRow(), 0));
				from.setText((String) model.getValueAt(table.getSelectedRow(), 3));
				to.setText((String) model.getValueAt(table.getSelectedRow(), 4));
				weight.setText((String) model.getValueAt(table.getSelectedRow(), 5));
				rate.setText((String) model.getValueAt(table.getSelectedRow(), 6));
				freight.setText((String) model.getValueAt(table.getSelectedRow(), 7));
				advance.setText((String) model.getValueAt(table.getSelectedRow(), 8));
				balance.setText((String) model.getValueAt(table.getSelectedRow(), 9));
				transporter.setText((String) model.getValueAt(table.getSelectedRow(), 10));
				terms.setText((String) model.getValueAt(table.getSelectedRow(), 11));
				remarks.setText((String) model.getValueAt(table.getSelectedRow(), 12));
				btnModify.setEnabled(true);
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.No.", "Date", "Vehicle No.", "From", "To", "Weight", "Rate", "Freight", "Advance", "Balance", "Transporter", "Payment Terms", "Remarks"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(89);
		table.getColumnModel().getColumn(5).setPreferredWidth(88);
		table.getColumnModel().getColumn(6).setPreferredWidth(88);
		table.getColumnModel().getColumn(7).setPreferredWidth(92);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(140);
		table.getColumnModel().getColumn(11).setPreferredWidth(110);
		table.getColumnModel().getColumn(12).setPreferredWidth(170);
		
		JLabel lblTripDetails = new JLabel("Trip Details");
		contentPane.add(lblTripDetails);
		lblTripDetails.setForeground(Color.BLACK);
		lblTripDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTripDetails.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTripDetails.setBounds(102, 11, 630, 64);
		
		JLabel lblNewLabel_1 = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("vehicledetails.png");
		try {
			lblNewLabel_1.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lblNewLabel_1.setBounds(0, 0, 790, 592);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
