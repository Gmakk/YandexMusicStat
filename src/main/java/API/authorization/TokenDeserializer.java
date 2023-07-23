package API.authorization;

import API.entities.Track;
import API.entities.Tracks;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Нужен для вычленения токена из JSON ответа.
 */
public class TokenDeserializer implements JsonDeserializer<Token> {

    /**
     * Метод десериализует json-строку и заполняет статическое поле в классе-обертке {@link Token}
     * @param jsonElement Исходный объект
     * @param type Тип результата
     * @param jsonDeserializationContext Контекст, содержащий методы для десериализации других объектов
     * @return возвращает просто {@link Token} , тк нужное поле static
     * @throws JsonParseException Выбрасывает при невозможности преобразовать переданный элемент
     */
    @Override
    public Token deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(!jsonElement.isJsonObject())
            throw new JsonParseException("Element isn`t a JsonObject as it should be");
        JsonObject jsonObject = jsonElement.getAsJsonObject();//преобразуем из абстрактного класса в объект для дальнейшей работы
        Token.setToken(jsonObject.get("access_token").getAsJsonPrimitive().getAsString());
        return new Token();
    }
}
