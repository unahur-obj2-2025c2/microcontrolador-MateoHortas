package ar.unahur.edu.obj2.patroncommand;

public class LODV implements Operable {
    private final int val;

    public LODV(int val) {
        this.val = val;
    }

    @Override
    public Mantenimiento execute(Programable micro) {
      
        Mantenimiento memento = micro.saveState(); 
        
      
        micro.setAcumuladorA(val);
        micro.incProgramCounter();
        
       
        return memento;
    }
    @Override
    public String toString() { return "LODV " + val; }
}