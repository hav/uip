package userinterface;

import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This class is the MainController menu that contains the items that you can see in the
 * menu.
 * 
 * @author Jesper Andersson
 * 
 */
public class MainMenu extends JMenuBar {
	JPanel mainPane;
	JMenuBar menubar;
	JMenu menuFile;
	JMenu menuEdit;
	JMenu menuSettings;
	JMenu settingsChangeLanguage;
	JMenu settingsLookandFeel;
	JMenuItem languageEN;
	JMenuItem languageSV;
	JMenuItem languageCN;

	JMenuItem lfMetal;
	JMenuItem lfSystem;


	JMenu menuHelp;
	JMenuItem helpDescription;
	

	/**
	 * The constructor for MainMenu
	 * 
	 * @author Jesper Andersson
	 * @param messages
	 *            - The resourceBundle that contains our supported languages.
	 */
	public MainMenu(ResourceBundle messages) {

		// Menu initialization.
		menuFile = new JMenu(messages.getString("File"));
		menuEdit = new JMenu(messages.getString("Edit"));
		menuSettings = new JMenu(messages.getString("Window"));



		menuHelp = new JMenu(messages.getString("Help"));
		

		settingsChangeLanguage = new JMenu(messages.getString("ChangeLang"));
		ImageIcon changeLangIcon = new ImageIcon("n");
		
		settingsLookandFeel = new JMenu(messages.getString("ChangeLookandfeel"));
		ImageIcon changeLookandfeelIcon = new ImageIcon("n");

		MenuAction LangEnAct = new MenuAction(messages.getString("ChangeToEn"),
				changeLangIcon, "Change Language to English.", 1);
		languageEN = new JMenuItem(LangEnAct);

		MenuAction LangSvAct = new MenuAction(messages.getString("ChangeToSv"),
				changeLangIcon, "Change Language to Swedish.", 2);
		languageSV = new JMenuItem(LangSvAct);

		MenuAction LangZhAct = new MenuAction(messages.getString("ChangeToZh"),
				changeLangIcon, "Change Language to Chinese.", 3);
		languageCN = new JMenuItem(LangZhAct);
		

		ImageIcon changeLFIcon = new ImageIcon("lf");
		MenuAction LFMetalAct = new MenuAction(messages.getString("ChangeToMetal"),changeLFIcon,"Change Look and Feel to Metal.",9);
		lfMetal = new JMenuItem(LFMetalAct);
		
		MenuAction LFSystemAct = new MenuAction(messages.getString("ChangeToSystem"),changeLFIcon,"Change Look and Feel to System.",10);
		lfSystem = new JMenuItem(LFSystemAct);
		

		MenuAction descAct = new MenuAction(messages.getString("Description"),changeLangIcon,"Description",4);
		helpDescription = new JMenuItem(descAct);
		
		
		this.add(menuFile);
		this.add(menuEdit);
		this.add(menuSettings);
		this.add(menuHelp);
		menuHelp.add(helpDescription);
		menuSettings.add(settingsChangeLanguage);
		settingsChangeLanguage.add(languageEN);
		settingsChangeLanguage.add(languageSV);
		settingsChangeLanguage.add(languageCN);
		menuSettings.add(settingsLookandFeel);
		settingsLookandFeel.add(menuSettings.add(lfMetal));
		settingsLookandFeel.add(menuSettings.add(lfSystem));

	}

}
