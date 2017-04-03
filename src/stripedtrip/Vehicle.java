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
        List<CheckPoint> checkpoints;
        
        CheckPoints checkPoints = null;
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
                 this.checkpoints.add(order, checkPoint);
                 this.recalculate();
                }
            catch(Exception e) {
                
                }
            return true;
        }
        
        public void recalculate(){
            // Расчёт первого отрезка
            int wayX = this.checkpoints.get(0).x - this.posX;
            int wayY = this.checkpoints.get(0).y - this.posY;
            int totalWay =  (int) Math.sqrt(wayX*wayX + wayY*wayY);
            
            // Имееться более 1-й точки
            if(this.checkpoints.size()>1){
                for (CheckPoint checkPoint : this.checkpoints) {
                 wayX = checkPoint.x - this.posX;
                 wayY = checkPoint.y - this.posY;
                 int totalWay =  (int) Math.sqrt(wayX*wayX + wayY*wayY);
                }
            }
        }
    }
   