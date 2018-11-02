package com.example.shadabkhan.healthcare;

import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<String> list=new ArrayList<String>();
    List<place>  places = new ArrayList<place>();
    String syncquery;
    place markerobject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SearchView sv = (SearchView) findViewById(R.id.searchbar);
        final ListView lv= (ListView) findViewById(R.id.list_view);
        lv.setBackgroundColor(Color.WHITE);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MapsActivity.super.getApplicationContext(),
                android.R.layout.simple_list_item_1,
                list);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                markerobject = new place();
                markerobject = places.get(position);
                list.clear();
                lv.setAdapter(arrayAdapter);
                places.clear();
                setloc(mMap);
            }
        });



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                syncquery = query;
                if(query.equals(""))
                {
                    list.clear();
                    lv.setAdapter(arrayAdapter);
                    return true;
                }
                list.clear();
                lv.setAdapter(arrayAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                syncquery = query;
                if(query.equals(""))
                {
                    list.clear();
                    lv.setAdapter(arrayAdapter);
                    return true;
                }
                list.clear();
                lv.setAdapter(arrayAdapter);

                list= getdata(query);

                lv.setAdapter(arrayAdapter);
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void setloc(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.clear();
        // Add a marker in delhi and move the camera
        LatLng marker = new LatLng( Double.parseDouble(markerobject.longitude),Double.parseDouble(markerobject.latitude));
        mMap.addMarker(new MarkerOptions().position(marker).title("Marker in "+markerobject.getPlace_name()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,10));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        // Add a marker in delhi and move the camera
        LatLng marker = new LatLng( 28.7041,77.1025);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,10));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker));
    }

    synchronized public List<String> getdata(String quer){

        places.clear();

        final String query = quer.toLowerCase();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReferenceFromUrl("https://healthcare-1540558185483.firebaseio.com");


        Query q1 = myRef.orderByChild("place name").startAt(query).endAt(query+'\uf8ff').limitToFirst(50);

        /*q1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                place tempplace = new place(dataSnapshot.child("admin name1").getValue().toString(),
                        dataSnapshot.child("country code").getValue().toString(),
                        dataSnapshot.child("longitude").getValue().toString(),
                        dataSnapshot.child("latitude").getValue().toString(),
                        dataSnapshot.child("malaria cause").getValue().toString(),
                        dataSnapshot.child("malaria deaths").getValue().toString(),
                        dataSnapshot.child("malaria risk factor").getValue().toString(),
                        dataSnapshot.child("malaria risk index").getValue().toString(),
                        dataSnapshot.child("malnutrition cause").getValue().toString(),
                        dataSnapshot.child("malnutrition deaths").getValue().toString(),
                        dataSnapshot.child("malnutrition risk factor").getValue().toString(),
                        dataSnapshot.child("malnutrition risk index").getValue().toString(),
                        dataSnapshot.child("place name").getValue().toString(),
                        dataSnapshot.child("population").getValue().toString(),
                        dataSnapshot.child("postal code").getValue().toString(),
                        dataSnapshot.child("tuberculosis cause").getValue().toString(),
                        dataSnapshot.child("tuberculosis deaths").getValue().toString(),
                        dataSnapshot.child("tuberculosis risk factor").getValue().toString(),
                        dataSnapshot.child("tuberculosis risk index").getValue().toString()
                );
                places.add(tempplace);
                list.add(dataSnapshot.child("place name").getValue().toString()+","+dataSnapshot.child("admin name1").getValue().toString());

                if(!query.equals(syncquery))
                {
                    places.clear();
                    list.clear();
                    return;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        q1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(!query.equals(syncquery))
                {
                    places.clear();
                    list.clear();
                    return;
                }
               place tempplace = new place(dataSnapshot.child("admin name1").getValue().toString(),
                        dataSnapshot.child("country code").getValue().toString(),
                        dataSnapshot.child("longitude").getValue().toString(),
                        dataSnapshot.child("latitude").getValue().toString(),
                        dataSnapshot.child("malaria cause").getValue().toString(),
                        dataSnapshot.child("malaria deaths").getValue().toString(),
                        dataSnapshot.child("malaria risk factor").getValue().toString(),
                        dataSnapshot.child("malaria risk index").getValue().toString(),
                        dataSnapshot.child("malnutrition cause").getValue().toString(),
                        dataSnapshot.child("malnutrition deaths").getValue().toString(),
                        dataSnapshot.child("malnutrition risk factor").getValue().toString(),
                        dataSnapshot.child("malnutrition risk index").getValue().toString(),
                        dataSnapshot.child("place name").getValue().toString(),
                        dataSnapshot.child("population").getValue().toString(),
                        dataSnapshot.child("postal code").getValue().toString(),
                        dataSnapshot.child("tuberculosis cause").getValue().toString(),
                        dataSnapshot.child("tuberculosis deaths").getValue().toString(),
                        dataSnapshot.child("tuberculosis risk factor").getValue().toString(),
                        dataSnapshot.child("tuberculosis risk index").getValue().toString()
                        );
                places.add(tempplace);
                list.add(dataSnapshot.child("place name").getValue().toString()+","+dataSnapshot.child("admin name1").getValue().toString()
                );
                final ListView lv= (ListView) findViewById(R.id.list_view);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MapsActivity.super.getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        list);
                lv.setAdapter(arrayAdapter);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }

        );


        return list;
    }
}
