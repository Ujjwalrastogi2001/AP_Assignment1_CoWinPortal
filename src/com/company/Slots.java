package com.company;

public class Slots {
    private int day,quan;
    private String doseName;

    Slots(String dose,int d,int q){
        doseName=dose;
        day=d;
        quan=q;
    }

    //---------------------------------------------getValues------------------------------------------------------------
    public String getDoseName(){
        return doseName;
    }
    public int getDay(){
        return day;
    }
    public int getQuan(){
        return quan;
    }

    //------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------getValues------------------------------------------------------------
    public void setQuan(int q){
        quan=q;
    }
    //------------------------------------------------------------------------------------------------------------------

}
