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
    Toolbar toolbar_fragment;
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
        toolbar_fragment = view.findViewById(R.id.toolbar_fragment);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar_fragment);
         toolbar_fragment.setTitle("Vehicle Details");

        Bundle bundle = getArguments();
        dataModels = (List<DataModel>) bundle.getSerializable("modeldetails");
        position = bundle.getInt("Position");
        vin = dataModels.get(position).getVin();
        make_and_model = dataModels.get(position).getMakeAndModel();
        color = dataModels.get(position).getColor();
        car_type = dataModels.get(position).getCarType();

        tv_fetch_vin.setText("Vin:  " + vin);
        tv_fetch_model.setText("Make_and_Model:  " + make_and_model);
        tv_fetch_color.setText("Color:  " + color);
        tv_fetch_car_type.setText("Car_Type:  " + car_type);
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