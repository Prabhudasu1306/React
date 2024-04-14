

package Student.NRI.Service;

import Student.NRI.model.OrderInfo;
import Student.NRI.model.OrderedItem;
import java.util.List;
import java.util.Optional;

public interface OrderedItemService {
    List<OrderedItem> saveOrderedItems(List<OrderedItem> orderedItems);

    OrderInfo saveOrder(OrderInfo orderInfo);

    List<OrderInfo> getAllOrders();

    OrderInfo updateOrder(OrderInfo orderInfo);

    void deleteOrder(Long id);

    Optional<OrderInfo> getOrderById(Long id);
}
