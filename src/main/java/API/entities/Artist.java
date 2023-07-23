package API.entities;

import java.util.ArrayList;

/**
 * Класс для отображения отдельного артиста из JSON
 */
public class Artist {
    private String id;
    private String name;
    private ArrayList<String> genres;
    private Integer tracks;
    private Integer directAlbums;
    private Integer monthRating; //"ratings":"month"
    private Boolean ticketsAvailable;

    public Artist(){
    }

    public Artist(String id,String name,ArrayList<String> genres,Integer tracks,Integer directAlbums,Integer monthRating,Boolean ticketsAvailable){
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.directAlbums = directAlbums;
        this.monthRating = monthRating;
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public Integer getTracks() {
        return tracks;
    }

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    public Integer getDirectAlbums() {
        return directAlbums;
    }

    public void setDirectAlbums(Integer directAlbums) {
        this.directAlbums = directAlbums;
    }

    public Integer getMonthRating() {
        return monthRating;
    }

    public void setMonthRating(Integer monthRating) {
        this.monthRating = monthRating;
    }

    public Boolean getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Boolean ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genres=" + genres +
                ", tracks=" + tracks +
                ", directAlbums=" + directAlbums +
                ", monthRating=" + monthRating +
                ", ticketsAvailable=" + ticketsAvailable +
                '}';
    }
}
