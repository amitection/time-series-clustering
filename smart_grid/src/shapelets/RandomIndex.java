/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapelets;

import java.util.List;

/**
 *
 * @author hp
 */
public class RandomIndex {

    public static double calculateRandomIndex(List cls1, List cls2) {

        int length_cls1 = cls1.size();
        double A, B, C, D;

        A = B = C = D = 0;

        for (int i = 0; i < length_cls1; i++) {
            for (int j = 0; j < i; j++) {
                if((cls1.get(i) == cls2.get(j)) && cls2.get(i)== cls2.get(j)) 
                    A++;
                else if((cls1.get(i) != cls1.get(j)) && cls2.get(i) != cls2.get(j))
                    B++;
                else if((cls1.get(i) == cls1.get(j)) && cls2.get(i) != cls2.get(j))
                    C++;
                else
                    D++;
            }

        }
        
        double randIndex = (A+B) / (A+B+C+D);
        return randIndex;

    }
}
