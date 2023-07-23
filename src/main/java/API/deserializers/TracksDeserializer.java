package API.deserializers;

import API.entities.Track;
import API.entities.Tracks;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Нужен для преобразования из JSON.
 * <b>Имеет единственный метод и служит для преобразования JsonElement в {@link Tracks},
 * в котором информация о каждом треке лежит в отдельном объекте {@link Track} внутри TreeMap
 * <b>Объект класса регистрируется в GsonBuilder с указанием, какой класс
 * он десериализует
 */
public class TracksDeserializer implements JsonDeserializer<Tracks> {
    /**
     *
     * @param jsonElement Исходный объект
     * @param type Тип результата
     * @param jsonDeserializationContext Контекст, содержащий методы для десериализации других объектов
     * @return Разделенные треки
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public Tracks deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        Tracks tracks = new Tracks();
        Track track;
        String id, albumId, timestamp;
        JsonArray allTracks =  jsonObject.get("result").getAsJsonObject().get("library").getAsJsonObject().get("tracks").getAsJsonArray();
        for(JsonElement trackAsElement : allTracks){
            JsonObject trackAsObject = trackAsElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
            //проверки нужны, тк объекта с таким именем может не найтись
            if(trackAsObject.get("id") == null){
                id = null;
            }else
                id = trackAsObject.get("id").getAsJsonPrimitive().getAsString();
            if(trackAsObject.get("albumId") == null){
                albumId = null;
            }else
                albumId = trackAsObject.get("albumId").getAsJsonPrimitive().getAsString();
            if(trackAsObject.get("timestamp") == null){
                timestamp = null;
            }else
                timestamp = trackAsObject.get("timestamp").getAsJsonPrimitive().getAsString();
            track = new Track(id,albumId,timestamp);
            tracks.put(track);
        }
        return tracks;
    }
}
