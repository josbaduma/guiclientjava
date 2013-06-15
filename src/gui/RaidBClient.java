package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import client.Client;

public class RaidBClient implements ActionListener {

	private JFrame frmRaidb;
	private JPasswordField passwordField;
	private JTextField userField;
	private JTextField diskIDField;
	private JButton logInButton;
	private JButton signUpButton;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private String message, answer;
	private Client client;
	private UserWindows loginWind;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaidBClient window = new RaidBClient();
					window.frmRaidb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RaidBClient() {
		this.client = new Client("186.15.92.57", 1234);
		this.loginWind = new UserWindows(this, this.client);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRaidb = new JFrame();
		frmRaidb.setResizable(false);
		frmRaidb.setTitle("RaidB");
		frmRaidb.setBounds(100, 100, 720, 500);
		frmRaidb.getContentPane().setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(50, 250, 250, 30);
		frmRaidb.getContentPane().add(passwordField);

		userField = new JTextField();
		userField.setBounds(50, 175, 250, 30);
		frmRaidb.getContentPane().add(userField);
		userField.setColumns(10);

		logInButton = new JButton("Log In");
		logInButton.setBounds(50, 367, 117, 25);
		logInButton.addActionListener(this);
		frmRaidb.getContentPane().add(logInButton);

		signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(183, 367, 117, 25);
		signUpButton.addActionListener(this);
		frmRaidb.getContentPane().add(signUpButton);

		diskIDField = new JTextField();
		diskIDField.setBounds(50, 320, 100, 25);
		frmRaidb.getContentPane().add(diskIDField);
		diskIDField.setColumns(10);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(50, 148, 70, 15);
		frmRaidb.getContentPane().add(userLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 223, 70, 15);
		frmRaidb.getContentPane().add(passwordLabel);

		JLabel diskIdLabel = new JLabel("Disk ID");
		diskIdLabel.setBounds(50, 293, 70, 15);
		frmRaidb.getContentPane().add(diskIdLabel);

		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(RaidBClient.class
				.getResource("/gui/logoTEC_gd.png")));
		logoLabel.setBounds(400, 150, 280, 270);
		frmRaidb.getContentPane().add(logoLabel);

		JLabel lblRaidb = new JLabel("RaidB");
		lblRaidb.setForeground(new Color(0, 0, 128));
		lblRaidb.setFont(new Font("Comfortaa", Font.BOLD | Font.ITALIC, 40));
		lblRaidb.setBounds(310, 50, 140, 60);
		frmRaidb.getContentPane().add(lblRaidb);

		JMenuBar menuBar = new JMenuBar();
		frmRaidb.setJMenuBar(menuBar);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mnOptions.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.signUpButton) {
			this.client.initClient();
			this.message = "adduser " + this.getLocalIP() + ":" + "1234" + ":"
					+ this.userField.getText() + ":"
					+ this.passwordField.getText() + ":"
					+ this.diskIDField.getText();
			this.client.sendMessage(message);
			this.answer = this.client.recibeAnswer();

			if (answer.equals("true")) {
				System.out.println("User registry");
			}
			
			this.userField.setText("");
			this.passwordField.setText("");
			this.diskIDField.setText("");
			this.client.closeClient();
		}
		if (e.getSource() == this.logInButton) {
			this.client.initClient();
			this.message = "connect " + this.getLocalIP() + ":" + "1234" + ":"
					+ this.userField.getText() + ":"
					+ this.passwordField.getText() + ":"
					+ this.diskIDField.getText();
			this.client.sendMessage(message);
			this.answer = this.client.recibeAnswer();
			
			if (answer.equals("true")) {
				System.out.println("User log in");
				this.loginWind.setVisible(true);
				this.frmRaidb.setVisible(false);
			}
			
			this.userField.setText("");
			this.passwordField.setText("");
			this.diskIDField.setText("");
			this.client.closeClient();
		}
		if (e.getSource() == this.mntmExit) {
			System.exit(0);
		}
		if (e.getSource() == this.mntmAbout) {
			String messageBox = ("Made by:\nJosé Daniel Badilla\nBrallan Aguilar\nDaniel Araya\nHowar Blandon");
			JOptionPane.showMessageDialog(null, messageBox, "About RaidB...",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public JFrame getFrame() {
		return this.frmRaidb;
	}

	public String getLocalIP() {
		String Ip = "";
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface iface = interfaces.nextElement();
				if (iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					Ip = addr.getHostAddress();
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return Ip;

	}
}
