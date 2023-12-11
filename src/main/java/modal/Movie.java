package modal;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a movie entity with a name, main URL, and a list of additional URLs.
 * <p>
 * Example Usage:
 * ```
 * // Create a new Movie object
 * Movie movie = new Movie("The Shawshank Redemption", "http://example.com/shawshank");
 * <p>
 * // Add additional URLs (useful for TV series)
 * movie.getUrls().add("http://example.com/shawshank/episode1");
 * movie.getUrls().add("http://example.com/shawshank/episode2");
 * <p>
 * // Print the movie information
 * System.out.println("Movie Name: " + movie.getName());
 * System.out.println("Main URL: " + movie.getMainUrl());
 * ```
 */
@Getter
public class Movie {

    /**
     * The name of the movie.
     */
    private String name;

    /**
     * The main URL address of the movie.
     */
    @Setter
    private String mainUrl;

    /**
     * A list of additional URL addresses related to the movie (e.g., for TV series).
     */
    @Setter
    private List<String> urls = new ArrayList<>();

    /**
     * Constructs a new Movie object with a name and main URL.
     *
     * @param name    The name of the movie.
     * @param mainUrl The main URL address of the movie.
     */
    public Movie(String name, String mainUrl) {
        setName(name);
        this.mainUrl = mainUrl;
    }

    public Movie(String mainUrl) {
        this.name = "";
        this.mainUrl = mainUrl;
    }

    /**
     * Sets the name of the movie after processing. Removes leading/trailing spaces and slashes.
     *
     * @param name The name of the movie to be set.
     */
    public void setName(String name) {
        this.name = name.trim().replaceAll("[:/«»’.?*<>|\"']", "");
    }

    /**
     * Overrides the toString method for convenient object representation as a string.
     *
     * @return A string representing the Movie object.
     */
    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", mainUrl='" + mainUrl + '\'' +
                ", urls=" + urls +
                '}';
    }
}
