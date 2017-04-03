/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stripedtrip;

import java.util.List;

/**
 * Checkpoints for 1 vehicle
 * @author aleksandr
 */
public class CheckPoints {
    List<CheckPoint> checkPoints;
    int startX;
    int startY;
    
    
    
    public void add(CheckPoint checkPoint, int order){
        checkPoints.add(order, checkPoint);
    }
}
