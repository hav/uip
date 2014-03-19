package userinterface;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import controller.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Add new task window
 * @author Da Zhang
 *
 */
public class AddTaskView implements ActionListener {
	
	public AddTaskView adv = this;
	public JFrame popUp = null;
	public JTextField jtaskname = null;
	public JTextField jtaskcat = null;
	public JComboBox jtaskpri = null;
	public JDateChooser dcstart = null;
	public JDateChooser dcend = null;
	public JTextField jhour = null;
	public JTextField jmin = null;
	public JTextField jendhour = null;
	public JTextField jendmin = null;

	public AddTaskView() {

		// creation of a new frame
		popUp = new JFrame();
		popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popUp.pack();
		popUp.setResizable(false);
		popUp.setSize(600, 300); // setting the size of the frame
		popUp.setTitle("Add a new task"); // the title of the window
		Container pane = popUp.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		// pane.setLayout(new GridLayout(0,2)); //only two objects can be in
		// each row

		// Top panel for task name, task category, task priority
		JPanel toppanel = new JPanel();

		// Panel for task name
		JPanel namepanel = new JPanel();
		namepanel.setLayout(new FlowLayout());
		// Label
		JLabel lTaskName = new JLabel("Task name"); // label task name
		namepanel.add(lTaskName); // adding the label task name

		// Text Field
		jtaskname = new JTextField(40);
		jtaskname.setText("mytask");
		namepanel.add(jtaskname); // adding the text area

		// Panel for category and priority
		JPanel catandpriority = new JPanel();
		// Category panel
		JPanel pcat = new JPanel();
		pcat.setLayout(new FlowLayout());
		JLabel ltaskcat = new JLabel("Category");
		jtaskcat = new JTextField(17);
		pcat.add(ltaskcat);
		pcat.add(jtaskcat);
		// Priority Panel
		JPanel ppriority = new JPanel();
		ppriority.setLayout(new FlowLayout());
		JLabel ltaskpri = new JLabel("Priority");
		
		
		String[] priStrings = { "High", "Normal", "Low" };
		jtaskpri = new JComboBox(priStrings);
		jtaskpri.setSelectedIndex(1);
		jtaskpri.addActionListener(this);
		
		ppriority.add(ltaskpri);
		ppriority.add(jtaskpri);

		catandpriority.setLayout(new FlowLayout());
		catandpriority.add(ppriority);
		catandpriority.add(pcat);

		toppanel.add(namepanel, BorderLayout.PAGE_START);
		toppanel.add(catandpriority, BorderLayout.PAGE_END);

		pane.add(toppanel);

		// Panel for DatePicker
		JPanel ptasktime = new JPanel();
		ptasktime.setLayout(new GridLayout(0,2));

		// Panel For Start Time
		JPanel pStartpanel = new JPanel();
		pStartpanel.setLayout(new BoxLayout(pStartpanel, BoxLayout.Y_AXIS));

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		JLabel lstartdate = new JLabel("Start Date");
		dcstart = new JDateChooser();
		dcstart.setDate(new Date());
		p1.add(lstartdate);
		p1.add(dcstart);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		JLabel lstarttime = new JLabel("Start time");
		jhour = new JTextField(2);
		jhour.setText("00");
		JLabel maohao = new JLabel(":");
		jmin = new JTextField(2);
		jmin.setText("00");
		p2.add(lstarttime);
		p2.add(jhour);
		p2.add(maohao);
		p2.add(jmin);

		pStartpanel.add(p1);
		pStartpanel.add(p2);

		JPanel pEndpanel = new JPanel();
		pEndpanel.setLayout(new BoxLayout(pEndpanel, BoxLayout.Y_AXIS));

		JPanel ep1 = new JPanel();
		ep1.setLayout(new FlowLayout());
		JLabel lenddate = new JLabel("End Date");
		dcend = new JDateChooser();
		dcend.setDate(new Date());
		ep1.add(lenddate);
		ep1.add(dcend);

		JPanel ep2 = new JPanel();
		ep2.setLayout(new FlowLayout());
		JLabel lendtime = new JLabel("End time");
		jendhour = new JTextField(2);
		jendhour.setText("00");
		JLabel maohaoend = new JLabel(":");
		jendmin = new JTextField(2);
		jendmin.setText("00");
		ep2.add(lendtime);
		ep2.add(jendhour);
		ep2.add(maohaoend);
		ep2.add(jendmin);

		pEndpanel.add(ep1);
		pEndpanel.add(ep2);

		ptasktime.add(pStartpanel, BorderLayout.LINE_START);
		ptasktime.add(pEndpanel, BorderLayout.LINE_END);
		// dateChooser.setBounds(20, 20, 200, 20);
		
		pane.add(ptasktime);

		// create a button to save the task
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(0,2));
		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Cancel");
		buttonpanel.add(cancelButton); // adding the button
		buttonpanel.add(saveButton);
		
		pane.add(buttonpanel);
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.setVisible(false);
			}
		});

		// add an action to the save button
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().saveTaskClicked(adv);
			}
		});

		// keep the window visible
		popUp.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
