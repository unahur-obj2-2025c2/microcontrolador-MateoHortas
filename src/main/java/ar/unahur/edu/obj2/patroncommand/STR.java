package ar.unahur.edu.obj2.patroncommand;

public class STR implements Operable {
    private final int addr;

    public STR(int addr) {
        this.addr = addr;
    }

    @Override
    public Mantenimiento execute(Programable micro) {
       
        Mantenimiento memento = micro.saveState(); 
        
       
        micro.setAddr(addr, micro.getAcumuladorA()); 

       
        if (!((Microcontrolador)micro).isErrorDetected()) {
            micro.incProgramCounter();
        }
        
        
        return memento;
    }
    @Override
    public String toString() { return "STR " + addr; }
}


