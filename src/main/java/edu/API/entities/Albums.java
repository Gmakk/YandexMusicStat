package edu.API.entities;

import java.util.TreeMap;

/**
 * Класс для отображения всех альбомов из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Albums {
    private TreeMap<String,Album> allAlbums = new TreeMap<>();

    public TreeMap<String, Album> getAllAlbums() {
        return allAlbums;
    }

    public void setAllAlbums(TreeMap<String, Album> allAlbums) {
        this.allAlbums = allAlbums;
    }

    public void put(Album album){
        allAlbums.put(album.getId(), album);
    }

    public Album get(String id){
        return allAlbums.get(id);
    }

    public Integer size(){
        return allAlbums.size();
    }

    @Override
    public String toString(){
        return allAlbums.toString();
    }
}
