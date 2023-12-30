import client.MovieClient;
import client.UaKinoBot;
import modal.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class serves as the entry point for the UaKinoBot application.
 * It demonstrates how to use the UaKinoBot to download movies or series from UaKino website.
 */
public class Main {
    public static void main(String[] args) {
        // Create a list of film URLs to process

        List<Movie> dcMovie = new ArrayList<>();
        dcMovie.add(new Movie("3 Гаррі Поттер і в'язень Азкабану", "https://uakino.club/filmy/genre_adventure/213-garr-potter-vyazen-azkabanu.html"));

        // Create a UaKinoBot instance with the list of film URLs
        MovieClient bot = new UaKinoBot(dcMovie);

        // Start the program, download videos, and measure execution time
        bot.startProgramWithDownloadAndTimer("Your dir for save files");
    }
}

