package com.example.projecte_uf1;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class DynamicFragmentsViewModel extends ViewModel {

    private Dimensions dimensions;
    private Dimensions[] checkboxesPositions = new Dimensions[4];
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

        if(counter == 4){
            counter = 0;
        }
        checkboxesPositions[counter] = newDimensions;
        counter++;

        return newDimensions;
    }

    public Dimensions[] getLastFragmentDimensions(){
        if(checkboxesPositions.length != 0){
            return checkboxesPositions;
        } else {
            return null;
        }
    }

}
