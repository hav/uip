package userinterface;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.table.*;

import storage.TaskList;


public class CalendarView extends JPanel implements ActionListener {
    Calendar calDate = GregorianCalendar.getInstance(); //the current date displayed on the calendar
    Calendar currentDate = GregorianCalendar.getInstance();

    JPanel panelTable = new JPanel();
    DefaultTableModel tableModel = new DefaultTableModel();
    int firstDay;
    HashMap<Integer, Integer> monthTasks = new HashMap<Integer, Integer>();


    Container pane = this;
    String months[] = {"January", "February","March", "April","May","June","July","August","September","October","November","December"};
    JLabel labelMonth;


    public CalendarView(ResourceBundle messages) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //user messages bundle to change the language
        months[0] = messages.getString("january");
        months[1] = messages.getString("february");
        months[2] = messages.getString("march");
        months[3] = messages.getString("april");
        months[4] = messages.getString("may");
        months[5] = messages.getString("june");
        months[6] = messages.getString("july");
        months[7] = messages.getString("august");
        months[8] = messages.getString("september");
        months[9] = messages.getString("october");
        months[10] = messages.getString("november");
        months[11] = messages.getString("december");



        labelMonth = new JLabel(months[calDate.get(Calendar.MONTH)]  + " " + calDate.get(Calendar.YEAR));

