package edu.API.authorization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс, с помощью которого производится авторизация пользователя
 */
public class Authorization {
    private static final String CLIENT_ID = "9f566e4f6e0c412a9fdb2e23fa15bcf8";
    //private static final String CLIENT_ID = "53151a40353d46b294d936800ee4f763";
    private static final String CLIENT_SECRET = "8ee0fdfe66414895a14d38936c7d2ddd";
    //private static final String CLIENT_SECRET = "cd9f9ada3f124be4a8c34a79ade0eb42";
    private static Integer confirmationCode;

//    /**
//     * Метод запрашивает код HTML страницы и через регулярные выражения находит csfr_token и process_uuid
//     * @throws IOException Выбрасывается когда не удается найти информацию
//     */
//    public static void getDataFromHTML() throws IOException {
//        Content getResult = Request.Get("https://passport.yandex.ru/am?app_platform=android")
//                .execute().returnContent();
//
//        Pattern csfrPattern = Pattern.compile("\"csrf_token\" value=\"[^\"]+\"");// 54символа поставить круглые скобки?
//        Pattern uuidPattern = Pattern.compile("process_uuid=[^\"]+\"");
//
//        Matcher csfrMatcher = csfrPattern.matcher(getResult.asString());
//        if(!csfrMatcher.find())
//            throw new IOException("Unable to find csfr token"); //TODO: подобрать нормальное исключение
//        String tokenString = getResult.asString().substring(csfrMatcher.start(), csfrMatcher.end());
//        //System.out.println(tokenString);
//        System.out.println(csfrToken = tokenString.substring(20, tokenString.length()-15));      //ВОЗМОЖНО НУЖНО БРАТЬ ДО ДВОЕТОЧИЯ
//
//
//        Matcher uuidMatcher = uuidPattern.matcher(getResult.asString());
//        if(!uuidMatcher.find())
//            throw new IOException("Unable to find process uuid"); //TODO: подобрать нормальное исключение
//        String uuidString = getResult.asString().substring(uuidMatcher.start(), uuidMatcher.end());
//        //System.out.println(uuidString);
//        System.out.println(processUuid = uuidString.substring(13, uuidString.length()-1));
//        //System.out.println(getResult);
//    }
//
//    public static void authorizationRequest() throws IOException {
//        String postResult = Request.Post("https://passport.yandex.ru/registration-validations/auth/multi_step/start")
//                //.addHeader("X-Custom-header", "stuff")
//                .bodyForm(Form.form().add("csrf_token", csfrToken).add("process_uuid",processUuid)
//                        .add("login", username).add("app_platform","android").add("client_id",CLIENT_ID)
//                        .add("retpath","https://oauth.yandex.ru/authorize?response_type=token&client_id="+CLIENT_ID).add("origin","oauth").build())
//                .execute().returnContent().toString();
//
//        System.out.println(postResult);
//    }


    public static void setConfirmationCode(Integer confirmationCode) {
        Authorization.confirmationCode = confirmationCode;
    }

    /**
     * Метод делает приглашение ко вводу кода подтверждения и считывает ответ
     * @return всегда возвращает confirmationCode, который будет отличным от null только при корректном вводе
     */
    private static Integer scanCode(){
        confirmationCode = null;
        Scanner scanner = new Scanner(System.in);
        try{
            confirmationCode = scanner.nextInt();
            if(String.valueOf(confirmationCode).length() != 7){
                System.out.println("Wrong length, try again");
                confirmationCode = null;
            }
        }catch (InputMismatchException ex){
            System.out.println("Wrong format, try again");
        }
        return confirmationCode;
    }

    public static String getAuthLink(){
        return "Please follow the link and enter the confirmation code:\n" +
                "https://oauth.yandex.ru/authorize?response_type=code&client_id=" + CLIENT_ID;
    }

    /**
     * Метод совершает обмен кода подтверждения на auth токен посредством post запроса
     * @return true - токен получен, false - токен не получен
     */
    public static boolean getToken(){
        //TODO: QR-код?
        System.out.println(getAuthLink());
        //TODO: обработать exit и неправильный код правильного формата
        //while (scanCode() == null);//Ввод пока не будет введен код правильного формата
        try {
            //совершаем обмен кода на токен
            Content postResult = Request.Post("https://oauth.yandex.ru/token")
                    .addHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()))//закодированная строка `client_id:client_secret`
                    .bodyForm(Form.form().add("code", Integer.toString(confirmationCode))
                            .add("grant_type", "authorization_code").build())
                    .execute().returnContent();

            //при десериализации выставляется актуальный токен в поле класса Token
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Token.class, new TokenDeserializer())//регистрируем десериализатор для треков
                    .create();
            gson.fromJson(String.valueOf(postResult), Token.class);

            return true;
        }catch (HttpResponseException ex){
            System.out.println(ex.toString() + "\nTry again");
            return false;
        }catch (IOException ex){
            System.out.println(ex.toString() + "Wrong request");
            return false;
        }
    }
}
