package com.example.projecte_uf1;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondGameDynamicFragment_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondGameDynamicFragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondGameDynamicFragment_1 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;

    private OnFragmentInteractionListener mListener;

    public SecondGameDynamicFragment_1() {

    }

    public static SecondGameDynamicFragment_1 newInstance(String param1, String param2) {
        SecondGameDynamicFragment_1 fragment = new SecondGameDynamicFragment_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_game_dynamic_fragment_1, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cb1 = getView().findViewById(R.id.f1cb1);
        cb2 = getView().findViewById(R.id.f1cb2);
        cb3 = getView().findViewById(R.id.f1cb3);
        cb4 = getView().findViewById(R.id.f1cb4);
        cb5 = getView().findViewById(R.id.f1cb5);

        final FrameLayout layout = (FrameLayout) getView().findViewById(R.id.fl1);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                float width  = layout.getMeasuredWidth();
                float height = layout.getMeasuredHeight();
                int animationDuration = 300;

                width = width - (width/100*8);
                height = height - (height/100*6);

                if(FragmentsComm.getLastFragmentDimensions() != null){
                    cb1.setX(FragmentsComm.getLastFragmentDimensions().get(0).getWidth());
                    cb1.setY(FragmentsComm.getLastFragmentDimensions().get(0).getHeight());
                    cb2.setX(FragmentsComm.getLastFragmentDimensions().get(1).getWidth());
                    cb2.setY(FragmentsComm.getLastFragmentDimensions().get(1).getHeight());
                    cb3.setX(FragmentsComm.getLastFragmentDimensions().get(2).getWidth());
                    cb3.setY(FragmentsComm.getLastFragmentDimensions().get(2).getHeight());
                    cb4.setX(FragmentsComm.getLastFragmentDimensions().get(3).getWidth());
                    cb4.setY(FragmentsComm.getLastFragmentDimensions().get(3).getHeight());
                    cb5.setX(FragmentsComm.getLastFragmentDimensions().get(4).getWidth());
                    cb5.setY(FragmentsComm.getLastFragmentDimensions().get(4).getHeight());
                }

                FragmentsComm.setWidthHeight(new Dimensions(width, height));
                Dimensions newDimensions = FragmentsComm.getRandomDimensions(new Dimensions(0,0));

                cb1.animate()
                        .x(newDimensions.getWidth())
                        .y(newDimensions.getHeight())
                        .setDuration(animationDuration)
                        .start();

                newDimensions = FragmentsComm.getRandomDimensions(new Dimensions(newDimensions.getWidth(), newDimensions.getHeight()));

                cb2.animate()
                        .x(newDimensions.getWidth())
                        .y(newDimensions.getHeight())
                        .setDuration(animationDuration)
                        .start();

                newDimensions = FragmentsComm.getRandomDimensions(new Dimensions(newDimensions.getWidth(), newDimensions.getHeight()));

                cb3.animate()
                        .x(newDimensions.getWidth())
                        .y(newDimensions.getHeight())
                        .setDuration(animationDuration)
                        .start();

                newDimensions = FragmentsComm.getRandomDimensions(new Dimensions(newDimensions.getWidth(), newDimensions.getHeight()));

                cb4.animate()
                        .x(newDimensions.getWidth())
                        .y(newDimensions.getHeight())
                        .setDuration(animationDuration)
                        .start();

                newDimensions = FragmentsComm.getRandomDimensions(new Dimensions(newDimensions.getWidth(), newDimensions.getHeight()));

                cb5.animate()
                        .x(newDimensions.getWidth())
                        .y(newDimensions.getHeight())
                        .setDuration(animationDuration)
                        .start();


            }
        });

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
