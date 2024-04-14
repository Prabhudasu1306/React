package Student.NRI.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderedItem> orderedItems;

    @Column(name = "total_bill_amount")
    private Long totalBillAmount;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "order_date")
    private Date OrderdDate = new Date();

}