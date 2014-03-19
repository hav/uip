package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.*;

import org.joda.time.DateTime;

import storage.Task;
import storage.TaskList;
import controller.MainController;

/**
 * A view that manages everything related to creating and listing Tasks in the
 * sidebar.
 */
public class TaskListView extends JPanel implements ActionListener {
    /**
     * don't know why this row is needed.
     */
    private static final long serialVersionUID = 1L;

    private JPanel taskListView;
    private JPanel taskMenu;
    private JList list;
    private MainController controller;

    ResourceBundle messages;

    public TaskListView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Internationlization internationlization = new Internationlization();
        messages = internationlization.getInternationlizationBundle();

        /**
         * Create and add a TaskList to the pane.
         */
        taskMenu = this.setupTaskList();
        this.add(taskMenu);

        /**
         * Configuring and creating taskListView. Configuring and creating a
         * scrolling panel that will house the list of tasks.
         */

        //final ArrayList<Task> tasks = this.createTasks();//this.getTasks();
        final TaskList tasks = TaskList.getInstance();
        ListModel<String> tasksList = new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return tasks.getListSize();
            }

            @Override
            public String getElementAt(int index) {
                return tasks.getTaskByIndex(index).getTaskName();
            }
        };

        list = new JList();
        list.setModel(tasksList);
        list.setComponentPopupMenu(new PopupMenu());

        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    int listIndex = index+1;
                    System.out.println("Double clicking item " + listIndex + " with name: " + tasks.getTaskByIndex(index).getTaskName());
                    DetailTaskView dt = new DetailTaskView(tasks.getTaskByIndex(index));
                    // TODO Implement detail window here
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    list.setSelectedIndex(list.locationToIndex(e.getPoint()));
                    System.out.println("Right clicking!");
                }
            }
        };
        list.addMouseListener(listener);


        JScrollPane scrollPane = new JScrollPane(list,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
    }

    /**
     * Generates a panel with buttons for creating and scrollbox for sorting
     * tasks
     *
     * @return A panel with buttons, labe	ls and scrollboxes.
     */
    private JPanel setupTaskList() {
        JPanel tempPanel = new JPanel(new GridBagLayout());
        //tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempPanel.setMaximumSize(new Dimension(250, 250));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        /**
         * Create and set constraints for the "New task" button. Populate the
         * task window with the current taskListView. Add the button to the
         * pane.
         */
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.ipady = 20; // make this component tall
        c.weightx = 1;
        JButton newTask = new JButton(messages.getString("newtask"));
        newTask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddTaskView et = new AddTaskView();
            }
        });

        tempPanel.add(newTask, c);

        /**
         * Create and set the constraints for the "Sort by" label. Add the label
         * to the pane.
         */
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        JLabel sortLabel = new JLabel(messages.getString("sortby"),
                JLabel.CENTER);
        tempPanel.add(sortLabel, c);

        /**
         * Create the sort values drop-down menu and set the constraints for it.
         * Add the label to the pane..
         */
        c.gridx = 2;
        c.gridy = 1;
        String[] sortValues = { messages.getString("starred"),
                messages.getString("priority"), messages.getString("color") };
        tempPanel.add(new JComboBox(sortValues), c);

        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;

        JLabel tasksLabel = new JLabel(messages.getString("tasks"));
        Font font;
        font = new Font(tasksLabel.getFont().getFontName(), Font.BOLD,
                tasksLabel.getFont().getSize());
        tasksLabel.setFont(font);
        tempPanel.add(tasksLabel, c);

        return tempPanel;
    }

    private TaskList createTasks() {
        ArrayList<Task> test = new ArrayList<Task>();
        TaskList taskList = TaskList.getInstance();

        for (int i = 0; i < 20; i++) {
            /*
            Task temp = new Task("TestTask: " + i, // taskName
                    "Test Cat", // Task Category
                    DateTime.now(), // created at
                    DateTime.now(), // updated at
                    false, // isdone
                    false, // isdeleted
                    3, // priority
                    "Test Type", // tasktype
                    DateTime.now(), // startdate
                    DateTime.now()
            ); // enddate
            //test.add(temp);
            */
            taskList.addTask("TestTask: " + i, // taskName
                    "Test Cat", // Task Category
                    DateTime.now(), // created at
                    DateTime.now(), // updated at
                    false, // isdone
                    false, // isdeleted
                    Integer.toString(3), // priority
                    "Test Type", // tasktype
                    DateTime.now(), // startdate
                    DateTime.now(), //enddate
                    0 // progress
            );
        }


        return taskList;
    }

    private ArrayList<Task> getTasks() {
        TaskList taskList = TaskList.getInstance();

        ArrayList<Task> tempArray = new ArrayList<Task>();

        for (Task tempTask : taskList.getTaskList()) {
            //tempArray.add(tempTask.getTaskName());
            tempArray.add(tempTask);
        }

        return tempArray;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    public void saveData (AddTaskView addTask){
        TaskList t1 = new TaskList();
        //AddTaskView task = new AddTaskView();
        //String tn = AddTaskView.text1.getText();
        t1.addTask("tname", "category", new DateTime(), new DateTime(), false, false, Integer.toString(0), "type",new DateTime(),new DateTime(),0);
    }
}