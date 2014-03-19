package storage;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

/**
 * All task could be managed by this tasklist model incliuding adding new task and read tasks
 * @author Da Zhang
 *
 */
public class TaskList {

	private List<Task> taskList = new ArrayList<Task>();
	private static TaskList instance = null;

	/**
	 * Keep one instance globally
	 * @return TaskList instance	
	 */
	public static synchronized TaskList getInstance() {
		if (instance == null) {
			try {
				instance = new TaskList();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public TaskList() {
		this.taskList = XmlManager.getInstance().readTasklist();
	}
	
	public Task getTaskByIndex(int index) {
		return taskList.get(index);
	}
	
	public int getListSize() {
		return taskList.size();
	}

	public void addTask(String taskName, String taskCategory,
			DateTime createdDt, DateTime updatedDt,
			boolean isDone, boolean isDeleted, String priority, String taskType, DateTime startDt, DateTime endDt, Integer progress) {
		Task newTask = new Task(taskName, taskCategory,
				createdDt, updatedDt, isDone, isDeleted, priority, taskType, startDt, endDt, progress);
		taskList.add(newTask);
	}

	public void removeTask(Integer taskId) {
		for (int i = 0; i < taskList.size(); i++) {
			Task newTask;
			newTask = taskList.get(i);
			
			if(newTask.getTaskId() == taskId){
				newTask.setDeleted(true);
			}
		}
	}
	
	public List<Task> getTaskList() {
		return taskList;
	}
	
	public Object[][] getTaskListAsObjectArray(){
		
		Object[][] arrayen = new Object[taskList.size()][7];
		//Object[][] arrayen = new Object[][](null);
		for(int i=0; i<taskList.size(); i++){
			arrayen[i] = taskList.get(i).getTaskAsArray();
		}
		
		return arrayen;
	}
}
