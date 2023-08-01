package edu.API.deserializers;

import edu.API.entities.Artists;
import edu.API.entities.Artist;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Нужен для преобразования из JSON.
 * <b>Имеет единственный метод и служит для преобразования JsonElement в {@link Artists},
 * в котором информация о каждом треке лежит в отдельном объекте {@link Artists} внутри TreeMap
 * <b>Объект класса регистрируется в GsonBuilder с указанием, какой класс
 * он десериализует
 */
public class ArtistsDeserializer implements JsonDeserializer<Artists> {
    /**
     *
     * @param jsonElement Исходный объект
     * @param type Тип результата
     * @param jsonDeserializationContext Контекст, содержащий методы для десериализации других объектов
     * @return Разделенные артисты
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public Artists deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        Artists artists = new Artists();
        Artist artist;
        ArrayList<String> genres = new ArrayList<>();
        String id, name;
        Integer tracks, directAlbums, monthRating;
        Boolean ticketsAvailable;

        JsonArray allArtists =  jsonObject.get("result").getAsJsonArray();
        for(JsonElement artistAsElement : allArtists){
            JsonObject artistAsObject = artistAsElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
            //проверки нужны, тк объекта с таким именем может не найтись

            if(artistAsObject.get("id") == null){
                id = null;
            }else
                id = artistAsObject.get("id").getAsJsonPrimitive().getAsString();
            if(artistAsObject.get("name") == null){
                name = null;
            }else
                name = artistAsObject.get("name").getAsJsonPrimitive().getAsString();


            for(JsonElement genreAsElement : artistAsObject.get("genres").getAsJsonArray()){
                genres.add(genreAsElement.getAsString());
            }


            if (artistAsObject.get("counts") == null || artistAsObject.get("counts").getAsJsonObject().get("tracks") == null)
                tracks = null;
            else
                tracks = artistAsObject.get("counts").getAsJsonObject().get("tracks").getAsJsonPrimitive().getAsInt();
            if (artistAsObject.get("counts") == null || artistAsObject.get("counts").getAsJsonObject().get("directAlbums") == null)
                directAlbums = null;
            else
                directAlbums = artistAsObject.get("counts").getAsJsonObject().get("directAlbums").getAsJsonPrimitive().getAsInt();
            if(artistAsObject.get("ratings") == null || artistAsObject.get("ratings").getAsJsonObject().get("month") == null)
                monthRating = null;
            else
                monthRating = artistAsObject.get("ratings").getAsJsonObject().get("month").getAsJsonPrimitive().getAsInt();

            ticketsAvailable = artistAsObject.get("ticketsAvailable").getAsJsonPrimitive().getAsBoolean();

            artist = new Artist(id, name, genres, tracks, directAlbums, monthRating, ticketsAvailable);
            artists.put(artist);
        }
        return artists;
    }
}
