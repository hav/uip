package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import storage.TaskList;
import userinterface.AddTaskView;
import userinterface.MainLayout;
import userinterface.TaskListView;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * MainController is the class that will control the whole program
 *	There can only be 1 instance of this class.
 */
public class MainController {

	private static MainController instance = null;
	private int height = 500;
	private int width = 800;
	private int inlineLeft = 0;
	private int inlineTop = 0;
	private String language = "en";
	private String country = "US";
	private static MainLayout mainLayout;
	
	private String lAndF = "Default";
	private String theme = "Default";


	private TaskList taskList = TaskList.getInstance();

	
	/**
	 * Make controller could be got globally
	 * @return
	 * @author Da Zhang
	 */
	public static synchronized MainController getInstance() {
		if (instance == null) {
			try {
				instance = new MainController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	/*
	 * This is the MainController for the program.
	 */
	public static void main(String[] args) {

		mainLayout = new MainLayout();
	}

	/**
	 * ReloadGui closes the old window and starts up a new.
	 * @author Jesper Andersson
	 */
	public void ReloadGui(){
		MainLayout temp = new MainLayout();
		mainLayout.setVisible(false); //you can't see me!
		mainLayout.dispose();
		mainLayout = temp;
		
	}

	/**
	 * Load tasks from db and show in the tasklistview
	 * @param tlview
	 */
	public void loadandshowTaskList(TaskListView tlview) {
		//Get tasklist
		TaskList tasklist = TaskList.getInstance();
		//Load to list model
        /*
		for(int i=0; i<tasklist.getListSize(); i++) {
			tlview.model.addElement(tasklist.getTaskByIndex(i).getTaskName());
		}
		//Init tasklist
		tlview.tlist.setModel(tlview.model);
		tlview.taskpanel.add(tlview.tlist);
			*/
	}

	/*
	 * 
	 * Getters
	 */
	public String getLanguage() {
		return this.language;
	}
	public String getCountry() {
		return this.country;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public int getInlineTop() {
		return this.inlineTop;
	}

	public int getInlineLeft() {
		return this.inlineLeft;
	}

	
	public String getLookandfeel() {
		return this.lAndF;
	}
	
	public String getTheme() {
		return this.theme;
	}

	public TaskList getTaskList(){
		return taskList;
		
	}

	/*
	 * 
	 * Setters
	 */
	public void setNewSize(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public void setNewInline(int inlineLeft, int inlineTop) {
		this.inlineLeft = inlineLeft;
		this.inlineTop = inlineTop;
	}
	public void setNewLanguage(String language, String country){
		this.language = language;
		this.country = country;
	}

	/**
	 * LoadConfig takes all the variables from the property file and saves them into the corresponding variable.
	 * @author Jesper Andersson
	 */
	public void LoadConfig() {

		File configFile = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"config.properties");

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			this.height = Integer.parseInt(props.getProperty("height"));
			this.width = Integer.parseInt(props.getProperty("width"));
			this.inlineLeft = Integer.parseInt(props.getProperty("inlineLeft"));
			this.inlineTop = Integer.parseInt(props.getProperty("inlineTop"));
			this.language = props.getProperty("language");
			this.country = props.getProperty("country");
			this.lAndF = props.getProperty("laf");
			this.theme = props.getProperty("theme");
			reader.close();
		} catch (FileNotFoundException ex) {
			// file does not exist
			System.out.println("First Time Start");
		} catch (IOException ex) {
			// I/O error
		}
	}
	// Saves the current position and size of the window
	/**
	 * SaveConfig takes all the variables and saves them to the property.
	 * @author Jesper Andersson
	 */
		public void SaveConfig() {
			File configFile = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"config.properties");
			try {
				FileOutputStream out = new FileOutputStream(configFile);
				Properties props = new Properties();
				props.setProperty("height", Integer.toString(this.height));
				props.setProperty("width", Integer.toString(this.width));
				props.setProperty("inlineTop", Integer.toString(this.inlineTop));
				props.setProperty("inlineLeft", Integer.toString(this.inlineLeft));
				props.setProperty("language", language);
				props.setProperty("laf", lAndF);
				props.setProperty("theme", theme);
				props.setProperty("country", country);
				props.store(out, "");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	/**
	 * When "save" button in addtaskview has been clicked. A new task is created and saved to XML file.	
	 * @param adv
	 * @author Da Zhang
	 */
		
		
		public void saveTaskClicked(AddTaskView adv) {
			/*
			String temp = adv.jtaskname.getText() + "\n" + adv.jtaskcat.getText() + "\n" + adv.jtaskpri.getSelectedItem().toString() + "\n" + 
					((JTextField)adv.dcstart.getDateEditor().getUiComponent()).getText() + "\n" + ((JTextField)adv.dcend.getDateEditor().getUiComponent()).getText() + 
					"\n" + adv.jhour.getText() + " : " + adv.jmin.getText() + "\n" + adv.jendhour.getText() + " : " + adv.jendmin.getText();
			System.out.println(temp);
			*/
			String taskName = adv.jtaskname.getText();
			String taskCategory = adv.jtaskcat.getText();
			DateTime createdDt = DateTime.now();
			DateTime updatedDt = DateTime.now();
			Boolean isDone = false;
			Boolean isDeleted = false;
			String priority = adv.jtaskpri.getSelectedItem().toString();
			Integer progress = 0;
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String sstartDt = ((JTextField)adv.dcstart.getDateEditor().getUiComponent()).getText() + " " + adv.jhour.getText() + ":" + adv.jmin.getText() + ":00";
			DateTime startDt = formatter.parseDateTime(sstartDt);
			String sendDt = ((JTextField)adv.dcend.getDateEditor().getUiComponent()).getText() + " " + adv.jendhour.getText() + ":" + adv.jendmin.getText() + ":00";
			DateTime endDt = formatter.parseDateTime(sendDt);
			
			TaskList.getInstance().addTask(taskName, taskCategory, createdDt, updatedDt, isDone, isDeleted, priority, null, startDt, endDt, progress);
			
			adv.popUp.setVisible(false);
			this.ReloadGui();
		}
		
		/**
		 * Set and update look and feel
		 * @param landf
		 * @param theme
		 * @author Da Zhang
		 */
		
		public void initLookAndFeel(String landf, String theme) {
			String lookAndFeel;
			
			if (theme == null) {
				this.theme = "Default";
			}
			
			this.lAndF = landf;
			this.theme = theme;
			this.SaveConfig();

			if (landf != null && !landf.equals("Default")) {
				if (landf.equals("Metal")) {
					lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
				}
				else if (landf.equals("System")) {
					lookAndFeel = UIManager.getSystemLookAndFeelClassName();
				}
				else if (landf.equals("Motif")) {
					lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
				}
				else if (landf.equals("GTK")) {
					lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
				}
				else {
					System.err.println("Unexpected value of lookandfeel specified: " + landf);
					lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				}

				try {
					UIManager.setLookAndFeel(lookAndFeel);

					// If L&F = "Metal", set the theme
					
					if (landf.equals("Metal")) {
						if (theme.equals("DefaultMetal")) {
							MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
						} else {
							MetalLookAndFeel.setCurrentTheme(new OceanTheme());
						}
						UIManager.setLookAndFeel(new MetalLookAndFeel());
					}
				}
				catch (ClassNotFoundException e) {
					System.err
							.println("Couldn't find class for specified look and feel:"
									+ lookAndFeel);
					System.err
							.println("Did you include the L&F library in the class path?");
					System.err.println("Using the default look and feel.");
				}
				catch (UnsupportedLookAndFeelException e) {
					System.err.println("Can't use the specified look and feel ("
							+ lookAndFeel + ") on this platform.");
					System.err.println("Using the default look and feel.");
				}
				catch (Exception e) {
					System.err.println("Couldn't get specified look and feel ("
							+ lookAndFeel + "), for some reason.");
					System.err.println("Using the default look and feel.");
					e.printStackTrace();
				}
			}

		}
}
