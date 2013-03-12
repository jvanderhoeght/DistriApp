/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolentities;

/**
 *
 * @author Jan
 */
public class Test {
    
    private String title;
    private int max;

    public Test(String title, int max) throws TestException {
        this.setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws TestException {
        if(title!=null && !title.equals("")){
            this.title = title;
        }else{
            throw new TestException("Geef een titel in");
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

    @Override
    public boolean equals(Object o) {
        boolean res=false;
        if(o!=null && o instanceof Test){
            Test t=(Test)o;
            res=this.getTitle().equals(t.getTitle()) && this.getMax()==t.getMax();
        }
        return res;
    }
    
    
}
