package storage;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Task Model, Task management including set and get
 * @author Da Zhang
 *
 */
public class Task {

	private static final String DEFAULT_UNNAMED_TASK_NAME = "DEFAULT";
	protected DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
	
	protected Integer taskId;
	protected String taskName;
	protected String taskCategory;
	protected String priority;
	protected DateTime taskCreated;
	protected DateTime taskUpdated;
	protected Boolean isDone;
	protected Boolean isDeleted;
	protected String taskType;
	protected DateTime startTime;
	protected DateTime endTime;
	protected Integer progress;
	protected XmlManager xm;

	/*
	 * Init without taskID
	 */
	public Task (String taskName, String taskCategory,
			DateTime createdDt, DateTime updatedDt, boolean isDone,
			boolean isDeleted, String priority, String taskType, DateTime startDt, DateTime endDt,Integer progress) {
		
		xm = XmlManager.getInstance();
		
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.priority = priority;
		this.taskCreated = createdDt;
		this.taskUpdated = updatedDt;
		this.isDone = isDone;
		this.isDeleted = isDeleted;
		this.taskType = taskType;
		this.startTime = startDt;
		this.endTime = endDt;
		this.progress = progress;
		
		this.taskId = xm.newtask(this);
		
	}
	
	/*
	 * Init for reading xml
	 */
	public Task(int taskId, String taskName, String taskCategory,
			DateTime createdDt, DateTime updatedDt, boolean isDone,
			boolean isDeleted, String priority, String taskType, DateTime startDt, DateTime endDt, Integer progress) {
		
		xm = XmlManager.getInstance();
		
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskCategory = taskCategory;
		this.priority = priority;
		this.taskCreated = createdDt;
		this.taskUpdated = updatedDt;
		this.isDone = isDone;
		this.isDeleted = isDeleted;
		this.taskType = taskType;
		this.startTime = startDt;
		this.endTime = endDt;
		this.progress = progress;
	
	}
	
	/*
	 * Getters
	 */
	public int getTaskId() {
		return taskId;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public String getTaskCategory() {
		return taskCategory;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public DateTime getTaskCreated() {
		return taskCreated;
	}
	
	public DateTime getTaskUpdated() {
		return this.taskUpdated;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public String getTaskType() {
		return taskType;
	}
	
	public DateTime getStartTime() {
		return this.startTime;
	}
	
	public DateTime getEndTime() {
		return this.endTime;
	}
	

	public int getProgress() {
		return this.progress;
	}

	public Object[] getTaskAsArray(){
		Object[] temp = {taskId, taskName, taskCategory, priority, isDone, startTime, endTime};
		return temp;

	}
	
	/*
	 * Setters
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public void setTaskName(String taskName) {
		if (isTaskNameNullOrEmpty(taskName)) {
			this.taskName = DEFAULT_UNNAMED_TASK_NAME;
		} else {
			this.taskName = taskName;
		}
		
		xm.editTask(this.taskId, "taskname", this.taskName);
	}
	
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
		
		xm.editTask(this.taskId, "taskcategory", this.taskCategory);
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
		
		xm.editTask(this.taskId, "priority", this.priority);
	}
	
	public void setTaskCreated(DateTime taskCreated) {
		this.taskCreated = taskCreated;

		xm.editTask(this.taskId, "taskcreated", this.taskCreated.toString(this.formatter));
	}
	
	public void setTaskUpdated(DateTime taskUpdated) {
		this.taskUpdated = taskUpdated;

		xm.editTask(this.taskId, "taskupdated", this.taskUpdated.toString(this.formatter));
	}
	
	public void setDone(boolean isDone) {
		this.isDone = isDone;
		
		xm.editTask(this.taskId, "isdone", this.isDone.toString());
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
		xm.editTask(this.taskId, "isdeleted", this.isDeleted.toString());
	}
	
	public void setTaskType(String taskType2) {
		this.taskType = taskType2;
		
		xm.editTask(this.taskId, "tasktype", this.taskType);
	}
	
	public void setStartTime(DateTime startDt) {
		this.startTime = startDt;
		
		xm.editTask(this.taskId, "starttime", this.startTime.toString(this.formatter));
	}
	
	public void setEndTime(DateTime endDt) {
		this.endTime = endDt;
		
		xm.editTask(this.taskId, "endtime", this.endTime.toString(this.formatter));
	}	
	
	public void setProgress(Integer progress) {
		this.progress = progress;
		
		xm.editTask(this.taskId, "progress", Integer.toString(this.progress));
	}
	
	/*
	 * Utilities
	 */
	
	protected boolean isTaskNameNullOrEmpty(String taskName) {
		return taskName == null || taskName.isEmpty();
	}
	
	/*
	 * To String
	 */
	public String toString() {
		String taskToString = "";
		if (taskId != null) {
			taskToString += "taskId=" + taskId + "\n";
		}
		if (taskName != null) {
			taskToString += "taskName=" + taskName + "\n";
		}
		if (taskCategory != null) {
			taskToString += "taskCategory=" + taskCategory + "\n";
		}
		if (taskCreated != null) {
			taskToString += "taskCreated=" + taskCreated.toString() + "\n";
		}
		if (taskUpdated != null) {
			taskToString += "taskUpdated=" + taskUpdated.toString() + "\n";
		}
		if (priority != null) {
			taskToString += "priotiy=" + priority.toString() + "\n";
		}
		if (isDone != null) {
			taskToString += "isDone=" + isDone.toString() + "\n";
		}
		if (isDeleted != null) {
			taskToString += "isDeleted=" + isDeleted.toString() + "\n";
		}
		if (taskType != null) {
			taskToString += "taskType=" + taskType + "\n";
		}
		if (startTime != null) {
			taskToString += "startTime=" + startTime.toString() + "\n";
		}
		if (endTime != null) {
			taskToString += "endTime=" + endTime.toString() + "\n";
		}
		if (progress != null) {
			taskToString += "progress=" + progress.toString() + "\n";
		}
		return taskToString;
	}
}
