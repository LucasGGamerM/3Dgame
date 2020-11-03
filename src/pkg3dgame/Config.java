package pkg3dgame;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



public class Config {
    private static float shadow;
    private static int difficulty;
    private static int volume;
    private static boolean sound;
    private static String config;
    private static Path path;
    public Config(String FilePath) throws IOException, NumberFormatException
    {   
        path = FileSystems.getDefault().getPath(FilePath);
        try{
        config = new String(Files.readAllBytes(Paths.get("config.txt")));
        }catch(Exception exception)
        {
            System.out.println("Criando Outro Arquivo");
            Files.writeString(path, "s: 1.0, d: 2, v: 100, so: false", StandardOpenOption.CREATE_NEW);
            config = new String(Files.readAllBytes(Paths.get("config.txt")));
            
        }
        try{
        shadow = Float.parseFloat(config.substring(config.indexOf(":") + 2, config.indexOf(",")));
        difficulty = Integer.parseInt(config.substring(config.indexOf("d") + 3, config.indexOf("d") + 4));
        
        volume = Integer.parseInt(config.substring(config.indexOf("v") + 3, config.indexOf("v") + 5));
        if(config.substring(config.indexOf("o") + 3, config.indexOf("o") + 8).equals("false"))
        {
            sound = false;
        }else{
            sound = true;
        }
        }catch(Exception exception)
        {
            System.out.println("Criando Outro Arquivo");
            Files.writeString(path, "s: 1.0, d: 2, v: 100, so: false", StandardOpenOption.CREATE_NEW);
            config = new String(Files.readAllBytes(Paths.get("config.txt")));
        }

        
    }
    public static void close() throws IOException
    {
        config = "s: " + shadow + ", d: " + HUD.Dif + ",v: " + volume + ", so: " + sound + "               ";
        if(volume == 0)
        {
            config = "s: " + shadow + ", d: " + HUD.Dif + ",v: " + volume + "0" + ", so: " + sound + "               ";
        }

        System.out.println(config);
        Files.writeString(path, config, StandardOpenOption.WRITE);
    } 


    public static int getDifficulty() {
        return difficulty;
    }
    public static float getShadow() {
        return shadow;
    }
    public static int getVolume() {
        return volume;
    }
    public static boolean getSound()
    {
        return sound;
    }
    public static void setDifficulty(int difficulty1) {
        difficulty = difficulty1;
    }
    public static void setShadow(float shadow1) {
        shadow = shadow1;
    }
    public static void setSound(boolean sound1) {
        sound = sound1;
    }
    public static void setVolume(int volume1) {
        volume = volume1;
    }
     
}
