import tkinter as tk
from tkinter import ttk, messagebox


class ExtraInfoWindow(tk.Toplevel):
    def __init__(self, parent, manager, name, specie, weight, age):
        super().__init__(parent)
        self.manager = manager
        self.name = name
        self.original_name = name
        self.specie = specie
        self.weight = weight
        self.age = age
        
        self.title(f"Información de {name}")
        self.geometry("500x500")
        self.configure(bg="#333333")
        
        self.create_widgets()
        self.load_values()
    
    def create_widgets(self):
        # Título
        title = ttk.Label(self, text="Animal Name", font=('Segoe UI', 18))
        title.pack(pady=10)
        
        # Nombre
        name_label = ttk.Label(self, text="Nombre")
        name_label.pack(anchor=tk.W, padx=20)
        
        self.name_entry = ttk.Entry(self)
        self.name_entry.pack(fill=tk.X, padx=20, pady=5)
        self.name_entry.bind('<KeyRelease>', lambda e: self.set_name(self.name_entry.get()))
        
        # Peso
        weight_label = ttk.Label(self, text="Peso")
        weight_label.pack(anchor=tk.W, padx=20)
        
        self.weight_entry = ttk.Entry(self)
        self.weight_entry.pack(fill=tk.X, padx=20, pady=5)
        self.weight_entry.bind('<KeyRelease>', lambda e: self.set_weight(self.weight_entry.get()))
        
        # Edad
        age_label = ttk.Label(self, text="Edad")
        age_label.pack(anchor=tk.W, padx=20)
        
        self.age_entry = ttk.Entry(self)
        self.age_entry.pack(fill=tk.X, padx=20, pady=5)
        self.age_entry.bind('<KeyRelease>', lambda e: self.set_age(self.age_entry.get()))
        
        # Imagen
        img_label = ttk.Label(self, text="Foto cute de mi bro", font=('Segoe UI', 14))
        img_label.pack(pady=10)
        
        self.img_display = ttk.Label(self)
        self.img_display.pack()
        
        # Botones
        btn_frame = ttk.Frame(self)
        btn_frame.pack(pady=20)
        
        save_btn = ttk.Button(
            btn_frame, text="Guardar Cambios", 
            command=self.save_changes
        )
        save_btn.pack(side=tk.LEFT, padx=10)
        
        play_btn = ttk.Button(
            btn_frame, text="Jugar con el bro", 
            command=self.play_game
        )
        play_btn.pack(side=tk.LEFT, padx=10)
    
    def load_values(self):
        self.name_entry.insert(0, self.name)
        self.weight_entry.insert(0, str(self.weight))
        self.age_entry.insert(0, str(self.age))
        
        # Cargar imagen según la especie
        from animal import Animal
        animal = Animal()
        animal.set_label_icon(self.img_display, self.get_image_path())
    
    def get_image_path(self):
        return f"assets/images/{self.specie.lower()}.png"
    
    def set_name(self, name):
        self.name = name
    
    def set_specie(self, specie):
        self.specie = specie
    
    def set_weight(self, weight_str):
        try:
            self.weight = float(weight_str)
        except ValueError:
            pass
    
    def set_age(self, age_str):
        try:
            self.age = int(age_str)
        except ValueError:
            pass
    
    def save_changes(self):
        from animal import Animal
        new_animal = Animal(self.name, self.specie, self.weight, self.age)
        
        if self.manager.edit_animal(self.original_name, new_animal):
            messagebox.showinfo("Éxito", "Cambios guardados correctamente")
            self.destroy()
        else:
            messagebox.showerror("Error", "No se pudo guardar los cambios")
    
    def play_game(self):
        from game.game_panel import GamePanel
        GamePanel(self, self.get_image_path())