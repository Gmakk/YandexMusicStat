package API;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//загружаем всю информацию в группу файлов(JSON), оттуда уже анализируем
public class Saver {
    private static String token = "AgAAAAATSr_6AAG8Xu70TUy3-0x0kMf3uPc04fA";
    private static final String INFO_PATH = new File("src/main/resources").getAbsolutePath();

    private static void loadContent(String request,String fileName) throws IOException {
        final Content getResult = Request.Get(request)
                .addHeader("accept","application/json").addHeader("Authorization","OAuth " + token)
                .execute().returnContent();
        //System.out.println(getResult.asString());


        FileWriter writer = new FileWriter(INFO_PATH + "/" + fileName + ".json");
        writer.write(getResult.asString());
        writer.flush();
        writer.close();
    }

    public static void setToken(String token) {
        Saver.token = token;
    }

    public void loadInfo(){//СДЕЛАТЬ ОСТАЛЬНЫЕ МЕТОДЫ ПРИВАТНЫМИ
        //по очереди вызывать методы по загрузке каждого аспекта
    }

    public static void loadAlbums(){
    }
    //....
    public static void loadTracks(){
        try {
            loadContent("https://api.music.yandex.net:443/users/541320800/likes/tracks","Tracks");
        } catch (IOException e) {
            System.out.println("Unable to load tracks");
        }
    }
}
