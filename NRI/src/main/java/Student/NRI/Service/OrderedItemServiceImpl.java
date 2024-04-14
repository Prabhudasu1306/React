package Student.NRI.Service;

import Student.NRI.Repository.OrderInfoRepository;
import Student.NRI.model.OrderInfo;
import Student.NRI.model.OrderedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Override
    public List<OrderedItem> saveOrderedItems(List<OrderedItem> orderedItems) {
        return null;
    }

    @Override
    public OrderInfo saveOrder(OrderInfo orderInfo) {
        return orderInfoRepository.save(orderInfo);
    }
    public Optional<OrderInfo> getOrderById(Long id){
        return orderInfoRepository.findById(id);
    }
    @Override
    public List<OrderInfo> getAllOrders() {
        return orderInfoRepository.findAll();
    }
    @Override
    public OrderInfo updateOrder(OrderInfo orderInfo) {
        return orderInfoRepository.save(orderInfo);
    }
    @Override
    public void deleteOrder(Long id) {
        orderInfoRepository.deleteById(id);
    }

}

