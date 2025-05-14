package projc1;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Animal {
    private String name;
    private String specie;
    private double weight;
    private int age;
    private String route =  "";


    public Animal() {
        this.name = "";
        this.specie = "";
        this.weight = 0;
        this.age = 0;
    }

    public Animal(String nombre, String especie, double peso, int edad) {
        this.name = nombre;
        this.specie = especie;
        this.weight = peso;
        this.age = edad;
    }
    public String getRoute() {
        return this.route;
    }
    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  void setLabelIcon(JLabel label, String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
    
}
