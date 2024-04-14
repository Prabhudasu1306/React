package Student.NRI.Service;

import Student.NRI.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    Bill getBillById(Long id);
    Bill createBill(Bill bill);
    Bill updateBill(Long id, Bill bill);
    void deleteBillById(Long id);

//    void deleteAllBills();
}

