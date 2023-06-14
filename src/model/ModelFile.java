package model;

public class ModelFile {
    public static int levels;
    public static String name;
    public static int posicion;


    public int getPosicion(){
        return posicion;
    }

    public void setPosicion(int posicion){
        this.posicion = posicion;
    }

    public int where(int level){
        int num = level;
        num = num +1;
        return num;
    }
    public int getLevel()
    {
        return levels;
    }

    public void setLevel(int level)
    {
        this.levels = level;
    }

    public String getName(String name)
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
