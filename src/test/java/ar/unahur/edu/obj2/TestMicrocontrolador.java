package ar.unahur.edu.obj2;

import ar.unahur.edu.obj2.patroncommand.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.Operable;
import ar.unahur.edu.obj2.patroncommand.NOP;
import ar.unahur.edu.obj2.patroncommand.LODV;
import ar.unahur.edu.obj2.patroncommand.SWAP;
import ar.unahur.edu.obj2.patroncommand.ADD;
import ar.unahur.edu.obj2.patroncommand.STR;
import ar.unahur.edu.obj2.patroncommand.LOD;
// Nuevos comandos para Parte II
import ar.unahur.edu.obj2.patroncommand.IFNZ;
import ar.unahur.edu.obj2.patroncommand.WHNZ;

import java.util.List;

public class TestMicrocontrolador {

    public static void main(String[] args) {
        Microcontrolador micro = new Microcontrolador();

        
        
        System.out.println("--- PRUEBA 1: Avance de PC (NOP) ---");
        List<Operable> programa1 = List.of(new NOP(), new NOP(), new NOP());
        micro.run(programa1);
        System.out.printf("PC final: %d (Esperado: 3)\n", micro.getProgramCounter());
        System.out.println("-----------------------------------\n");

        System.out.println("--- PRUEBA 2: Sumar 20 + 17 ---");
        List<Operable> programa2 = List.of(
            new LODV(20), 
            new SWAP(), 
            new LODV(17), 
            new ADD() 
        );
        micro.run(programa2);
        System.out.printf("Acumulador A: %d (Esperado: 37)\n", micro.getAcumuladorA());
        System.out.printf("Acumulador B: %d (Esperado: 0)\n", micro.getAcumuladorB());
        System.out.printf("PC final: %d (Esperado: 4)\n", micro.getProgramCounter());
        System.out.println("-----------------------------------\n");

        System.out.println("--- PRUEBA 3: Sumar 2 + 8 + 5 ---");
        List<Operable> programa3 = List.of(
            new LODV(2), new STR(0), new LODV(8), new SWAP(), 
            new LODV(5), new ADD(), new SWAP(), new LOD(0), new ADD() 
        );
        micro.run(programa3);
        // NOTA: El resultado de 15 es A=8+5+2.
        System.out.printf("Acumulador A: %d (Esperado: 15)\n", micro.getAcumuladorA());
        System.out.printf("Acumulador B: %d (Esperado: 0)\n", micro.getAcumuladorB());
        System.out.printf("Valor en M[0]: %d (Esperado: 2)\n", micro.getAddr(0));
        System.out.println("-----------------------------------\n");
        
        System.out.println("--- PRUEBA 4: Error de Acceso a Memoria ---");
        List<Operable> programaError = List.of(
            new LODV(10), new NOP(), new STR(1024), new NOP() 
        );
        micro.run(programaError);
        System.out.printf("Error detectado: %b (Esperado: true)\n", micro.isErrorDetected());
        System.out.printf("PC final: %d (Esperado: 2, la dirección de STR 1024)\n", micro.getProgramCounter());
        System.out.println("-----------------------------------\n");


        
        //PRUEBA 5
        System.out.println("PRUEBA 5: Funcionalidad UNDO");
        
        List<Operable> programa5 = List.of(
            new LODV(10), 
            new LODV(20), 
            new SWAP()    
        );
        micro.run(programa5);
        
       
        System.out.printf("Estado ANTES de UNDO (PC:%d): A=%d, B=%d\n", micro.getProgramCounter(), micro.getAcumuladorA(), micro.getAcumuladorB());

        
        micro.undo(); 

        
        System.out.printf("Estado DESPUÉS de UNDO (PC:%d E:2): A=%d (E:20), B=%d (E:10)\n", micro.getProgramCounter(), micro.getAcumuladorA(), micro.getAcumuladorB());
        System.out.println("-----------------------------------\n");
        
        
        System.out.println("PRUEBA 6: Instrucción IFNZ ---");
        
        
        List<Operable> bloqueIFNZ = List.of(new SWAP(), new LODV(5)); 
        
        
        List<Operable> programa6a = List.of(
            new LODV(1),            
            new LODV(100),          
            new SWAP(),             
            new IFNZ(bloqueIFNZ),   
            new NOP()               
        );
        micro.run(programa6a);
        System.out.printf("Resultado IFNZ (Verdadero): A=%d (E:5), B=%d (E:100), PC=%d (E:5)\n", micro.getAcumuladorA(), micro.getAcumuladorB(), micro.getProgramCounter());
        
        // Prueba 6
        List<Operable> programa6b = List.of(
            new LODV(0),            
            new SWAP(),             
            new IFNZ(bloqueIFNZ),   
            new NOP()               
        );
        micro.run(programa6b);
        System.out.printf("Resultado IFNZ (Falso): A=%d (E:100), B=%d (E:0), PC=%d (E:5)\n", micro.getAcumuladorA(), micro.getAcumuladorB(), micro.getProgramCounter());
        System.out.println("-----------------------------------\n");

        
        //PRUEBA 7
        System.out.println("PRUEBA 7: Instrucción WHNZ (Bucle)");
        
        
        List<Operable> bloqueWHNZ = List.of(
             new LODV(0) 
        ); 
        
        List<Operable> programa7 = List.of(
            new LODV(3),            
            new WHNZ(bloqueWHNZ),   
            new NOP()               
        );
        micro.run(programa7);
        
        System.out.printf("Resultado WHNZ: A=%d (E:0), PC=%d (E:3)\n", micro.getAcumuladorA(), micro.getProgramCounter());
        System.out.println("-----------------------------------\n");
    }
}