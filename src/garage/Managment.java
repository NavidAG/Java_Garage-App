/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.ListView;


/**
 *
 * @author navid
 */
public class Managment {
    
    
    
          
    public double carProfit=0;
    public double busProfit=0;
    public double truckProfit=0;
    public double motorProfit=0;

    public double getCarProfit() {
        return carProfit;
    }

    public double getBusProfit() {
        return busProfit;
    }

    public double getTruckProfit() {
        return truckProfit;
    }

    public double getMotorProfit() {
        return motorProfit;
    }
    
    public static List<TransformationVehicle> carsInfo() throws FileNotFoundException, IOException{
        List<TransformationVehicle> vehicles = new ArrayList<TransformationVehicle>();
        File file = new File("src/Data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int i=0;
        String[] info;
        
        while ((line = reader.readLine()) != null) {
            info = line.split(" ");
            TransformationVehicle vehicle;
            if(info[0].equals("car"))
                vehicle = new Car();
            else if(info[0].equals("bus"))
                vehicle = new Bus();
            else if(info[0].equals("motorcycle"))
                vehicle = new MotorCycle();
            else if(info[0].equals("truck"))
                vehicle = new Truck();
            else break;
            vehicle.setModelName(info[1]);
            vehicle.setBrand(info[2]);
            vehicle.setYear(Integer.parseInt(info[3]));
            vehicle.setTechnicalSpecs(info[4]);
            vehicle.setPrice(Double.parseDouble(info[5]));
            
            vehicles.add(i,vehicle);
            
            i++;
        }
        return vehicles;
    }
    
    public void remove(TransformationVehicle vehicle) throws IOException{
        List<TransformationVehicle> vehicles = carsInfo();
        vehicles.remove(vehicle);
        File inputFile = new File("src/data.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String delete = null;
        String currentLine;
        System.out.println(vehicle.getClass().getName());
        if(vehicle.getClass().getName().equals("garage.Car")){
            System.out.println("car deleted");
            delete = "car" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
        }
        else if(vehicle.getClass().getName().equals("garage.Bus")){
            delete = "bus" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
            System.out.println("Bus deleted");
        }
        else if(vehicle.getClass().getName().equals("garage.Truck")){
            delete = "truck" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
            System.out.println("truck deleted");
        }
        else if(vehicle.getClass().getName().equals("garage.MotorCycle")){
            System.out.println("motor deleted");
            delete = "motorcycle" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
        }
        System.out.println(delete);
        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(delete)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        
        writer.close(); 
        reader.close(); 
        boolean successful = tempFile.renameTo(inputFile);
        
    }
    
    public static void add(String kind, String brand, String modelName, String year, String describtion, String price) throws IOException{
        
        BufferedWriter  writer = new BufferedWriter(new FileWriter("src/data.txt", true));
        String info;
        info = kind + " " + modelName + " " + brand + " " + year + " " + describtion + " " + price;
        writer.append(info);
        writer.newLine();
        writer.close();
        
    }
    
    public static List<String> carsList() throws FileNotFoundException, IOException{
        List<String> vehicles = new ArrayList<String>();
        File file = new File("src/Data.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int i=0;
        String[] info;
        
        while ((line = reader.readLine()) != null) {
            info = line.split(" ");
            String vehicle = info[2] + " " + info[1];
            
            
            
            vehicles.add(i,vehicle);
            
            i++;
        }
        return vehicles;
    }
    public static ArrayList<String> stringer(List<TransformationVehicle> vehicles){
        ArrayList<String> stringsOfCars = new ArrayList<String>();
        for(int i=0; i<vehicles.size(); i++){
            stringsOfCars.add(i, vehicles.get(i).getBrand() + " " + vehicles.get(i).getModelName());
        }
        return stringsOfCars;
    }
    
    public void garageData(String fileName) throws IOException{
        createANewFile(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        
        
        
        
    }
    public static int id=0;

    public void rent(TransformationVehicle vehicle, String person, double kindOfRent) throws IOException{
        id++;
        BufferedWriter  writer = new BufferedWriter(new FileWriter("src/rents/rent.txt", true));
        String data = null;
        System.out.println(vehicle.getClass().getName());
        
        if(vehicle.getClass().getName().equals("garage.Car")){
            System.out.println("car ");
            data = "car" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice()*kindOfRent);
        }
        else if(vehicle.getClass().getName().equals("garage.Bus")){
            data = "bus" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice()*kindOfRent);
            //System.out.println("Bus ");
        }
        else if(vehicle.getClass().getName().equals("garage.Truck")){
            data = "truck" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice()*kindOfRent);
            //System.out.println("truck ");
        }
        else if(vehicle.getClass().getName().equals("garage.MotorCycle")){
            System.out.println("motor ");
            data = "motorcycle" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice()*kindOfRent);
        }
        data += " " + person + " " + id;
        writer.append(data);
        writer.newLine();
        writer.close();
        
    }
    
    public void profit() throws FileNotFoundException, IOException{
        File file = new File("src/rents/rent.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String[] info;
        carProfit = 0;
        busProfit = 0;
        motorProfit = 0;
        truckProfit = 0;
        while ((line = reader.readLine()) != null) {
            info = line.split(" ");
            
            if(info[0].equals("car"))
                carProfit += Integer.parseInt(info[5]);
            else if(info[0].equals("bus"))
                busProfit += Integer.parseInt(info[5]);
            else if(info[0].equals("motorcycle"))
                motorProfit += Integer.parseInt(info[5]);
            else if(info[0].equals("truck"))
                truckProfit += Integer.parseInt(info[5]);
            else break;
        }
    }
    
    public List<String> rentInfo() throws FileNotFoundException, IOException{
        List<String> vehicles = new ArrayList<>();
        File file = new File("src/Rents/rent.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int i=0;
        String[] info;
        
        while ((line = reader.readLine()) != null) {
            
            vehicles.add(line);
            i++;
        }
        return vehicles;
    }
    
    public static void createANewFile(String fileName){
        File file = new File("src/" + fileName + ".txt");
 
        //Create the file
        try {
            if (file.createNewFile()) {
                System.out.println("New Text File is created!");
            } else {
                System.out.println("File already exists.");
            }
            //Write Content
            FileWriter writer = new FileWriter(file);
            
            writer.write("Test data");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
