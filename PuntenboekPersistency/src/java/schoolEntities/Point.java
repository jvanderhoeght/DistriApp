/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolEntities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Jan
 */
@Entity
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;
    private int points;
    @OneToOne
    private Test test;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Point(){}
    
    public Point(Test t, int points) throws PointException{
        this.setTest(t);
        this.setPoints(points);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) throws PointException {
        if(points>=0 && points<=this.getTest().getMaxpoints()){
            this.points = points;
        }else{
            throw new PointException("Het punt moet minimum 0 zijn en maximum "+this.getTest().getMaxpoints());
        }
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Point)) {
            return false;
        }
        Point other = (Point) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getTest().toString()+" "+this.getPoints()+"/"+this.getTest().getMaxpoints();
    }
    
}
