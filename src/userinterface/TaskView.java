package userinterface;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.joda.time.DateTime;

import storage.Task;

public class TaskView extends JPanel implements ActionListener {
	private String taskName;
	private String taskCategory;
	private String priority;
	private DateTime taskDate;
	private Boolean isDone;

	private JSlider progressSlider;
	private JButton doneButton;

	private JLabel nameLabel;
	private JLabel categoryLabel;
	private JLabel priorityLabel;
	private JLabel dateLabel;

	public TaskView(Task activeTask) {

		if (activeTask != null) {
			this.taskName = activeTask.getTaskName();
			this.taskCategory = activeTask.getTaskCategory();
			this.priority = activeTask.getPriority();
			this.taskDate = activeTask.getTaskCreated();
			this.isDone = activeTask.isDone();

			this.createUI();
		}
	}

	private void createUI() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;

		c.ipadx = 20;
		this.nameLabel = new JLabel(this.taskName);
		this.add(this.nameLabel, c);

		c.ipadx = 0;
		c.gridx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.categoryLabel = new JLabel(this.taskCategory);
		this.add(this.categoryLabel, c);

		c.gridy = 1;
		c.gridx = 0;
		c.ipadx = 20;
		c.gridwidth = 1;
		this.priorityLabel = new JLabel(this.priority.toString());
		this.add(this.priorityLabel, c);

		c.gridx = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.ipadx = 0;
		this.dateLabel = new JLabel(this.taskDate.toString());
		this.add(this.dateLabel, c);

		c.gridx = 0;
		c.gridy = 2;
		this.progressSlider = this.setupProgressSlider();
		this.add(progressSlider, c);

		c.gridwidth = 2;
		c.gridy = 3;
		this.doneButton = new JButton("Finished!");
		this.add(doneButton, c);
	}

	private JSlider setupProgressSlider() {
		JSlider tempSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		tempSlider.setMajorTickSpacing(25);
		tempSlider.setMinorTickSpacing(5);
		tempSlider.setPaintTicks(true);
		tempSlider.setPaintLabels(true);
		tempSlider.setSnapToTicks(true);

		return tempSlider;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
