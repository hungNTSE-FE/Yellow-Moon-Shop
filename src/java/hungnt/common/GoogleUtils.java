/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.common;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hungnt.constants.AppConstants;

/**
 *
 * @author hungn
 */
public class GoogleUtils {
    public static String getToken(final String code) throws ClientProtocolException, IOException {
    String response = Request.Post(AppConstants.GOOGLE_LINK_GET_TOKEN)
        .bodyForm(Form.form().add("client_id", AppConstants.GOOGLE_CLIENT_ID)
            .add("client_secret", AppConstants.GOOGLE_CLIENT_SECRET)
            .add("redirect_uri",AppConstants.GOOGLE_REDIRECT_URI).add("code", code)
            .add("grant_type", AppConstants.GOOGLE_GRANT_TYPE).build())
        .execute().returnContent().asString();
      JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
      String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
      return accessToken;
  }
  public static GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
    String link = AppConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
    String response = Request.Get(link).execute().returnContent().asString();
    GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
    System.out.println(googlePojo);
    return googlePojo;
  }
}
