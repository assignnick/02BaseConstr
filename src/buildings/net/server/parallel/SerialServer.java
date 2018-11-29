package buildings.net.server.parallel;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SerialServer {
    private static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    private static class Server implements Runnable{
        private Socket clientSocket;

        public Server(Socket client) {
            this.clientSocket = client;
        }

        @Override
        public void run() {
            try {
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(new DataInputStream(clientSocket.getInputStream()));
                System.out.println("Server writing channel");

                while (!clientSocket.isClosed()) {
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
                clientSocket.close();
                System.out.println("Closing connections on serverSide");
            } catch (IOException | BuildingUnderArrestException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

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

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();

        while (!server.isClosed()) {
            Socket client = server.accept();
            System.out.print("Connection accepted.");

            executeIt.execute(new Server(client));
        }

        executeIt.shutdown();
        System.out.println("connections on serverSide closed.");
    }
}
