package com.company;

import java.util.ArrayList;

public class Hospital {
    String name;
    int pin,uniqid;
    public ArrayList<Slots> slot = new ArrayList<Slots>();


    Hospital(String n,int pincode,int uniqueId){
        name=n;
        pin=pincode;
        uniqid=uniqueId;

    }
    public int slotAdd(String n,int d,int q){
        boolean flag=true;
        Slots temp = null;
        for(int i=0;i<slot.size();i++){
            temp=slot.get(i);
            if(temp.day==d && temp.doseName==n){
                flag=false;
                temp.quan+=q;
                break;
            }
        }
        if(!flag) return temp.quan;
        Slots s=new Slots(n,d,q);
        slot.add(s);
        return s.quan;
    }

}
