package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;




public class MainMenu extends JFrame {

	private JPanel contentPane;

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
				try{
					MainMenu frame = new MainMenu();					
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
	public MainMenu() {
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
		setTitle("Logistic Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1280,720);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Font bt = new Font("Courier", Font.ITALIC,12);
		contentPane.setLayout(null);
		Font bt1 = new Font("Courier", Font.ITALIC,12);
		Font bt2 = new Font("Courier", Font.ITALIC,12);
		Font bt3 = new Font("Courier", Font.ITALIC,12);

		Font btm = new Font("Courier", Font.ITALIC,12);
		Font bt5 = new Font("Courier", Font.ITALIC,12);
		Font bt6 = new Font("Courier", Font.ITALIC,12);
		Font bt7 = new Font("Courier", Font.ITALIC,12);
		Font bt8 = new Font("Courier", Font.ITALIC,12);
		Font bt9 = new Font("Courier", Font.ITALIC,12);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1280, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Vehicle Details");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewVehicleDetails = new JMenuItem("New Vehicle Details");
		mntmNewVehicleDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mntmNewVehicleDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VehicleDetails().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewVehicleDetails);
		
		JMenuItem mntmAllVehicleDetails = new JMenuItem("All Vehicle Details Report");
		mntmAllVehicleDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VehicleReport().setVisible(true);
			}
		});
		mnNewMenu.add(mntmAllVehicleDetails);
		
		JMenu mnTrip = new JMenu("Trip");
		menuBar.add(mnTrip);
		
		JMenuItem mntmTripDetails = new JMenuItem("Trip Details");
		mntmTripDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mntmTripDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Trip().setVisible(true);
			}
		});
		mnTrip.add(mntmTripDetails);
		
		JMenuItem mntmTripReportFor = new JMenuItem("Trip Report For Vehicle");
		mntmTripReportFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TripReport().setVisible(true);
			}
		});
		mnTrip.add(mntmTripReportFor);
		
		JMenuItem mntmTripReportsBetween = new JMenuItem("Trip Reports Between Dates");
		mntmTripReportsBetween.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TripReportBtnDate().setVisible(true);
			}
		});
		mnTrip.add(mntmTripReportsBetween);
		
		JMenuItem mntmBalanceAmountReport = new JMenuItem("Balance Amount Report For Transporter");
		mntmBalanceAmountReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BalanceAmountReport().setVisible(true);
			}
		});
		mnTrip.add(mntmBalanceAmountReport);
		
		JMenu mnMaintenance = new JMenu("Maintenance");
		menuBar.add(mnMaintenance);
		
		JMenuItem mntmDetailsentry = new JMenuItem("Details/Entry");
		mntmDetailsentry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmDetailsentry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaintenanceDetails().setVisible(true);
			}
		});
		mnMaintenance.add(mntmDetailsentry);
		
		JMenuItem mntmReport = new JMenuItem("Report");
		mntmReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaintenanceReport().setVisible(true);
			}
		});
		mnMaintenance.add(mntmReport);
		
		JMenu mnTyre = new JMenu("Tyre Change");
		menuBar.add(mnTyre);
		
		JMenuItem mntmDetails = new JMenuItem("Details");
		mntmDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		mntmDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TyreDetails().setVisible(true);
			}
		});
		mnTyre.add(mntmDetails);
		
		JMenuItem mntmReport_1 = new JMenuItem("Report");
		mntmReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TyreReport().setVisible(true);
			}
		});
		mnTyre.add(mntmReport_1);
		
		JMenu mnDriver = new JMenu("Driver");
		menuBar.add(mnDriver);
		
		JMenuItem mntmDriverDetails = new JMenuItem("Driver Details");
		mntmDriverDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mntmDriverDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverDetails().setVisible(true);
			}
		});
		mnDriver.add(mntmDriverDetails);
		
		JMenuItem mntmDriverPay = new JMenuItem("Driver Pay");
		mntmDriverPay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmDriverPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverPay().setVisible(true);
			}
		});
		mnDriver.add(mntmDriverPay);
		
		JMenuItem mntmDriverPayReport = new JMenuItem("Driver Pay Report");
		mntmDriverPayReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverPayReport().setVisible(true);
			}
		});
		mnDriver.add(mntmDriverPayReport);
		
		JMenuItem mntmAllDriverPay = new JMenuItem("All Driver Details Report");
		mntmAllDriverPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AllDriverReport().setVisible(true);
			}
		});
		mnDriver.add(mntmAllDriverPay);
		
		JMenu mnUtilities = new JMenu("Utilities");
		menuBar.add(mnUtilities);
		
		JMenuItem mntmWordpad = new JMenuItem("Wordpad");
		mntmWordpad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		mntmWordpad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
		            Process p = Runtime.getRuntime().exec("C:/Program Files/Windows NT/Accessories/wordpad.exe");
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }

			}
		});
		mnUtilities.add(mntmWordpad);
		
		JMenuItem mntmNotepad = new JMenuItem("Notepad");
		mntmNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNotepad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            Process p = Runtime.getRuntime().exec("C:/Windows/System32/notepad.exe");
		        } catch (IOException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
				
			}
		});
		mnUtilities.add(mntmNotepad);
		
		JMenuItem mntmCalculator = new JMenuItem("Calculator");
		mntmCalculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
		            Process p = Runtime.getRuntime().exec("C:/Windows/System32/calc.exe");
		        } catch (IOException e2) {
		            // TODO Auto-generated catch block
		            e2.printStackTrace();
		        }
			}
		});
		mnUtilities.add(mntmCalculator);
		Font bt10 = new Font("Courier", Font.ITALIC,12);
		Font bt11 = new Font("Courier", Font.ITALIC,12);
		Font bt12 = new Font("Courier", Font.ITALIC,12);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Data Entry", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setOpaque(false);
		panel.setBounds(88, 55, 1098, 135);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button = new JButton("Vehicle Details");
		button.setBackground(Color.WHITE);
		button.setBounds(60, 21, 137, 45);
		panel.add(button);
		button.setFont(bt);
		
		JButton button_1 = new JButton("Trip Details");
		button_1.setBounds(250, 21, 158, 45);
		panel.add(button_1);
		button_1.setFont(bt1);
		
		JButton button_5 = new JButton("Tyre Details");
		button_5.setBounds(463, 21, 158, 45);
		panel.add(button_5);
		button_5.setFont(bt5);
		
		JButton button_2 = new JButton("Driver Details");
		button_2.setBounds(675, 21, 158, 45);
		panel.add(button_2);
		button_2.setFont(bt2);
		
		JButton button_3 = new JButton("Driver Pay");
		button_3.setBounds(887, 21, 158, 45);
		panel.add(button_3);
		button_3.setFont(bt3);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(195, 22, 55, 44);
		panel.add(verticalStrut);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setBounds(407, 22, 55, 44);
		panel.add(verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setBounds(620, 21, 55, 248);
		panel.add(verticalStrut_2);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setBounds(832, 22, 55, 239);
		panel.add(verticalStrut_3);
		
		JButton btnMaintainanceDetails = new JButton("Maintainance Details");
		btnMaintainanceDetails.setBounds(453, 77, 183, 45);
		panel.add(btnMaintainanceDetails);
		btnMaintainanceDetails.setFont(btm);
		
		btnMaintainanceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaintenanceDetails().setVisible(true);
				
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverPay().setVisible(true);
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverDetails().setVisible(true);
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Generate Reports", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(88, 228, 1098, 135);
		contentPane.add(panel_1);
		
		JButton btnTripReportFor = new JButton("Trip Report");
		btnTripReportFor.setBounds(60, 23, 137, 45);
		panel_1.add(btnTripReportFor);
		btnTripReportFor.setFont(bt6);
		
		JButton btnTripReportBetween = new JButton("Trip report between dates");
		btnTripReportBetween.setBounds(108, 79, 239, 45);
		panel_1.add(btnTripReportBetween);
		btnTripReportBetween.setFont(bt7);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setBounds(197, 23, 55, 44);
		panel_1.add(verticalStrut_4);
		
		JButton btnTyreReport = new JButton("Tyre Report");
		btnTyreReport.setBounds(250, 23, 158, 45);
		panel_1.add(btnTyreReport);
		btnTyreReport.setFont(bt9);
		
		JButton btnDriverPayReport = new JButton("Driver Pay Report");
		btnDriverPayReport.setBounds(463, 24, 158, 45);
		panel_1.add(btnDriverPayReport);
		btnDriverPayReport.setFont(bt12);
		
		JButton btnBalanceAmountReport = new JButton("Balance Report");
		btnBalanceAmountReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BalanceAmountReport().setVisible(true);
			}
		});
		btnBalanceAmountReport.setBounds(675, 24, 158, 45);
		panel_1.add(btnBalanceAmountReport);
		btnBalanceAmountReport.setFont(bt10);
		
		JButton btnAllDriverReport = new JButton("All Drivers Details Report");
		btnAllDriverReport.setBounds(417, 79, 239, 45);
		panel_1.add(btnAllDriverReport);
		btnAllDriverReport.setFont(bt11);
		
		JButton btnMaintenanceReportFor = new JButton("Maintenance Report");
		btnMaintenanceReportFor.setBounds(888, 23, 158, 45);
		panel_1.add(btnMaintenanceReportFor);
		btnMaintenanceReportFor.setFont(bt8);
		
		JButton btnAllVehicleDetails = new JButton("All Vehicle Details Report");
		btnAllVehicleDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VehicleReport().setVisible(true);
			}
		});
		btnAllVehicleDetails.setFont(new Font("Monospaced", Font.ITALIC, 12));
		btnAllVehicleDetails.setBounds(742, 79, 239, 45);
		panel_1.add(btnAllVehicleDetails);
		btnMaintenanceReportFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaintenanceReport().setVisible(true);
			}
		});
		btnAllDriverReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AllDriverReport().setVisible(true);
			}
		});
		
		btnDriverPayReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DriverPayReport().setVisible(true);
			}
		});
		btnTyreReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TyreReport().setVisible(true);
			}
		});
		btnTripReportBetween.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TripReportBtnDate().setVisible(true);
			}
		});
		
		
		JButton btnAlerts = new JButton("Alerts");
		btnAlerts.setBounds(1175, 32, 89, 23);
		btnAlerts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Alert().setVisible(true);
			}
		});
		contentPane.add(btnAlerts);
		
		JLabel label = new JLabel("");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("mainmenu.jpg");
		try {
			label.setIcon(new ImageIcon(ImageIO.read(input)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		label.setBounds(0, 22, 1280, 698);
		contentPane.add(label);
		btnTripReportFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TripReport().setVisible(true);
			}
		});
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TyreDetails().setVisible(true);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Trip().setVisible(true);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				new VehicleDetails().setVisible(true);
					
			}
		});
		
		new Alert().setVisible(true);
	}
}
