package project.newsfeed.connectors;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public abstract class RestConnector {
    protected RestTemplate restTemplate;
    protected final String baseUrl;

    protected RestConnector(String host) {
        this.baseUrl = String.format("https://%s/", host);
        restTemplate = makeRestTemplate();
    }

    protected RestTemplate makeRestTemplate() {
        RestTemplate template = new RestTemplate();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        template.setRequestFactory(requestFactory);

        return template;
    }

    protected RestTemplate getRestTemplate() {
        return this.restTemplate;
    }

    protected RestConnectorSession rq() {
        return new RestConnectorSession();
    }

    protected final class RestConnectorSession {
        private HttpMethod verb = HttpMethod.GET;
        private Object requestBody = null;
        private HttpHeaders headers = new HttpHeaders();

        public <T> ResponseEntity<T> send(
                String endpoint,
                ParameterizedTypeReference<T> returnClass,
                Object... uriVariables
        ) {
            String fullUri = baseUrl + endpoint;

            return getRestTemplate()
                    .exchange(fullUri, verb, new HttpEntity<>(requestBody, headers), returnClass, uriVariables);
        }

        public <T> ResponseEntity<T> send(
                String endpoint,
                Class<T> returnClass,
                Object... uriVariables
        ) {
            return send(endpoint, new ParameterizedTypeReference<T>() {
                @NotNull
                @Override
                public Type getType() {
                    return returnClass;
                }
            }, uriVariables);
        }

        public RestConnectorSession verb(HttpMethod verb) {
            this.verb = verb;
            return this;
        }

        public RestConnectorSession requestBody(Object requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public RestConnectorSession headers(HttpHeaders headers) {
            if (this.headers.isEmpty()) {
                this.headers = headers;
            } else {
                this.headers.putAll(headers.entrySet().stream()
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));
            }
            return this;
        }
    }
}