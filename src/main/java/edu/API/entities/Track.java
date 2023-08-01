package edu.API.entities;


// TODO Возможно для всех объектов сделать наследование?
// TODO Добавить название трека, артиста и альбома?

/**
 * Класс для отображения отдельного трека из JSON
 */
public class Track {
    private String id;
    private String albumId;
    private String timestamp;

    public Track(){
    }

    public Track(String id,String albumId,String timestamp){
        this.id = id;
        this.albumId = albumId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", albumId='" + albumId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
