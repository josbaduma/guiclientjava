package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateFileDialog extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6137308005483672838L;
	private JPanel contentPane;
	private JLabel directoryLabel;
	private JLabel formatLabel;
	private JTextField directoryField;
	private JTextField formatField;
	private JButton btnAccept;

	/**
	 * Create the frame.
	 */
	public CreateFileDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		directoryLabel = new JLabel();
		directoryLabel.setText("Directory");
		directoryLabel.setBounds(30, 15, 70, 25);
		contentPane.add(directoryLabel);

		directoryField = new JTextField();
		directoryField.setBounds(30, 40, 250, 30);
		contentPane.add(directoryField);

		formatLabel = new JLabel();
		formatLabel.setText("Format");
		formatLabel.setBounds(30, 75, 70, 25);
		contentPane.add(formatLabel);

		formatField = new JTextField();
		formatField.setBounds(30, 100, 250, 30);
		contentPane.add(formatField);

		btnAccept = new JButton("Accept");
		btnAccept.setBounds(163, 142, 117, 25);
		btnAccept.addActionListener(this);
		contentPane.add(btnAccept);
	}

	public String getMessage() {
		String message = this.directoryField.getText() + ":-f:"
				+ this.formatField.getText();
		return message;
	}
	
	public void deleteForLater()
	{
		this.directoryField.setText("");
		this.formatField.setText("");
	}
	
	public void buttonAction()
	{
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAccept)
		{
			this.buttonAction();
		}
	}
}
