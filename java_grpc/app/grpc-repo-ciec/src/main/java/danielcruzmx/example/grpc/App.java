package danielcruzmx.example.grpc;

import io.grpc.*;

/**
 * Hello world!
 *
 */
public class App
{


    public static void main( String[] args ) throws Exception
    {

        Server server = ServerBuilder.forPort(5051)
                        .addService(new RepocuotasServiceImpl())
                        .build();

        server.start();
        System.out.println("Servidor escuchando ->");
        server.awaitTermination();
    }


}
