package com.example.adminhomescreen;

import android.media.Image;

public class DataBaseConnector {

    DataBaseConnector() {

    }

    public static boolean RegisterHopital(String hopitalName, String hopitalContactNumber, String hopitalEmail, String hopitalPhysicalAddress, String hopitalDescription, String Location) {
        //TODO : Fire Base connector : REgister Hopital Method
        return true;
    }
    public static boolean RegisterDocotr(String doctorName, String doctorContactNumber, String doctorEmail, String doctorPhysicalAddress, String city, Image image) {
        //TODO : Fire Base connector : REgister Doctor Method
        return true;
    }
    public static boolean RegisterPharmacy(String pharmacistName, String pharmacistContactNumber, String pharmacistEmail, String pharmacistPhysicalAddress, String city, Image image, String Description) {
        //TODO : Fire Base connector : REgister pharmacist Method
        return true;
    }

    public static boolean CreatNewHopital(String newCat)
    {
        //TODO: Fire base connector : Create new cat method.
        return  true;
    }
    public static String[] LoadCitiesNames()
    {
        //TODO: Fire base connector : Getcnames of cities

        return new String[]{"Larkana","Sukkur","Shikarpur"};
    }

    public static String[] LoadHospitalsNames()
    {
        //TODO: Fire base connector : Getcnames of cities

        return new String[]{"Hira","LIQ","GSF"};
    }
}
