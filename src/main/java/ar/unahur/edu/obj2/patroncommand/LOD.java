package ar.unahur.edu.obj2.patroncommand;

public class LOD implements Operable {
    private final int addr;

    public LOD(int addr) {
        this.addr = addr;
    }

    @Override
    public Mantenimiento execute(Programable micro) {
        
        Mantenimiento memento = micro.saveState(); 
        
      
        Integer valor = micro.getAddr(addr); 

        
        if (!((Microcontrolador)micro).isErrorDetected()) {
            micro.setAcumuladorA(valor);
            micro.incProgramCounter();
        }
        
        
        return memento;
    }
    @Override
    public String toString() { return "LOD " + addr; }
}
