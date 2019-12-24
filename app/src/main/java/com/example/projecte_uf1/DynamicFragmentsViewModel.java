package com.example.projecte_uf1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class DynamicFragmentsViewModel extends ViewModel {

    private Dimensions dimensions;
    private MutableLiveData<ArrayList<Dimensions>> checkboxesPositions = new MutableLiveData<ArrayList<Dimensions>>();
    private int counter = 0;

    public void setWidthHeight(Dimensions d){
        this.dimensions = d;
    }

    public Dimensions getRandomDimensions(Dimensions previousDimensions){

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
        }
        checkboxesPositions.getValue().add(newDimensions);
        counter++;

        return newDimensions;
    }

    public MutableLiveData<ArrayList<Dimensions>> getLastFragmentDimensions(){
        if(checkboxesPositions.getValue().size() != 0){
            return checkboxesPositions;
        } else {
            return null;
        }
    }

}
