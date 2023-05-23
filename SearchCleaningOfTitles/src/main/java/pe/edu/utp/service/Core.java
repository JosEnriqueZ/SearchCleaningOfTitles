package pe.edu.utp.service;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author EnriqueZ Gutierrez Arias
 */

public class Core {
    private Integer count = 1;
    public List<String> auxLines;
    public List<Integer> trash = new ArrayList<>();
    public HashMap<String, Integer> output = new HashMap<String, Integer>();

    public Core() {
        this.run();
    }

    private void run() {
        String iso = "ISO-8859-1";
        Scanner scanner = new Scanner(System.in,iso);
        System.out.println("Ingresa directorio con los txt");
        String directory = scanner.nextLine();
        Integer l = 0;
        try {
            File fileName = new File(directory);
            File[] fileList = fileName.listFiles();
            for (File dir : fileList) {
                List<String> lines = Files.readAllLines(Path.of(String.valueOf(dir)));
                auxLines = new LinkedList<String>(lines);
                System.out.println("Procesando...");
                for (int i = 0; i < lines.size(); i++) {
                    detectDuplicate(lines.get(i));
                    for (int index : trash) {
                        if (index != 0) {
                            auxLines.remove(index - l);
                        } else {
                            auxLines.remove(index);
                        }
                        l++;
                    }
                    l = 0;
                    trash.clear();

                }
            }
            saveInExcel(directory.substring(directory.lastIndexOf("\\") + 1).split("\\.")[0], output);
        } catch (IOException e) {
            System.out.println("Error: MEnsaje" + e.getMessage());
            e.printStackTrace();
        }

    }

    private void detectDuplicate(String title) {
        for (int s = 0; s < auxLines.size(); s++) {
            if (title.equalsIgnoreCase(auxLines.get(s))) {
                if (!output.containsKey(auxLines.get(s))) {
                    output.put(title, this.count);
                } else {
                    output.put(title, output.get(auxLines.get(s)) + 1);
                }
                trash.add(s); 
            }
        }

    }

    private void saveInExcel(String nameDocument, HashMap<String, Integer> data) throws IOException {

        try {
            System.out.println("Ingreso a la creacion");
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Hoja 1");
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 4000);
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Titulo");
            headerCell = header.createCell(1);
            headerCell.setCellValue("# de repetido");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Filtrado");
            AtomicInteger b = new AtomicInteger(1);
            output.entrySet().stream().forEach(item ->
            {
                Row row = sheet.createRow(b.getAndIncrement());
                Cell cell = row.createCell(0);
                cell.setCellValue(String.valueOf(item).split("=")[0]);
                cell = row.createCell(1);
                cell.setCellValue(String.valueOf(item).split("=")[1]);
            });

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + nameDocument + ".xlsx";
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Archivo creado en su directorio " + fileLocation);
        } catch (Exception e) {
            System.out.println("Error: MEnsaje" + e.getMessage());
        }
    }


}
