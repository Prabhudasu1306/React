package Student.NRI.Controller;

import Student.NRI.Service.OrderedItemService;
import Student.NRI.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderItem")
@CrossOrigin("*")
public class OrderedItemController {

    @Autowired
    private OrderedItemService orderedItemService;

    @PostMapping("/add")
    public ResponseEntity<OrderInfo> addOrder(@RequestBody OrderInfo orderInfo) {
        OrderInfo savedOrder = orderedItemService.saveOrder(orderInfo);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderInfo> getOrderById(@PathVariable Long id) {
        Optional<OrderInfo> orderInfo = orderedItemService.getOrderById(id);
        return orderInfo.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderInfo>> getAllOrders() {
        List<OrderInfo> orderInfo = orderedItemService.getAllOrders();
        return new ResponseEntity<>(orderInfo, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderInfo> updateOrder(@RequestBody OrderInfo orderInfo) {
        if (orderInfo.getOrderId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OrderInfo updatedOrder = orderedItemService.updateOrder(orderInfo);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderedItemService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
