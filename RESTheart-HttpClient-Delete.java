
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.net.URI;

/**     ALL THESE DELETE OPERATIONS ARE BASED ON THE SCHEMA OF THE DOCUMENT AS MENTIONED WHILE DOCUMENT CREATION    **/

public class TestHttpClientDelete {

    public static void main(String[] args) throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response=null;


/**  TEST-CASE->1   DELETING A DATABASE  BASED ON DATABASE's '_etag'  **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1")
//                .setScheme("http")
//                .build();
//        HttpDelete delete = new HttpDelete(uri);
//        delete.setHeader("If-Match","5979c8b542483b1159660088");

/**  TEST-CASE->2   DELETING A COLLECTION  BASED ON COLLECTION's '_etag' **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .build();
//        HttpDelete delete = new HttpDelete(uri);
//        delete.setHeader("If-Match","5977399342483b1f01095f73");


/**  TEST-CASE->3   DELETING SINGLE DOCUMENT BASED ON DOCUMENT '_id' & '_etag'   **/
        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/59772e3b42483b1f01095f71")
                .setScheme("http")
                .build();
        HttpDelete delete = new HttpDelete(uri);
        delete.setHeader("If-Match","59772e3b42483b1f01095f6e");

/**  TEST-CASE->4   DELETING DOCUMENTS IN A COLLECTION BASED ON FILTER      **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/*")
//                .setScheme("http")
//                .addParameter("filter","{'age':21}")
//                .build();
//        HttpDelete delete = new HttpDelete(uri);

/**  TEST-CASE->5   DELETING ALL DOCUMENTS IN A COLLECTION   **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/*")
//                .setScheme("http")
//                .addParameter("filter","{'age':{$gt:0}}") // AS AGE IS ALWAYS greater then 0
//                .build();
//        HttpDelete delete = new HttpDelete(uri);


        delete.setHeader("Content-Type","application/hal+json");
        response = httpclient.execute(delete);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            EntityUtils.consume(entity1);
        } finally {
            response.close();
        }
    }
}
