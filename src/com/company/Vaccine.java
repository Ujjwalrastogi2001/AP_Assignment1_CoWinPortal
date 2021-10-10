package com.company;

public class Vaccine {
    private String vacName;
    private int dose,gap;
    Vaccine(String name,int d,int g){
        vacName=name;
        dose=d;
        gap=g;
    }
    //---------------------------------------------getValues------------------------------------------------------------
    public String getVacName(){
        return vacName;
    }
    public int getDose(){
        return dose;
    }
    public int getGap(){
        return gap;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------No setValues required---------------------------------------------------

}
