package com.demo.rupeek.thriftjavademo;

import generatedstubs.HelloSvc;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import services.HelloServiceImpl;

@SpringBootApplication
public class ThriftDemoServer extends SpringBootServletInitializer {
    TServer server;
    public void start() throws TTransportException {
        TServerTransport serverTransport = new TServerSocket(9090);
        this.server = new TSimpleServer(new TServer.Args(serverTransport)
                .processor(new HelloSvc.Processor(new HelloServiceImpl())));

        System.out.print("Starting the server... ");

        this.server.serve();

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
        ThriftDemoServer server = new ThriftDemoServer();
        server.start();
    }

    /* @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }

    @Bean
    public ServletRegistrationBean cache(TProtocolFactory protocolFactory, HelloServiceImpl handler) {
        TServlet servlet = new TServlet(new HelloSvc.Processor<HelloServiceImpl>(handler), protocolFactory);
        return new ServletRegistrationBean(servlet, "/hello");
    } */
}