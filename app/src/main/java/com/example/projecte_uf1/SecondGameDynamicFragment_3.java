package com.example.projecte_uf1;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondGameDynamicFragment_3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondGameDynamicFragment_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondGameDynamicFragment_3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;

    private OnFragmentInteractionListener mListener;

    public SecondGameDynamicFragment_3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondGameDynamicFragment_3.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondGameDynamicFragment_3 newInstance(String param1, String param2) {
        SecondGameDynamicFragment_3 fragment = new SecondGameDynamicFragment_3();
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
        return inflater.inflate(R.layout.fragment_second_game_dynamic_fragment_3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cb1 = getView().findViewById(R.id.f3cb1);
        cb2 = getView().findViewById(R.id.f3cb2);
        cb3 = getView().findViewById(R.id.f3cb3);
        cb4 = getView().findViewById(R.id.f3cb4);
        cb5 = getView().findViewById(R.id.f3cb5);

        final FrameLayout layout = (FrameLayout) getView().findViewById(R.id.fl3);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                int width  = layout.getMeasuredWidth();
                int height = layout.getMeasuredHeight();

                width = width - (width/100*8);
                height = height - (height/100*6);

                Random rand = new Random();
                float dx = rand.nextFloat() * width;
                float dy = rand.nextFloat() * height;

//                Log.e("xd", "xd"+width);
//                Log.e("xd", "xd"+height);

                cb1.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();

                dx = rand.nextFloat() * width;
                dy = rand.nextFloat() * height;

                cb2.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();

                dx = rand.nextFloat() * width;
                dy = rand.nextFloat() * height;

                cb3.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();

                dx = rand.nextFloat() * width;
                dy = rand.nextFloat() * height;

                cb4.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();

                dx = rand.nextFloat() * width;
                dy = rand.nextFloat() * height;

                cb5.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();
            }
        });

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
