package projc1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
public class AnimalManager {
    private static final String JSON_ROUTE = "src/assets/data.json";
    private List<Animal> anmialList;
    
    public AnimalManager() {
        loadJSON();
    }

    private void loadJSON() {
        try {
            FileReader reader = new FileReader(JSON_ROUTE);
            anmialList = new Gson().fromJson(reader, new TypeToken<List<Animal>>(){}.getType());
            reader.close();
            if (anmialList == null) anmialList = new ArrayList<>();
        } catch (Exception e) {
            anmialList = new ArrayList<>();
            e.printStackTrace();
        }
    }
    
        private void saveJSON() {
        try {
            FileWriter writer = new FileWriter(JSON_ROUTE);
            new GsonBuilder().setPrettyPrinting().create().toJson(anmialList, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void addAnimal(Animal m) {
        anmialList.add(m);
        saveJSON();
    }
        public boolean editAnimal(String ogName, Animal newAnimal) {
        for (int i = 0; i < anmialList.size(); i++) {
            if (anmialList.get(i).getName().equalsIgnoreCase(ogName)) {
                anmialList.set(i, newAnimal);
                saveJSON();
                return true;
            }
        }
        return false;
    }
        public boolean eliminarMascota(String name) {
        boolean eliminado = anmialList.removeIf(m -> m.getName().equalsIgnoreCase(name));
        if (eliminado) saveJSON();
        return eliminado;
    }
        public List<Animal> getAnimals() {
        return anmialList;
    }

}
