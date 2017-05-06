package com.example.harishram.fars;

public class Student {
    int Stid;
    String NetID;
    String Name;
    String email;
    String password;
    String Sq;
    String Sa;
    Long pn;
    String status;
    String type;
    Student(){

    }
    Student(int Stid, String NetID, String Name, String email, String password, String Sq, String Sa, Long pn, String status,String type){
        this.Stid= Stid;
        this.NetID=NetID;
        this.Name= Name;
        this.email=email;
        this.password=password;
        this.Sq=Sq;
        this.Sa=Sa;
        this.pn=pn;
        this.status=status;
        this.type = type;
    }
}
