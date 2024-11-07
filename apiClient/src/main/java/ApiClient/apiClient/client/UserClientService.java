package ApiClient.apiClient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final WebClient.Builder webClientBuilder;
    private static final String BASE_URL = "http://localhost:8080"; // URL of your other service

    // Create a new user (POST request to the other service)
    public Mono<Map<String, Object>> createUser(Map<String, Object> user) {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .post()
                .uri("/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Map.class)  // This will work, but be sure that Map is a Map<String, Object>
                .map(response -> (Map<String, Object>) response); // Explicitly cast to Map<String, Object>
    }

    // Get all users (GET request from the other service)
    public Mono<Map<String, Object>[]> getAllUsers() {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .get()
                .uri("/users")
                .retrieve()
                .bodyToMono(Map[].class)  // Expecting an array of Maps
                .map(response -> (Map<String, Object>[]) response);  // Cast response to Map<String, Object>[]
    }

    // Get a user by ID (GET request to the other service)
    public Mono<Map<String, Object>> getUserById(Long id) {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)  // This will work, but be sure that Map is a Map<String, Object>
                .map(response -> (Map<String, Object>) response); // Explicitly cast to Map<String, Object>
    }

    // Update an existing user (PUT request to the other service)
    public Mono<Map<String, Object>> updateUser(Long id, Map<String, Object> user) {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .put()
                .uri("/users/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Map.class)  // This will work, but be sure that Map is a Map<String, Object>
                .map(response -> (Map<String, Object>) response); // Explicitly cast to Map<String, Object>
    }

    // Delete a user by ID (DELETE request to the other service)
    public Mono<Void> deleteUser(Long id) {
        return webClientBuilder.baseUrl(BASE_URL)
                .build()
                .delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
