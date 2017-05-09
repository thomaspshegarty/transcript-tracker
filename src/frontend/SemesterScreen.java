package frontend;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import backend.*;
import backend.Class;


public class SemesterScreen {
	private JFrame frame;
	
	private JPanel panel;
	private JPanel semesterPanel;
	private JPanel classPanel;
	private JPanel buttonPanel;
	
	private JButton addClass;
	private JButton submitChanges;
	private JButton cancel;
	
	private ArrayList<ClassComponent> classComponents;

	private DocumentWriter dw;
	private DocumentReader dr;
	
	private String semesterName;
	private String mode;
	
	public SemesterScreen(String filename, String inMode, String name){
		dw = new DocumentWriter(filename);
		dr = new DocumentReader(filename);
		mode = inMode;
		if(inMode.equals("add")){
			semesterName = "";
		}else{
			semesterName = name;
		}
	}
	
	public void setGUI() throws IOException {
		GridBagConstraints c = new GridBagConstraints();
		
		semesterPanel = new SemesterPanel(dr,semesterName,mode);
		
		buttonPanel = new JPanel(new GridBagLayout());
		
		addClass = new JButton("Add Class");
		submitChanges = new JButton("Submit Changes");
		cancel = new JButton("Cancel");
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		addClass.addActionListener(new addClassListener());
		buttonPanel.add(addClass, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1;
		submitChanges.addActionListener(new submissionListener());
		buttonPanel.add(submitChanges,c);
		
		c.gridx = 1;
		buttonPanel.add(cancel,c);
		
		panel = new JPanel(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(semesterPanel,c);
		
		c.gridy = 1;
		panel.add(buttonPanel,c);
		
		frame = new JFrame("Trascript Tracker -- "+mode.toUpperCase()+" Semester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	private class addClassListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			ClassComponent c = new ClassComponent(classComponents.size());
			GridBagConstraints gbc = new GridBagConstraints();
			classComponents.add(c);
			
			gbc.gridx = 0;
			gbc.gridy = classComponents.size();
			for(JComponent jc : c){
				classPanel.add(jc, gbc);
				gbc.gridx = gbc.gridx+1;
			}
			
			frame.pack();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			frame.setVisible(true);
			frame.setResizable(false);
		}
		
	}
	
	private class submissionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			ArrayList<Class> classes = new ArrayList<Class>();
			boolean unfilledClass = false;
			
			for(ClassComponent c : classComponents){
				double grades;
				double credits;
				int idNum;
				String name;
				String code;
				int foundCount = 0;
				
				try{grades = c.getGrade();foundCount++;}catch(Exception gradeExc){grades = 0.0;}
				try{credits = c.getCredits();foundCount++;}catch(Exception credExc){credits = 0.0;}
				try{idNum = c.getId();foundCount++;}catch(Exception idExc){idNum = 0;}
				try{name = c.getName();foundCount++;}catch(Exception nameExc){name = "";}
				try{code = c.getCode();foundCount++;}catch(Exception codeExc){code = "";}
				
				if(idNum != 0 && !code.equals("") && !name.equals("") && credits != 0.0 && grades != 0.0){
					Class newC = new Class(idNum,code,name,grades,credits);
					classes.add(newC);
					newC.printClass();
				}else if(foundCount > 1 && foundCount < 5){
					unfilledClass = true;
				}
			}
			
			String semesterName = semesterPanel.getName();
			if(semesterName == null || semesterName.equals("")){
				ErrorGui eg = new ErrorGui("semester");
				eg.setGUI();
			}else if(unfilledClass){
				ErrorGui eg = new ErrorGui("class");
				eg.setGUI();
			}else{
				Semester sm = new Semester(classes,semesterName);
				dw.writeSemester(sm);
				frame.dispose();
				System.out.println("switch to display here");
			}
		}
		
	}
	
}
