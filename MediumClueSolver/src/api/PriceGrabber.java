package api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.tbot.bot.TBot;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.wrappers.GroundItem;
import org.tbot.wrappers.Item;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public enum PriceGrabber {

    METHODS;

    private final HashMap<Integer, Integer> itemPrices = new HashMap<>();

    private PriceGrabber() {
	grabAllPricesAndRemoveItemsWithPriceZero();
    }

    private void grabAllPricesAndRemoveItemsWithPriceZero() {
	LogHandler.log("Loading prices");
	try {
	    String sURL = "https://rsbuddy.com/exchange/summary.json";
	    URL url = new URL(sURL);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();
	    JsonParser jp = new JsonParser();
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
	    if (root != null && !root.toString().isEmpty() && !"null".equals(root.toString())) {
		JsonObject rootobj = root.getAsJsonObject();
		Set<Entry<String, JsonElement>> listItems = rootobj.entrySet();
		for (Entry<String, JsonElement> entry : listItems) {
		    this.itemPrices.put(Integer.parseInt(entry.getKey()),
			    entry.getValue().getAsJsonObject().get("overall_average").getAsInt());
		}
	    }
	} catch (MalformedURLException ex) {
	    JOptionPane.showMessageDialog(null, "Error during grabbing prices. Inform developer" + ex.getMessage());
	    TBot.getBot().getScriptHandler().stopScript();
	} catch (IOException | JsonIOException | JsonSyntaxException | NumberFormatException ex) {
	    JOptionPane.showMessageDialog(null, "Error during grabbing prices. Inform developer" + ex.getMessage());
	    TBot.getBot().getScriptHandler().stopScript();
	}
	LogHandler.log("Finished getting prices");
	// todo remove item which have 0 as price
    }

    public void initialisation() {
    }

    public boolean newPrices() {
	this.itemPrices.clear();
	grabAllPricesAndRemoveItemsWithPriceZero();
	return this.itemPrices.size() > 0;
    }

    public int price(GroundItem groundItem) {
	return price(groundItem.getID());
    }

    public int price(int itemId) {
	if (this.itemPrices.containsKey(itemId)) {
	    return this.itemPrices.get(itemId);
	}
	return 0;
    }

    public int price(int[] itemIds) {
	int total = 0;
	for (int i = 0; i < itemIds.length; i++) {
	    total += price(itemIds[i]);
	}
	return total;
    }

    public int price(Item item) {
	return price(item.getID());
    }
}
