package com.example.ito5046_assignment3.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ito5046_assignment3.MainActivity;
import com.example.ito5046_assignment3.R;
import com.example.ito5046_assignment3.RegisterActivity;
import com.example.ito5046_assignment3.databinding.ActivityRegisterBinding;
import com.example.ito5046_assignment3.databinding.HomeFragmentBinding;
import com.example.ito5046_assignment3.databinding.MapFragmentBinding;
import com.example.ito5046_assignment3.entity.User;
import com.example.ito5046_assignment3.viewmodel.UserViewModel;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class MapFragment extends Fragment {

    private MapFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MapFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        final Point point = Point.fromLngLat(145.045837, -37.876823 );
        CameraOptions position = new CameraOptions.Builder()
                .zoom(13.0)
                .center(point)
                .build();
        binding.mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
        binding.mapView.getMapboxMap().setCamera(position);
        AnnotationPlugin annotationApi = AnnotationPluginImplKt.getAnnotations(binding.mapView);
        PointAnnotationManager pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationApi, new AnnotationConfig());

        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_marker);

        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(bitmap);

        pointAnnotationManager.create(pointAnnotationOptions);

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
