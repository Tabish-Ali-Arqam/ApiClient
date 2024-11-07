package ApiClient.apiClient.client;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/client/users")
@RequiredArgsConstructor
public class UserClientController {

    private final UserClientService userClientService;

    // Create a new user (POST request to the other service)
    @PostMapping
    public Mono<Map<String, Object>> createUser(@RequestBody Map<String, Object> user) {
        return userClientService.createUser(user);
    }

    // Get all users (GET request from the other service)
    @GetMapping
    public Mono<Map<String, Object>[]> getAllUsers() {
        return userClientService.getAllUsers();
    }

    // Get a user by ID (GET request to the other service)
    @GetMapping("/{id}")
    public Mono<Map<String, Object>> getUserById(@PathVariable Long id) {
        return userClientService.getUserById(id);
    }

    // Update an existing user (PUT request to the other service)
    @PutMapping("/{id}")
    public Mono<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody Map<String, Object> user) {
        return userClientService.updateUser(id, user);
    }

    // Delete a user by ID (DELETE request to the other service)
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userClientService.deleteUser(id);
    }
}
