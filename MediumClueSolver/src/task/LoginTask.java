package task;

import core.Bot;
import core.LoginHandler;

public class LoginTask implements Task {

    @Override
    public void execute() {
	LoginHandler.METHODS.login();
	Bot.isLoggedOut = false;
	Bot.doTaskAfterLogin = true;
    }

    @Override
    public String toString() {
	return "Logging in";
    }

    @Override
    public boolean validate() {
	return LoginHandler.METHODS.shouldLogin();
    }

}
