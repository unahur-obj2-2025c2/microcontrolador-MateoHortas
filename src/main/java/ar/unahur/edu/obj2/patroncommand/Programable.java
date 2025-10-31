
package ar.unahur.edu.obj2.patroncommand;

import java.util.List;

public interface Programable {
    void run(List<Operable> operaciones);
    void incProgramCounter();
    Integer getProgramCounter();
    void setAcumuladorA(Integer value);
    Integer getAcumuladorA();
    void setAcumuladorB(Integer value);
    Integer getAcumuladorB();
    void setAddr(Integer addr, Integer value);
    Integer getAddr(Integer addr);
    void reset();
    
    
    Mantenimiento saveState();
    void restoreState(Mantenimiento memento);
    
}