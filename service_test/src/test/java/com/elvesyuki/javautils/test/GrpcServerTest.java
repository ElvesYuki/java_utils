package com.elvesyuki.javautils.test;

import com.elvesyuki.javautils.normal.object.HelloWorldServer;
import com.rabbitmq.client.*;
// import io.grpc.examples.helloworld.HelloReply;
// import io.grpc.examples.helloworld.HelloRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/8/2 下午12:38
 */
@SpringBootTest(classes = TestApplication.class)
public class GrpcServerTest {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerTest.class);


    @Test
    public void test01() throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                // .addService(new GreeterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                // try {
                //     // HelloWorldServer.this.stop();
                // } catch (InterruptedException e) {
                //     e.printStackTrace(System.err);
                // }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    //
    //     @Override
    //     public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    //         HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
    //         responseObserver.onNext(reply);
    //         responseObserver.onCompleted();
    //     }
    // }

}
