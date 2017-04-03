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
    List<Vehicle> closedPolygon = new ArrayList<Vehicle>();
    
    @Command // vehicle
    public String vehicle(int id, int x, int y, int v ){
        // Проверяем есть ли уже автомобиль с таким id
           List<Vehicle> result = closedPolygon.stream()
            .filter(item -> item.id == id)
            .collect(Collectors.toList());
            // Такой автомобиль уже есть
            if(result.size() > 0){
             return "fail";
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
        
        
        if(result.size() == 0){
            return "fail";
        }
        
      
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.x = x;
        checkPoint.y = y;
        // Здесь - расчёт времени
        checkPoint.t = time;
        result.get(0).addCheckpoint(order, checkPoint);
        
        
      } catch (Exception e){
          return "fail";
      }
        
        
        return "ok";
    }

    void stripedTrip(){
        closedPolygon = new ArrayList<Vehicle>();
        Vehicle testVehicle = new Vehicle(0, 0, 0, 100, true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ShellFactory.createConsoleShell("hello", "", new StripedTrip())
            .commandLoop(); // and three.
         
    } 
    
}
