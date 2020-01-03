package com.example.projecte_uf1;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Random;

public class FragmentsComm {

    // I got these values using a viewtreeobserver on the fragments to get measurements, then adjusted them
    private static Dimensions dimensions = new Dimensions(992.68f, 1515.28f);

    private static ArrayList<Dimensions> checkboxesPositions = new ArrayList<>();
    private static int counter = 0;

    public static Dimensions getRandomDimensions(){

        Random rand = new Random();

        float dx;
        float dy;
        // 50 is just enough so they don't collide
        float separation = 50;
        boolean areDistanced;

        // this prevents checkboxes overlapping between them
        do {
            areDistanced = true;
            dx = rand.nextFloat() * dimensions.getWidth();
            dy = rand.nextFloat() * dimensions.getHeight();
            for (int i = 0; i < checkboxesPositions.size(); i++) {
                if((dx >= checkboxesPositions.get(i).getWidth()-separation && dx <= checkboxesPositions.get(i).getWidth()+separation) && (dy >= checkboxesPositions.get(i).getHeight()-separation && dy <= checkboxesPositions.get(i).getHeight()+separation)){
                    Log.e("xd", "collision avoided");
                    areDistanced = false;
                    break;
                }
            }
        } while(!areDistanced);

        Dimensions newDimensions = new Dimensions(dx, dy);

        if(counter == Difficulty.getCheckBoxNo()){
            counter = 0;
            checkboxesPositions.clear();
        }
        checkboxesPositions.add(newDimensions);
        counter++;

        return newDimensions;
    }

    public static ArrayList<Dimensions> getLastFragmentDimensions(){

        if(!checkboxesPositions.isEmpty()){
            return checkboxesPositions;
        } else {
            return null;
        }
    }

}
