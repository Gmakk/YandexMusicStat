package edu.API.entities;

import java.util.TreeMap;

/**
 * Класс для отображения всех артистов из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Artists {
    private TreeMap<String,Artist> allArtist = new TreeMap<>();

    public TreeMap<String, Artist> getAllArtist() {
        return allArtist;
    }

    public void setAllArtist(TreeMap<String, Artist> allArtist) {
        this.allArtist = allArtist;
    }

    public void put(Artist artist){
        allArtist.put(artist.getId(),artist);
    }

    public Artist get(String id){
        return allArtist.get(id);
    }

    public Integer size(){
        return allArtist.size();
    }

    @Override
    public String toString() {
        return allArtist.toString();
    }
}
