package ar.unahur.edu.obj2.patroncommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Microcontrolador implements Programable {
    
    private Integer acumuladorA;
    private Integer acumuladorB;
    private Integer programCounter; 
    
    private final List<Integer> memoriaDatos; 
    private static final int MEMORY_SIZE = 1024;
    private boolean errorDetected;

    
    private Mantenimiento lastState; 

   
    public Microcontrolador() {
        
        this.memoriaDatos = new ArrayList<>(Collections.nCopies(MEMORY_SIZE, 0));
        this.reset();
    }

  
    @Override
    public void reset() {
        this.acumuladorA = 0;
        this.acumuladorB = 0;
        this.programCounter = 0;
        this.errorDetected = false;
        
    }

    

    @Override
    public Mantenimiento saveState() {
        return new Mantenimiento(
            this.acumuladorA, 
            this.acumuladorB, 
            this.programCounter, 
            this.memoriaDatos
        );
    }

    @Override
    public void restoreState(Mantenimiento memento) {
        this.acumuladorA = memento.getAcumuladorA();
        this.acumuladorB = memento.getAcumuladorB();
        this.programCounter = memento.getProgramCounter();
        
        
        this.memoriaDatos.clear();
        this.memoriaDatos.addAll(memento.getMemoriaDatosSnapshot());
        
        this.errorDetected = false; 
    }
    
    public void undo() {
        if (this.lastState != null) {
            System.out.println("-> Deshaciendo instrucción...");
            this.restoreState(this.lastState);
            this.lastState = null; 
        } else {
            System.out.println("No hay estado previo para deshacer.");
        }
    }

   
    @Override
    public void run(List<Operable> operaciones) {
        this.reset();
        this.lastState = null; 
        
        while (programCounter < operaciones.size() && !errorDetected) {
            Operable operacion = operaciones.get(programCounter);
            
            
            this.lastState = operacion.execute(this); 
            
            
            if (this.errorDetected) {
                break;
            }
        }
        
        if (errorDetected) {
            System.err.println("--- ERROR DE EJECUCIÓN ---");
            System.err.println("Programa detenido en la instrucción: " + programCounter);
        }
    }

   
    
    @Override
    public void incProgramCounter() {
        this.programCounter++;
    }

    @Override
    public Integer getProgramCounter() {
        return programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        this.acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        this.acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

 
    @Override
    public void setAddr(Integer addr, Integer value) {
        if (addr >= 0 && addr < MEMORY_SIZE) {
            this.memoriaDatos.set(addr, value);
        } else {
            System.err.println("Error de memoria: Dirección fuera de rango al intentar escribir: " + addr);
            this.errorDetected = true;
        }
    }

 
    @Override
    public Integer getAddr(Integer addr) {
        if (addr >= 0 && addr < MEMORY_SIZE) {
            return this.memoriaDatos.get(addr);
        } else {
            System.err.println("Error de memoria: Dirección fuera de rango al intentar leer: " + addr);
            this.errorDetected = true;
            return 0; 
        }
    }
    
   
    public boolean isErrorDetected() {
        return errorDetected;
    }
}