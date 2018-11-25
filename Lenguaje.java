
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Scanner;


/**
 *
 * @author supoar
 */
public class Lenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         int orden = 0;
         long posicion=0;
      String nombreFichero = "";
      File archivo = null;
      RandomAccessFile raf = null;
      FileLock bloqueo = null;
      String valor = null;
      String valorantiguo = "";
    
      
      //Comprobamos si estamos recibiendo argumentos en la línea de comandos
      if (args.length > 0){
        orden = Integer.parseInt(args[0]);
        //Número de orden de creación de este proceso
        try{
            //Rediregimos salida y error estándar a un fichero
            PrintStream ps = new PrintStream(
                             new BufferedOutputStream(new FileOutputStream(
                             new File("javalog.txt"),true)), true);
            System.setOut(ps);
            System.setErr(ps);
        }catch(Exception e){
            System.err.println("P"+orden+" No he podido redirigir salidas.");
        }
      }
      //Identificamos el sistema operativo para poder acceder por su ruta al
      //fichero de forma correcta.
          //GNU/Linux
          if (args.length > 1)
            nombreFichero = args[1];
          //Hemos recibido la ruta del fichero en la línea de comandos
          else{
               nombreFichero = "/home/supoar/Escritorio/Ejercicio2/miFicheroDeLenguaje.txt";
               //Fichero que se utilizará por defecto
        }
      
      //Preparamos el acceso al fichero
      archivo = new File(nombreFichero);
     //aumentamos las situaciones de concurrencia
         try{
            raf = new RandomAccessFile(archivo,"rwd"); //Abrimos el fichero
            System.out.print("ABRE FICHERO");
            //***************
            //Sección crítica
            bloqueo = raf.getChannel().lock();
            //bloqueamos el canal de acceso al fichero. Obtenemos el objeto que
            //representa el bloqueo para después poder liberarlo
            System.out.println("Proceso"+ orden +
                  ": ENTRA sección");
            // Lectura del fichero
            while(raf.readLine()!=null){
            String linea=raf.readLine();
            if(linea!=null){
                 
            valorantiguo=valorantiguo+raf.readLine();
            
            }
            }
            valor=generarCadena();         
           
            
            
            
            raf.writeBytes(valor); //escribimos el valor
            System.out.println("LA POSICION " + raf.getFilePointer());
            System.out.println("Proceso"+ orden +
                   ": SALE sección");
            bloqueo.release(); //Liberamos el bloqueo del canal del fichero
            bloqueo = null;
            //Fin sección crítica
            //*******************
            System.out.println("Proceso"+ orden +
                 ": valor escrito " + valor);
        }catch(Exception e){
            System.err.println("P"+orden+" Error al acceder al fichero");
            System.err.println(e.toString());
        }finally{
            try{
                if( null != raf ) raf.close();
                if( null != bloqueo) bloqueo.release();
            }catch (Exception e2){
                System.err.println("P"+orden+" Error al cerrar el fichero");
                System.err.println(e2.toString());
                System.exit(1);  //Si hay error, finalizamos
            }
        }

    }
     public static String generarCadena() 
    {
    int num1 = 97;

		int num2 = 122;
                    
		//a la variable de caracter le di un valor inicial de cero

		char c = 0;
                String cadena="";
               
                
              
                
		//establezco una secuencia de diez números aleatorios

		//podemos establecer la secuencia que queramos, siempre y cuando recordemos que el alfabeto en acssi tiene 26 letras, aí que ese es el máximo de la secuencia
                
		for (int i=1; i<=10; i++){

			int numAleatorio = (int)Math.floor(Math.random()*(num2 -num1)+num1);

			//para transformar los números en letras según ACSII

			

			System.out.print((char)numAleatorio);

			c++;

			cadena=cadena+(char)numAleatorio;
                        
                              
       
		}
                cadena=cadena+"\n";
                System.out.println();
                
        return cadena;
	}
} 
    
    
    
    
    

