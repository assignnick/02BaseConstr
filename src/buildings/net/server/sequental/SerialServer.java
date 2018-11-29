package buildings.net.server.sequental;

import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import com.company.Buildings;
import exceptions.BuildingUnderArrestException;
import interfaces.Building;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/*
 * Создайте новые классы buildings.net.client.SerialClient,
 * buildings.net.server.sequental.SerialServer и buildings.net.server.parallel.SerialServer,
 * решающие ту же задачу, но отличающиеся по протоколу взаимодействия:
 * для передачи данных следует использовать сериализацию.
 * Данные о здании передаются в виде объекта, исключение передаётся
 * клиенту в виде объекта и т.д.
 */
public class SerialServer {
    private static boolean isArrested() {
        Random random = new Random();
        int res = random.nextInt(9);
        if(res > 0) return true;
        return false;
    }

    private static double value(String type, Building theBuilding) throws BuildingUnderArrestException {
        if (isArrested()) {
            throw new BuildingUnderArrestException("The building is under arrest");
        }

        double multiplier = 0;
        switch (type) {
            case "Hotel" : multiplier = 2000;
            case "OfficeBuilding": multiplier = 1500;
            case "Dwelling" : multiplier = 1000;
        }
        return theBuilding.getTotalSize() * multiplier;
    }

    public static void main(String[] args) throws IOException, BuildingUnderArrestException, ClassNotFoundException {
        ServerSocket server = new ServerSocket();
        Socket client = server.accept();
        System.out.print("Connection accepted.");

        ObjectInputStream in = new ObjectInputStream(new DataInputStream(client.getInputStream()));
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        System.out.println("Server writing channel & reading channel initialized.");

        while(!client.isClosed()) {
            System.out.println("Server reading from channel.");
            String t = in.readUTF();
            switch (t) {
                case "Hotel" : Buildings.setBuildingFactory(new HotelFactory());
                case "OfficeBuilding": Buildings.setBuildingFactory(new OfficeFactory());
                case "Dwelling" : Buildings.setBuildingFactory(new DwellingFactory());
            }
            Building theBuilding = (Building) in.readObject();
            System.out.println("Server writing to channel");
            out.writeDouble(value(t, theBuilding));
            out.flush();
            System.out.println("Server wrote message in channel.");
        }
        in.close();
        out.close();
        System.out.println("Closing connections on serverSide");
    }
}
