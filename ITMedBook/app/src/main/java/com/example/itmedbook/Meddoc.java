package com.example.itmedbook;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class Meddoc {
    String hospital = new String();
    String typeRecord  = new String();
    String appeal  = new String();
    String doctor   = new String();
    Meddoc(String hospital,String typeRecord, String appeal, String doctor){
        this.hospital = hospital;
        this.typeRecord  = typeRecord;
        this.appeal  = appeal;
        this.doctor   = doctor;
    }

    String getHospital(){
        return this.hospital;
    }
    String getTypeRecord(){
        return this.typeRecord;
    }
    String getAppeal(){
        return this.appeal;
    }
    String getDoctor(){
        return this.doctor;
    }
}
