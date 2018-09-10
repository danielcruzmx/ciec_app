package danielcruzmx.example.grpc;

import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


public class RepocuotasServiceImpl extends RepocuotasServiceGrpc.RepocuotasServiceImplBase {

  private static List <Cuotas> lstCuotas;

  @Override
  public void repocuotas(RepocuotasServiceOuterClass.RepocuotasRequest request,
        StreamObserver<RepocuotasServiceOuterClass.RepocuotasResponse> responseObserver) {

    String[] deptos = {"PB03","PB04","PB05","PB06","PB07","PB08",
    					   "A101","A102","A103","A104",
    					   "A201","A202","A203","A204",
    					   "A301","A302","A303","A304",
    					   "A401","A402","A403","A404",
    					   "A501","A502","A503","A504",
    					   "B105","B106","B107","B108",
    					   "B205","B206","B207","B208",
    					   "B305","B306","B307","B308",
    					   "B405","B406","B407","B408",
    					   "B505","B506","B507","B508"};

  // HelloRequest has toString auto-generated.
    System.out.println(request);

    // You must use a builder to construct a new Protobuffer object
    RepocuotasServiceOuterClass.RepocuotasResponse response = RepocuotasServiceOuterClass.RepocuotasResponse.newBuilder()
      .setRepocuotas(" URL -> " + request.getName())
      .build();


    String[] depto = request.getName().split("/");

    System.out.println(depto[depto.length-1]);

        //for  (String depto: deptos) {

	try {
		//if (depto.equals(.....)){

                    obtDatos(request.getName());
		    generaReporte(depto[depto.length-1], "/home/app/grpc-repo-ciec/resultados/", "/home/app/grpc-repo-ciec/src/main/resources/Cuotas.jasper" );

		//}
	} catch (IOException e) {
		e.printStackTrace();
        } catch (JRException e) {
                e.printStackTrace();
	}

    //}
    // Use responseObserver to send a single response back
    responseObserver.onNext(response);

    // When you are done, you must call onCompleted.
    responseObserver.onCompleted();
  }

  private static void generaReporte(String depto, String path_result, String path_reporte) throws FileNotFoundException, JRException{

    	InputStream inputStream = new FileInputStream(path_reporte);
    	//JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
    	//JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
    	HashMap<String, Object> parameters = new HashMap<String, Object>();

    	Collections.sort(lstCuotas, new Comparator<Cuotas>() {
    		    public int compare(final Cuotas o1, final Cuotas o2) {
    		    	return o1.getId().compareTo(o2.getId());
    		    }
    	});

    	Cuotas ultima = lstCuotas.get(lstCuotas.size()-1);

    	//System.out.println(ultima.getId() +  " " + ultima.getFecha() + " " + ultima.getSaldo());
    	parameters.put("saldo",ultima.getSaldo());

    	Calendar c = Calendar.getInstance();
    	c.setTime(ultima.getFecha());
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    	Date end = c.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    	String corte = sdf.format(end);

    	parameters.put("corte", corte);

    	//System.out.println(ultima.getId() +  " " + end + " " + ultima.getSaldo());

    	JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstCuotas);
    	JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanColDataSource);
    	JasperExportManager.exportReportToPdfFile(jasperPrint, path_result  + depto + "_" + corte + ".pdf" );
  }

  public class CustomComparator implements Comparator<Cuotas> {
        @Override
        public int compare(Cuotas o1, Cuotas o2) {
            return o1.getId().compareTo(o2.getId());
        }
  }

  private static void obtDatos(String serverUrl) throws JsonParseException, JsonMappingException, IOException{

    	//String serverUrl = "http://administra-tucondominio.rhcloud.com/api-rest/olimpo/informe/";
    	//String serverUrl = "http://sadi-ciecv31.1d35.starter-us-east-1.openshiftapps.com/api-rest/olimpo/informe/";
    	//String serverUrl = "http://" + Host + "/api-rest/olimpo/informe/";
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

		//System.out.println("Output from Server .... \n");
		//System.out.println(output);

		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(df);
		lstCuotas = mapper.readValue(output, new TypeReference<List<Cuotas>>(){});

  }

}
