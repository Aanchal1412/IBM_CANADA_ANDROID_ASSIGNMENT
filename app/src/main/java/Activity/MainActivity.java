package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rides.DataModel;
import com.example.rides.MyAPICall;
import com.example.rides.R;
import com.example.rides.Space;
import com.example.rides.VehiclesAdapter;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Fragments.VehicleDetailsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements VehiclesAdapter.ItemClickListener {

private Button btn1;
private EditText et1;
private String val;
private int val2;
VehiclesAdapter vehiclesAdapter;
VehiclesAdapter.ItemClickListener itemClickListener;
RecyclerView rv_vehicle;
FrameLayout vehicle_details_fragment;
ProgressBar progressBar;
Toolbar toolbar_main_activity;
SwipeRefreshLayout swipeRefreshLayout;

private List<DataModel> model = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_vehicle);
        et1 = findViewById(R.id.et1);
        rv_vehicle = findViewById(R.id.rv_vehicle);
        vehicle_details_fragment = findViewById(R.id.vehicle_details_fragment);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.sr_vehicles);
        toolbar_main_activity = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar_main_activity);
        getSupportActionBar().setTitle("Vehicle List");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        rv_vehicle.setLayoutManager(linearLayoutManager);
        rv_vehicle.addItemDecoration(new Space(20));
        et1.addTextChangedListener(textWatcher);

        btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               val = et1.getText().toString();

               if (val.isEmpty() == false) {
                   val2 = Integer.parseInt(val);
                   showProgress(progressBar);
                   ResponseDetails();
                   hideSoftKeyboard();
               }
           }
       });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgress(progressBar);
                ResponseDetails();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void ResponseDetails() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://random-data-api.com/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MyAPICall retrofitApiClient = retrofit.create(MyAPICall.class);

        Call<List<DataModel>> call = retrofitApiClient.responseModel(val2);

        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                try {

                    if (val2 >= 1 && val2 <= 100) {

                        Log.e("Success", "" + new Gson().toJson(response.body()));
                        model = response.body();
                        hideProgress(progressBar);

                        itemClickListener = new VehiclesAdapter.ItemClickListener() {
                            @Override
                            public void onItemClick(DataModel dataModel, int position) {

                                vehicle_details_fragment.setVisibility(View.VISIBLE);
                                toolbar_main_activity.setVisibility(View.GONE);

                                Fragment fragment = new VehicleDetailsFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("modeldetails", (Serializable) model);
                                bundle.putInt("Position",position);


                                fragment.setArguments(bundle);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.vehicle_details_fragment, fragment)
                                        .commit();
                            }
                        };

                        vehiclesAdapter = new VehiclesAdapter(getApplicationContext(), model, itemClickListener);
                        rv_vehicle.setAdapter(vehiclesAdapter);
                        vehiclesAdapter.notifyDataSetChanged();

                        Collections.sort(model, new Comparator<DataModel>() {
                            @Override
                            public int compare(DataModel t1, DataModel t2) {
                                return t1.getVin().compareTo(t2.getVin());
                            }
                        });

                        vehiclesAdapter.notifyDataSetChanged();
                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter the number ranging from 1-100", Toast.LENGTH_LONG).show();
                        hideProgress(progressBar);
                    }
                }

                catch (Exception e)
                {

                    Log.e("Error","" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Please Enter the number ranging from 1-100", Toast.LENGTH_LONG).show();
                hideProgress(progressBar);

                Log.e("Failure","" + t.getMessage());
            }
        });
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showProgress(ProgressBar progressBar) {
        if (progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public static void hideProgress(ProgressBar progressBar) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);

        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            val = et1.getText().toString();
            btn1.setEnabled(!val.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onItemClick(DataModel dataModel, int position) {

    }
}
