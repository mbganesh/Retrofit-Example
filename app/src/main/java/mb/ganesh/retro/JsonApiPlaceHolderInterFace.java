package mb.ganesh.retro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApiPlaceHolderInterFace {

    @GET("posts")
    Call<List<PostModel>> getPostData();



}
