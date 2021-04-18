package com.example.tourismapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceDetailsFragment extends Fragment {

    ImageView fragmentImageView;
    TextView fragmentNameTextView;
    TextView fragmentDesTextView;
    FrameLayout frameLayout;
    private int image;
    private String name;
    private String des;


    public PlaceDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PlaceDetailsFragment newInstance(int image, String name, String des) {
        PlaceDetailsFragment fragment = new PlaceDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("IMAGE", image);
        args.putString("NAME", name);
        args.putString("DES", des);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt("IMAGE");
            name = getArguments().getString("NAME");
            des = getArguments().getString("DES");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_details, container, false);
        fragmentImageView = (ImageView) view.findViewById(R.id.fragmentImageView);
        fragmentNameTextView = (TextView) view.findViewById(R.id.fragmentNameTextView);
        fragmentDesTextView = (TextView) view.findViewById(R.id.fragmentDesTextView);

        fragmentImageView.setImageResource(image);
        fragmentNameTextView.setText(name);
        fragmentDesTextView.setText(des);
        return view;
    }
}
