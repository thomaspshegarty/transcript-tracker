package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class OpenTranscriptScreen {
	private JFrame frame;
	private JPanel panel;
	private JFileChooser jfc;
	
	public void setGUI() throws IOException {
		jfc = new JFileChooser();
		jfc.addActionListener(new fileListener());
		
		panel = new JPanel();
		panel.add(jfc);
		
		frame = new JFrame("Trascript Tracker -- Open Transcript");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(600,350));
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public class fileListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			File xmlFile = jfc.getSelectedFile();
			frame.dispose();
			if(xmlFile == null){
				System.out.println("cancelled");
			}else{
				DisplayTranscriptScreen dts = new DisplayTranscriptScreen(xmlFile.getName());
				dts.setGUI();
			}
		}
		
	}
}
