package edu.API.deserializers;

import edu.API.entities.Album;
import edu.API.entities.Albums;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Нужен для преобразования из JSON.
 * <b>Имеет единственный метод и служит для преобразования JsonElement в {@link Albums},
 * в котором информация о каждом треке лежит в отдельном объекте {@link Album} внутри TreeMap
 * <b>Объект класса регистрируется в GsonBuilder с указанием, какой класс
 * он десериализует
 */
public class AlbumsDeserializer implements JsonDeserializer<Albums> {
    /**
     *
     * @param jsonElement Исходный объект
     * @param type Тип результата
     * @param jsonDeserializationContext Контекст, содержащий методы для десериализации других объектов
     * @return Разделенные альбомы
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public Albums deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        Albums albums = new Albums();
        Album album;
        String id, timestamp;
        JsonArray allAlbums =  jsonObject.get("result").getAsJsonArray();
        for(JsonElement albumAsElement : allAlbums){
            JsonObject albumAsObject = albumAsElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
            //проверки нужны, тк объекта с таким именем может не найтись
            if(albumAsObject.get("id") == null){
                id = null;
            }else
                id = albumAsObject.get("id").getAsJsonPrimitive().getAsString();
            if(albumAsObject.get("timestamp") == null){
                timestamp = null;
            }else
                timestamp = albumAsObject.get("timestamp").getAsJsonPrimitive().getAsString();
            album = new Album(id,timestamp);
            albums.put(album);
        }
        return albums;
    }
}
