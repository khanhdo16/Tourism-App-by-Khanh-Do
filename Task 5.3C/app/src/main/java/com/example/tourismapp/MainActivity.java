package com.example.tourismapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements PlaceAdapter.OnClickListener,TopAdapter.OnClickListener {
    RecyclerView topRecyclerView;
    RecyclerView placesRecyclerView;
    TopAdapter topAdapter;
    PlaceAdapter placeAdapter;
    List<Place> placeList = new ArrayList<>();
    List<Place> topList = new ArrayList<>();
    FrameLayout frameLayout;

    Integer[] imageList = {
        R.drawable.ngv,
        R.drawable.flinder,
        R.drawable.botanicgarden,
        R.drawable.queenvicmarket,
        R.drawable.melbournezoo,
        R.drawable.eurekaskydeck,
        R.drawable.puffingbilly
    };
    String[] nameList = {
        "National Gallery of Victoria",
        "Flinder Street Station",
        "Royal Botanic Garden",
        "Queen Victoria Market",
        "Melbourne Zoo",
        "Eureka Skydeck",
        "Puffing Billy Railway"
    };
    String[] desList = {
        "The National Gallery of Victoria, popularly known as the NGV, is an art museum in Melbourne, Victoria, Australia. Founded in 1861, it is Australia's oldest, largest and most visited art museum. The NGV houses an encyclopedic art collection across two sites: NGV International, located on St Kilda Road in the Melbourne Arts Precinct of Southbank, and the Ian Potter Centre: NGV Australia, located nearby at Federation Square. (Wikipedia)",
        "Flinders Street railway station is located on the corner of Flinders and Swanston streets in the central business district of Melbourne, Victoria, Australia. Opened in 1854, the historic station serves the entire metropolitan rail network, as well as some country services to eastern Victoria. Backing onto the Yarra River in the heart of the city, the complex includes platforms and structures that stretch over more than two whole city blocks, from east of Swanston Street nearly to Market Street. (Wikipedia)",
        "Royal Botanic Gardens Victoria are botanic gardens across two sites - Melbourne and Cranbourne. Melbourne Gardens was founded in 1846 when land was reserved on the south side of the Yarra River for a new botanic garden. It extends across 38 hectares that slope to the river with trees, garden beds, lakes and lawns. It displays almost 50,000 individual plants representing 8,500 different species.",
        "The Queen Victoria Market is a major landmark in the central business district of Melbourne, Victoria, Australia. Covering over seven hectares, it is the largest open air market in the Southern Hemisphere. (Wikipedia)",
        "Melbourne Zoo is a zoo in Melbourne, Australia. It is located within Royal Park in Parkville, approximately 4 kilometres north of the centre of Melbourne. It is the primary zoo serving Melbourne. The zoo contains more than 320 animal species from Australia and around the world, and is accessible via Royal Park station on the Upfield railway line, and is also accessible via tram routes 58 and 19, as well as by bicycle on the Capital City Trail. (Wikipedia)",
        "Eureka Tower is a 297.3 m skyscraper located in the Southbank precinct of Melbourne, Victoria, Australia. Construction began in August 2002 and the exterior was completed on 1 June 2006. The plaza was finished in June 2006 and the building was officially opened on 11 October 2006. The project was designed by Melbourne architectural firm Fender Katsalidis Architects and was built by Grocon. (Wikipedia)",
        "The Puffing Billy Railway is a 2 ft 6 in narrow gauge heritage railway in the Dandenong Ranges in Melbourne, Australia. The primary starting point, operations and administration centre, main refreshment room and ticket purchasing are located at Belgrave station. Journeys may also be commenced at out-stations of which some have limited facilities for the purchase of tickets, refreshments and souvenirs. Tickets may also be purchased from the conductor before boarding the train. (Wikipedia)"
    };
    String[] topPlaces = {"Flinder Street Station", "Royal Botanic Garden", "Melbourne Zoo"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topRecyclerView = findViewById(R.id.topRecyclerView);
        placesRecyclerView = findViewById(R.id.placesRecyclerView);
        frameLayout = findViewById(R.id.frameLayout);

        for(int i = 0; i < imageList.length; i++) {
            Place place = new Place(i, imageList[i], nameList[i], desList[i]);
            placeList.add(place);
        }
        for(int i = 0; i < topPlaces.length; i++) {
            for(int x = 0; x < placeList.size(); x++) {
                if(topPlaces[i].equals(placeList.get(x).getName())) {
                    topList.add(placeList.get(x));
                }
            }
        }

        topAdapter = new TopAdapter(topList, MainActivity.this, this);
        topRecyclerView.setAdapter(topAdapter);
        RecyclerView.LayoutManager topLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topRecyclerView.setLayoutManager(topLayoutManager);

        placeAdapter = new PlaceAdapter(placeList, MainActivity.this, (PlaceAdapter.OnClickListener) this);
        placesRecyclerView.setAdapter(placeAdapter);
        RecyclerView.LayoutManager placeLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        placesRecyclerView.setLayoutManager(placeLayoutManager);
    }

    @Override
    public void onItemClick(int id) {
        if(id >= 0 && id < placeList.size()) {
            Fragment fragment;
            Place place = placeList.get(id);
            fragment = PlaceDetailsFragment.newInstance(place.getImage(), place.getName(), place.getDescription());

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
            frameLayout.setVisibility(VISIBLE);
        }
        else {
            throw new IllegalArgumentException("Unexpected value: " + id);
        }
    }

    public void onBackPressed() {
        if(frameLayout.getVisibility() != GONE) frameLayout.setVisibility(GONE);
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) getSupportFragmentManager().popBackStack();
    }
}
