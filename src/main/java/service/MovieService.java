package service;

import modal.Movie;
import io.github.kanglong1023.m3u8.M3u8Downloads;
import io.github.kanglong1023.m3u8.http.config.HttpRequestManagerConfig;

import java.io.File;
import java.util.List;

import static io.github.kanglong1023.m3u8.M3u8Downloads.download;

public class MovieService {
    /**
     * Downloads videos to the specified directory for all movies in the resultMovieList.
     *
     * @param resultMovieList The list of movie with .m3u8 format.
     * @param dir             The directory where videos will be downloaded.
     */
    public void downLoadVideoToDir(List<Movie> resultMovieList, String dir) {
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
}
