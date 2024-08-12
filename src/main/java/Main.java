import client.MovieClient;
import client.UaKinoBot;
import modal.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * New website - https://uaserials.vip/
 * The Main class serves as the entry point for the UaKinoBot application.
 * It demonstrates how to use the UaKinoBot to download movies or series from UaKino website.
 */
public class Main {
    public static void main(String[] args) {
        // Create a list of film URLs to process бю

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("https://uakino.me/filmy/genre-action/1042-red-2.html"));

        // Create a UaKinoBot instance with the list of film URLs
        MovieClient bot = new UaKinoBot(movies);

        // Start the program, download videos, and measure execution time
        bot.startProgramWithDownloadAndTimer("Your dir for save files");
    }
}

