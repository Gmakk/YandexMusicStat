import API.Loader;
import API.Saver;
import API.entities.Tracks;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Tracks tracks;
        Saver.saveTracks();
        tracks = Loader.loadTracks();
        System.out.println(tracks);
    }
}