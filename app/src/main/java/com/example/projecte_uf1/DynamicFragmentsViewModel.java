package com.example.projecte_uf1;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class DynamicFragmentsViewModel extends ViewModel {

    private Dimensions dimensions;

    public void setWidthHeight(Dimensions d){
        this.dimensions = d;
    }

    public Dimensions getRandomDimensions(Dimensions previousDimensions){

        Random rand = new Random();
        float dx = rand.nextFloat() * dimensions.getWidth();
        float dy = rand.nextFloat() * dimensions.getHeight();

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
    }

}
