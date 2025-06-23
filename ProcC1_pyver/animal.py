from PIL import Image, ImageTk

class Animal:
    def __init__(self, name="", specie="", weight=0.0, age=0):
        self.name = name
        self.specie = specie
        self.weight = weight
        self.age = age
        self.route = ""
    
    def set_label_icon(self, label, image_path):
        try:
            image = Image.open(image_path)
            image = image.resize((150, 150), Image.LANCZOS)
            photo = ImageTk.PhotoImage(image)
            label.config(image=photo)
            label.image = photo  # Guardar referencia
        except Exception as e:
            print(f"Error loading image: {e}")
            label.config(text="Image not found")

class Cow(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route

class Fox(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route
    
class Chicken(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route

class Donkey(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route

class Sheep(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route
class Horse(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route
class Llama(Animal):
    def __init__(self, name, specie, weight, age, route):
        super().__init__(name, specie, weight, age)
        self.route = route
# ... (similar para Chicken, Donkey, Sheep, Horse, Llama)