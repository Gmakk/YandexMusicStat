package edu.API.deserializers;

import com.google.gson.*;
import edu.API.entities.Track;
import edu.API.entities.Tracks;
import edu.API.entities.User;

import java.lang.reflect.Type;

/**
 * Нужен для преобразования из JSON.
 * <b>Имеет единственный метод и служит для преобразования JsonElement в {@link User},
 */
public class UserDeserializer implements JsonDeserializer<User> {
    /**
     *
     * @param jsonElement Исходный объект
     * @param typeOfT Тип результата
     * @param context Контекст, содержащий методы для десериализации других объектов
     * @return Информация о пользователе
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public User deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        String id, login, name, email;

        //проверки нужны, тк объекта с таким именем может не найтись
        if(jsonObject.get("id") == null){
            id = null;
        }else
            id = jsonObject.get("id").getAsJsonPrimitive().getAsString();
        if(jsonObject.get("login") == null){
            login = null;
        }else
            login = jsonObject.get("login").getAsJsonPrimitive().getAsString();
        if(jsonObject.get("display_name") == null){
            name = null;
        }else
            name = jsonObject.get("display_name").getAsJsonPrimitive().getAsString();
        if(jsonObject.get("emails") == null){
            email = null;
        }else
            email = jsonObject.get("emails").getAsJsonArray().get(0).getAsJsonPrimitive().getAsString();
        User.setAll(id,login,name,email);

        return new User();
    }
}
