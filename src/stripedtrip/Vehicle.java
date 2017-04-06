/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stripedtrip;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aleksandr
 */
 
    
    public class Vehicle {
        boolean drive = false;
        // m/s - speed
        short speed = 0;
        int posX = 0;
        int posY = 0;
        int id=0;
        double time_stop = 0.0;
        List<CheckPoint> checkpoints;
        
        Vehicle(int id, int posX, int posY, int speed, boolean drive){
            this.id = id;
            this.posX = posX;
            this.posY = posY;
            this.speed = (short) speed;
            this.drive = drive;
            this.checkpoints = new ArrayList<CheckPoint>();
        }
        
        public boolean addCheckpoint(int order, CheckPoint checkPoint){
            try {
                if(this.checkpoints.size() == order){
                    System.out.println("Dobavlenie v konec spiska");
                    // Это первая после init точка
                    if(order == 0  ){
                        System.out.println("Add 1st cp:");
                             
                        int x1 = this.posX;
                        int x2 = checkPoint.x;
                        
                        int y1 = this.posY;
                        int y2 = checkPoint.y;
                        
                        double time = (Math.sqrt( (Math.pow((y2 - y1),2) +Math.pow(( x2-x1),2)) )/this.speed);
                        this.time_stop =  (checkPoint.t - time);
                        if(this.time_stop < 0){
                             System.out.println("fail. User input time less then calculated time.");
                             return false;
                        } else {
                            System.out.println("time_stop: "+this.time_stop);
                            this.checkpoints.add(order, checkPoint);
                            return true;
                        }
                        // Точка добавляеться уже после 1-й заданной точки, значит
                        // надо вычеслить относительное время из "от общего старта".
                    } else if (order>0) { 
                        System.out.println("Add next cp:");
                        
                        int x1 = this.checkpoints.get(order-1).x;
                        int x2 = checkPoint.x;
                            
                        int y1 = this.checkpoints.get(order-1).y;
                        int y2 = checkPoint.y;
                      
                        double time = (Math.sqrt( (Math.pow((y2 - y1),2) +Math.pow(( x2-x1),2)) )/this.speed);
                        
                        // Вычисляем time
                        int calc_time = this.checkpoints.get(order).t - this.checkpoints.get(order-1).t;
                        
                        this.checkpoints.get(order-1).stop_time = (int) (calc_time - time);
                        System.out.println("time_stop: "+this.time_stop);
                    }
                    
                    
                } else if (this.checkpoints.size()>order) {
                    System.out.println("Dobavlenie v seredinu spiska");
                } else {
                    System.out.println("fail. Please add points without spaces.");
                }
                 this.checkpoints.add(order, checkPoint);
                
                }
            catch(Exception e) {
                
                }
            return true;
        }
        

    }
   