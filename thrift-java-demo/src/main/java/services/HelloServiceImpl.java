package services;

import org.apache.thrift.TException;
import generatedstubs.HelloSvc;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloSvc.Iface {

    public String hello_func() throws TException {
        System.out.println("Inside hello_func");
        return "Hello";
    }
}
