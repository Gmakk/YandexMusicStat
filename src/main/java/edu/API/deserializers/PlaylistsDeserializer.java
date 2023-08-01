package edu.API.deserializers;

import edu.API.entities.Playlists;
import edu.API.entities.Playlist;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Нужен для преобразования из JSON.
 * <b>Имеет единственный метод и служит для преобразования JsonElement в {@link Playlists},
 * в котором информация о каждом треке лежит в отдельном объекте {@link Playlist} внутри TreeMap
 * <b>Объект класса регистрируется в GsonBuilder с указанием, какой класс
 * он десериализует
 */
public class PlaylistsDeserializer implements JsonDeserializer<Playlists> {
    /**
     *
     * @param jsonElement Исходный объект
     * @param type Тип результата
     * @param jsonDeserializationContext Контекст, содержащий методы для десериализации других объектов
     * @return Разделенные артисты
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public Playlists deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        Playlists playlists = new Playlists();
        Playlist playlist;

        String title, ownersID, ownersName, ownersSex, playlistUuid, visibility, created;
        Integer kind, trackCount, durationMs;
        JsonElement owner;
        JsonArray allPlaylists =  jsonObject.get("result").getAsJsonArray();
        for(JsonElement playlistAsElement : allPlaylists){
            JsonObject playlistAsObject = playlistAsElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
            owner = playlistAsObject.get("owner");
            //проверки нужны, тк объекта с таким именем может не найтись
            if(playlistAsObject.get("title") == null)
                title = null;
            else
                title = playlistAsObject.get("title").getAsString();
            if(owner == null || owner.getAsJsonObject().get("uid") == null){
                ownersID = null;
            }else
                ownersID =  owner.getAsJsonObject().get("uid").getAsString();
            if(owner == null || owner.getAsJsonObject().get("name") == null){
                ownersName = null;
            }else
                ownersName =  owner.getAsJsonObject().get("name").getAsString();
            if(owner == null || owner.getAsJsonObject().get("sex") == null){
                ownersSex = null;
            }else
                ownersSex =  owner.getAsJsonObject().get("sex").getAsString();
            if(playlistAsObject.get("playlistUuid") == null)
                playlistUuid = null;
            else
                playlistUuid = playlistAsObject.get("playlistUuid").getAsString();
            if(playlistAsObject.get("kind") == null)
                kind = null;
            else
                kind = playlistAsObject.get("kind").getAsInt();
            if(playlistAsObject.get("trackCount") == null)
                trackCount = null;
            else
                trackCount = playlistAsObject.get("trackCount").getAsInt();
            if(playlistAsObject.get("visibility") == null)
                visibility = null;
            else
                visibility = playlistAsObject.get("visibility").getAsString();
            if(playlistAsObject.get("created") == null)
                created = null;
            else
                created = playlistAsObject.get("created").getAsString();
            if(playlistAsObject.get("durationMs") == null)
                durationMs = null;
            else
                durationMs = playlistAsObject.get("durationMs").getAsInt();
            playlist = new Playlist(title, ownersID, ownersName, ownersSex, playlistUuid, kind, trackCount, visibility, created, durationMs);
            playlists.put(playlist);
        }
        return playlists;
    }
}
