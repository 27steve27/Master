package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tbot.methods.Mouse;

public class Utils {

    private static final Pattern pattern = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");

    public static String getFormattedTime(final long timeMillis) {
	long second = (timeMillis / 1000) % 60;
	long minute = (timeMillis / (1000 * 60)) % 60;
	long hour = (timeMillis / (1000 * 60 * 60)) % 24;
	return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static long getMilliscondsFromFormattedTime(String formattedTime) {
	Matcher matcher = pattern.matcher(formattedTime);
	if (matcher.matches()) {
	    return Long.parseLong(matcher.group(1)) * 3600000L + Long.parseLong(matcher.group(2)) * 60000
		    + Long.parseLong(matcher.group(3)) * 1000;
	} else {
	    return 0;
	}
    }

    public static boolean isMouseClicked(int x1, int y1, int x2, int y2) {
	int px = Mouse.getX();
	int py = Mouse.getY();
	x2 = x1 + x2;
	y2 = y1 + y2;
	return (px > x1 && py > y1 && px < x2 && py < y2);
    }
}
