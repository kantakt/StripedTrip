/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stripedtrip;

/**
 *
 * @author aleksandr
 */
public class CheckPoint {
    int x, y;
    int order;
    // secs. from global start
    int t;
   void CheckPoint(int x, int y, int order){
       this.x = x;
       this.y = y;
       this.order = order;
   } 
}
