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
                    // Слишком большое значение order - ошибка
                    if(order > this.checkpoints.size()){
                        System.out.println("error. order is too big.");
                        return false;
                    }
                    if(order == 0){
                        double cur_time = path_time(this.posX, checkPoint.x, this.posY, checkPoint.y, this.speed);
                        double wait_time = checkPoint.t - cur_time;
                        if(wait_time < 0) {
                            System.out.println("Stop_time must be bigger than driving min time.");
                            return false;
                        }
                            this.time_stop = wait_time;
                            this.checkpoints.add(order, checkPoint);
                            System.out.println("Time to wait: " + this.time_stop);
                    } else {
                        double cur_time = path_time(this.checkpoints.get(order-1).x, checkPoint.x,
                                                    this.checkpoints.get(order-1).y, checkPoint.y, this.speed);
                        double wait_time = checkPoint.t - cur_time;
                        if(wait_time < 0) {
                            System.out.println("Stop_time must be bigger than driving min time.");
                            return false;
                        }
                            this.checkpoints.get(order-1).stop_time = wait_time;
                            this.checkpoints.add(order, checkPoint);              
                            System.out.println("Time to wait: " + this.checkpoints.get(order-1).stop_time);
                    }
                    // Добавление в середину
                    if(order!=(this.checkpoints.size()-1)){
                        double cur_time = path_time(this.checkpoints.get(order).x, this.checkpoints.get(order+1).x,
                                                    this.checkpoints.get(order).y, this.checkpoints.get(order+1).y, this.speed);
                        // TODO - перепроверить
                        this.checkpoints.get(order).stop_time = this.checkpoints.get(order+1).stop_time - cur_time;
                        System.out.println("Time to wait for current checkpoint: " + this.checkpoints.get(order).stop_time);
                    }
                }
            catch(Exception e) {
                System.out.println(e.toString());
                return false;
                }
            return true;
        }
        

    }
   
