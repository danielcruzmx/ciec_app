package danielcruzmx.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: RepocuotasService.proto")
public final class RepocuotasServiceGrpc {

  private RepocuotasServiceGrpc() {}

  public static final String SERVICE_NAME = "danielcruzmx.example.grpc.RepocuotasService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest,
      danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse> METHOD_REPOCUOTAS =
      io.grpc.MethodDescriptor.<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest, danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "danielcruzmx.example.grpc.RepocuotasService", "repocuotas"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse.getDefaultInstance()))
          .setSchemaDescriptor(new RepocuotasServiceMethodDescriptorSupplier("repocuotas"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RepocuotasServiceStub newStub(io.grpc.Channel channel) {
    return new RepocuotasServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RepocuotasServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RepocuotasServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RepocuotasServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RepocuotasServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RepocuotasServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void repocuotas(danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest request,
        io.grpc.stub.StreamObserver<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_REPOCUOTAS, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REPOCUOTAS,
            asyncUnaryCall(
              new MethodHandlers<
                danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest,
                danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse>(
                  this, METHODID_REPOCUOTAS)))
          .build();
    }
  }

  /**
   */
  public static final class RepocuotasServiceStub extends io.grpc.stub.AbstractStub<RepocuotasServiceStub> {
    private RepocuotasServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RepocuotasServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RepocuotasServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RepocuotasServiceStub(channel, callOptions);
    }

    /**
     */
    public void repocuotas(danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest request,
        io.grpc.stub.StreamObserver<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_REPOCUOTAS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RepocuotasServiceBlockingStub extends io.grpc.stub.AbstractStub<RepocuotasServiceBlockingStub> {
    private RepocuotasServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RepocuotasServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RepocuotasServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RepocuotasServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse repocuotas(danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_REPOCUOTAS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RepocuotasServiceFutureStub extends io.grpc.stub.AbstractStub<RepocuotasServiceFutureStub> {
    private RepocuotasServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RepocuotasServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RepocuotasServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RepocuotasServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse> repocuotas(
        danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_REPOCUOTAS, getCallOptions()), request);
    }
  }

  private static final int METHODID_REPOCUOTAS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RepocuotasServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RepocuotasServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REPOCUOTAS:
          serviceImpl.repocuotas((danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasRequest) request,
              (io.grpc.stub.StreamObserver<danielcruzmx.example.grpc.RepocuotasServiceOuterClass.RepocuotasResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RepocuotasServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RepocuotasServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return danielcruzmx.example.grpc.RepocuotasServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RepocuotasService");
    }
  }

  private static final class RepocuotasServiceFileDescriptorSupplier
      extends RepocuotasServiceBaseDescriptorSupplier {
    RepocuotasServiceFileDescriptorSupplier() {}
  }

  private static final class RepocuotasServiceMethodDescriptorSupplier
      extends RepocuotasServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RepocuotasServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RepocuotasServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RepocuotasServiceFileDescriptorSupplier())
              .addMethod(METHOD_REPOCUOTAS)
              .build();
        }
      }
    }
    return result;
  }
}
