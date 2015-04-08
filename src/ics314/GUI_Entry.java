package ics314;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class GUI_Entry {
	JButton button;
	JFrame frame;
	JTextField eventNameTextField;
	JLabel eventNameLabel;
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
		eventNameTextField = new JTextField(20);
		eventNameLabel = new JLabel("Event Name");
		
		eventNameLabel.setBounds(10,10, 130,25);
		eventNameTextField.setBounds(170,10, 130, 25);
		button.setBounds(400, 300, 200, 40);
		button.addActionListener(new SubmitButtonHandle());
		
		frame.add(eventNameTextField);
		frame.add(eventNameLabel);
		frame.add(button);
		

		
		
		
		//frame.pack();
		frame.setVisible(true);
		
	}
	
	private class SubmitButtonHandle implements ActionListener{
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(eventNameTextField.getText());
			
		}
		
	}
	private static void createAndShowGUI(){
		GUI_Entry gui = new GUI_Entry();
		
	}
}
