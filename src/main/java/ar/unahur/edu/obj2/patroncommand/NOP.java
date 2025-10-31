package ar.unahur.edu.obj2.patroncommand;

public class NOP implements Operable {
    @Override
    public Mantenimiento execute(Programable micro) {
     
        Mantenimiento memento = micro.saveState(); 

     
        micro.incProgramCounter();
        
        
        return memento; 
    }
    @Override
    public String toString() { return "NOP"; }
}