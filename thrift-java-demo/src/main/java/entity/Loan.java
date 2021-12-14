package entity;

import generatedstubs.LoanDto;
import lombok.Getter;
import lombok.Setter;

public class Loan {
    @Getter
    @Setter
    Integer loanid;
    @Getter
    @Setter
    Double closingamount;
    @Getter
    @Setter
    Double interest;
    @Getter
    @Setter
    Double balanceamount;
    public Loan(LoanDto loanDto) {
        this.loanid = loanDto.getLoanid();
        this.closingamount = loanDto.getClosingamount();
        this.interest = loanDto.getInterest();
        this.balanceamount = loanDto.getBalanceamount();
    }
}
