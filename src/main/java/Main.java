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
        dcMovie.add(new Movie("DC-1 Людина зі сталі", "https://uakino.club/filmy/genre-action/76-lyudina-z-stal.html"));
        dcMovie.add(new Movie("DC-2 Бетмен проти Супермена: На зорі справедливості", "https://uakino.club/filmy/genre-action/145-betmen-proti-supermena-na-zor-spravedlivost.html"));
        dcMovie.add(new Movie("DC-3 Загін самогубців [Розширена версія]", "https://uakino.club/filmy/genre-action/182-zagn-samogubcv.html"));
        dcMovie.add(new Movie("DC-4 Ліга справедливості", "https://uakino.club/filmy/5701-lga-spravedlivost-ukrainskoiu.html"));
        dcMovie.add(new Movie("DC-5 Ліга справедливості Зака Снайдера", "https://uakino.club/filmy/genre-action/11784-lga-spravedlivost-ukrainskoiu.html"));
        dcMovie.add(new Movie("DC-6 Аквамен", "https://uakino.club/filmy/genre-action/8117-akvamen.html"));
        dcMovie.add(new Movie("DC-7 Диво-жінка 1984", "https://uakino.club/filmy/11588-divo-zhnka-1984.html"));
        dcMovie.add(new Movie("DC-8 Загін самогубців: Місія навиліт", "https://uakino.club/filmy/genre-action/12488-zagn-samogubcv-msya-navilt.html"));
        dcMovie.add(new Movie("DC-9 Миротворець 1 сезон", "https://uakino.club/seriesss/comedy_series/13058-mirotvorec-1-sezon.html"));

        // Create a UaKinoBot instance with the list of film URLs
        MovieClient bot = new UaKinoBot(dcMovie);

        // Start the program, download videos, and measure execution time
        bot.startProgramWithDownloadAndTimer("C:\\Users\\Мішаня\\Desktop");
    }
}

