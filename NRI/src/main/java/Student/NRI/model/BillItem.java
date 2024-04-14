package Student.NRI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill_item")
public class BillItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    @JsonBackReference
    private Bill bill;

    @Column(name = "item_name")
    private String itemName;

    private int quantity;

    private double stateGST;

    private double centralGST;

    private double price;

    @Column(name = "price_with_gst")
    private double priceWithGST;

    @Column(name = "total_price")
    private double totalPrice;

   
}
