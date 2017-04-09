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
        
        public double path_time(double x1, double x2, double y1, double y2, double speed){
             return Math.sqrt( (Math.pow((y2 - y1),2) +Math.pow(( x2-x1),2)) )/speed;
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
                        
                        double time = path_time(x1, x2, y1, y2, this.speed);
                        
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
                      
                        double time = path_time(x1, x2, y1, y2, this.speed);
                     
                        // Вычисляем time
                        int calc_time = this.checkpoints.get(order).t - this.checkpoints.get(order-1).t;
                        
                        
                        double tmp_time = this.checkpoints.get(order-1).stop_time;
                        this.checkpoints.get(order-1).stop_time =  (calc_time - time);
                        if(this.checkpoints.get(order-1).stop_time<0){
                            //this.checkpoints.remove(order-1);
                            // Если данные введены не корректно, то возвращаем прежнее значение.
                            this.checkpoints.get(order-1).stop_time = tmp_time;
                            System.out.println("fail. Checkpoint\'s stop_time: must be highter then 0");
                            return false;
                         } 
                        this.checkpoints.add(order, checkPoint);
                        
                        System.out.println("time_stop: "+this.checkpoints.get(order-1).stop_time);
                    }
                    
                    // You need to calculate 1 more current point
                    if((this.checkpoints.size()-1) > order){
                        System.out.println("size(): "+this.checkpoints.size() + " order: "+ order);
                        System.out.println("Current calculation ..."); 
                        // Добавляем проверку на "превышение" времени от общего старта, если посредине время
                        // больше чем у след. точки                     
                        if(checkPoint.t > this.checkpoints.get(order+1).t){
                            System.out.println("Fail. You must enter time values in a sequence order");
                            return false;
                        }
                        int x2 = this.checkpoints.get(order+1).x;
                        int x1 = this.checkpoints.get(order).x;
                        
                        int y2 = this.checkpoints.get(order+1).y;
                        int y1 = this.checkpoints.get(order).y;
                        
                        double time = path_time(x1, x2, y1, y2, this.speed);
                       
                        double calc_time = this.checkpoints.get(order+1).t - checkPoint.t ;

                        if(calc_time<time){
                            System.out.println("fail. User input time less then calculated time.");
                            return false;
                        }
                        this.checkpoints.get(order).stop_time = calc_time-time;
                        System.out.println("Current stop_time: "+this.checkpoints.get(order).stop_time);
                        return true;
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
   
