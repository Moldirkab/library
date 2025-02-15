import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {
    private static Map<String, Object> config;

    static {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("config.yaml")) {
            if (inputStream == null) {
                throw new IllegalStateException("YAML configuration file not found!");
            }
            Yaml yaml = new Yaml();
            config = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
        String[] keys = key.split("\\.");
        Map<String, Object> temp = config;
        for (int i = 0; i < keys.length - 1; i++) {
            if (temp == null || !temp.containsKey(keys[i])) {
                throw new IllegalArgumentException("Key not found: " + keys[i]);
            }
            temp = (Map<String, Object>) temp.get(keys[i]);
        }
        Object value = temp.get(keys[keys.length - 1]);
        if (value == null) {
            throw new IllegalArgumentException("Key not found: " + keys[keys.length - 1]);
        }
        return value.toString();
    }
}


