package net.server.parallel;

import interfaces.Building;
import net.server.ServerSetting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket();

        while (!server.isClosed()) {
            Socket client = server.accept();
            System.out.println("connected");

            Thread thread = new Thread(() -> {
                try (ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());
                     ObjectInputStream reader = new ObjectInputStream(client.getInputStream())) {

                    Building[] buildings = (Building[]) reader.readObject();
                    Object[] result = ServerSetting.generateCosts(buildings);
                    writer.writeObject(result);
                    writer.flush();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        System.out.println("connections on serverSide closed.");
    }
}
