import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONManager {

    final String FILES_PATH = "../lib/files/";
    
    private ObjectMapper mapper;
    private Map<String, File> jsonFiles;

    public JSONManager() {

        this.mapper = new ObjectMapper();
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NON_PRIVATE);
        this.mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        this.mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        this.mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        this.mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.jsonFiles = new HashMap<String, File>();
        this.jsonFiles.put("products", new File(FILES_PATH + "products.json"));
        this.jsonFiles.put("users", new File(FILES_PATH + "users.json"));

    }

    public <T> void writeToJSON(String filename, T value) throws IOException {

        File jsonFile = jsonFiles.get(filename);

        mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, value);

    }

    // public Map<String, List<Product>> loadJSON(String filename) throws IOException {

    //     File file = jsonFiles.get(filename);

    //     System.out.println(file);

    //     return mapper.readValue(new File(FILES_PATH + "products.json"), new TypeReference<Map<String, List<Product>>>(){});

    // }

    public <T> T loadJSON(String filename, TypeReference<T> type) throws IOException {

        File file = jsonFiles.get(filename);

        System.out.println(file);

        return mapper.readValue(new File(FILES_PATH + "products.json"), type);

    }

}