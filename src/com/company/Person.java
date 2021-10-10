package com.company;

public class Person {
    String name;
    int age;
    String id;
    int doseTaken;
    String doseName;
    int doseDay;
    int doseReq;
    int reqGap;
    int nextDose;
    boolean flag=true;
    Person(String n,int a,String uid){
        name=n;
        age=a;
        id=uid;
        doseTaken=0;
        doseDay=0;
        doseReq=-1;
    }

    public void setVaccine(String n){
        doseName=n;
        if(flag){
            for(int i=0;i<Main.vac.size();i++){
                Vaccine v=Main.vac.get(i);
                if(v.vacName.equals(doseName)){
                    doseReq=v.dose;
                    reqGap=v.gap;
                }
            }
        }
    }
    public void printInfo(){
        System.out.println("name: "+name);
        System.out.println("age: "+age);
        System.out.println("doseTaken: "+doseTaken);
        System.out.println("doseDay"+ doseDay);
        System.out.println("doseReq:" + doseReq);
        System.out.println("reqGap:" + reqGap);
        System.out.println("nextDose"+ nextDose);

    }

}
