
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author supoar
 */
public class OrdenarNumerosTub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader (isr);
        // Obtenemos el stream de lectura de la entrada estándar
        // utilizamos un lector con Buffered, para no perder ningún dato
        String lineaTeclado = null;
        int [] numeros = new int[40];
        int i=0;
        try{
            System.out.println("Proceso lector");
            //Mostramos que el proceso que está escribiendo es el que está
            //leyendo los datos.
            while ((lineaTeclado = bf.readLine())!= null){
                //Vamos leyendo y mostrando los datos
                System.out.print(lineaTeclado+",");
                numeros[i]=Integer.parseInt(lineaTeclado);
                  
                  i++; 
            }
        }catch(IOException ex){
            System.err.println("Se ha producido un error de E/S.");
            System.err.println(ex.toString());
        }
        System.out.println();
      
        
        ordenarArray(numeros);
        System.out.println();
        System.out.println("Los números aleatorios ordenados: ");
        for(int h=0;h<=numeros.length-1;h++){
            System.out.print(numeros[h]+",");
    }
    }
    public static int[] ordenarArray(int[] n) 
    {
    int aux;

    for (int i = 0; i < n.length - 1; i++) {
        for (int x = i + 1; x < n.length; x++) {
            if (n[x] < n[i]) {
                aux = n[i];
                n[i] = n[x];
                n[x] = aux;
            }
        }
    }

    return n;
}
    }
    

