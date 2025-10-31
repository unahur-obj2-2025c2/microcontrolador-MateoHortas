
package ar.unahur.edu.obj2.patroncommand;

import java.util.ArrayList;
import java.util.List;

public class Mantenimiento {
    private final Integer acumuladorA;
    private final Integer acumuladorB;
    private final Integer programCounter;
    
    private final List<Integer> memoriaDatosSnapshot; 

    public Mantenimiento(Integer a, Integer b, Integer pc, List<Integer> memoria) {
        this.acumuladorA = a;
        this.acumuladorB = b;
        this.programCounter = pc;
        
        this.memoriaDatosSnapshot = new ArrayList<>(memoria); 
    }

    public Integer getAcumuladorA() { return acumuladorA; }
    public Integer getAcumuladorB() { return acumuladorB; }
    public Integer getProgramCounter() { return programCounter; }
    public List<Integer> getMemoriaDatosSnapshot() { return memoriaDatosSnapshot; }
}
