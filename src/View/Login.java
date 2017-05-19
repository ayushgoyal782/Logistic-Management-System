package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;

import Models.Connector;

import javax.swing.border.BevelBorder;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public Login() {
		ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader1.getResourceAsStream("icon.png");
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(input1);
			setIconImage(myImg);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		

		setUndecorated(true);
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 507);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Information");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Logistic Management System V1.0\nDeveloped by:-\nAyush Goyal,\nPuneet Ojha\nStudent Of Illinois Institute of Technology,Chicago\nGuided By Prof. Yong Zheng", "Developer"
						, JOptionPane.INFORMATION_MESSAGE);
				 
			}
		});
		btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/net/sf/jasperreports/view/images/jricon.GIF")));
		btnNewButton.setBounds(462, 476, 16, 16);
		contentPane.add(btnNewButton);
		
		JLabel lblLogin_1 = new JLabel("Login");
		lblLogin_1.setForeground(SystemColor.desktop);
		lblLogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_1.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 20));
		lblLogin_1.setBounds(66, 41, 345, 31);
		contentPane.add(lblLogin_1);
		
		JLabel lblDevelopedByAyush = new JLabel("Developed by:- Ayush Goyal, Puneet Ojha");
		lblDevelopedByAyush.setForeground(new Color(105, 105, 105));
		lblDevelopedByAyush.setBounds(253, 478, 210, 14);
		contentPane.add(lblDevelopedByAyush);
		
		JLabel lblLogin = new JLabel("Logistic Management System");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Vrinda", Font.BOLD, 25));
		lblLogin.setBounds(0, 13, 493, 31);
		contentPane.add(lblLogin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(70, 215, 345, 233);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
		btnLogin.setForeground(new Color(75, 0, 130));
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setBounds(49, 154, 253, 37);
		panel.add(btnLogin);
		btnLogin.setToolTipText("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/org/hsqldb/util/GreenCircle.gif")));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				 Connection con;
					try {
						con=Connector.getConnection();
						PreparedStatement ps = con.prepareStatement("SELECT username, password, type FROM login WHERE (username = ? AND password = ?)");
					        ps.setString(1, username.getText());
					        ps.setString(2, password.getText());
					        ps.executeQuery();
					        ResultSet rs = ps.executeQuery();
					        String checkUser=null;
					        String checkPass=null;
					        String type=null;
					        while(rs.next())
					        {
					        checkUser = rs.getString(1);
					        checkPass = rs.getString(2);
					        type = rs.getString(3);
					        }
					        rs.close();
			        		con.close();
			        		if(checkUser==null){
					        	JOptionPane.showMessageDialog(null, "Wrong Username or Password\nTry Again", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
							     	
					        }
					        else if((checkUser.equals(username.getText())) && (checkPass.equals(password.getText()))&& type.contains("admin"))
					        {
					           	new MainMenu().setVisible(true);
					            	dispose();
					        
					        }
					        else if((checkUser.equals(username.getText())) && (checkPass.equals(password.getText()))&& type.contains("guest"))
					        {
					           
					            	new GuestMenu().setVisible(true);
							}
					        else
					        {
					        	 JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error!!!" , JOptionPane.INFORMATION_MESSAGE);
					        }

			        		
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
				
				
			}
		});
		
		username = new JTextField();
		username.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password.requestFocusInWindow();
			}
		});
		username.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 17));
		username.setToolTipText("Please Enter Usrname");
		username.setBounds(35, 26, 281, 37);
		panel.add(username);
		username.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(128, 202, 89, 23);
		panel.add(btnExit);
		btnExit.setToolTipText("Exit");
		btnExit.setIcon(new ImageIcon(Login.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		
		password = new JPasswordField();
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin.doClick();
			}
		});
		password.setToolTipText("Enter Password");
		password.setBounds(35, 91, 281, 37);
		panel.add(password);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 11));
		lblPassword.setBounds(35, 77, 66, 14);
		panel.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 11));
		lblUsername.setBounds(35, 12, 56, 14);
		panel.add(lblUsername);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/net/sf/dynamicreports/examples/images/user_male.png")));
		label.setBounds(181, 79, 128, 128);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setToolTipText("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("login.jpg");
		try {
			label_1.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}label_1.setBounds(10, 11, 473, 485);
		contentPane.add(label_1);
	}
}
