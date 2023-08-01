package edu.API;

/**
 * Класс для работы с интервалами
 */
public class Interval {
    private Integer hourStart = 0;
    private Integer minStart = 0;
    private Integer secStart = 0;
    private Integer hourEnd = 0;
    private Integer minEnd = 0;
    private Integer secEnd = 0;

    public Interval(){
    }

    /**
     * Конструктор с секундами
     */
    public Interval(Integer hourStart, Integer minStart, Integer secStart, Integer hourEnd, Integer minEnd,Integer secEnd){
        setAll(hourStart, minStart, secStart, hourEnd, minEnd, secEnd);
    }

    /**
     * Конструктор без секунд
     */
    public Interval(Integer hourStart, Integer minStart, Integer hourEnd, Integer minEnd){
        setAll(hourStart, minStart, hourEnd, minEnd);
    }

    /**
     * Конструктор, копирующий поля другого объекта
     * @param interval Исходный объект
     */
    public Interval(Interval interval){
        this.hourStart = interval.hourStart;
        this.minStart = interval.minStart;
        this.secStart = interval.secStart;
        this.hourEnd = interval.hourEnd;
        this.minEnd = interval.minEnd;
        this.secEnd = interval.secEnd;
    }

    public Integer getHourStart() {
        return hourStart;
    }

    public void setHourStart(Integer hourStart) {
        if(hourStart < 0 || hourStart > 23)
            throw new IllegalArgumentException("Wrong hours number");
        else
            this.hourStart = hourStart;
    }

    public Integer getMinStart() {
        return minStart;
    }

    public void setMinStart(Integer minStart) {
        if(minStart < 0 || minStart > 59)
            throw new IllegalArgumentException("Wrong minutes number");
        else
            this.minStart = minStart;
    }

    public Integer getSecStart() {
        return secStart;
    }

    public void setSecStart(Integer secStart) {
        if(secStart < 0 || secStart > 59)
            throw new IllegalArgumentException("Wrong seconds number");
        else
            this.secStart = secStart;
    }

    public Integer getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Integer hourEnd) {
        if(hourEnd < 0 || hourEnd > 24)
            throw new IllegalArgumentException("Wrong hours number");
        else
            this.hourEnd = hourEnd;
    }

    public Integer getMinEnd() {
        return minEnd;
    }

    public void setMinEnd(Integer minEnd) {
        if(minEnd < 0 || minEnd > 60)
            throw new IllegalArgumentException("Wrong minutes number");
        else
            this.minEnd = minEnd;
    }

    public Integer getSecEnd() {
        return secEnd;
    }

    public void setSecEnd(Integer secEnd) {
        if(secEnd < 0 || secEnd > 60)
            throw new IllegalArgumentException("Wrong seconds number");
        else
            this.secEnd = secEnd;
    }

    /**
     * Метод для задания сразу всех полей кроме секунд
     */
    public void setAll(Integer hourStart, Integer minStart, Integer hourEnd, Integer minEnd){
        setHourStart(hourStart);
        setMinStart(minStart);
        setHourEnd(hourEnd);
        setMinEnd(minEnd);
    }

    /**
     * Метод для задания сразу всех полей
     */
    public void setAll(Integer hourStart, Integer minStart, Integer secStart, Integer hourEnd, Integer minEnd,Integer secEnd){
        setAll(hourStart, minStart, hourEnd, minEnd);
        setSecStart(secStart);
        setSecEnd(secStart);
    }

    /**
     * Метод, копирующий все поля объекта
     * @param interval Исходный объект
     */
    public void setAll(Interval interval){
        this.hourStart = interval.hourStart;
        this.minStart = interval.minStart;
        this.secStart = interval.secStart;
        this.hourEnd = interval.hourEnd;
        this.minEnd = interval.minEnd;
        this.secEnd = interval.secEnd;
    }

    /**
     * Приводит интервал времени к формату "00:00:00-00:00:00"
     * @return Полученную строку
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(hourStart < 10)
            result.append("0" + Integer.toString(hourStart) + ":");
        else
            result.append(Integer.toString(hourStart) + ":");
        if(minStart < 10)
            result.append("0" + Integer.toString(minStart) + ":");
        else
            result.append(Integer.toString(minStart) + ":");
        if(secStart < 10)
            result.append("0" + Integer.toString(secStart) + "--");
        else
            result.append(Integer.toString(secStart) + "---");

        if(hourEnd < 10)
            result.append("0" + Integer.toString(hourEnd) + ":");
        else
            result.append(Integer.toString(hourEnd) + ":");
        if(minEnd < 10)
            result.append("0" + Integer.toString(minEnd) + ":");
        else
            result.append(Integer.toString(minEnd) + ":");
        if(secEnd < 10)
            result.append("0" + Integer.toString(secEnd) + ":");
        else
            result.append(Integer.toString(secEnd) + "-");

        return result.toString();
    }
}
