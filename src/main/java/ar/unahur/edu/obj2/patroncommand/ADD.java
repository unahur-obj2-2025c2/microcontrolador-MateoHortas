package ar.unahur.edu.obj2.patroncommand;


public class ADD implements Operable {
    @Override
    public Mantenimiento execute(Programable micro) {
       
        Mantenimiento memento = micro.saveState(); 

    
        int resultado = micro.getAcumuladorA() + micro.getAcumuladorB();
        micro.setAcumuladorA(resultado);
        micro.setAcumuladorB(0);
        micro.incProgramCounter();
        
       
        return memento; 
    }
    @Override
    public String toString() { return "ADD"; }
}
