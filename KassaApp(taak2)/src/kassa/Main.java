/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kassa;

import components.Kassier;
import components.Product;
import components.Rekening;
import connect.KassaBean;
import connect.KassaCheckBean;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Jan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Context c;
       
        try{
            c = new InitialContext();
                    //java:global/KassaServer_taak2_/KassaBean!connect.KassaCheckBean
                    //java:global/KassaServer_taak2_/KassaBean!connect.KassaCheckBean
            //KassaBean kb=(KassaBean);
            KassaCheckBean kb=(KassaCheckBean)c.lookup("java:global/KassaServer_taak2_/KassaBean!connect.KassaCheckBean");
            System.out.println(kb.getClass().getName());
            
            Product p1 = new Product("Appel", 0.99);
            Product p2 = new Product("Rijst", 0.39);
            Product p3 = new Product("Koek", 1.45);
            Product p4 = new Product("pasta", 0.89);
            Product p5 = new Product("Cola", 0.57);
            Kassier k=new Kassier("Chuck Norris", 1617.17);
            Kassier k2=new Kassier(null, 1617.17);
            Rekening r1 = new Rekening(k);
            Rekening r2 = new Rekening(k);
            Rekening r3 = new Rekening(k2);
            r1.addProduct(p1);r1.addProduct(p2);
            r2.addProduct(p3);r2.addProduct(p1);r2.addProduct(p4);
            r3.addProduct(p5);r3.addProduct(p2);r3.addProduct(p1);r3.addProduct(p4);
            r1.generateOutprint();
            System.out.println(r1.getOutprint());
            try{
                kb.voegRekeningToe(r1);
                kb.voegRekeningToe(r2);
                kb.voegRekeningToe(r3);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            kb.generateFullOutprint();
            ArrayList<Rekening> list=(ArrayList<Rekening>) kb.getContents();
            for(Rekening rek:list){
                System.out.println(rek.getKassier().getNaam());
                for(Product p: rek.getProducten()){
                    System.out.println("\t"+p.toString());
                }
                System.out.println("\t"+rek.getTotaal());
            }
            //System.out.println(kb.getOutput());
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
