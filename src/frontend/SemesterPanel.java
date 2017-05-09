package frontend;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.DocumentReader;
import backend.Semester;
import backend.Class;

public class SemesterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel semesterName;
	private JTextField inputSemester;

	private JLabel gpa;
	private JTextField gpaDisplay;

	private JLabel credits;
	private JTextField creditsDisplay;
	
	private ArrayList<Class> classes;

	public SemesterPanel(DocumentReader dr, String name, String mode){
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		semesterName = new JLabel("Semester:-");
		gpa = new JLabel("GPA:-");
		credits = new JLabel("Credits:-");

		inputSemester = new JTextField();
		inputSemester.setPreferredSize(new Dimension(50,20));
		inputSemester.setEditable(true);

		if(mode.equals("add")){
			gpaDisplay = new JTextField("0.00");
			gpaDisplay.setEditable(false);

			creditsDisplay = new JTextField("0.0");
			creditsDisplay.setEditable(false);
		}else if(mode.equals("edit")){
			inputSemester.setText(name);
			
			gpaDisplay = new JTextField(Double.toString(dr.getSemester(name).getGpa()));
			gpaDisplay.setEditable(false);

			creditsDisplay = new JTextField(Double.toString(dr.getSemester(name).getCredits()));
			creditsDisplay.setEditable(false);
		}else if(mode.equals("display")){
			inputSemester.setText(name);
			inputSemester.setEditable(false);
			
			gpaDisplay = new JTextField(Double.toString(dr.getSemester(name).getGpa()));
			gpaDisplay.setEditable(false);

			creditsDisplay = new JTextField(Double.toString(dr.getSemester(name).getCredits()));
			creditsDisplay.setEditable(false);
		}

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.anchor = GridBagConstraints.WEST;
		this.add(semesterName,c);

		c.gridx = 1;
		this.add(inputSemester,c);

		c.gridx = 2;
		this.add(gpa,c);

		c.gridx = 3;
		this.add(gpaDisplay,c);

		c.gridx = 4;
		this.add(credits,c);

		c.gridx = 5;
		this.add(creditsDisplay,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridwidth = 6;
		
		if(mode.equals("display") || mode.equals("edit")){
			Semester thisSemester = dr.getSemester(name);
			classes = thisSemester.getClasses();

			for(Class cl : classes){
				ClassComponent newC = new ClassComponent(cl, mode);
				c.gridx = 0;
				for(JComponent jc : newC){
					this.add(jc, c);
					c.gridx = c.gridx+1;
				}
				c.gridy = c.gridy+1;
			}
		}else{
			for(int count = 1; count < 5; count++){
				ClassComponent newC = new ClassComponent(count);
				c.gridx = 0;
				for(JComponent jc : newC){
					this.add(jc, c);
					c.gridx = c.gridx+1;
				}
				c.gridy = c.gridy+1;
			}
		}
		
	}
	
	public String getName(){return inputSemester.getText();}

}
