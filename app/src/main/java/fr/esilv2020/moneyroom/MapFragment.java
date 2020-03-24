package fr.esilv2020.moneyroom;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapFragment extends Fragment implements LocationListener {

    private static final int PERMS_CALL_ID = 123;
    private LocationManager lm;
    private MapFragment mapFragment;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        FragmentManager fragmentManager = getFragmentManager();
       mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        //mapFragment.getMap
        return v;
    }


    @Override
    public void onPause() {
        super.onPause();

        if(lm !=null){
            lm.removeUpdates(this);
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        checkPermissions();

    }

    private void checkPermissions()
    {
        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }




        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000,0, this);
        }
        if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,0, this);
        }
        if(googleMap==null){
            Toast.makeText(getActivity(), "null",Toast.LENGTH_SHORT).show();
        }

       // googleMap.moveCamera(CameraUpdateFactory.zoomBy(100));
        //map(googleMap);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
       double latitude = location.getLatitude();
       double longitude = location.getLongitude();
        Toast.makeText(getActivity(), "Location: "+latitude+ " | "+longitude,Toast.LENGTH_SHORT).show();
        if( googleMap != null){
            LatLng latLng = new LatLng(latitude,longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }




        public void map(GoogleMap googleMap)
        {
            //mapFragment.getMap
            googleMap.moveCamera(CameraUpdateFactory.zoomBy(100));
            //googleMap.setMyLocationEnabled(true);
            //googleMap.addMarker(new MarkerOptions().position(new LatLng( 43.999,52.000)).title("Salut"));
        }


}
