package com.demo.rupeek.thriftjavademo;

import generatedstubs.HelloSvc;
import generatedstubs.LoanService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import services.HelloServiceImpl;
import services.LoanServiceImpl;

@SpringBootApplication
public class ThriftJavaDemoApplication extends SpringBootServletInitializer {

    TServer server;
    public void start() throws TTransportException {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor( "HelloService",new HelloSvc.Processor(new HelloServiceImpl()));
            processor.registerProcessor( "LoanService",new LoanService.Processor(new LoanServiceImpl()));
            /* this.server = new TSimpleServer(new TServer.Args(serverTransport)
                    .processor(new HelloSvc.Processor(new HelloServiceImpl()))); */
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
            ttpsArgs.processor(processor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
            this.server = new TThreadPoolServer(ttpsArgs);
            System.out.print("Starting Apache Thrift server... ");
            this.server.serve();
        } catch(Error error) {
            System.out.println(error);
        }
        System.out.println("done.");
    }

    public void stop() {
        if (this.server != null && this.server.isServing()) {
            System.out.print("Stopping the server... ");
            this.server.stop();
            System.out.println("done.");
        }
    }

    public static void main(String[] args) throws TTransportException {
        // SpringApplication.run(ThriftDemoServer.class, args);
        ThriftJavaDemoApplication server = new ThriftJavaDemoApplication();
        server.start();
    }

}