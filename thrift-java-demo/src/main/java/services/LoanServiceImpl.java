package services;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

import entity.Loan;
import generatedstubs.LoanDto;
import generatedstubs.LoanService;
import repository.LoanRepository;

public class LoanServiceImpl implements LoanService.Iface {

    LoanRepository loanRepository = new LoanRepository();

    @Override
    public void saveLoan(LoanDto loanDto) throws TException {
        Loan loan = new Loan(loanDto);
        loanRepository.addLoan(loan);
    }

    public LoanDto getDto(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanid(loan.getLoanid());
        loanDto.setClosingamount(loan.getClosingamount());
        loanDto.setInterest(loan.getInterest());
        loanDto.setBalanceamount(loan.getBalanceamount());
        return loanDto;
    }

    @Override
    public List<LoanDto> getLoans() throws TException {
        List<Loan> allLoans = loanRepository.getLoans();
        List<LoanDto> allLoanDtos= new ArrayList<>();
        for(int i = 0; i<allLoans.size(); i++) {
            LoanDto loanDto = getDto(allLoans.get(i));
            allLoanDtos.add(loanDto);
        }
        return allLoanDtos;
    }

}