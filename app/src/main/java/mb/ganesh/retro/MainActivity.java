package mb.ganesh.retro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewId);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiPlaceHolderInterFace  placeHolderInterFace = retrofit.create(JsonApiPlaceHolderInterFace.class);

        Call<List<PostModel>> call = placeHolderInterFace.getPostData();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code : " + response.code());
                    return;
                }

                List<PostModel> model = response.body();
                for (PostModel post : model){
                    String content = "";
                    content += "Id : " + post.getId() + "\n" ;
                    content += "UserId : " + post.getUserId() + "\n" ;
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Body : " + post.getText() + "\n\n";

                    textView.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                    textView.setText("Message : "+t.getMessage());
            }
        });
    }
}