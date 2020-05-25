/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garage;

import static garage.Managment.carsInfo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author navid
 */
public class Garage {
    
    
    
    private String garageName;
    private int staff;
    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }
    
    
    public void garageData(TransformationVehicle vehicle, String garageName) throws IOException{
        System.out.println(garageName + "??");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/" + garageName + ".txt", true));
        
        String data = null;
        System.out.println(vehicle.getClass().getName());
        if(vehicle.getClass().getName().equals("garage.Car")){
            System.out.println("car ");
            data = "car" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
        }
        else if(vehicle.getClass().getName().equals("garage.Bus")){
            data = "bus" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
            //System.out.println("Bus ");
        }
        else if(vehicle.getClass().getName().equals("garage.Truck")){
            data = "truck" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
            //System.out.println("truck ");
        }
        else if(vehicle.getClass().getName().equals("garage.MotorCycle")){
            System.out.println("motor ");
            data = "motorcycle" + " " + vehicle.getModelName() + " " + vehicle.getBrand() + " " + vehicle.getYear() + " " + vehicle.getTechnicalSpecs() + " " + String.format("%.0f", vehicle.getPrice());
        }
        writer.append(data);
        writer.newLine();
        writer.close();
    }
    
    public static boolean removeGarage(String fileName){
        File file = new File("src/" + fileName + ".txt");
        if(file.delete()){
            System.out.println("file.txt File deleted from Project root directory");
            return true;
        }else {
            System.out.println("File file.txt doesn't exist in the project root directory");
            return false;
        }
    }
    
    public boolean createANewFile(String fileName){
        File file = new File("src/" + fileName + ".txt");
 
        //Create the file
        try {
            if (file.createNewFile()) {
                System.out.println("New Text File is created!");
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
            //Write Content
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    
    
    public static List<TransformationVehicle> carsInfo(String fileName) throws FileNotFoundException, IOException{
        List<TransformationVehicle> vehicles = new ArrayList<TransformationVehicle>();
        File file = new File("src/" + fileName + ".txt");
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
    
    public static List<String> carsList(String fileName) throws FileNotFoundException, IOException{
        List<String> vehicles = new ArrayList<String>();
        File file = new File("src/" + fileName + ".txt");
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
    
    public void remove(TransformationVehicle vehicle, String garageName) throws IOException{
        List<TransformationVehicle> vehicles = carsInfo(garageName);
        vehicles.remove(vehicle);
        File inputFile = new File("src/" + garageName + ".txt");
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
    
    public Garage(String garageName, int staff) {
        this.garageName = garageName;
        this.staff = staff;
        
    }
    
    
    
}
