/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolEntities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Jan
 */
@Entity
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String title;
    @ManyToOne
    private SchoolClass scclass;
    private int maxpoints;
    
    public Test(){}
    
    public Test(String title, SchoolClass scclass, int max) throws TestException {
        this.setTitle(title);
        this.setSchoolClass(scclass);
        this.setMaxpoints(max);
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
    
    public void setSchoolClass(SchoolClass scclass) {
        this.scclass=scclass;
    }

    public SchoolClass getSchoolClass() {
        return scclass;
    }

    public int getMaxpoints() {
        return maxpoints;
    }

    public void setMaxpoints(int maxpoints) throws TestException {
        if(maxpoints>0 && maxpoints<Integer.MAX_VALUE){
            this.maxpoints = maxpoints;
        }else{
            throw new TestException("Geef een correct maximum in");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getTitle()+" /"+this.getMaxpoints();
    }
    
}
