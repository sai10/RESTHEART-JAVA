import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;

/**     SCHEMA OF THE DOCUMENT
 *      {
 *          name -> STRING
 *          age  -> INTEGER
 *          bool -> BOOLEAN
 *      }
 */

public class TestHttpClientRestCreate {

    public static void main(String[] args) throws Exception{

        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        StringEntity se = null;
        HttpPut put = null;
        HttpPost post = null;
        HttpEntity entity = null;
        JSONObject jsonObject = null , jsonObject1 = null , jsonObject2 = null , jsonObject3 = null;
        JSONArray jarray = null;

/**     DATABASE CREATION   **/
        URI uri = new URIBuilder("http://127.0.0.1:8080/testDB1")
                .setScheme("http")
                .build();
        put = new HttpPut(uri);
        response = httpclient.execute(put);

        try {
            System.out.println(response.getStatusLine());
            entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }


/**     COLLECTION CREATION   **/
        uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
                .setScheme("http")
                .build();
        put = new HttpPut(uri);
        response = httpclient.execute(put);

        try {
            System.out.println(response.getStatusLine());
            entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }


/**     DOCUMENT CREATION   **/
        uri = new URIBuilder("http://127.0.0.1:8080/testDB1/col1")
                .setScheme("http")
                .build();
        post = new HttpPost(uri);

//        SINGLE WRITE

//        jsonObject = new JSONObject();
//        jsonObject.put("name","rama");
//        jsonObject.put("age",25);
//        jsonObject.put("bool",true);
//        System.out.println(jsonObject.toString());
//        se = new StringEntity(jsonObject.toString());

//        BULK WRITE

        jsonObject1 = new JSONObject();
        jsonObject1.put("name","rama");
        jsonObject1.put("age",25);
        jsonObject1.put("bool",true);

        jsonObject2 = new JSONObject();
        jsonObject2.put("name","ram ki wife");
        jsonObject2.put("age",21);
        jsonObject2.put("bool",false);

        jsonObject3 = new JSONObject();
        jsonObject3.put("name","ravan");
        jsonObject3.put("age",50);
        jsonObject3.put("bool",false);

        jarray = new JSONArray();
        jarray.put(jsonObject1);
        jarray.put(jsonObject2);
        jarray.put(jsonObject3);

        System.out.println(jarray.toString());
        se = new StringEntity(jarray.toString());

        post.setHeader("Content-Type","application/hal+json");
        post.setEntity(se);
        response = httpclient.execute(post);

        try {
            System.out.println(response.getStatusLine());
            entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

    }
}
