package dev.deltric.sprout.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SproutConfig {

    private static Gson gson;

    public static <T> T load(JavaPlugin instance, String configName, Class<T> configObject) {
        return load(instance, configName, configObject, true);
    }

    public static <T> T load(JavaPlugin instance, String configName, Class<T> configObject, boolean createDefault) {
        Logger loggerInstance = instance.getLogger();

        // Make configuration folder if it doesn't exist
        boolean created = new File(System.getProperty("user.dir"), "/config/" + instance.getName() + "/").mkdirs();
        if(created)
            loggerInstance.info("Created " + instance.getName() + " Config directory.");

        // Create default configuration if it's not there and its requested
        Path configPath = Paths.get(System.getProperty("user.dir"), "/config/" + instance.getName() + "/" + configName + ".json");
        if (createDefault && Files.notExists(configPath)) {
            try (InputStream is = instance.getClass().getClassLoader().getResourceAsStream("/" + configName + ".json")) {
                Files.copy(is, configPath);
            } catch (IOException e) {
                loggerInstance.log(Level.WARNING, "Failed to copy the default config.");
            }
        }

        // Call static load function for Gson parsing
        File configFile = new File(System.getProperty("user.dir"),
                "/config/" + instance.getName() + "/" + configName + ".json");

        // Check if config doesn't exist
        if(!configFile.exists()) {
            return null;
        }

        Gson gson = getGson();
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(configFile));
            return gson.fromJson(jsonReader, configObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean save(JavaPlugin instance, String configName, Object object) {
        // Make configuration folder if it doesn't exist
        boolean created = new File(System.getProperty("user.dir"), "/config/" + instance.getName() + "/").mkdirs();
        if(created)
            instance.getLogger().info("Created " + instance.getName() + " Config directory.");

        File configFile = new File(System.getProperty("user.dir"),
                "/config/" + instance.getName() + "/" + configName + ".json");

        Gson gson = getGson();
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            gson.toJson(object, fileWriter);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Gson getGson() {
        if(gson == null) {
            gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        }
        return gson;
    }

}
