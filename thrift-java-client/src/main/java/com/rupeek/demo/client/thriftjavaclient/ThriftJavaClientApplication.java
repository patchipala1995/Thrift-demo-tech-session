package com.rupeek.demo.client.thriftjavaclient;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import generatedstubs.*;

@SpringBootApplication
public class ThriftJavaClientApplication {

	public static void main(String[] args) throws TException {
        TSocket trans = new TSocket("localhost", 9090);
		TBinaryProtocol protocol = new TBinaryProtocol(trans);
		
		TMultiplexedProtocol multiplex;

		multiplex = new TMultiplexedProtocol(protocol, "HelloService");
		HelloSvc.Iface helloServiceClient = new HelloSvc.Client(multiplex);

		multiplex = new TMultiplexedProtocol(protocol, "LoanService");
		LoanService.Iface loanServiceClient = new LoanService.Client(multiplex);

        // HelloSvc.Client client = new HelloSvc.Client(protocol);

        trans.open();
		String responseFromHelloClient = helloServiceClient.hello_func();
		System.out.println("\n\n\n");
		System.out.println("Hello service client received: " + responseFromHelloClient);
		System.out.println("=========================================================\n\n\n");
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanid(1001);
		loanDto.setInterest(500);
		loanDto.setBalanceamount(10000);
		loanDto.setClosingamount(10500);
		loanServiceClient.saveLoan(loanDto);
		LoanDto loanDto2 = new LoanDto();
		loanDto2.setLoanid(1002);
		loanDto2.setInterest(300);
		loanDto2.setBalanceamount(5000);
		loanDto2.setClosingamount(5300);
		loanServiceClient.saveLoan(loanDto2);
		System.out.println("Loan service client save loans done");
		System.out.println("=========================================================\n\n\n");
		List<LoanDto> allLoanDtos = loanServiceClient.getLoans();
		System.out.println("Loan service client received the following loan Dtos: ");
		for(int i = 0; i<allLoanDtos.size(); i++) {
			System.out.println("Loan dto received: " + allLoanDtos.get(i));
		}
		System.out.println("=========================================================\n\n\n");
        // trans.close();
    }
}
