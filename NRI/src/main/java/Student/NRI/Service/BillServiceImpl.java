// BillServiceImpl.java
package Student.NRI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Student.NRI.Repository.BillRepository;
import Student.NRI.model.Bill;
import Student.NRI.model.BillItem;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }

    @Override
    public Bill createBill(Bill bill) {
        for (BillItem item : bill.getOrderedItems()) {
            item.setBill(bill);
        }
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill != null) {
            for (BillItem item : bill.getOrderedItems()) {
                item.setBill(existingBill);
            }
            existingBill.setOrderId(bill.getOrderId());
            existingBill.setOrderDate(bill.getOrderDate());
            existingBill.setTotalBillAmount(bill.getTotalBillAmount());
            return billRepository.save(existingBill);
        }
        return null;
    }

    @Override
    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

//    @Override
//    public void deleteAllBills() {


}
