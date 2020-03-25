package fr.esilv2020.moneyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mainScreen extends AppCompatActivity {

    private static final String TAG = "mainScreen";
    private Button btnHome;
    private TextView globalStats;
    private ImageView covImg;
    private ListView countriesStats;
    private EditText searchCountries;
    private ArrayList<String> data = new ArrayList<>();
    private List<ListCountries> countriesList;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Log.d(TAG, "onCreate: Started");
        btnHome = (Button) findViewById(R.id.infohome);
        globalStats = (TextView) findViewById(R.id.globalStats);
        covImg = (ImageView) findViewById(R.id.covImg);
        countriesStats = (ListView) findViewById(R.id.countryStats);
        searchCountries = (EditText) findViewById(R.id.searchCountry);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main);
                finish();
            }
        });
        int imageResource = getResources().getIdentifier("@drawable/covid19", null, this.getPackageName());
        covImg.setImageResource(imageResource);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coronavirus-19-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        TotalApi totalApi = retrofit.create(TotalApi.class);
        Call<Total> callTotal = totalApi.getTotal();

        callTotal.enqueue(new Callback<Total>() {
            @Override
            public void onResponse(Call<Total> call, Response<Total> response) {
                if (!response.isSuccessful()){
                    globalStats.setText("Code: " + response.code() + "\n\n");
                    return;
                }
                Total posts = response.body();

                String content = "";
                content += "Cases: " + posts.getCases() + "\n";
                content += "Deaths: " + posts.getDeaths() + "\n";
                content += "Recovered: " + posts.getRecovered() + "\n\n\n";
                Log.d(TAG, "OnCreateStarted "+content);
                globalStats.setText(content);

            }

            @Override
            public void onFailure(Call<Total> call, Throwable t) {
                globalStats.append(t.getMessage() + "\n\n");
            }
        });



        ListApi listApi = retrofit.create(ListApi.class);
        Call<List<ListCountries>> callList = listApi.getList();


        callList.enqueue(new Callback<List<ListCountries>>() {
            @Override
            public void onResponse(Call<List<ListCountries>> call, Response<List<ListCountries>> response) {
                if (!response.isSuccessful()){
                    globalStats.setText("Code: " + response.code());
                    return;
                }
                countriesList = response.body();

                for (ListCountries post : countriesList){
                    String content = "";
                    content += "Country: " + post.getCountry() + "\n\n";
                    content += "Cases:               " + post.getCases() + "\n";
                    content += "Today cases:   " + post.getTodayCases() + "\n";
                    content += "Deaths:             " + post.getDeaths() + "\n";
                    content += "Today deaths: " + post.getTodayDeaths() + "\n";
                    content += "Active:              " + post.getActive() + "\n";
                    content += "Critical:             " + post.getCritical() + "\n";
                    content += "Case per one million: " + post.getCasesPerOneMillion() + "\n\n\n";

                    data.add(content);
                    listViewModifs(data);
                }

            }

            @Override
            public void onFailure(Call<List<ListCountries>> call, Throwable t) {
                globalStats.setText(t.getMessage());
            }
        });
    }

    private void listViewModifs(ArrayList<String> data ){
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        countriesStats.setAdapter(adapter);

        searchCountries.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (mainScreen.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
