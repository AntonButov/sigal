package pro.butovanton.sigal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface JSONPlaceHolderApi {
    @Headers({"Authorization: Bearer "+ "270c912889785a9f743ef8b6e4410bb9"})
    @GET("/api/v1.0/shops")
    public Call<JsonArray> getAllPosts();

    @GET("")
    Call<ResponseBody> downloadFlag(@Url String fileUrl);
}
