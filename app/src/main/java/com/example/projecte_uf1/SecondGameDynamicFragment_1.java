package com.example.projecte_uf1;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
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

import java.util.ArrayList;
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

    private FrameLayout fl;
    private ArrayList<CheckBox> cbList = new ArrayList<>();

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

        fl = getActivity().findViewById(R.id.fl1);

        // since the view loads after the initial code this waits until the view is generated to be able to get the measurements
        // and then animates the checkboxes, animating the checkboxes for the first time outside of this would crash it
        if(FragmentsComm.getDimensions() == null) {
            ViewTreeObserver observer = fl.getViewTreeObserver();
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    // this is what works best after some testing
                    float layout_width = fl.getWidth() - (fl.getWidth() / 100 * 8);
                    float layout_height = fl.getHeight() - (fl.getHeight() / 100 * 6);

                    FragmentsComm.setDimensions(new Dimensions(layout_width, layout_height));
                    createCheckBoxes();
                    checkboxesMotion();
                    fl.getViewTreeObserver().removeGlobalOnLayoutListener(
                            this);
                }
            });
        } else {
            checkboxesMotion();
        }
    }

//    public void checkboxesMotion(){
//
//        int animationDuration = Difficulty.getAnimationTime();
//
//        for (int i = 0; i < Difficulty.getCheckBoxNo(); i++) {
//            CheckBox cb = new CheckBox(getContext());
//            // this sets the checkbox width and height to wrap_content so its size is the space it actually takes up
//            cb.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//            // add checkbox to the frame layout otherwise it won't show
//            fl.addView(cb);
//            // set color
//            CompoundButtonCompat.setButtonTintList(cb, ContextCompat.getColorStateList(getContext(), R.color.colorSecondary));
//
//
//            cb.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ((ThirdActivity)getActivity()).checkBoxes(v);
//                }
//            });
//
//            // except for the first checkboxes this will get the positions where the previous fragment checkbox ended in and set this new one there
//            if(FragmentsComm.getLastFragmentDimensions() != null) {
//                cb.setX(FragmentsComm.getLastFragmentDimensions().get(i).getWidth());
//                cb.setY(FragmentsComm.getLastFragmentDimensions().get(i).getHeight());
//            }
//            cbList.add(cb);
//        }
//
//        for (int i = 0; i < cbList.size(); i++) {
//            Dimensions d = FragmentsComm.getRandomDimensions();
//
//            cbList.get(i).animate()
//                    .x(d.getWidth())
//                    .y(d.getHeight())
//                    .setDuration(animationDuration)
//                    .start();
//        }
//        cbList.clear();
//    }

    private void createCheckBoxes(){

        for (int i = 0; i < Difficulty.getCheckBoxNo(); i++) {
            CheckBox cb = new CheckBox(getContext());
            // this sets the checkbox width and height to wrap_content so its size is the space it actually takes up
            cb.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            // set color
            CompoundButtonCompat.setButtonTintList(cb, ContextCompat.getColorStateList(getContext(), R.color.colorSecondary));

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ThirdActivity)getActivity()).checkBoxes(v);
                }
            });

            cbList.add(cb);
        }
    }

    private void checkboxesMotion(){

        int animationDuration = Difficulty.getAnimationTime();

        // I wanted to do this all in one for but it doesn't work properly when setting the positions and animating in the same loop

        for (int i = 0; i < cbList.size(); i++) {
            // add checkbox to the frame layout otherwise it won't show
            fl.addView(cbList.get(i));

            // except for the first checkboxes this will get the positions where the previous fragment checkbox ended in and set this new one there
            if (FragmentsComm.getLastFragmentDimensions() != null) {
                cbList.get(i).setX(FragmentsComm.getLastFragmentDimensions().get(i).getWidth());
                cbList.get(i).setY(FragmentsComm.getLastFragmentDimensions().get(i).getHeight());
            }
        }

        for (int i = 0; i < cbList.size(); i++) {
            // get new dimensions accounted for collision and animate
            Dimensions d = FragmentsComm.getRandomDimensions();
            cbList.get(i).animate()
                    .x(d.getWidth())
                    .y(d.getHeight())
                    .setDuration(animationDuration)
                    .start();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // detach checkboxes from this frame layout, every time the fragment is created
        // the frame layout is technically different so I need to attach the views again
        // to do this I need them to not have any parent otherwise it won't let me
        fl.removeAllViews();
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
