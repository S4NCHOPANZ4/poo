package projc1.animalClasses;
import javax.swing.JLabel;
import projc1.Animal;
public class Llama extends Animal{

    private String route =  "";
    public Llama() {
    }

    public Llama(String nombre, String especie, double peso, int edad, String route) {
        super(nombre, especie, peso, edad);
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public void setLabelIcon(JLabel label, String imagePath) {
          super.setLabelIcon(label, imagePath);
    }
    @Override
    public void setAge(int age) {
        super.setAge(age); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setWeight(double weight) {
        super.setWeight(weight); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setSpecie(String specie) {
        super.setSpecie(specie); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setName(String name) {
        super.setName(name); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int getAge() {
        return super.getAge(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public double getWeight() {
        return super.getWeight(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getSpecie() {
        return super.getSpecie(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getName() {
        return super.getName(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
