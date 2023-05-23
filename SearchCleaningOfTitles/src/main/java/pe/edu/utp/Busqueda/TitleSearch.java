/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.Busqueda;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import pe.edu.utp.auxiliar.Auxiliar;

/**
 *
 * @author EnriqueZ Gutierrez Arias
 */
public class TitleSearch {
    
    String Ruta;
    String nombreArchivo;
    int seleccion;
    ArrayList<String> valores = new ArrayList<>(); 
    Auxiliar aux;
    
    public void RUN() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("***********************************************************************************");
        System.out.println("Ingresa directorio del archivo .bib\n");
        Ruta = scanner.nextLine();
        aux = new Auxiliar(Ruta);
        seleccion = aux.seleccionarArchivo()-1;
        File f = aux.getFileList()[seleccion];
        Scanner s;
        
        try {
            //s = new Scanner(f);
            String iso = "ISO-8859-1";
            String utf = "UTF-8";
            Integer com = 0;
            Scanner scan = new Scanner(f,iso);
            scan.useDelimiter("\n");
            int cont = 0;
            while (scan.hasNext()) {
                
                String line = scan.next();
                if (line.length()==0) {
                    line = scan.next();
                }
                Scanner sl = new Scanner(line);
                sl.useDelimiter("\t");
                String value    =               sl.next().trim();   
                if (value.indexOf("title = ")>=0) {
                    valores.add(value);
                    //cont++;
                    //System.out.println(cont + "/ Titulo Agregado: " + value);
                }else{
                    //cont++;
                    //System.out.println("-");
                }
            }
            CrearArchivo();
            //System.out.println("ALLX");
            scan.close();
            System.out.println("archivo-creado");
        } catch (FileNotFoundException e) {
            System.out.print("ERROR" + e);
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }

    }
    
    public void CrearArchivo(){
    try {
            
            String ruta = aux.cambiarNombreResultado(seleccion);
            System.out.println("Ruta del archivo: "+ aux.getRuta());
            //String contenido = "Contenido de ejemplo";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for (String val : valores) {
                    bw.write(LimpiarTitulo(val) + "\n");
                }
    
            //bw.write(contenido);
            bw.close();
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }
    }
    
    public String LimpiarTitulo(String str)
    {
        String titulo = "";
        try {
        StringBuffer sb = new StringBuffer(str);
        sb.delete(str.length() - 2, str.length());
        sb.delete(0, 9);
        titulo =  sb.toString();
        } catch (Exception e) {
            System.out.println("ERROR limtpiado de titulo: " + e);
        }
        return titulo;
    }
    
}
