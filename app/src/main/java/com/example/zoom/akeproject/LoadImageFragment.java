package com.example.zoom.akeproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by adobebeta on 10/12/2018 AD.
 */

public class LoadImageFragment extends Fragment{

    String url;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loadimage,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getView().findViewById(R.id.image_view);


        initOKBtnPressed();

//        String url = "https://codinginflow.com/wp-content/uploads/2018/04/fragment-newinstance.jpg";


    }

    private void initOKBtnPressed() {

        Button okBtn = getView().findViewById(R.id.confirmBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText urlField = getView().findViewById(R.id.urlField);
                url = urlField.getText().toString();
                Picasso.with(getActivity()).load(url).resize(200,200).centerCrop().into(imageView);

            }
        });


    }
}
