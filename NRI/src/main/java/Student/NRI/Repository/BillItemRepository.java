package Student.NRI.Repository;

import Student.NRI.model.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem,Long  > {
}
