package buildings.net.client;

import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import com.company.Buildings;
import interfaces.Building;

import java.io.*;
import java.net.Socket;

/*
        Реализуйте клиентскую часть приложения в новом классе buildings.net.client.BinaryClient,содержащем метод main().
        Задайте имена трех файлов.
        Первый файл существует на момент запуска программы и содержит
        в текстовом виде информацию о зданиях(например,одна строка – одно здание).
        Второй файл существует на момент запуска программы и содержит в текстовом виде
        информацию о видах зданий(например,одна строка – одно слово,определяющее вид здания).
        Количество записей в первом и втором файле можно считать соответствующим друг другу,
        но неизвестным заранее(т.е.оно даже не записано в первой строке файлов,но точно не одно здание).
        Файлы можно считать корректными.
        Третий файл должен быть создан программой в ходе работы и должен хранить
        в текстовом виде оценки стоимости зданий(например,одна строчка – одна оценка стоимости).
        Программа должна установить через сокеты соединение с сервером,после чего считывать из первого
         и второго файла данные о здании,передавать их в бинарной форме серверу и получать от него результат
          оценки стоимости здания,и так по очереди для всех исходных данных.
        Для обеспечения работы приложения потребуется определение протокола взаимодействия клиентской
         и серверной частей:порядка передачи данных,определения условия завершения передачи данных.
        Считывание данных из первого файла,а также запись данных в поток сокета рекомендуется реализовать
         с помощью средств класса Buildings.Также рекомендуется реализовать вывод информации о текущей операции
          в консоль(например,с помощью ранее реализованных методов toString()зданий).
*/


public class BinaryClient {
    public static void main(String[] args) throws IOException, InterruptedException {


        Socket socket = new Socket();
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
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
            Buildings.writeBuilding(building, writer);
            writer.flush();
            System.out.println("Client sent message to server.");
            Thread.sleep(100);
            buildingCosts.println(reader.read());
            System.out.println("Client read message from server and wrote it in the file.");
        }

//        buildingCosts.close();
//        writer.close();
//        reader.close();
//        buildingInfo.close();
//        buildingType.close();
        System.out.println("Closing connections on clentSide");
    }
}
