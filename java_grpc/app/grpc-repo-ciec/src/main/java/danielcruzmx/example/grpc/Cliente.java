package danielcruzmx.example.grpc;

import io.grpc.*;
import org.apache.commons.validator.routines.UrlValidator;

public class Cliente
{
    public static void main( String[] args ) throws Exception
    {
	
	   String[] schemes = {"http","https"};
	   UrlValidator urlValidator = new UrlValidator(schemes);
	   if (urlValidator.isValid(args[0])){	
      
			// Channel is the abstraction to connect to a service endpoint
			// Let's use plaintext communication because we don't have certs
			final ManagedChannel channel = ManagedChannelBuilder.forTarget("grpc:5051")
				.usePlaintext(true)
				.build();

			// It is up to the client to determine whether to block the call
			// Here we create a blocking stub, but an async stub,
			// or an async stub with Future are always possible.
			RepocuotasServiceGrpc.RepocuotasServiceBlockingStub stub = RepocuotasServiceGrpc.newBlockingStub(channel);
			RepocuotasServiceOuterClass.RepocuotasRequest request =
				RepocuotasServiceOuterClass.RepocuotasRequest.newBuilder()
				.setName(args[0])
				.build();

			// Finally, make the call using the stub
			RepocuotasServiceOuterClass.RepocuotasResponse response =
			stub.repocuotas(request);

			System.out.println(response);

			// A Channel should be shutdown before stopping the process.
			channel.shutdownNow();
			
	   } else {
		   
		   System.out.println("\n");
		   System.out.println("\n Especifique una URL valida como parametro del programa");
		   System.out.println("\n Ejemplo http://172.17.0.3:9000/api-rest/olimpo/informe/A303 ");
		   System.out.println("\n");
	   }   
    }
}
