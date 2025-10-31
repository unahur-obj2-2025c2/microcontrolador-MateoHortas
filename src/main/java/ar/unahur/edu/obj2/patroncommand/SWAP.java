package ar.unahur.edu.obj2.patroncommand;

public class SWAP implements Operable {
    @Override
    public Mantenimiento execute(Programable micro) {
       
        Mantenimiento memento = micro.saveState(); 
        
       
        Integer tempA = micro.getAcumuladorA();
        Integer tempB = micro.getAcumuladorB();
        micro.setAcumuladorA(tempB);
        micro.setAcumuladorB(tempA);
        micro.incProgramCounter();
        
        
        return memento;
    }
    @Override
    public String toString() { return "SWAP"; }
}