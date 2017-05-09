package frontend;

import backend.*;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class DisplayTranscriptScreen {

	private DocumentReader dr;
	
	private JFrame frame;
	
	private ArrayList<SemesterPanel> semesterPanels;
	
	private JButton addSemester;
	private JPanel semesterPanel;
	private JPanel totalPanel;
	private JScrollPane scrollSemesters;
	
	private ArrayList<Semester> semesters;
	
	public DisplayTranscriptScreen(String docName){
		dr = new DocumentReader(docName);
	}
	
	public void setGUI(){
		semesters = dr.getAllSemesters();
		semesterPanels = new ArrayList<SemesterPanel>();
		
		for(Semester s : semesters){
			SemesterPanel dsp = new SemesterPanel(dr,s.getSemester(),"display");
			semesterPanels.add(dsp);
		}
		
		addSemester = new JButton("Add Semester");
		
		semesterPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		for(SemesterPanel dsp : semesterPanels){
			semesterPanel.add(dsp,c);
			c.gridy = c.gridy + 1;
		}
		
		scrollSemesters = new JScrollPane(semesterPanel);
		scrollSemesters.setPreferredSize(new Dimension(650,400));

		totalPanel = new JPanel(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		totalPanel.add(scrollSemesters,c);
		
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		totalPanel.add(addSemester,c);
		
		frame = new JFrame("Transcript Tracker -- Display Transcript");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(totalPanel,BorderLayout.CENTER);
		frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
}
