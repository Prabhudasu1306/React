package Student.NRI.Repository;

import Student.NRI.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation is optional but recommended for clarity
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
