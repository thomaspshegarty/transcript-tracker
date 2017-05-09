package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class OpeningScreen {
	private JFrame frame;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JButton newButton;
	private JButton openButton;
	private JLabel prompt;
	private JTextField name;
	
	public void setGUI() throws IOException {
		newButton = new JButton("New Transcript");
		openButton = new JButton("Open Transcript");
		prompt = new JLabel("<html><body style='width: 250 px'>Would you like to view your transcript, or create a new one?</html>");
		
		name = new JTextField();
		name.setPreferredSize(new Dimension(200,20));
		
		newButton.addActionListener(new newTranscriptListener());
		openButton.addActionListener(new openTranscriptListener());
		
		panel1 = new JPanel();
		prompt.setBackground(panel1.getBackground());
		panel1.add(prompt);
		
		panel2 = new JPanel();
		panel2.add(newButton);
		panel2.add(openButton);
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panel1,c);
		
		c.gridy = 1;
		panel.add(panel2,c);
		
		frame = new JFrame("Trascript Tracker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(400,200));
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	private class newTranscriptListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			NewTranscriptScreen nts = new NewTranscriptScreen();
			frame.dispose();
			try {
				nts.setGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private class openTranscriptListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			OpenTranscriptScreen ets = new OpenTranscriptScreen();
			frame.dispose();
			try{
				ets.setGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		OpeningScreen os = new OpeningScreen();
		os.setGUI();
	}
}
