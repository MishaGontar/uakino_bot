import client.UaKinoBot;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class serves as the entry point for the UaKinoBot application.
 * It demonstrates how to use the UaKinoBot to download movies or series from UaKino website.
 */
public class Main {
    public static void main(String[] args) {
        // Create a list of film URLs to process
        List<String> urls = new ArrayList<>();
        urls.add("https://uakino.club/seriesss/drama_series/5727-zagostren-kozirki-2-sezon.html");

        // Create a UaKinoBot instance with the list of film URLs
        UaKinoBot bot = new UaKinoBot(urls);

        // Start the program, download videos, and measure execution time
        bot.startProgramWithDownloadAndTimer("your directory");
    }
}

