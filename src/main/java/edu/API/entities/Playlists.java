package edu.API.entities;

import java.util.TreeMap;

/**
 * Класс для отображения всех плейлистов из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Playlists {
    private TreeMap<String,Playlist> allPlaylists = new TreeMap<>();

    public TreeMap<String, Playlist> getAllPlaylists() {
        return allPlaylists;
    }

    public void setAllPlaylists(TreeMap<String, Playlist> allPlaylists) {
        this.allPlaylists = allPlaylists;
    }

    public void put(Playlist playlist){
        allPlaylists.put(playlist.getPlaylistUuid(),playlist);
    }

    public  Playlist get(String id){
        return allPlaylists.get(id);
    }

    public Integer size(){
        return allPlaylists.size();
    }

    @Override
    public String toString() {
        return allPlaylists.toString();
    }
}
