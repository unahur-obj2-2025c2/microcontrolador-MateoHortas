package ar.unahur.edu.obj2.patroncommand;

import java.util.List;

public class WHNZ implements Operable {
    private final List<Operable> bloqueInstrucciones;

    public WHNZ(List<Operable> bloque) {
        this.bloqueInstrucciones = bloque;
    }

    @Override
    public Mantenimiento execute(Programable micro) {
        
        Mantenimiento memento = micro.saveState(); 

     
        while (micro.getAcumuladorA() != 0) {
           
            for (Operable op : bloqueInstrucciones) {
                op.execute(micro);
                if (((Microcontrolador)micro).isErrorDetected()) return memento;
            }
            
        }
        
        micro.incProgramCounter(); 
        return memento;
    }
    @Override
    public String toString() { return "WHNZ (Bloque: " + bloqueInstrucciones.size() + " ops)"; }
}