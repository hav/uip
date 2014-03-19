package storage;

import org.dom4j.*;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manange XML file as datebase. Connect DB with datamodel
 * @author Da Zhang
 *
 */

public class XmlManager {

	private static final String XML_FILE_PATH = System.getProperty("user.home")+System.getProperty("file.separator")+"tmdb.xml";
	//private static final String XML_FILE_PATH = "db/tmdb.xml";
	private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
	
	protected Document doc;
	protected Element root;
	protected int taskcount;
	private static XmlManager instance = null;

	/**
	 * Keep one instance globally
	 * @return XmlManage instance
	 */
	public static synchronized XmlManager getInstance() {
		if (instance == null) {
			try {
				instance = new XmlManager();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return instance;
	}

	/**
	 * Initialization
	 */
	public XmlManager() throws Exception {
		File dbfile = new File(XML_FILE_PATH);
		if (!dbfile.exists()) {
			doc = DocumentHelper.createDocument();
			Element root = doc.addElement("tasks");
			this.write(doc);
			taskcount = 0;
		} else {
			SAXReader reader = new SAXReader();
			doc = reader.read(XML_FILE_PATH);
			List list = doc.selectNodes("/tasks/task");
			taskcount = list.size();
		}
		root = doc.getRootElement();
	}

	/**
	 * Create and write a task element in XML file 
	 * @param t
	 * @return taskid
	 */
	public int newtask(Task t) {
		int id = taskcount + 1;
		Element task = root.addElement("task");
		task.addAttribute("taskid", Integer.toString(id));

		Element taskname = task.addElement("taskname");
		if (t.taskName != null) {
			taskname.setText(t.taskName);
		} else {
			taskname.setText("null");
		}

		Element taskcategory = task.addElement("taskcategory");
		if (t.taskCategory != null) {
			taskcategory.setText(t.taskCategory);
		} else {
			taskcategory.setText("null");
		}

		Element priority = task.addElement("priority");
		if (t.priority != null) {
			priority.setText(t.priority);
		} else {
			priority.setText("null");
		}

		Element taskcreated = task.addElement("taskcreated");
		if (t.taskCreated != null) {
			
			taskcreated.setText(t.taskCreated.toString(this.formatter));
		} else {
			taskcreated.setText("null");
		}

		Element taskupdated = task.addElement("taskupdated");
		if (t.taskUpdated != null) {
			taskupdated.setText(t.taskUpdated.toString(this.formatter));
		} else {
			taskupdated.setText("null");
		}

		Element isdone = task.addElement("isdone");
		if (t.isDone != null) {
			isdone.setText(t.isDone.toString());
		} else {
			isdone.setText("null");
		}

		Element isdeleted = task.addElement("isdeleted");
		if (t.isDeleted != null) {
			isdeleted.setText(t.isDeleted.toString());
		} else {
			isdeleted.setText("null");
		}

		Element tasktype = task.addElement("tasktype");
		if (t.taskType != null) {
			tasktype.setText(t.taskType);
		} else {
			tasktype.setText("null");
		}
		
		Element startTime = task.addElement("starttime");
		if (t.startTime != null) {
			startTime.setText(t.startTime.toString(this.formatter));
		} else {
			startTime.setText("null");
		}
		
		Element endTime = task.addElement("endtime");
		if (t.endTime != null) {
			endTime.setText(t.endTime.toString(this.formatter));
		} else {
			endTime.setText("null");
		}
		
		Element progress = task.addElement("progress");
		if (t.progress != null) {
			progress.setText(Integer.toString(t.progress));
		} else {
			progress.setText("null");
		}

		this.write(doc);

		taskcount = taskcount + 1;
		
		return id;
	}
	
	/**
	 * Read task from XML file according to taskid
	 * @param taskid
	 * @return task
	 */
	public Task readTask(int taskid) {
		
		String xtaskName = null;
		String xtaskCategory = null;
		String xpriority = null;
		DateTime xtaskCreated = null;
		DateTime xtaskUpdated = null;
		Boolean xisDone = null;
		Boolean xisDeleted = null;
		String xtaskType = null;
		DateTime xstartTime = null;
		DateTime xendTime = null;
		Integer xprogress = null;
		
		
		String xpath = "/tasks/task[@taskid=" + Integer.toString(taskid) + "]";
		Element task = (Element) doc.selectSingleNode(xpath);
		
		int nodeindex = 0;
		for (Iterator i = task.elementIterator(); i.hasNext();) {
			Element childelement = (Element) i.next();
			nodeindex = nodeindex + 1;
			
			if (nodeindex == 1) {
				if (childelement.getText().equals("null")) {
					xtaskName = null;
				} else {
					xtaskName = childelement.getText();
				}
			} else if (nodeindex == 2) {
				if (childelement.getText().equals("null")) {
					xtaskCategory = null;
				} else {
					xtaskCategory = childelement.getText();
				}
			} else if (nodeindex == 3) {
				if (childelement.getText().equals("null")) {
					xpriority = null;
				} else {
					xpriority = childelement.getText();
				}
			} else if (nodeindex == 4) {
				if (childelement.getText().equals("null")) {
					xtaskCreated = null;
				} else {
					xtaskCreated = this.formatter.parseDateTime(childelement.getText());
				}
			} else if (nodeindex == 5) {
				if (childelement.getText().equals("null")) {
					xtaskUpdated = null;
				} else {
					xtaskUpdated = this.formatter.parseDateTime(childelement.getText());
				}
			} else if (nodeindex == 6) {
				if (childelement.getText().equals("null")) {
					xisDone = null;
				} else {
					xisDone = Boolean.valueOf(childelement.getText());
				}
			} else if (nodeindex == 7) {
				if (childelement.getText().equals("null")) {
					xisDeleted = null;
				} else {
					xisDeleted = Boolean.valueOf(childelement.getText());
				}
			} else if (nodeindex == 8) {
				if (childelement.getText().equals("null")) {
					xtaskType = null;
				} else {
					xtaskType = childelement.getText();
				}
			} else if (nodeindex == 9) {
				if (childelement.getText().equals("null")) {
					xstartTime = null;
				} else {
					xstartTime = this.formatter.parseDateTime(childelement.getText());
				}
			} else if (nodeindex == 10) {
				if (childelement.getText().equals("null")) {
					xendTime = null;
				} else {
					xendTime = this.formatter.parseDateTime(childelement.getText());
				}
			} else if (nodeindex == 11) {
				if (childelement.getText().equals("null")) {
					xprogress= null;
				} else {
					xprogress = Integer.parseInt(childelement.getText());
				}
			}
		}
		
		Task t = new Task(taskid, xtaskName, xtaskCategory, xtaskCreated, xtaskUpdated, xisDone, xisDeleted, xpriority, xtaskType, xstartTime, xendTime, xprogress);
		return t;
	}
	
	/**
	 * Read all tasks and return a list of tasks
	 * @return TaskList
	 */
	public List<Task> readTasklist () { 
		List<Task> taskList = new ArrayList <Task>();
		
		// iterate through child elements of root
        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element task = (Element) i.next();
            int taskid = Integer.parseInt(task.attributeValue("taskid"));
            System.out.println(taskid);
            taskList.add(this.readTask(taskid));
        }
        
        return taskList;
	}
	
	/**
	 * Edit one property of task according to taskid with property name and value
	 * @param taskid
	 * @param name
	 * @param value
	 * @return true if succeed
	 */
	public Boolean editTask (int taskid, String name, String value) {
		String xpath = "/tasks/task[@taskid=" + Integer.toString(taskid) + "]/"+ name +"";
		Element taskproperty = (Element) doc.selectSingleNode(xpath);
		
		taskproperty.setText(value);
		
		this.clear();
		this.write(doc);
		
		return true;
	}
	
	/*
	 * Utilies 
	 */
	public void clear() {
		try {
			FileWriter writer = new FileWriter(XML_FILE_PATH);
			writer.write("");
			writer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void write(Document document) {

		// write to a file
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = null;

		try {
			writer = new XMLWriter(new FileWriter(XML_FILE_PATH), format);
			// writer = new XMLWriter(new FileWriter("db/tmdb.xml"));
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
