package com.example.zoom.akeproject.Bundle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.zoom.akeproject.R;

public class BundleFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bundle,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText editText = getView().findViewById(R.id.bundle_edittext);
        Button button = getView().findViewById(R.id.bundle_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("bundle", text);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment directionFragment = new DirectionFragment();
                directionFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.main_view, directionFragment);
                fragmentTransaction.commit();
            }
        });
    }
}
