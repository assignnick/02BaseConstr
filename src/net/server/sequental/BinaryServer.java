package net.server.sequental;

import interfaces.Building;
import net.server.ServerSetting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/*
Реализуйте серверную часть приложения в новом классе net.server.sequental.BinaryServer,содержащем метод main().
Сервер должен выполнять последовательную обработку запросов в соответствии с созданным ранее протоколом
общения клиентской и серверной части.
Оценка стоимости здания считается как сумма всех площадей помещений здания, умноженная на 1000$ для жилого дома,
на 1500$ для офиса и на 2000$ для любой гостиницы.
Процедуру оценки стоимости здания следует реализовать в виде отдельного метода класса сервера.
При считывании данных из потока рекомендуется использовать методы класса Buildings, включая механизм настройки фабрик.


Включите в начало метода оценки стоимости проверку на то, не наложен ли на здание арест,
и реализуйте в классе сервера метод проверки ареста здания. В рамках учебного задания
для простоты будем считать, что метод проверки использует датчик случайных чисел и в
среднем сообщает об аресте здания в 10% случаев.
Метод оценки стоимости в случае ареста здания должен выбросить объявляемое
исключение BuildingUnderArrestException (также опишите класс этого исключения).
Клиенту же в таком случае вместо оценки стоимости следует отправить сообщение об аресте здания.
 */

public class BinaryServer {


    public static void main(String[] args)  {
        ServerSocket server = null;
        try {
            server = new ServerSocket(4004);

        Socket client = null;

        System.out.print("Connection accepted");

        while (!server.isClosed()) {
            client = server.accept();
            System.out.println("Client connected");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter writer = new PrintWriter(client.getOutputStream())) {
                Building[] buildings = ServerSetting.readBuildingsWithTypes(reader);
                ServerSetting.writeCosts(buildings, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Closing connections on serverSide");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

