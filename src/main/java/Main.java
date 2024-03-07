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

        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie("1 Фантастичні звірі і де їх шукати","https://uakino.club/filmy/genre_adventure/461-fantastichn-zvr-de-yih-shukati.html"));
//        movies.add(new Movie("2 Фантастичні звірі: Злочини Ґріндельвальда","https://uakino.club/filmy/genre_adventure/7890-fantastichn-zvr-zlochini-rndelvalda.html"));
        movies.add(new Movie("3 Фантастичні звірі: Таємниці Дамблдора","https://uaserials.net/films/6566-fantastichni-zviri-tayemnici-dambldora.html"));


        // Create a UaKinoBot instance with the list of film URLs
        MovieClient bot = new UaKinoBot(movies);

        // Start the program, download videos, and measure execution time
        bot.startProgramWithDownloadAndTimer("Your dir for save files");
    }
}

