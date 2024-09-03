package Student.NRI.Controller;

import Student.NRI.Exception.ResourceNotFoundException;
import Student.NRI.Repository.ItemRepository;
import Student.NRI.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @GetMapping("/getAll")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        existingItem.setItemName(item.getItemName());
        existingItem.setPriceWithGST(item.getPriceWithGST());
        existingItem.setStateGST(item.getStateGST());
        existingItem.setCentralGST(item.getCentralGST());
        existingItem.setTotalGST(item.getTotalGST());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());

        Item updatedItem = itemRepository.save(existingItem);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Item not found with id: %d", id)));

        itemRepository.delete(item);
        return ResponseEntity.noContent().build();
    }
}
