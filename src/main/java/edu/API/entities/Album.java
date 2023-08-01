package edu.API.entities;

/**
 * Класс для отображения отдельного альбома из JSON
 */
public class Album {
    private String id;
    private String timestamp;

    public Album(){
    }

    public Album(String id, String timestamp){
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
