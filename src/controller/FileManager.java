package controller;

import model.ModelFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;
    private ModelFile model;
    private static ArrayList<String> names, words;
    private static boolean esta;
    private int counter;
    private Level level;

    public ArrayList<String> readWords(){
        String text = "";

        try {
            words= new ArrayList<>();
            fileReader = new FileReader("src/resources/words.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line != null){
                words.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) { // Entra a esta seccion cuando no encuentra el archivo
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Estoy dentro de la excepcion 4");
            e.printStackTrace();
        } finally {
            try {
                input.close();
            }  catch (IOException e) {
                System.out.println("Estoy dentro de la excepcion 6");
                e.printStackTrace();
            } catch (NullPointerException e){
                System.out.println("Estoy dentro del finally");
                e.printStackTrace();
            }
        }

        return words;
    }

    public ArrayList<String> getRandomWords() {
        this.readWords();
        model = new ModelFile();
        ArrayList<String> randomWords = new ArrayList<>();
        Random random = new Random();
        int numWords = 0;
        System.out.println("Lllego aca" + model.getLevel());
        if(model.getLevel()==1){
            numWords =20;
        }
        if(model.getLevel()==2){
            numWords =40;
        }
        if(model.getLevel()==3){
            numWords =60;
        }
        if(model.getLevel()==4){
            numWords =80;
        }
        if(model.getLevel()==5){
            numWords =100;
        }
        if(model.getLevel()==6){
            numWords =120;
        }
        if(model.getLevel()==7){
            numWords =140;
        }
        if(model.getLevel()==8){
            numWords =160;
        }

        for (int i = 0; i < numWords; i++) {
            int randomIndex = random.nextInt(words.size());
            String randomWord = words.get(randomIndex);
            randomWords.add(randomWord);
        }

        return randomWords;
    }


    //Permite contar cuantos registros existen en el archivo y asignarlos en un ArrayList
    public int readerUsers(String Users) {
        int lineCount = 0;
        level = new Level();
        names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lineCount++;
                    names.add(line);
                    System.out.println("Encontro el nombre de:"  + line );
            }
            if(lineCount==0){
                this.writer(Users);
                level.readerLevel(0);
                level.state2=false;
                System.out.println("No esta registrado el usuario: " + Users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }


    //Permite validar si existe el registro en el arreglo
    public void readUsers(String Users){
        try{
            counter = this.readerUsers(Users);
            model = new ModelFile();
            esta = names.contains(Users);
            level = new Level();
            if(esta){
                model.posicion = names.indexOf(Users);
                level.readerLevel(model.getPosicion());
                System.out.println("El usuario existe: " +Users+ " " + model.getPosicion());

            }else if(counter!=0){
                this.writer(Users);
                System.out.println("No se encontro el nombre y se agrego: " +Users);
                names.add(Users);
                model.name = Users;
                model.posicion = names.indexOf(Users);
                level.state2=false;
                level.readerLevel(model.posicion);
                System.out.println("Banderazo " + model.getPosicion());

            }

        }catch (Exception e){

        }
    }



    //Escribe en el archivo.
    public void writer(String line){
        model = new ModelFile();
        try {
            fileWriter = new FileWriter("src/resources/users.txt", true);
            output = new BufferedWriter(fileWriter);
            output.write(line);
            output.newLine();
            //this.readerUsers(line);
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

