package controller;

import model.ModelFile;

import java.io.*;
import java.util.ArrayList;

public class Level {

    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    private ModelFile model;
    private static ArrayList<String> levels;
    private static boolean state;
    public static boolean  state2;
    private int counter;


    public ArrayList<String> readerLevel(int posicion) {
        int lineCount = 0;
        model = new ModelFile();
        levels = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/level.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                levels.add(line);
                state=true;

            }
            if(state){
                this.writeLevel(posicion);
                System.out.println("Es un usuario que existe");
            }else{
                this.writeLevel(posicion);
                System.out.println("Es un usuario nuevo y va empezar el nivel # ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public void subir(){

    }


    //Permite validar si existe el registro en el arreglo


    public void writeLevel(int posicion){
        try {
            if(state){
                model = new ModelFile();
                fileWriter = new FileWriter("src/resources/level.txt", false);
                output = new BufferedWriter(fileWriter);
                String num = levels.get(posicion);
                int nuevo =  model.where(Integer.parseInt(num));
                levels.set(posicion,String.valueOf(nuevo));
                for (String lines : levels){
                    output.write(lines);
                    output.newLine();
                }
                model.levels= nuevo;
                System.out.println("Si va subir nivel " + nuevo );
            }else{
                fileWriter = new FileWriter("src/resources/level.txt", true);
                output = new BufferedWriter(fileWriter);
                System.out.println("No va subir nivel" );
                output.write("1");
                output.newLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
