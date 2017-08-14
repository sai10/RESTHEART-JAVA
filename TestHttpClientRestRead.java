
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**     ALL THESE GET OPERATIONS ARE BASED ON THE SCHEMA OF THE DOCUMENT AS MENTIONED WHILE DOCUMENT CREATION    **/

class TestHttpClientRestRead{

    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response1=null;
        String string = null;

/**          Everything to know about databases   **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080")
//                .setScheme("http")
//                //.addParameter("np","true")
//                .build();

/**          Everything to know about collections in a database   **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1")
//                .setScheme("http")
//                //.addParameter("np","true")
//                .build();

/**  TEST-CASE->1    QUERYING ALL DOCUMENTS IN A COLLECTION**/
        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
                .setScheme("http")
               // .addParameter("np","true")
                .build();


/**   TEST-CASE->2   QUERYING DOCUMENT IN FULL MODE    **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("hal","f")
//                //.addParameter("np","true")
//                .build();


/**   TEST-CASE->3   QUERYING DOCUMENT IN COMPACT MODE    **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("hal","c")
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->4   QUERYING STRING VALUE  **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("filter","{\"name\":\"ram ki wife\"}")
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->5   QUERYING INTEGER VALUE  **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("filter","{age:21}")
//                //.addParameter("np","true")
//                .build();


/**   TEST-CASE->6   QUERYING USING COMAPATORS  **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("filter","{age:{$gt:20}}")
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->7   QUERYING AS PER PAGE-SIZE PROPERTY   **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("pagesize","1")
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->8   QUERYING DOCUMENT USING COUNT PARAMETER    **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("hal","c")
//                .addParameter("count",null)
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->9   QUERYING DOCUMENT IN SORTED ORDER    **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                //.addParameter("sort_by","age")   //ascending
//                //.addParameter("sort_by","-age")  //descending
//                //.addParameter("sort_by","{'age':1}")   //ascending
//                //.addParameter("sort_by","{'age':-1}")  //descending
//                .addParameter("sort_by","{'age':1,'name':-1}")
//                //.addParameter("np","true")
//                .build();

/**   TEST-CASE->10   QUERYING DOCUMENT BY APPLYING PROJECTION    **/
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
//                .setScheme("http")
//                .addParameter("keys","{'name':1}")
//                //.addParameter("keys","{'name':0}")
//                //.addParameter("keys","{'age':1,'_id':0}")
//                //.addParameter("np","true")
//                .build();

        //System.out.println(uri.toString());
        HttpGet get = new HttpGet(uri);
        response1 = httpclient.execute(get);

        try {
            System.out.println();
            HttpEntity entity1 = response1.getEntity();
            InputStream instream = entity1.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            StringBuilder sb = new StringBuilder();
            while ((string = reader.readLine()) != null)
                sb.append(string + "\n");
            instream.close();
            string = sb.toString();
            System.out.println(string);
            EntityUtils.consume(entity1);
        }
        finally {
            response1.close();
        }

/**     DEPENDS ON WHAT YOU WANT TO GET OUT OF RETRIEVED DATA        **/
        // note:-THIS WILL NOT WORK WHEN np IS TRUE

        JSONObject json = new JSONObject(string);

        try {

            // TO PRINT COLLECTION OR DATABASE PROPERTIES DEPENDING ON URI
//      System.out.println(json.get("_etag"));      // and so on..

            // TO PRINT DOCUMENTS
            JSONArray arr = (JSONArray) json.get("_embedded");
            System.out.println(arr.length());
            //System.out.println(arr);
            if(arr.length()>0) {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonObj = arr.getJSONObject(i);
                    System.out.println(jsonObj);
//                                int age = jsonObj.getInt("age");
//                                String name = jsonObj.getString("name");
//                                System.out.println("name "+i+" is:"+name+" and age is: "+age);
                }
            }
        }
        catch (Exception e){
            // WHEN IN FULL MODE OR COMPACT MODE
            System.out.println(json.get("_embedded").toString());
//            System.out.println(json.get("_size").toString()); // WHILE USING COUNT PARAMETER
//            System.out.println(json.get("_total_pages").toString()); // WHILE USING COUNT PARAMETER
        }
    }
}