package com.company;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import interfaces.*;

public class Buildings {

   //записи данных о здании в байтовый поток
    public static void outputBuilding (Building building, OutputStream out){

    }
    //чтения данных о здании из байтового потока
    public static Building inputBuilding (InputStream in){
        return null;
    }
 	//записи здания в символьный поток
    public static void writeBuilding (Building building, Writer out){

    }
	//чтения здания из символьного потока
    public static Building readBuilding (Reader in){
    return null;
    }

    //сериализации здания в байтовый поток
    public static void serializeBuilding (Building building, OutputStream out){

    }
    //десериализации здания из байтового потока
    public static Building deserializeBuilding (InputStream in){
        return null;
    }


}
