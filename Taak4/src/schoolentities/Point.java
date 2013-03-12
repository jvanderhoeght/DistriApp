/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolentities;

/**
 *
 * @author Jan
 */
public class Point {
    
    private int points;
    private int max;
    private Test test;
    
    public Point(Test t, int max, int points){
        
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) throws PointException {
        if(points>=0 && points<=this.getMax()){
            this.points = points;
        }else{
            throw new PointException("Het punt moet minimum 0 zijn en maximum "+this.getMax());
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) throws PointException {
        if(max>0 && max<=200){
            this.max = max;
        }else{
            throw new PointException("Het maximum aantal punten moet minimum 0 zijn en maximum 200");
        }
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
