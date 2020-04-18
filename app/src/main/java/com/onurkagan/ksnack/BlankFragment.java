package com.onurkagan.ksnack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.onurkagan.ksnack_lib.Animations.Fade;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnack;
import com.onurkagan.ksnack_lib.MinimalKSnack.MinimalKSnackStyle;

public class BlankFragment extends Fragment {

    private Button          btnShow, btnDismiss;
    private MinimalKSnack   minimalKSnack;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize objects.
        minimalKSnack   = new MinimalKSnack(BlankFragment.this);
        btnShow     = getActivity().findViewById(R.id.fragment_main_btn_show);
        btnDismiss  = getActivity().findViewById(R.id.fragment_main_btn_dissmis);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Minimal KSnack
                minimalKSnack
                        .setMessage("This is minimal KSnack on Fragment !")
                        .setStyle(MinimalKSnackStyle.STYLE_SUCCESS)
                        .setBackgroundColor(R.color.colorGray)
                        .setBackgrounDrawable(R.drawable.background_minimal_snack)
                        .setAnimation(Fade.In.getAnimation(), Fade.Out.getAnimation())
                        .alignBottom()
                        .show();
            }
        });

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minimalKSnack.dismiss();
            }
        });
    }
}
