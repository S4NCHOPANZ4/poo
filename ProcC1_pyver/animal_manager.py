import json
import os
from pathlib import Path
from animal import Animal, Cow, Fox, Chicken, Donkey, Sheep, Horse, Llama

class AnimalManager:
    def __init__(self):
        self.animals = []
        self.json_path = Path("assets/data.json")
        self.load_json()
    
    def load_json(self):
        try:
            if self.json_path.exists():
                with open(self.json_path, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                    self.animals = [self.create_animal(item) for item in data]
            else:
                self.animals = []
        except Exception as e:
            print(f"Error loading JSON: {e}")
            self.animals = []
    
    def create_animal(self, data):
        # Crea la instancia de animal correcta según la especie
        species_classes = {
            "Cow": Cow,
            "Fox": Fox,
            "Chicken": Chicken,
            "Donkey": Donkey,
            "Sheep": Sheep,
            "Horse": Horse,
            "Llama": Llama
        }
        cls = species_classes.get(data['specie'], Animal)
        return cls(
            data.get('name', ''),
            data.get('specie', ''),
            data.get('weight', 0.0),
            data.get('age', 0),
            data.get('route', '')
        )
    
    def save_json(self):
        try:
            with open(self.json_path, 'w', encoding='utf-8') as f:
                json.dump([a.__dict__ for a in self.animals], f, indent=2)
        except Exception as e:
            print(f"Error saving JSON: {e}")
    
    def add_animal(self, animal):
        self.animals.append(animal)
        self.save_json()
    
    def edit_animal(self, original_name, new_animal):
        for i, animal in enumerate(self.animals):
            if animal.name == original_name:
                self.animals[i] = new_animal
                self.save_json()
                return True
        return False
    
    def get_animals(self):
        return self.animals.copy()