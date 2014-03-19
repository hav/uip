package userinterface;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import controller.MainController;

/**
 * MenuAction is an abstractAction class that handles all the actions for the
 * menu.
 * 
 * @author Jesper Andersson & Da Zhang
 * 
 */
class MenuAction extends AbstractAction {
	MainController controller = MainController.getInstance();
    public MenuAction(String text, ImageIcon icon,
                      String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       Object type = getValue(MNEMONIC_KEY);
       if(type.equals(1)){ controller.setNewLanguage("en", "US"); controller.SaveConfig(); controller.ReloadGui();}
       else if(type.equals(2)){ controller.setNewLanguage("sv", "SE"); controller.SaveConfig(); controller.ReloadGui();}
       else if(type.equals(3)){ controller.setNewLanguage("zh", "CN"); controller.SaveConfig(); controller.ReloadGui();}
       else if(type.equals(9)){ controller.initLookAndFeel("Metal", "Default"); controller.ReloadGui();}
       else if(type.equals(10)){ controller.initLookAndFeel("System", "Default"); controller.ReloadGui();}
       else if (type.equals(4)){
    	   Description description = new Description();
    	   //description.setSize(300, 250);
    	   description.show();
       }
       else if(type.equals(1337)){new AddTaskView();}
       
    }

}