package API.entities;

import java.util.TreeMap;

/**
 * Класс для отображения всех треков из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Tracks {
    private TreeMap<String,Track> allTracks = new TreeMap<>();

    public TreeMap<String, Track> getAllTracks() {
        return allTracks;
    }

    public void setAllTracks(TreeMap<String, Track> allTracks) {
        this.allTracks = allTracks;
    }

    public void put(Track track){
        allTracks.put(track.getId(),track);
    }

    public Track get(String id){
        return allTracks.get(id);
    }

    @Override
    public String toString() {
        return allTracks.toString();
    }
}
