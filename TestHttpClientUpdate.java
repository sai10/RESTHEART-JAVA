
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.Document;
import org.json.JSONObject;
import java.net.URI;

/**     ALL THESE PATCH OPERATIONS ARE BASED ON THE SCHEMA OF THE DOCUMENT AS MENTIONED WHILE DOCUMENT CREATION    **/

/**     note:- '_etag' WILL VARY EVERY TIME A DOCUMENT UPDATED      **/

public class TestHttpClientUpdate {

    public static void main(String[] args) throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response=null;
        JSONObject jsonObject = null;
        HttpPatch patch = null;

/**  TEST-CASE->1   ADDING A NEW FIELD WITH A VALUE TO A DOCUMENT BASED ON ITS '_id' & '_etag'       **/
        jsonObject=new JSONObject();
        jsonObject.put("qwerty",2);
        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/5979df3542483b1159660096")
                .setScheme("http")
                .build();
        patch = new HttpPatch(uri);
        patch.setHeader("If-Match","597a27f442483b1159660098");

/**  TEST-CASE->2   MODIFYING AN EXISTING FIELD OF A DOCUMENT BASED ON ITS '_id' & '_etag'       **/
//        jsonObject=new JSONObject();
//        jsonObject.put("age",100);
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/5979df3542483b1159660096")
//                .setScheme("http")
//                .build();
//        patch = new HttpPatch(uri);
//        patch.setHeader("If-Match","597a274842483b1159660097");

/**  TEST-CASE->3   MODIFYING DOCUMENTS i.e MODIFYING AN EXISTING FIELD & ADDING A NEW FIELD BASED ON FILTER       **/
//        jsonObject=new JSONObject();
//        jsonObject.put("bool",true);            // MODIFYING A FIELD
//        jsonObject.put("genre","thriller");     // ADDING A NEW FIELD
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/*")
//                .setScheme("http")
//                .addParameter("filter","{'age':{$gt:20}}")
//                .build();
//        patch = new HttpPatch(uri);

/**  TEST-CASE->4   UNSETTING OR REMOVING FIELD OF A DOCUMENT BASED USING ITS '_id' & '_etag'      **/
//        jsonObject=new JSONObject();
//        jsonObject.put("$unset",new Document().append("qwerty",""));
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/5979df3542483b1159660096")
//                .setScheme("http")
//                .build();
//        patch = new HttpPatch(uri);
//        patch.setHeader("If-Match","597a27f442483b1159660098");

/**  TEST-CASE->5   UNSETTING OR REMOVING FIELD OF A DOCUMENT BASED FILTER      **/
//        jsonObject=new JSONObject();
//        jsonObject.put("$unset",new Document().append("age",""));
//        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1/*")
//                .setScheme("http")
//                .addParameter("filter","{'age':{$gt:20}}")
//                .build();
//        patch = new HttpPatch(uri);

        patch.setHeader("Content-Type","application/hal+json");
        StringEntity params=new StringEntity(jsonObject.toString());
        patch.setEntity(params);
        response = httpclient.execute(patch);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            EntityUtils.consume(entity1);
        } finally {
            response.close();
        }

    }
}
