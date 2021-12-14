struct LoanDto {
    1: i32 loanid,
    2: double closingamount,
    3: double interest,
    4: double balanceamount
}

service LoanService {
   void saveLoan(1:LoanDto loan),
   list <LoanDto> getLoans(),
}
