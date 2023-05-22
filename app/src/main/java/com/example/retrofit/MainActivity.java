package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

        textView = findViewById(R.id.textView);

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        MyApiCall myApiCall = retrofit.create(MyApiCall.class);
        Call<DataModel> call = myApiCall.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                Log.d("RES",  response.toString());

                //checking for response
                if (response.code()!=200){
                    textView.setText("Check the connection");
                    return;
                }

                //get the data in textview
                String jsonData = "";
                jsonData = "userId= " + response.body().getUserId() +
                        "\nID= " + response.body().getId() +
                        "\ntitle= " + response.body().getTitle() +
                        "\ncompleted= " + response.body().isCompleted();

                textView.append(jsonData);


            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });



    }
}