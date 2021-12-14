package repository;

import entity.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    List<Loan> listOfLoans = new ArrayList<>();

    public void addLoan(Loan loan) {
        listOfLoans.add(loan);
    }
    public List<Loan> getLoans() {
        return listOfLoans;
    }
}
