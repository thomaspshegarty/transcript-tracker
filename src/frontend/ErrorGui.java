package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorGui extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public ErrorGui(String errorType){
		if(errorType.equals("semester")){
			errorMessage = "<html><body style='width: 250 px'>Please name your semester</html>";
		}else if(errorType.equals("class")){
			errorMessage = "<html><body style='width: 250 px'>Please fill out empty boxes for all entered classes</html>";

		}
	}

	public void setGUI(){
		JLabel errorLabel = new JLabel(errorMessage);
		JPanel errorPanel = new JPanel();
		errorPanel.add(errorLabel,JPanel.CENTER_ALIGNMENT);
		errorPanel.setPreferredSize(new Dimension(400,100));

		this.setTitle("Error");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(errorPanel,BorderLayout.CENTER);
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);
		this.setResizable(false);
	}

}