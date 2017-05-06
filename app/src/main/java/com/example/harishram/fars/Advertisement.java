package com.example.harishram.fars;

/**
 * Created by Harish Ram on 25-04-2017.
 */

public class Advertisement {
    String Name;
    String AdvertisementID;
    String Date;
    String AdType;
    String ApType;
    String Location;
    String Gender;
    String Utilities;
    String Availability;
    int Rent_Share;
    String Date_av;
    String status;
    String Description;
    String comment;
    String reason;
    Advertisement(){

    }
    Advertisement(String Name,String AdvertisementID,String Date,String AdType,String ApType,String Location,String Gender,String Utilities,String Availability,int Rent_Share,String Date_av,String status,String Description,String reason,String comment){
        this.Name=Name;
        this.AdvertisementID=AdvertisementID;
        this.Date = Date;
        this.AdType=AdType;
        this.ApType=ApType;
        this.Location=Location;
        this.Gender=Gender;
        this.Utilities=Utilities;
        this.Availability=Availability;
        this.Rent_Share=Rent_Share;
        this.Date_av=Date_av;
        this.status = status;
        this.Description = Description;
        this.comment = comment;
        this.reason = reason;
    }
}
