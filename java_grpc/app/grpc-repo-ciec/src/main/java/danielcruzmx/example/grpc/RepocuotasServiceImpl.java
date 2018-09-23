package danielcruzmx.example.grpc;

import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRParameter;

public class RepocuotasServiceImpl extends RepocuotasServiceGrpc.RepocuotasServiceImplBase {


  //  Datos del reporte, conjunto de objetos <Cuotas>
  //
  private static List <Cuotas> lstCuotas;

  @Override
  public void repocuotas(RepocuotasServiceOuterClass.RepocuotasRequest request,
        StreamObserver<RepocuotasServiceOuterClass.RepocuotasResponse> responseObserver) {

    System.out.println(request);

    // You must use a builder to construct a new Protobuffer object
    RepocuotasServiceOuterClass.RepocuotasResponse response = RepocuotasServiceOuterClass.RepocuotasResponse.newBuilder()
      .setRepocuotas(" URL -> " + request.getName())
      .build();


    String[] depto = request.getName().split("/");

    System.out.println(depto[depto.length-1]);
	
	  try {

        // getName() es la direccion HTTP del servicio REST de la aplicación Django
        //
        obtDatos(request.getName());
		generaReporte(depto[depto.length-1], "/tmp/", "/home/java_grpc/app/grpc-repo-ciec/src/main/resources/Cuotas.jasper" );

	  } catch (IOException e) {
		      e.printStackTrace();
    } catch (JRException e) {
          e.printStackTrace();
	  }

    // Use responseObserver to send a single response back
    responseObserver.onNext(response);

    // When you are done, you must call onCompleted.
    responseObserver.onCompleted();
  }

  private static void generaReporte(String depto, String path_result, String path_reporte) throws FileNotFoundException, JRException{

    	InputStream inputStream = new FileInputStream(path_reporte);

    	HashMap<String, Object> parameters = new HashMap<String, Object>();

    	Collections.sort(lstCuotas, new Comparator<Cuotas>() {
    		    public int compare(final Cuotas o1, final Cuotas o2) {
    		    	return o1.getId().compareTo(o2.getId());
    		    }
    	});

    	Cuotas ultima = lstCuotas.get(lstCuotas.size()-1);

    	parameters.put("saldo",ultima.getSaldo());

		Locale spanishLocale = new Locale("es", "MX");
    	Calendar c = Calendar.getInstance(spanishLocale);
    	c.setTime(ultima.getFecha());
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	Date end = c.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", spanishLocale);
    	String corte = sdf.format(end);

    	parameters.put("corte", corte);
    	//parameters.put(JRParameter.REPORT_LOCALE, spanishLocale);
    	parameters.put("REPORT_LOCALE", new java.util.Locale("ES")); 

    	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstCuotas);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
    	JasperExportManager.exportReportToPdfFile(jasperPrint, path_result + depto + ".pdf" );
  }

  public class CustomComparator implements Comparator<Cuotas> {
        @Override
        public int compare(Cuotas o1, Cuotas o2) {
            return o1.getId().compareTo(o2.getId());
        }
  }

  private static void obtDatos(String serverUrl) throws JsonParseException, JsonMappingException, IOException{

    	String serverUser = "danielcruzmx@hotmail.com";
    	String serverPassword = "valeria1";

    	Client client = Client.create();
  		client.addFilter(new HTTPBasicAuthFilter(serverUser, serverPassword));

	 	  WebResource webResource = client.resource(serverUrl);
		  ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		  if (response.getStatus() != 200) {
		      throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
		  }

		  String output = response.getEntity(String.class);

		  ObjectMapper mapper = new ObjectMapper();
		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  mapper.setDateFormat(df);
		  lstCuotas = mapper.readValue(output, new TypeReference<List<Cuotas>>(){});

  }

}
