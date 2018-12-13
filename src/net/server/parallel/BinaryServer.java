package net.server.parallel;

import interfaces.Building;
import net.server.ServerSetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class BinaryServer {


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        System.out.println("Connection accepted.");
        while (!server.isClosed()) {
            Socket client = server.accept();
            System.out.println("Client connected");
            Thread thread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter writer = new PrintWriter(client.getOutputStream())) {
                    Building[] buildings = ServerSetting.readBuildingsWithTypes(reader);
                    ServerSetting.writeCosts(buildings, writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        System.out.println("connections on serverSide closed.");
    }

}
