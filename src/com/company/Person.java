package com.company;

public class Person {
    private String name;
    private int age;
    private String id;
    private int doseTaken;
    private String doseName;
    private int doseDay;
    private int doseReq;
    private int reqGap;
    private int nextDose;
    boolean flag=true;
    Person(String n,int a,String uid){
        name=n;
        age=a;
        id=uid;
        doseTaken=0;
        doseDay=0;
        doseReq=-1;
    }
    //-------------------------------------getValues-------------------------------------------------------------------
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getDoseName(){
        return doseName;
    }
    public int getAge(){
        return age;
    }
    public int getDoseTaken(){
        return doseTaken;
    }
    public int getDoseDay(){
        return doseDay;
    }
    public int getDoseReq(){
        return doseReq;
    }
    public int getReqGap(){
        return reqGap;
    }
    public int getNextDose(){
        return nextDose;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------Set_Values--------------------------------------------------------------

    public void setName(String n){
        name=n;
    }
    public void setId(String id_){
        id=id_;
    }
    public void setDoseName(String dn){
        doseName=dn;
    }
    public void setAge(int a){
        age=a;
    }
    public void setDoseTaken(int dt){
        doseTaken=dt;
    }
    public void setDoseDay(int dd){
        doseDay=dd;
    }
    public void setDoseReq(int dr){
        doseReq=dr;
    }
    public void setReqGap(int rg){
        reqGap=rg;
    }
    public void setNextDose(int nd){
        nextDose=nd;
    }

    public void setVaccine(String n){
        doseName=n;
        if(flag){
            for(int i=0;i<Main.vac.size();i++){
                Vaccine v=Main.vac.get(i);
                if(v.getVacName().equals(doseName)){
                    doseReq=v.getDose();
                    reqGap=v.getGap();
                }
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------


}
