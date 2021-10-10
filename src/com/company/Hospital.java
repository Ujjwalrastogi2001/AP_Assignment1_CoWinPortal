package com.company;

import java.util.ArrayList;

public class Hospital {
    private String name;
    private int pin,uniqid;
    public ArrayList<Slots> slot = new ArrayList<Slots>();
    Hospital(String n,int pincode,int uniqueId){
        name=n;
        pin=pincode;
        uniqid=uniqueId;

    }
    public String getName(){
        return name;
    }
    public int getPin(){
        return pin;
    }
    public int getUniqid(){
        return uniqid;
    }
    public int slotAdd(String n,int d,int q){
        boolean flag=true;
        Slots temp = null;
        for(int i=0;i<slot.size();i++){
            temp=slot.get(i);
            if(temp.getDay()==d && temp.getDoseName()==n){
                flag=false;
                temp.setQuan(temp.getQuan()+q);
                break;
            }
        }
        if(!flag) return temp.getQuan();
        Slots s=new Slots(n,d,q);
        slot.add(s);
        return s.getQuan();
    }

}
