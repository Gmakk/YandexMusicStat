package edu.API.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import edu.API.Dates;
import edu.API.Interval;

/**
 * Класс для отображения всех треков из JSON.
 * <b>
 * Элементы находятся в TreeMap, тк большая часть работы с ними
 * подразумевает поиск среди множества элементов
 */
public class Tracks {
    private TreeMap<String,Track> allTracks = new TreeMap<>();//id трека и сам трек

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
     * Метод подсчитывает количество отмеченных "мне нравится/не нравится" треков за каждый день
     * @return Возвращает LinkedHashMap с отсортированными по убыванию значения элементами
     */
    //TODO: Откуда берется время
    //TODO: Статистика судя по всему неправильная
    public Map<String,Integer> addedPerDay(){
        Map<String,Integer> result = new LinkedHashMap<>();//дата и количество
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        allTracks.forEach((String id, Track track) -> {
            String date = df.format(Dates.timestampToDate(track.getTimestamp()));
            if(!result.containsKey(date))
                result.put(date,1);
            else{
                result.replace(date,result.get(date)+1);
            }
            });

        return sortByValue(result);
    }

    /**
     * Метод распределяет моменты добавления треков по временным интервалам в сутках
     * @param intervalSizeMins Кратный суткам промежуток времени в минутах
     * @return Полученное распределение
     */
    public Map<Interval,Integer> divideByTimeIntervals(Integer intervalSizeMins){
        //TODO: Проверить на очень маленьких промежутках
        //проверка корректности введенного интервала
        if(1440 % intervalSizeMins != 0 && intervalSizeMins > 0)
            throw new IllegalArgumentException("It is impossible to divide a day into such intervals");

        //создаем таблицу с интервалами для дальнейшего заполнения
        Map<Interval,Integer> result = new LinkedHashMap<>();//интервал и количество
        Interval intervalBuf;
        for(int i = 0; i < 1440/intervalSizeMins; i++){
                intervalBuf = new Interval(i * intervalSizeMins / 60, i * intervalSizeMins % 60 //задаются границы очередного интервала
                        , (i + 1) * intervalSizeMins / 60, (i + 1) * intervalSizeMins % 60);
                result.put(intervalBuf, 0);
        }

        //проходимся по трекам и считаем сумму в каждом интервале
        Interval intervalWithRightEnd = new Interval(); //необходимо, тк например конец 12:00 соответствует по факту 12:60
        allTracks.forEach((String id, Track track) -> {
            Date timeOfTrack = Dates.timestampToDate(track.getTimestamp());
            //находим подходящий интервал и увеличиваем соответствующее ему количество на 1
            result.forEach((Interval interval,Integer amount)->{
                intervalWithRightEnd.setAll(interval);//копируем значения текущего интервала
                if(interval.getMinEnd() == 0)
                    intervalWithRightEnd.setMinEnd(60);
                //случай когда время равно не концу/началу и находится строго между ними
                if( ((interval.getHourStart() < timeOfTrack.getHours() ||  //когда время точно больше начала интервала
                        (intervalWithRightEnd.getHourStart() == timeOfTrack.getHours() && intervalWithRightEnd.getMinStart() < timeOfTrack.getMinutes())) &&

                        (intervalWithRightEnd.getHourEnd() > timeOfTrack.getHours() || //когда время точно меньше конца интервала
                                (intervalWithRightEnd.getHourEnd() == timeOfTrack.getHours() && intervalWithRightEnd.getMinEnd() < timeOfTrack.getMinutes()))) ||

                        //случай когда время равно началу
                        (interval.getHourStart() == timeOfTrack.getHours() && interval.getMinStart() == timeOfTrack.getMinutes()))
                    result.replace(interval, result.get(interval) + 1);
            });
        });
        return result;
    }

    @Override
    public String toString(){
        return allTracks.toString();
    }
}