        //set starting calendar month
        calDate.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 1); //sets calDate to the first day of the current month
        //Declare Frame
        Container pane = this;

        //Declare Panels
        JPanel panelLblCurrentDate = new JPanel();
        JPanel panelLblMonth = new JPanel();

        //Declare Labels
        JLabel labelCurrentDate = new JLabel(String.valueOf(currentDate.get(Calendar.DATE)) + ", " + months[currentDate.get(Calendar.MONTH)] + ", " + String.valueOf(currentDate.get(Calendar.YEAR)));

        //Current date label and panel
        panelLblCurrentDate.setBorder(BorderFactory.createLineBorder(Color.black));
        panelLblCurrentDate.add(labelCurrentDate);
        pane.add(panelLblCurrentDate);

        //Backwards icon/button to change current month displayed
        java.net.URL backArrowURL = getClass().getResource("/resources/backArrow.gif");
        if (backArrowURL != null) {
            JButton buttonBackArrow = new JButton(new ImageIcon(backArrowURL));
            panelLblMonth.add(buttonBackArrow);
            buttonBackArrow.setBorder(BorderFactory.createEmptyBorder());
            buttonBackArrow.setContentAreaFilled(false);

            buttonBackArrow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int tempMonth = calDate.get(Calendar.MONTH);
                    if(tempMonth == 0){
                        calDate.set(Calendar.MONTH, 11);
                        calDate.set(Calendar.YEAR, calDate.get(Calendar.YEAR) - 1);
                    }
                    else{
                        calDate.set(Calendar.MONTH,( tempMonth - 1));
                    }
                    tableUpdate();
                    labelMonth.setText(months[calDate.get(Calendar.MONTH)] + " " + calDate.get(Calendar.YEAR));	//gets the month, must use get method to get correct value
                }
            });
        }


        //Month label and panel
        panelLblMonth.setBorder(BorderFactory.createLineBorder(Color.black));
        panelLblMonth.add(labelMonth);
        pane.add(panelLblMonth);

        //Forwards icon/button to change current month displayed
        java.net.URL forwardArrowURL = getClass().getResource("/resources/forwardArrow.gif");
        if (forwardArrowURL != null) {
            JButton buttonForwardArrow = new JButton(new ImageIcon(forwardArrowURL));
            panelLblMonth.add(buttonForwardArrow);
            buttonForwardArrow.setBorder(BorderFactory.createEmptyBorder());
            buttonForwardArrow.setContentAreaFilled(false);

            buttonForwardArrow.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    int tempMonth = calDate.get(Calendar.MONTH);
                    if(tempMonth == 11){
                        calDate.set(Calendar.MONTH, 0);
                        calDate.set(Calendar.YEAR, calDate.get(Calendar.YEAR) + 1);
                    }
                    else{
                        calDate.set(Calendar.MONTH, tempMonth + 1);
                    }
                    tableUpdate();
                    labelMonth.setText(months[calDate.get(Calendar.MONTH)] + " " + calDate.get(Calendar.YEAR));	//gets the month, must use get method to get correct value
                }
            });
        }

        tableSetup(messages);


    }

    /**
     * Initial setup of the table for the current month
     */
    public void tableSetup(ResourceBundle messages){
        //Table set up

        JTable calTable = new JTable(tableModel);
        String[] columnNames = {messages.getString("sun"), messages.getString("mon"), messages.getString("tue"), messages.getString("wed"), messages.getString("thu"),messages.getString("fri"),messages.getString("sat")};


        for(int i = 0; i < 7; i++){
            tableModel.addColumn(columnNames[i]);
        }
        tableModel.setRowCount(6);
        calTable.setRowHeight(450/tableModel.getRowCount());
        JTableHeader tableHeader = calTable.getTableHeader(); //display days of the week
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);

        firstDay = calDate.get(Calendar.DAY_OF_WEEK);


        int dateSet = 1;
        int j =firstDay - 1; //puts the first day of the month on the proper day of the week
        for(int i = 0; i < 6; i++){
            for(; j < 7; j++){
                if(dateSet <= calDate.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    tableModel.setValueAt(dateSet, i, j);
                    dateSet++;
                }
            }
            j=0;
        }
        calTable.setEnabled(false);
        panelTable.setBorder(BorderFactory.createLineBorder(Color.black));
        panelTable.add(tableHeader);
        panelTable.add(calTable);
        pane.add(panelTable);
        tableUpdate();
    }

    /**
     * Changes the days and tasks on the calendar to match current month and year
     */
    public void tableUpdate(){
        tableModel.setRowCount(0);	//clear the table
        tableModel.setRowCount(6); //add the rows back in

        firstDay = calDate.get(Calendar.DAY_OF_WEEK);
        getTasks();
        int dateSet = 1;
        int j = firstDay - 1; //puts the first day of the month on the proper day of the week
        for(int i = 0; i < 6; i++){
            for(; j < 7; j++){
                if(dateSet <= calDate.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    if(monthTasks.containsKey(dateSet)){
                        tableModel.setValueAt(dateSet + "   " + monthTasks.get(dateSet) + " Tasks", i, j);

                    }
                    else{
                        tableModel.setValueAt(dateSet, i, j);
                    }
                    dateSet++;
                }
            }
            j=0;
        }
        tableModel.fireTableDataChanged();
    }

    /**
     * Retrieves the lists of tasks
     * Adds tasks from the current month and year in a map
     */
    public void getTasks(){
        TaskList taskList = TaskList.getInstance();

        monthTasks.clear();
        if(taskList.getListSize() > 0){
            for(int i = 0; i < taskList.getListSize(); i++){
                if((taskList.getTaskByIndex(i).getStartTime() != null)){
                    if(taskList.getTaskByIndex(i).getStartTime().getMonthOfYear() == calDate.get(Calendar.MONTH) + 1){	//compares the month of the task to the current month of the calendar
                        //check year																					//Joda Time months start at 1 but Calendar months start at 0, thus the + 1
                        if(taskList.getTaskByIndex(i).getStartTime().getYear() == calDate.get(Calendar.YEAR)){
                            int tempNum = 0;
                            if(monthTasks.containsKey(taskList.getTaskByIndex(i).getStartTime().getDayOfMonth())){
                                tempNum = monthTasks.get(taskList.getTaskByIndex(i).getStartTime().getDayOfMonth()) + 1;
                            }
                            else{
                                tempNum = 1;
                            }
                            monthTasks.put(taskList.getTaskByIndex(i).getStartTime().getDayOfMonth(), tempNum);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
