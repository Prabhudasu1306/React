package Student.NRI.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ordered_item")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_item_id")
    private Long orderedItemId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private double price;

    @Column(name = "central_gst")
    private double centralGST;

    @Column(name = "state_gst")
    private double stateGST;

    @Column(name = "total_gst")
    private double totalGST;

    @Column(name = "price_with_gst")
    private double priceWithGST;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;
}
