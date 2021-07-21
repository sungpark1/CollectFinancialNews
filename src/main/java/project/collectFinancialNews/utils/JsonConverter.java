package project.collectFinancialNews.utils;


public final class JsonConverter extends JsonUtility {
    private static final JsonConverter INSTANCE = new JsonConverter();

    private JsonConverter() { }

    public static JsonConverter getInstance() { return INSTANCE; }
}
