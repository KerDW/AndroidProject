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

    FrameLayout fl;

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

        int animationDuration = Difficulty.getAnimationTime();

        for (int i = 0; i < Difficulty.getCheckBoxNo(); i++) {
            CheckBox cb = new CheckBox(getContext());
            fl.addView(cb);

            CompoundButtonCompat.setButtonTintList(cb, ContextCompat.getColorStateList(getContext(), R.color.colorSecondary));

            if(FragmentsComm.getLastFragmentDimensions() != null && FragmentsComm.getLastFragmentDimensions().size() == Difficulty.getCheckBoxNo()) {
                cb.setX(FragmentsComm.getLastFragmentDimensions().get(i).getWidth());
                cb.setY(FragmentsComm.getLastFragmentDimensions().get(i).getHeight());
            }

            Dimensions d = FragmentsComm.getRandomDimensions();

            cb.animate()
                    .x(d.getWidth())
                    .y(d.getHeight())
                    .setDuration(animationDuration)
                    .start();
        }
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
