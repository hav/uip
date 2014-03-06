package userinterface;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

//import com.sun.javadoc.Type;

import controller.MainController;
/**
 * MenuAction is an abstractAction class that handles all the actions for the menu.
 * @author Jesper Andersson
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
       
    }
    
	
}