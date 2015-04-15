package ics314;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class GUI_Entry {
	JButton button;
	JFrame frame;
	JTextField eventNameTextField;
	JLabel eventNameLabel;
	JComboBox<String> timeZone;
	JLabel timeZoneLabel;
	public static void main(String [] args){
		
		final class GuiRunner implements Runnable {

			@Override
			public void run() {
				createAndShowGUI();

			}

		}
		
		SwingUtilities.invokeLater(new GuiRunner());
	}
	
	public GUI_Entry(){
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Calendar app");
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton("Submit");
		button.setBounds(400, 300, 200, 40);
		button.addActionListener(new SubmitButtonHandle());
		frame.add(button);
		
		
		eventNameTextField = new JTextField(20);
		eventNameTextField.setBounds(170,10, 130, 25);
		frame.add(eventNameTextField);
		
		eventNameLabel = new JLabel("Event Name");
		eventNameLabel.setBounds(10,10, 130,25);
		frame.add(eventNameLabel);
		
		timeZoneLabel = new JLabel("TimeZone");
		timeZoneLabel.setBounds(10, 50, 130, 25);
		
		frame.add(timeZoneLabel);
		initTimeZone();
		frame.add(timeZone);

		//frame.pack();
		frame.setVisible(true);
		
	}
	
	private void initTimeZone(){
		
		Vector<String> timeZoneList = new Vector<String>();
		timeZoneList.add("Pacific/Honolulu");
		timeZoneList.add("Pacific/Cali");
		timeZoneList.add("Tester");
		timeZone = new JComboBox<String>(timeZoneList);
		timeZone.setBounds(170,50,155,25);
	}
	
	private class SubmitButtonHandle implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(eventNameTextField.getText());
			System.out.println(timeZone.getSelectedItem());
		}
		
	}
	private static void createAndShowGUI(){
		GUI_Entry gui = new GUI_Entry();
		
	}
}
