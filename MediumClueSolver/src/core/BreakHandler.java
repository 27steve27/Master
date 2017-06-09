package core;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import org.tbot.bot.TBot;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Game;
import org.tbot.methods.Time;


public enum BreakHandler {

    METHODS;

    private static final ArrayList<Break> breaks = new ArrayList<>();
    private Break nextBreak = null;

    private BreakHandler() {
    }

    public boolean shouldBreak() {
	for (Break break1 : breaks) {
	    if (break1.shouldBreak(Bot.startingTime)) {
		nextBreak = break1;
		return true;
	    }
	}
	nextBreak = null;
	return false;
    }

    public void addBreak(long startAt, long stopAt) {
	breaks.add(new Break(startAt, stopAt));
    }

    public void doBreak() {
	while (nextBreak != null && nextBreak.shouldBreak(Bot.startingTime)) {
	    if (Bot.isLoggedOut == false) {
		Game.logout();
		Time.sleep(5000);
	    }
	    if (!Game.isLoggedIn()) {
		Bot.isLoggedOut = true;
	    }
	}
	Time.sleep(1000);
    }

    public void initialisation() {
    }

    public void loadBreaks() {
	MaskFormatter mask = null;
	int amountOfBreaks = 0;
	try {
	    mask = new MaskFormatter("##:##:##");
	    mask.setPlaceholderCharacter('0');
	} catch (ParseException ex) {
	    JOptionPane.showMessageDialog(null, "Error while creating breaks. Inform developer." + ex.getMessage());
	    TBot.getBot().getScriptHandler().stopScript();
	}
	JSpinner mySpinner = new JSpinner();
	mySpinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
	JFormattedTextField txt = ((JSpinner.NumberEditor) mySpinner.getEditor()).getTextField();
	((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
	Object[] breaksAmount = { "Amount of breaks", mySpinner };
	int option = JOptionPane.showConfirmDialog(null, breaksAmount, "BreakHandler", JOptionPane.DEFAULT_OPTION);
	if (option == JOptionPane.OK_OPTION) {
	    amountOfBreaks = (Integer) mySpinner.getValue();
	    for (int i = 0; i < amountOfBreaks; i++) {
		JFormattedTextField startTime = new JFormattedTextField(mask);
		JFormattedTextField endTime = new JFormattedTextField(mask);
		Object[] times = { "Start time", startTime, "End time", endTime };
		int option2 = JOptionPane.showConfirmDialog(null, times, "Enter break", JOptionPane.DEFAULT_OPTION);
		if (option2 == JOptionPane.OK_OPTION) {
		    LogHandler.log(Utils.getMilliscondsFromFormattedTime(startTime.getText()) + " - "
			    + Utils.getMilliscondsFromFormattedTime(endTime.getText()));
		    BreakHandler.METHODS.addBreak(Utils.getMilliscondsFromFormattedTime(startTime.getText()),
			    Utils.getMilliscondsFromFormattedTime(endTime.getText()));
		} else {
		    i--;
		}
	    }
	}
    }
}
