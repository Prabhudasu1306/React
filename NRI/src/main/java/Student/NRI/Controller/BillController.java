package Student.NRI.Controller;// BillController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Student.NRI.model.Bill;
import Student.NRI.Service.BillService;

import java.util.List;

@RestController
@RequestMapping("/api/Bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/add")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill createdBill = billService.createBill(bill);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill bill = billService.getBillById(id);
        if (bill != null) {
            return new ResponseEntity<>(bill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill updatedBill) {
        Bill existingBill = billService.getBillById(id);
        if (existingBill != null) {

            existingBill.setOrderId(updatedBill.getOrderId());
            existingBill.setOrderDate(updatedBill.getOrderDate());
            existingBill.setTotalBillAmount(updatedBill.getTotalBillAmount());



            Bill savedBill = billService.updateBill(id, existingBill); // Pass the ID and updated bill to the service method
            return new ResponseEntity<>(savedBill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillById(@PathVariable Long id) {
        billService.deleteBillById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity<Void> deleteAllBills() {
//        billService.deleteAllBills(); // Call the service method to delete all bills
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 after successful deletion
//    }

}
