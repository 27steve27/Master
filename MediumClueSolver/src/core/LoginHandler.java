package core;

import java.awt.Rectangle;

import org.tbot.bot.TBot;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Game;
import org.tbot.methods.Mouse;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.input.keyboard.Keyboard;
import org.tbot.methods.input.mouse.target.RectangleMouseTarget;

public enum LoginHandler {

    METHODS;

    private int attempts;
    private final Rectangle existingUserBox;
    private final Rectangle cancelBox;
    private final Rectangle tryAgainBox;

    private LoginHandler() {
	attempts = 0;
	existingUserBox = new Rectangle(397, 280, 529 - 397, 30);
	cancelBox = new Rectangle(396, 308, 529 - 397, 30);
	tryAgainBox = new Rectangle(318, 264, 444 - 315, 30);
    }

    private boolean isAccountDisabled() {
	return Game.getLoginMessage1().contains("disabled");
    }

    private boolean isNonMember() {
	return Game.getLoginMessage1().contains("members account");
    }

    private boolean rsUpdated() {
	return Game.getLoginMessage1().contains("updated");
    }

    private boolean clickExistingUser() {
	return Mouse.click(new RectangleMouseTarget(existingUserBox).getPoint(), true);
    }

    private boolean clickCancel() {
	return Mouse.click(new RectangleMouseTarget(cancelBox).getPoint(), true);
    }

    private void handleDisabled() {
	LogHandler.log("Disabled :(");
	TBot.getBot().getScriptHandler().stopScript();
    }

    private boolean isConnecting() {
	return Game.getLoginMessage1().contains("Connecting");
    }

    public boolean shouldLogin() {
	return !Game.isLoggedIn() && Game.getGameState() == 10 || Widgets.getWidget(378, 17).isOnScreen()
		|| Bot.isLoggedOut;
    }

    private enum WorldType {
	F2P, P2P
    }

    private void hopWorlds(WorldType worldType) {
	final int world = Game.getCurrentWorld();
	switch (worldType) {
	case F2P:
	    Game.hopRandomF2P();
	    break;

	case P2P:
	    Game.hopRandomP2P();
	    break;
	}
	Time.sleepUntil(() -> Game.getCurrentWorld() != world, 5000);
    }

    private void handleWrongPassword() {
	attempts++;
	Mouse.click(new RectangleMouseTarget(tryAgainBox).getPoint(), true);
	Time.sleep(1000);
	if (attempts >= 5) {
	    LogHandler.log("Too many attempts.");
	    TBot.getBot().getScriptHandler().stopScript();
	}
    }

    private void enterUserName() {
	if (Keyboard.hasFocus()) {
	    Keyboard.sendText(TBot.getBot().getCurrentAccount().getUsername(), true);
	} else {
	    Keyboard.requestFocus();
	}
    }

    private void enterPassword() {
	if (Keyboard.hasFocus()) {
	    Keyboard.sendText(TBot.getBot().getCurrentAccount().getPassword(), true);
	} else {
	    Keyboard.requestFocus();
	}
    }

    private boolean onSecondScreen() {
	return Game.getGameState() == 30;
    }

    private boolean clickPlay() {
	return Widgets.getWidget(378, 17).click();
    }

    public void login() {
	if (!onSecondScreen()) {
	    int index = Game.getLoginMenuIndex();
	    switch (index) {
	    case 0:
		clickExistingUser();
		break;

	    case 1:
		hopWorlds(WorldType.P2P);
		clickCancel();
		break;

	    case 2:
		if (isAccountDisabled()) {
		    handleDisabled();
		}
		if (isNonMember()) {
		    TBot.getBot().getScriptHandler().stopScript();
		}

		if (rsUpdated()) {
		    LogHandler.log("RS Update");
		    TBot.getBot().getScriptHandler().stopScript();
		}

		if (!isConnecting() && !isAccountDisabled()) {
		    clickCancel();
		    Time.sleep(250);
		    clickExistingUser();
		    Time.sleep(250);
		    enterUserName();
		    enterPassword();
		}
		break;
	    case 3:
		handleWrongPassword();
		break;
	    }

	} else {
	    attempts = 0;
	    clickPlay();
	    Time.sleep(2000);
	    Bot.doTaskAfterLogin = true;
	}

    }

    public void initialisation() {
    }
}
