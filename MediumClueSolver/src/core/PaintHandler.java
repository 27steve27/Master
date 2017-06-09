package core;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.tbot.internal.event.listeners.PaintListener;

public enum PaintHandler implements PaintListener, MouseListener {

    METHODS;

    @Override
    public void onRepaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	int[] x = new int[7];
	if (Bot.showPaint) {
	    Graphics2D gPaint = (Graphics2D) g.create();
	    gPaint.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
	    gPaint.setColor(Color.BLACK);
	    // Draw the skill buttons
	    gPaint.fillRect(6, 344, 51, 22 + x[0]);
	    gPaint.fillRect(481, 344, 31, 22);
	    gPaint.fillRect(6, 369, 507, 80);
	    gPaint.fillRect(6, 452, 507, 22);
	    Graphics2D gPaintText = (Graphics2D) g.create();
	    gPaintText.setColor(Color.WHITE);
	    // Label skill buttons
	    gPaintText.drawString("Info", 15, 359);
	    gPaintText.drawString("Hide", 484, 359);
	    // Get info
	    // Draw Information
	    gPaintText.drawString("Generic name 1.0", 16, 395);
	    gPaintText.drawString("Earned GP: " + 9999999, 16, 415);
	    gPaintText.drawString("GP/hour: " + 9999999, 16, 435);
	    gPaintText.drawString(
		    "Time running: " + Utils.getFormattedTime(System.currentTimeMillis() - Bot.startingTime), 10, 467);
	    gPaintText.drawString("Status: " + "TASK.toString()", 160, 467);
	    gPaintText.drawString("By iPot", 395, 467);
	} else {
	    Graphics2D gPaint = (Graphics2D) g.create();
	    gPaint.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
	    gPaint.setColor(Color.BLACK);
	    // Draw the "show" button
	    gPaint.fillRect(481, 344, 31, 22);
	    Graphics2D gPaintText = (Graphics2D) g.create();
	    gPaintText.setColor(Color.WHITE);
	    gPaintText.drawString("Show", 481, 359);
	}
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	if (Utils.isMouseClicked(481, 344, 31, 22) && Bot.showPaint) {
	    arg0.consume();
	    Bot.showPaint = false;
	} else if (Utils.isMouseClicked(481, 344, 31, 22)) {
	    arg0.consume();
	    Bot.showPaint = true;
	}
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void initialisation() {
    }
}
