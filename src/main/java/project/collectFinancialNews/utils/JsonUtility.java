package project.collectFinancialNews.utils;

import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public abstract class JsonUtility {
    protected final ObjectMapper mapper = new ObjectMapper();

    public final ObjectMapper getMapper() { return mapper; }

    public JsonNode toJsonNode(Object object) {
        try {
            return mapper.readTree(mapper.writeValueAsString(object));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T toObject(Class<T> clz, JsonNode node) {
        try {
            return mapper.readValue(mapper.writeValueAsString(node), clz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
