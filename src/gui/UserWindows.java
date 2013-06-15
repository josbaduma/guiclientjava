package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import client.Client;

public class UserWindows extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fileField;
	private JTextField registryField;
	private JTextField numRegistryField;
	private RaidBClient parentWind;
	private JMenuItem optExit;
	private JButton btnAddRegistry;
	private JButton btnDeleteRegistry;
	private JButton btnModify;
	private JButton btnSeek;
	private JButton btnRead;
	private JMenuItem optCreateFile;
	private JMenuItem optOpenFile;
	private JMenuItem optGetFile;
	private JMenuItem optDeleteFile;
	private JMenuItem optDeleteFolder;
	private JMenuItem optCreateFolder;
	private JLabel userLabel;
	private CreateFileDialog newDialog;
	private Client client;
 
	/**
	 * Create the frame.
	 */
	public UserWindows(RaidBClient pParent, Client pClient) {
		this.parentWind = pParent;
		this.client = pClient;
		this.newDialog = new CreateFileDialog();
		
		setResizable(false);
		setBounds(100, 100, 740, 520);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu optionMenu = new JMenu("Options");
		menuBar.add(optionMenu);
		
		optCreateFile = new JMenuItem("Create File");
		optCreateFile.addActionListener(this);
		optionMenu.add(optCreateFile);
		
		optOpenFile = new JMenuItem("Open File");
		optOpenFile.addActionListener(this);
		optionMenu.add(optOpenFile);
		
		optGetFile = new JMenuItem("Get File");
		optGetFile.addActionListener(this);
		optionMenu.add(optGetFile);
		
		optCreateFolder = new JMenuItem("Create Folder");
		optCreateFolder.addActionListener(this);
		optionMenu.add(optCreateFolder);
		
		optDeleteFile = new JMenuItem("Delete File");
		optDeleteFile.addActionListener(this);
		optionMenu.add(optDeleteFile);
		
		optDeleteFolder = new JMenuItem("Delete Folder");
		optDeleteFolder.addActionListener(this);
		optionMenu.add(optDeleteFolder);
		
		optExit = new JMenuItem("LogOut");
		optExit.addActionListener(this);
		optionMenu.add(optExit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fileField = new JTextField();
		fileField.setBounds(50, 141, 640, 320);
		contentPane.add(fileField);
		fileField.setColumns(10);
		
		registryField = new JTextField();
		registryField.setBounds(110, 65, 580, 30);
		contentPane.add(registryField);
		registryField.setColumns(10);
		
		numRegistryField = new JTextField();
		numRegistryField.setBounds(50, 65, 50, 30);
		contentPane.add(numRegistryField);
		numRegistryField.setColumns(10);
		
		btnAddRegistry = new JButton("Add Registry");
		btnAddRegistry.setBounds(186, 104, 117, 25);
		btnAddRegistry.addActionListener(this);
		contentPane.add(btnAddRegistry);
		
		btnDeleteRegistry = new JButton("Delete Registry");
		btnDeleteRegistry.setBounds(315, 104, 117, 25);
		btnDeleteRegistry.addActionListener(this);
		contentPane.add(btnDeleteRegistry);
		
		btnModify = new JButton("Modify");
		btnModify.setBounds(444, 104, 117, 25);
		btnModify.addActionListener(this);
		contentPane.add(btnModify);
		
		btnSeek = new JButton("Seek");
		btnSeek.setBounds(573, 104, 117, 25);
		btnSeek.addActionListener(this);
		contentPane.add(btnSeek);
		
		btnRead = new JButton("Read");
		btnRead.setBounds(50, 104, 117, 25);
		btnRead.addActionListener(this);
		contentPane.add(btnRead);
		
		userLabel = new JLabel("User: ");
		userLabel.setBounds(587, 21, 103, 15);
		contentPane.add(userLabel);
		
		JLabel lblRaidB = new JLabel("Raid B");
		lblRaidB.setFont(new Font("LM Roman Unslanted 10", Font.BOLD | Font.ITALIC, 24));
		lblRaidB.setBounds(50, 12, 95, 30);
		contentPane.add(lblRaidB);
	}
	
	public void setUserLabel(String pUser)
	{
		this.userLabel.setText(pUser);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.optExit)
		{
			this.client.sendMessage("close");
			this.client.closeClient();
			this.setVisible(false);
			this.parentWind.getFrame().setVisible(true);
		}
		if(e.getSource() == this.optCreateFile)
		{
			this.newDialog.setVisible(true);
			String message = "touch " + this.newDialog.getMessage();
			this.newDialog.deleteForLater();
			this.client.sendMessage(message);	
			this.client.closeClient();
		}
		if(e.getSource() == this.optCreateFolder)
		{
			String message = "mkdir " + JOptionPane.showInputDialog(null, "Directory...", "Enter the directory: ", JOptionPane.QUESTION_MESSAGE);
			this.client.sendMessage(message);	
			this.client.closeClient();
		}
		if(e.getSource() == this.optOpenFile)
		{
			String message = "openfile " + JOptionPane.showInputDialog(null, "Directory...", "Enter the directory: ", JOptionPane.QUESTION_MESSAGE);
			this.client.sendMessage(message);
			this.fileField.setText(this.client.recibeAnswer());
			this.client.closeClient();
		}
		if(e.getSource() == this.optGetFile)
		{
			String message = "get " + JOptionPane.showInputDialog(null, "Directory...", "Enter the directory: ", JOptionPane.QUESTION_MESSAGE);
			this.client.sendMessage(message);
			this.fileField.setText(this.client.recibeAnswer());
			this.client.closeClient();
		}
		if(e.getSource() == this.optDeleteFile)
		{
			String message = "rm " + JOptionPane.showInputDialog(null, "Directory...", "Enter the directory: ", JOptionPane.QUESTION_MESSAGE);
			this.client.sendMessage(message);	
			this.client.closeClient();
		}
		if(e.getSource() == this.optDeleteFolder)
		{
			String message = "rm " + JOptionPane.showInputDialog(null, "Directory...", "Enter the directory: ", JOptionPane.QUESTION_MESSAGE);
			this.client.sendMessage(message);	
			this.client.closeClient();
		}
		if(e.getSource() == this.btnAddRegistry)
		{
			this.addAction();
		}
		if(e.getSource() == this.btnDeleteRegistry)
		{
			this.removeAction();
		}
		if(e.getSource() == this.btnModify)
		{
			this.modifyAction();
		}
		if(e.getSource() == this.btnRead)
		{
			this.readAction();
		}
		if(e.getSource() == this.btnSeek)
		{
			this.seekAction();
		}
	}
	
	public void addAction()
	{
		String message = "appendReg " + this.numRegistryField.getText() + ":"
	            + this.registryField.getText();
		System.out.println(message);
		this.client.sendMessage(message);
	}
	
	public void removeAction()
	{
		String message = "delReg " + this.numRegistryField.getText();
		System.out.println(message);
		this.client.sendMessage(message);
	}
	public void modifyAction()
	{
		String message = "write "+ this.registryField.getText() +
	            ":" + this.numRegistryField.getText() ;
		System.out.println(message);
		this.client.sendMessage(message);
	}
	
	public void seekAction()
	{
		String message = "seek " + this.numRegistryField.getText() ;
		System.out.println(message);
	    this.client.sendMessage(message);
	}
	public void readAction()
	{
		String answer, message = "read " + this.numRegistryField.getText() ;
		System.out.println(message);
	    this.client.sendMessage(message);
	    answer = this.client.recibeAnswer();
	    this.registryField.setText(answer);
	}
}
