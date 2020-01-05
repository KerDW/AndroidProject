package com.example.projecte_uf1;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondGameDynamicFragment_2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondGameDynamicFragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondGameDynamicFragment_2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FrameLayout fl;
    private ArrayList<CheckBox> cbList = new ArrayList<>();
    private boolean checkboxesCreated;

    private OnFragmentInteractionListener mListener;

    public SecondGameDynamicFragment_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondGameDynamicFragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondGameDynamicFragment_2 newInstance(String param1, String param2) {
        SecondGameDynamicFragment_2 fragment = new SecondGameDynamicFragment_2();
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
        return inflater.inflate(R.layout.fragment_second_game_dynamic_fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fl = getActivity().findViewById(R.id.fl2);

        if(checkboxesCreated){
            checkboxesMotion();
        } else {
            createCheckBoxes();
            checkboxesMotion();
        }

    }

    private void createCheckBoxes(){

        for (int i = 0; i < Difficulty.getCheckBoxNo(); i++) {
            CheckBox cb = new CheckBox(getContext());
            // this sets the checkbox width and height to wrap_content so its size is the space it actually takes up
            cb.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            // set color
            CompoundButtonCompat.setButtonTintList(cb, ContextCompat.getColorStateList(getContext(), R.color.green));

            // set unique tag to identify it on the activity
            cb.setTag("f2cb"+i);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ThirdActivity)getActivity()).checkBoxes(v);
                }
            });

            cbList.add(cb);
        }
        checkboxesCreated = true;
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

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
