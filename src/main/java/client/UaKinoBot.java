package client;

import io.github.kanglong1023.m3u8.M3u8Downloads;
import io.github.kanglong1023.m3u8.http.config.HttpRequestManagerConfig;
import lombok.Getter;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.kanglong1023.m3u8.M3u8Downloads.download;
import static java.lang.String.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static utils.FolderSizeCalculator.printFolderSize;

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
public class UaKinoBot extends SeleniumClient {
    private final List<String> filmList;
    private final List<Movie> resultMovieList = new ArrayList<>();

    /**
     * Constructs a UaKinoBot instance with a list of film URLs.
     *
     * @param filmList A list of film URLs to process.
     */
    public UaKinoBot(List<String> filmList) {
        driver.manage().window().maximize();
        this.filmList = filmList;
    }

    /**
     * Starts the program, processes the film URLs, and downloads videos to the specified directory.
     *
     * @param dir The directory where videos will be downloaded.
     */
    public void startProgramWithDownloadAndTimer(String dir) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        startProgram();
        downLoadVideoToDir(dir);

        stopWatch.stop();

        long totalTimeInSeconds = stopWatch.getTime() / 1000;
        System.out.println("Time of work: " + totalTimeInSeconds + " seconsd.");
        System.out.println("Count of urls: " + getTotalUrlsCount());
        printFolderSize(new File(dir));
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
        for (String filmUrl : filmList) {
            goToFilm(filmUrl);
            String getName = getVideoName();
            getWebDriverWait().until(presenceOfElementLocated(By.xpath(XPATH_VIDEO_IFRAME)));

            Movie movie = new Movie(getName, filmUrl);
            scrollIntoView(driver.findElement(By.xpath(XPATH_VIDEO_IFRAME)));
            List<WebElement> series = driver.findElements(By.xpath(XPATH_VIDEO_LIST));
            series.forEach(s -> {
                clickJs(s);
                waitForLoad();
                movie.getUrls().add(getVideoLink());
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
     * Downloads videos to the specified directory for all movies in the resultMovieList.
     *
     * @param dir The directory where videos will be downloaded.
     */
    public void downLoadVideoToDir(String dir) {
        if (resultMovieList == null || resultMovieList.isEmpty()) {
            System.out.println("Can't download. Check resultMovieList! " + resultMovieList);
            return;
        }

        HttpRequestManagerConfig managerConfig = HttpRequestManagerConfig.custom()
                .maxConnPerRoute(10)
                .overrideSystemProxy()
                .build();

        resultMovieList.forEach(movie -> {
            String directory = dir + "/" + movie.getName();
            String name;
            new File(directory).mkdirs();

            for (int count = 0; count < movie.getUrls().size(); count++) {
                try {
                    name = movie.getUrls().size() > 1 ? (count + 1 + "_" + movie.getName()) : movie.getName();
                    name += ".mp4";
                    download(managerConfig, M3u8Downloads.newDownload(movie.getUrls().get(count), name, directory));
                } catch (IllegalAccessError e) {
                    System.out.println("Check java version. Need to be java 8. " + e);
                } catch (Exception e) {
                    System.out.println("Can't download " + directory);
                    System.out.println(e);
                }
            }
        });
    }

    /**
     * Navigates to the specified film URL and waits for the page to load.
     *
     * @param url The URL of the film to navigate to.
     */
    private void goToFilm(String url) {
        driver.get(url);
        waitForLoad();
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
        String movieId = getIdMovie(src);
        String urlFormat = src.contains(FILMS) ? filmFormat : serialsFormat;

        driver.switchTo().defaultContent();
        return format(urlFormat, movieId);
    }

    /**
     * Retrieves the total count of video URLs across all movies in resultMovieList.
     *
     * @return The total count of video URLs.
     */
    private int getTotalUrlsCount() {
        int totalUrlsCount = 0;

        for (Movie movie : resultMovieList) {
            totalUrlsCount += movie.getUrls().size();
        }
        return totalUrlsCount;
    }

    /**
     * Retrieves the movie ID from a video URL.
     *
     * @param url The video URL.
     * @return The movie ID extracted from the URL.
     */
    private String getIdMovie(String url) {
        String http = url.contains(FILMS) ? "https://s1.ashdi.vip/video20/films/" : "https://s1.ashdi.vip/video20/serials/";
        return url.replace("/hls/index.m3u8", "").replace(http, "");
    }

    /**
     * Retrieves the name of the current video.
     *
     * @return The name of the video.
     */
    private String getVideoName() {
        return driver.findElement(By.xpath(XPATH_VIDEO_NAME)).getText();
    }

    // Constants for URL formats and XPaths
    private static final String FILMS = "films";
    private static final String serialsFormat = "https://s1.ashdi.vip/content/stream/serials/%s/hls/1080/index.m3u8";
    private static final String filmFormat = "https://s1.ashdi.vip/content/stream/films/%s/hls/1080/index.m3u8";

    private static final String XPATH_VIDEO_LINK = "//video";
    private static final String XPATH_VIDEO_IFRAME = "//iframe[contains(@src,'ashdi.vip/vod')]";
    private static final String XPATH_VIDEO_NAME = "//h1//span";
    private static final String XPATH_VIDEO_LIST = "//div[contains(@class,'playlists-videos')]//li[contains(@data-id,'0_0')]";
}
