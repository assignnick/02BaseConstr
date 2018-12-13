package net.server.sequental;

import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import com.company.Buildings;
import exceptions.BuildingUnderArrestException;
import interfaces.Building;
import net.server.ServerSetting;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/*
 * Создайте новые классы net.client.SerialClient,
 * net.server.sequental.SerialServer и net.server.parallel.SerialServer,
 * решающие ту же задачу, но отличающиеся по протоколу взаимодействия:
 * для передачи данных следует использовать сериализацию.
 * Данные о здании передаются в виде объекта, исключение передаётся
 * клиенту в виде объекта и т.д.
 */
public class SerialServer {

    public static void main(String[] args) throws IOException {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        ServerSocket server = new ServerSocket();
        Socket client = server.accept();
        System.out.print("Connection accepted.");


        System.out.println("Server writing channel & reading channel initialized.");

        while(!client.isClosed()) {
            System.out.println("Server reading from channel.");
            client = server.accept();
            System.out.println("connected");
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            Building[] buildings = null;

            try {
                buildings = (Building[]) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Building received");

            out.writeObject(ServerSetting.generateCosts(buildings));
            out.flush();
            in.close();
            out.close();
            System.out.println("Server wrote message in channel.");
        }
              System.out.println("Closing connections on serverSide");
    }
}
