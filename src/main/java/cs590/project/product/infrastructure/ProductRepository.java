package cs590.project.product.infrastructure;

import cs590.project.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final Map<String, Product> inMemoryStore = new ConcurrentHashMap<>();

    public Product save(Product product) {
        inMemoryStore.put(product.getProductId(), product);
        return product;
    }

    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(inMemoryStore.get(productId));
    }

    public void deleteById(String productId) {
        inMemoryStore.remove(productId);
    }

    public List<Product> findByName(String name) {
        return inMemoryStore.values().stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
