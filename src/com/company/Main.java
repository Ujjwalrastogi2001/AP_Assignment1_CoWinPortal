package com.company;
import java.util.*;
import java.io.*;
public class Main {
    public static ArrayList<Hospital> hospital = new ArrayList<Hospital>();
    public static ArrayList<Person> people = new ArrayList<Person>();
    public static ArrayList<Vaccine> vac = new ArrayList<Vaccine>();
    public static int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        System.out.println("CoWin Portal initialized"+
                "\n ------------------------------");
        int option;
        System.out.println("1. Add Vaccine"
                +"\n2. Register Hospital"
                +"\n3. Register Citizen"
                +"\n4. Add Slot for Vaccination"
                +"\n5. Book Slot for Vaccination"
                +"\n6. List all slots for a hospital"
                +"\n7. Check Vaccination Status"
                +"\n8. Exit"
                +"\n9. personInfo"
                +"\n------------------------------------");
        String vacName;int noOfDose,gap;
        String hosName;int pin,hosId;
        String name;int age;String nameId;
        int slots,day,quantity,vacType;
        String id;
        while(true){
            System.out.println("{Menu Options}");
            option=Reader.nextInt();
            switch(option){
                case 1:

                    System.out.print("Vaccine Name:");
                    vacName=Reader.nextLine();
                    //System.out.println();
                    System.out.print("Number of Doses:");
                    noOfDose=Reader.nextInt();
                    //System.out.println();
                    gap=0;
                    if(noOfDose==1);
                    else {
                        System.out.print("Gap between Doses:");
                        gap = Reader.nextInt();
                    }
                     //   System.out.println();
                    Vaccine v=new Vaccine(vacName,noOfDose,gap);
                    vac.add(v);
                    System.out.println("Vaccine Name:"+v.vacName+", Number of Doses:"+v.dose+",Gap Between Doses:"+v.gap);
                    break;
                case 2:
                    System.out.print("Hospital Name:");
                    hosName=Reader.nextLine();
                    //System.out.println();
                    System.out.print("PinCode:");
                    pin=Reader.nextInt();
                    //System.out.println();
                    int num=getRandom(100000,999999);
                    int size=hospital.size();
                    while(true){
                        int i=0;
                        boolean flag=true;
                        while(flag && i<size){
                            if(num==hospital.get(i).uniqid){
                                flag=false;
                                break;
                            }
                            i++;
                        }
                        if(flag) break;
                        num=getRandom(100000,999999);
                    }
                    hosId=num;
                    Hospital hos=new Hospital(hosName,pin,hosId);
                    System.out.println("Hospital Name:"+hos.name+" Pincode:"+hos.pin+" Unique ID:"+hos.uniqid);
                    hospital.add(hos);
                    break;
                case 3:
                    System.out.print("Citizen Name:");
                    name=Reader.nextLine();
                    //System.out.println();
                    System.out.print("Age:");
                    age=Reader.nextInt();
                    //System.out.println();
                    System.out.print("Unique ID: ");
                    nameId=Reader.nextLine();
                    if(age<18){
                        System.out.println("Only above 18 are allowed");
                        break;
                    }
                    Person p=new Person(name,age,nameId);
                    people.add(p);
                    System.out.println("REGISTERED");
                    System.out.println("Citizen Name:"+p.name+",Age:"+p.age+", Unique ID: "+p.id);
                    break;
                case 4:
                    System.out.print("Enter Hospital ID: ");
                    hosId=Reader.nextInt();
                    System.out.print("Enter number of Slots to be added: ");
                    slots=Reader.nextInt();
                    while(slots--!=0) {
                        System.out.print("Enter Day Number:");
                        day = Reader.nextInt();
                        System.out.print("Enter Quantity:");
                        quantity = Reader.nextInt();
                        System.out.println("Select Vaccine");
                        for(int i=0;i<vac.size();i++){
                            System.out.println(i+". "+vac.get(i).vacName);
                        }
                        System.out.println();
                        vacType = Reader.nextInt();
                        String doseN=vac.get(vacType).vacName;
                        int totalDoseQuant=(-1);
                        for(int i=0;i<hospital.size();i++){
                            if(hospital.get(i).uniqid==hosId){
                                totalDoseQuant=hospital.get(i).slotAdd(doseN,day,quantity);
                            }
                        }
                        System.out.println("Slot added for Hospital "+hosId+" for Day:"+day+",Total Available Quantity:"+totalDoseQuant+" of vaccine "+doseN);
                        System.out.println();
                    }
                    break;
                case 5:
                    String slotBookNameId = null;
                    System.out.print("Enter patient Unique ID:");
                    slotBookNameId=Reader.nextLine();
                    int search=0;
                    System.out.println("1. Search by area\n" +
                            "2. Search by Vaccine\n" +
                            "3. Exit");
                    System.out.println();
                    System.out.print("Enter: ");
                    search=Reader.nextInt();
                    boolean isFullyVac=false;
                    boolean personPresent=false;
                    int nxtDoseDate=0;
                    int personIndex=-1;
                    for(int i=0;i<people.size();i++){
                        if(people.get(i).id.equals(slotBookNameId)) {
                            personPresent=true;
                            personIndex=i;
                            nxtDoseDate = people.get(i).nextDose;
                            if (people.get(i).doseTaken == people.get(i).doseReq) {
                                System.out.println("Already Fully Vaccinated");
                                isFullyVac = true;
                                break;
                            }
                            break;
                        }
                    }
                    if(isFullyVac) break;
                    switch(search){
                        case 1:
                            System.out.print("Enter PinCode: ");
                            int bookHosPin=Reader.nextInt();
                            int hosIndex=(-1);
                            for(int i=0;i<hospital.size();i++){
                                if(hospital.get(i).pin==bookHosPin){
                                    //hosIndex=i;
                                    System.out.println(hospital.get(i).uniqid+" "+hospital.get(i).name);
                                }
                            }
                            System.out.print("Enter Hospital Id:");
                            int hosIndexId=Reader.nextInt();
                            for(int i=0;i<hospital.size();i++){
                                if(hospital.get(i).uniqid==hosIndexId){
                                    hosIndex=i;
                                }
                            }
                            Hospital temp=hospital.get(hosIndex);
                            for(int i=0;i<temp.slot.size();i++){
                                Slots s=temp.slot.get(i);
                                System.out.println(i+"->Day: "+s.day+" Available Qty:"+s.quan+" Vaccine:"+s.doseName);
                            }
                            int slot=(-1);
                            System.out.print("Choose Slot:");
                            slot=Reader.nextInt();

                            if(people.get(personIndex).doseTaken>=1){
                                if(!(temp.slot.get(slot).doseName.equals(people.get(personIndex).doseName))){
                                    System.out.println("You can't mix vaccines.");
                                    break;
                                }
                            }

                            temp.slot.get(slot).quan--;
                            System.out.println();
                            boolean flag=true;
                            for(int i=0;i<people.size();i++){
                                if(people.get(i).id.equals(slotBookNameId)){
                                    System.out.println(people.get(i).name+"  Vaccinated with "+temp.slot.get(slot).doseName);
                                    flag=false;
                                }
                            }
                            if(temp.slot.get(slot).quan<=0){
                                temp.slot.remove(slot);
                            }
                            if(flag){
                                System.out.println("Invalid");
                            }else{
                                //----------------Updating Citizen Status---------------------------------
                                for(int i=0;i<people.size();i++){
                                    Person per=people.get(i);
                                    if(per.id.equals(slotBookNameId)){
                                        if(per.doseTaken==0) per.setVaccine(temp.slot.get(slot).doseName);
                                        per.doseDay=temp.slot.get(slot).day;
                                        per.nextDose=per.doseDay+per.reqGap;
                                        per.doseTaken++;
                                    }
                                }
                                //------------------------------------------------------------------------
                                System.out.println();
                            }
                            break;
                        case 2:
                            String bookvacname;
                            System.out.print("Enter Vaccine name: ");
                            bookvacname=Reader.nextLine();
                            for(int i=0;i<hospital.size();i++){
                                Hospital h=hospital.get(i);
                                for(int j=0;j<h.slot.size();j++){
                                    if(h.slot.get(j).doseName.equals(bookvacname)){
                                        System.out.println(h.uniqid+" "+h.name);
                                    }
                                }
                            }
                            if(people.get(personIndex).doseTaken>=1){
                                if(!(people.get(personIndex).doseName.equals(bookvacname))){
                                    System.out.println("You can't mix vaccines.");
                                    break;
                                }
                            }
                            int bookhosid;
                            System.out.print("Enter Hospital Id: ");
                            bookhosid=Reader.nextInt();
                            int count=0;
                            for(int i=0;i<hospital.size();i++) {
                                Hospital h = hospital.get(i);
                                if (h.uniqid == bookhosid) {
                                    for (int j = 0; j < h.slot.size(); j++) {
                                        if (h.slot.get(j).doseName.equals(bookvacname)) {
                                            if(people.get(personIndex).doseTaken==0) {
                                                //dosetaken==0;
                                                System.out.println(j + "->Day:" + h.slot.get(j).day + " Available Qty:" + h.slot.get(j).quan + " Vaccine:" + h.slot.get(j).doseName);
                                                count++;
                                            }
                                            else {
                                                if(h.slot.get(j).day==nxtDoseDate) {

                                                    System.out.println(j + "->Day:" + h.slot.get(j).day + " Available Qty:" + h.slot.get(j).quan + " Vaccine:" + h.slot.get(j).doseName);
                                                    count++;
                                                }
                                            }
                                            
                                        }
                                    }
                                    if(count==0){
                                        System.out.println("No slots available");
                                        break;
                                    }
                                    int slotbookviavac;
                                    System.out.print("Choose Slot:");
                                    slotbookviavac = Reader.nextInt();
                                    h.slot.get(slotbookviavac).quan--;
                                    boolean f = true;
                                    for (int j = 0; j < people.size(); j++) {
                                        if (people.get(j).id.equals(slotBookNameId)) {
                                            System.out.println(people.get(j).name + " Vaccinated with " + h.slot.get(slotbookviavac).doseName);
                                            f = false;
                                        }
                                    }
                                    if (h.slot.get(slotbookviavac).quan <= 0) {
                                        h.slot.remove(slotbookviavac);
                                    }
                                    if (f) {
                                        System.out.println("ID not yet registered TRY AGAIN / Invalid");
                                    } else {
                                        //----------------Updating Citizen Status---------------------------------
                                        for(int j=0;j<people.size();j++){
                                            Person per=people.get(j);
                                            if(per.id.equals(slotBookNameId)){
                                                if(per.doseTaken==0) per.setVaccine(h.slot.get(slotbookviavac).doseName);
                                                per.doseDay=h.slot.get(slotbookviavac).day;
                                                per.nextDose=per.doseDay+per.reqGap;
                                                per.doseTaken++;
                                            }
                                        }
                                        //------------------------------------------------------------------------
                                        System.out.println();
                                    }
                                    break;
                                }
                            }
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    System.out.print("Hospital Id");
                    int slotcheckhosid=Reader.nextInt();
                    for(int i=0;i<hospital.size();i++){
                        Hospital h=hospital.get(i);
                        if(hospital.get(i).uniqid==slotcheckhosid){
                            for(int j=0;j<h.slot.size();j++) {
                                System.out.println("Day:" + h.slot.get(j).day + " Available Qty:" + h.slot.get(j).quan + " Vaccine:" + h.slot.get(j).doseName);
                            }
                            break;
                        }
                    }
                    System.out.println();
                    break;

                case 7:
                    System.out.print("Enter Patient ID:");
                    id=Reader.nextLine();
                    int idIndex=-1;
                    for(int i=0;i<people.size();i++){
                        if(people.get(i).id.equals(id)){
                            idIndex=i;
                        }
                    }
                    Person pr=people.get(idIndex);
                    if(pr.doseTaken==0){
                        System.out.println("Citizen REGISTERED");
                    }else if(pr.doseTaken<pr.doseReq){
                        System.out.println("PARTIALLY VACCINATED");
                        System.out.print("Vaccine Given:"+pr.doseName);
                        System.out.println("Number of Doses given:"+pr.doseTaken);
                        System.out.println("Next Dose due date:"+(pr.doseDay+pr.reqGap));
                    }else{
                        System.out.println("FULLY VACCINATED");
                        System.out.print("Vaccine Given:"+pr.doseName);
                        System.out.println("Number of Doses given:"+pr.doseReq);
                    }
                    break;
                case 8:
                    System.out.println("{End of Test Case}");
                    System.out.println("ThankYou");
                    return;

                case 9:
                    System.out.println("Enter id:");
                    String sampleIdForCheck=Reader.nextLine();
                    for(int i=0;i<people.size();i++){
                        if(people.get(i).id.equals(sampleIdForCheck)){
                            people.get(i).printInfo();
                        }
                    }
            }
            System.out.println("-------------------------------------------");

        }
    }
}
class Reader {

    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }

}

