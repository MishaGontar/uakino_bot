package client;

import modal.Movie;
import org.apache.commons.lang3.time.StopWatch;
import service.MovieService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static utils.FolderSizeCalculator.printFolderSize;

public class MovieClient extends SeleniumClient {
    private final MovieService service = new MovieService();
    protected final List<Movie> resultMovieList = new ArrayList<>();
    protected final List<Movie> filmList;

    public MovieClient(List<Movie> filmList) {
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
        service.downLoadVideoToDir(resultMovieList, dir);

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
        throw new RuntimeException("Unsupported operations!");
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
     * Navigates to the specified film URL and waits for the page to load.
     *
     * @param url The URL of the film to navigate to.
     */
    protected void goToFilm(String url) {
        driver.navigate().to(url);
        waitForLoad();
    }
}
