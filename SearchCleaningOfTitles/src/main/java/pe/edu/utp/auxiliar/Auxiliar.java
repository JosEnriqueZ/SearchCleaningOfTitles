/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.utp.auxiliar;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import pe.edu.utp.Busqueda.TitleSearch;
import pe.edu.utp.service.Core;

/**
 *
 * @author EnriqueZ Gutierrez Arias
 */
public class Auxiliar {
    
    File file;
    File[] fileList;
    String ruta;
    String name;
    String name2;

    public Auxiliar() {
    }

    public Auxiliar(File file, String ruta, String name, String name2) {
        this.file = file;
        this.ruta = ruta;
        this.name = name;
        this.name2 = name2;
    }
    
    public Auxiliar(String ruta) {
        this.ruta = ruta;
        file = new File(ruta);
        fileList = file.listFiles();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public File[] getFileList() {
        return fileList;
    }

    public void setFileList(File[] fileList) {
        this.fileList = fileList;
    }
    
    
    
    public String cambiarNombreResultado(int seleccion){
        System.out.println("path: " + file.getPath());
        System.out.println("name: " + file.getName());
        System.out.println("namelista: " + fileList[seleccion].getName());
        
        String nombreResultado = fileList[seleccion].getPath().replace(fileList[seleccion].getName(), "ResultadoBusquedaTitulos-"+cambiarExtension(fileList[seleccion].getName()));
        
        return nombreResultado;
    }
    
    public int seleccionarArchivo(){  
        System.out.println("---------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona un Archivo a escontrar los titulos dentro:");
                int num=1;
                for (File dir : fileList) {
                    System.out.println(num+". "+dir.getName());
                    num++;
                }  
            
            System.out.println("Ingrese el numero del Archivo a analizar");
            String opcion = scanner.nextLine();
            System.out.println("---------------------------------------");
            return Integer.parseInt(opcion);
    }
    
    public String cambiarExtension(String name){
        String nombreArchivo = name;
        String nombreArchivoSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
        String nuevaExtension = ".txt"; // Nueva extensión deseada

        String nuevoNombreArchivo = nombreArchivoSinExtension + nuevaExtension;   
        return nuevoNombreArchivo;
    }
}
