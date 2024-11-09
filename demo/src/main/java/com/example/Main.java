package com.example;
import java.util.Date;
import java.io.*;
import java.nio.file.FileSystemException;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) throws IOException {
        try {
            makeRecord();
            System.out.println("Приложение работает.");
        }catch (FileSystemException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

}
public static void makeRecord() throws Exception {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите данные в формате: Фамилия Имя Отчество дата _ рождения номер _ телефона пол");
String input = scanner.nextLine();
scanner.close();

    String[] array = input.split(" ");
    if (array.length != 6) {
        throw new Exception("Колличество данных не совпадает.");
        }

        String surname = array[0];
        String name = array[1];
        String middleName = array[2];

        String dateOfBirthStr = array[3];
        DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
        try {
            Date dateOfBirth = formatter.parse(dateOfBirthStr);
        } catch (ParseException e) {
            throw new ParseException("Неверный формат даты рождения.", e.getErrorOffset());
        }
        
        

        String phoneStr = array[4];
        int phone;
        try {
            phone = Integer.parseInt(phoneStr);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Неверный формат телефона.");
        }

        String gender = array[5];
        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
            throw new RuntimeException("Неверный формат пола.");
        }

        String fileName = surname.toLowerCase() + ".txt";
        File file = new File(fileName);
        file.createNewFile();
        try (FileWriter fileWriter = new FileWriter(file, true)){
            
            fileWriter.write(String.format("\n %s %s %s %s %s %s", surname, name, middleName, phone, dateOfBirthStr, gender));
            fileWriter.close();
           
        } catch (IOException e) {
            throw new FileSystemException("Проблема работы с файлом.");
        }

    }
}