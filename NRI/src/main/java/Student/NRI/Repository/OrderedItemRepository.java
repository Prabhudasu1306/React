package Student.NRI.Repository;


import Student.NRI.model.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem,Long> {
}
