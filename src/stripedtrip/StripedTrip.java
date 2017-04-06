/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stripedtrip;


import asg.cliche.Command;
import asg.cliche.ShellFactory;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author aleksandr
 */
public class StripedTrip {
    List<Vehicle> closedPolygon = new ArrayList<>();
    int N = 2147483647;
    int M = 2147483647;
    ////
    
    @Command // vehicle
    public String vehicle(int id, int x, int y, int v ){
        if(x < 0 || x > N || y < 0 || y > M){
            return "fail - vehicle not in bounds";
        }
        // Проверяем есть ли уже автомобиль с таким id
           List<Vehicle> result = closedPolygon.stream()
            .filter(item -> item.id == id)
            .collect(Collectors.toList());
            // Такой автомобиль уже есть
            if(result.size() > 0){
             return "fail - there is a vehicle with the same id";
            } else {
             // Нет создаём новый
             Vehicle newVehicle = new Vehicle(id, x,y, v, true);
             closedPolygon.add(newVehicle);
             return "ok";   
            }
    }
    
    @Command // checkpoint
    public String checkpoint(int veh_id, int order, int time, int x, int y){
      try{ 
        List<Vehicle> result = closedPolygon.stream()
        .filter(item -> item.id == veh_id)
        .collect(Collectors.toList());
        
        if(result.isEmpty()){
            return "fail";
        }
      
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.x = x;
        checkPoint.y = y;
        checkPoint.t = time;
        // Нажимаем добавить cp - в ней расчёт времени остановки
        if(result.get(0).addCheckpoint(order, checkPoint))
            return "ok";
        else 
            return "fail"; 
      } catch (Exception e){
          return "fail";
      }
    }

    void stripedTrip(){
        closedPolygon = new ArrayList<Vehicle>();
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ShellFactory.createConsoleShell("StripedTrip", "", new StripedTrip())
            .commandLoop(); // and three.
         
    } 
    
}
