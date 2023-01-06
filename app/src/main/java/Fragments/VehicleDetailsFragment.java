package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import Activity.MainActivity;

import com.example.rides.DataModel;
import com.example.rides.R;

import java.util.List;


public class VehicleDetailsFragment extends Fragment {

    private String vin,make_and_model,color,car_type;
    int kilometrage,remaining_kilometrage;
    double carbon_emission = 0;
    double remaining_carbon_emission = 0.0;
    double total_carbon_emission = 0.0;
    Toolbar toolbar_fragment;
    Button btn_carbon_emission;
    LinearLayout ll_carbon_emission;
    List<DataModel> dataModels;
    int position;

    public VehicleDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView tv_fetch_vin,tv_fetch_model,tv_fetch_color,tv_fetch_car_type,tv_kilometres,tv_carbon_emission;

        View view = inflater.inflate(R.layout.fragment_vehicle_details, container, false);

        tv_fetch_vin = view.findViewById(R.id.tv_fetch_vin);
        tv_fetch_model = view.findViewById(R.id.tv_fetch_model);
        tv_fetch_color = view.findViewById(R.id.tv_fetch_color);
        tv_fetch_car_type = view.findViewById(R.id.tv_fetch_car_type);
        tv_kilometres = view.findViewById(R.id.tv_kilometres);
        tv_carbon_emission = view.findViewById(R.id.tv_carbon_emission);
        btn_carbon_emission = view.findViewById(R.id.btn_carbon_emission);
        toolbar_fragment = view.findViewById(R.id.toolbar_fragment);
        ll_carbon_emission = view.findViewById(R.id.ll_carbon_emission);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar_fragment);
         toolbar_fragment.setTitle("Vehicle Details");

        Bundle bundle = getArguments();
        dataModels = (List<DataModel>) bundle.getSerializable("modeldetails");
        position = bundle.getInt("Position");
        vin = dataModels.get(position).getVin();
        make_and_model = dataModels.get(position).getMakeAndModel();
        color = dataModels.get(position).getColor();
        car_type = dataModels.get(position).getCarType();
        kilometrage = dataModels.get(position).getKilometrage();


        btn_carbon_emission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_carbon_emission.setVisibility(View.VISIBLE);
            }
        });

        if(kilometrage > 5000) {
            carbon_emission = carbon_emission + (1 * 5000);
            remaining_kilometrage = kilometrage - 5000;
            remaining_carbon_emission = remaining_carbon_emission + (1.5 * remaining_kilometrage);
            total_carbon_emission = carbon_emission + remaining_carbon_emission;
        }

        else if(kilometrage <= 5000)
        {
            carbon_emission = carbon_emission + (1 * kilometrage);
            total_carbon_emission = carbon_emission;
        }

        tv_fetch_vin.setText("Vin:  " + vin);
        tv_fetch_model.setText("Make_and_Model:  " + make_and_model);
        tv_fetch_color.setText("Color:  " + color);
        tv_fetch_car_type.setText("Car_Type:  " + car_type);
        tv_kilometres.setText("" + kilometrage);
        tv_carbon_emission.setText("" + total_carbon_emission);
        setupOnBackPressed();

        return view;

    }

    private void setupOnBackPressed() {

        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(isEnabled())
                {
                    Toast.makeText(requireContext(), "Go Back", Toast.LENGTH_SHORT).show();
                    setEnabled(false);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().onBackPressed();
                }
            }
        });

    }
}