package API.entities;

import java.util.*;

import API.Dates;

/**
 * Класс для отображения всех треков из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Tracks {
    private static TreeMap<String,Track> allTracks = new TreeMap<>();//id трека и сам трек

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

    public Integer size(){
        return allTracks.size();
    }

    //TODO: Перенести в отдельный класс
    /**
     * Сортирует убыванию Map через превращение в LinkedList и вызов у него соответствующего метода
     * @param map Исходная коллекция
     * @return Отсортированная коллекция
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        LinkedList<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<K, V> result = new LinkedHashMap<>();
        Iterator<Map.Entry<K, V>> iterator = list.descendingIterator();

        while(iterator.hasNext()){
            Map.Entry<K, V> next = iterator.next();
            result.put(next.getKey(),next.getValue());
        }

        return result;
    }

    /**
     * Метод подсчитывает количество отмеченных "мне нравится" треков за каждый день
     * @return Возвращает LinkedHashMap с отсортированными по убыванию значения элементами
     */
    public static Map<Date,Integer> likedPerDay(){
        Map<Date,Integer> result = new LinkedHashMap<>();//дата и количество
        allTracks.forEach((String id, Track track) -> {
            Date date = Dates.timestampToDate(track.getTimestamp());
            if(!result.containsKey(date))
                result.put(date,1);
            else{
                result.replace(date,result.get(date)+1);
            }
            });

        return sortByValue(result);
    }

    @Override
    public String toString(){
        return allTracks.toString();
    }
}
