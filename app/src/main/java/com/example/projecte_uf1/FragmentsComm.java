package com.example.projecte_uf1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class FragmentsComm {

    private static Dimensions dimensions;
    private static boolean dimensionsSet = false;
    private static ArrayList<Dimensions> checkboxesPositions = new ArrayList<>();
    private static int counter = 0;

    public static void setWidthHeight(Dimensions d){
        dimensions = d;
        dimensionsSet = true;
    }

    public static boolean areDimensionsSet(){
        return dimensionsSet;
    }

    public static Dimensions getRandomDimensions(Dimensions previousDimensions){

        Random rand = new Random();

        float dx;
        float dy;
        float separation = 10;

        do {
            dx = rand.nextFloat() * dimensions.getWidth();
            dy = rand.nextFloat() * dimensions.getHeight();
        } while((dx >= previousDimensions.getWidth()-separation && dx <= previousDimensions.getWidth()+separation) || (dy >= previousDimensions.getHeight()-separation && dy <= previousDimensions.getHeight()+separation));

        Dimensions newDimensions = new Dimensions(dx, dy);

        if(counter == 5){
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
