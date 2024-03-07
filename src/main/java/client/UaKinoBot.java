package client;

import lombok.Getter;
import modal.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * UaKinoBot is a bot for automating movie or series downloading from UaKino website(https://uakino.club/).
 * <p>
 * Example Usage:
 * ```
 * List<String> filmList = new ArrayList<>();
 * filmList.add("https://uakino.club/films/12345");
 * UaKinoBot bot = new UaKinoBot(filmList);
 * bot.startProgramWithDownloadAndTimer("download_directory");
 * ```
 */
@Getter
public class UaKinoBot extends MovieClient {

    public UaKinoBot(List<Movie> filmList) {
        super(filmList);
    }

    /**
     * Starts the program by processing the list of film URLs.
     * Retrieves movie information and video links and populates the resultMovieList.
     */
    public void startProgram() {
        if (filmList.isEmpty()) {
            System.out.println("List of movies are empty!");
            return;
        }
        for (Movie filmUrl : filmList) {
            goToFilm(filmUrl.getMainUrl());
            String getName = filmUrl.getName().isEmpty() ? getVideoName() : filmUrl.getName();
            getWebDriverWait().until(presenceOfElementLocated(By.xpath(XPATH_VIDEO_IFRAME)));

            Movie movie = new Movie(getName, filmUrl.getMainUrl());
            scrollIntoView(driver.findElement(By.xpath(XPATH_VIDEO_IFRAME)));
            List<WebElement> series = driver.findElements(By.xpath(XPATH_VIDEO_LIST));
            series.forEach(s -> {
                clickJs(s);
                waitForLoad();

                try {
                    movie.getUrls().add(getVideoLink());
                } catch (NoSuchElementException e) {
                    System.out.println("Exception while get getVideoLink() for " + filmUrl);
                    System.out.println(e.getMessage());
                }
            });
            if (series.isEmpty()) {
                System.out.println("This is a film.");
                movie.getUrls().add(getVideoLink());
            }
            resultMovieList.add(movie);
        }
        System.out.println("Program finished");
        driver.close();
    }


    /**
     * Retrieves the video link for the current movie.
     *
     * @return The video link.
     */
    private String getVideoLink() {
        driver.switchTo().frame(driver.findElement(By.xpath(XPATH_VIDEO_IFRAME)));
        getWebDriverWait().until(presenceOfElementLocated(By.xpath(XPATH_VIDEO_LINK)));

        String src = driver.findElement(By.xpath(XPATH_VIDEO_LINK)).getAttribute("src");
        driver.switchTo().defaultContent();
        return src;
    }

    /**
     * Retrieves the name of the current video.
     *
     * @return The name of the video.
     */
    private String getVideoName() {
        return driver.findElement(By.xpath(XPATH_VIDEO_NAME)).getText();
    }

    private static final String XPATH_VIDEO_LINK = "//video";
    private static final String XPATH_VIDEO_IFRAME = "//iframe[contains(@src,'ashdi.vip/vod')]";
    private static final String XPATH_VIDEO_NAME = "//h1//span";
    private static final String XPATH_VIDEO_LIST = "//div[contains(@class,'playlists-videos')]//li[contains(@data-id,'0_0')]";
}
