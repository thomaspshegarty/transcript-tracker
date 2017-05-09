package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;



public class NewTranscriptScreen {
	private JFrame frame;
	private JPanel panel;
	private JPanel prompt_panel;
	private JPanel button_panel;
	private JPanel name_panel;
	private JButton submitButton;
	private JLabel prompt;
	private JTextField name;
	
	public void setGUI() throws IOException {
		submitButton = new JButton();
		String p = "<html><body style='width: 250 px'>"
				+ "Enter your name. Note: this will make the corresponding file \"yourname\"_transcript.xml"
				+ "</html>";
		prompt = new JLabel(p,SwingConstants.CENTER);
		name = new JTextField();
		name.setPreferredSize(new Dimension(200,20));
		
		submitButton.setText("Submit");
		submitButton.addActionListener(new nameListener());
		
		prompt_panel = new JPanel();
		prompt.setBackground(prompt_panel.getBackground());
		prompt_panel.add(prompt);
		
		button_panel = new JPanel();
		button_panel.add(submitButton);
		
		name_panel = new JPanel();
		name_panel.add(name);
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(prompt_panel,c);
		
		c.gridy = 1;
		panel.add(name_panel,c);
		
		c.gridy = 2;
		panel.add(button_panel,c);
		
		frame = new JFrame("Trascript Tracker -- New Transcript");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(400,200));
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	private class nameListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String n = name.getText();
			if(n == null || n.equals("")){
				frame.dispose();
				System.out.println("bad name");
			}else{
				frame.dispose();
				String filename = (n+"_transcript.xml").toLowerCase();
				SemesterScreen addSem = new SemesterScreen(filename,"add","");
				try {
					addSem.setGUI();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
}
