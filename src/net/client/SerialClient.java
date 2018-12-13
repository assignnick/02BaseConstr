package net.client;

import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import com.company.Buildings;
import interfaces.Building;

import java.io.*;
import java.net.Socket;

public class SerialClient {
    public static void main (String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        BufferedReader buildingInfo = new BufferedReader(new FileReader("buildinginfo.txt"));
        BufferedReader buildingType = new BufferedReader(new FileReader("buildingtypes.txt"));
        PrintWriter buildingCosts = new PrintWriter(new FileWriter(new File("buildingcosts.txt")));


        System.out.println("Client connected to socket.");

        String str;
        while ((str = buildingType.readLine()) != null) {

            System.out.println("Client start reading info about building");

            switch (str) {
                case "Hotel":
                    Buildings.setBuildingFactory(new HotelFactory());
                case "OfficeBuilding":
                    Buildings.setBuildingFactory(new OfficeFactory());
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
            }
            Building building = Buildings.readBuilding(buildingInfo);

            out.writeObject(building);
            out.flush();
            System.out.println("Client sent message to server.");
            Thread.sleep(100);
            buildingCosts.println(reader.read());
            System.out.println("Client read message from server and wrote it in the file.");
        }

        buildingCosts.close();
        writer.close();
        reader.close();
        buildingInfo.close();
        buildingType.close();
        System.out.println("Closing connections on clentSide");
    }
}
