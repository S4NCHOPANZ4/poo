import tkinter as tk
from tkinter import ttk, messagebox

class AddAnimalPanel(ttk.Frame):
    def __init__(self, parent, manager, menu_panel=None):
        super().__init__(parent)
        self.manager = manager
        self.menu_panel = menu_panel
        self.create_widgets()
    
    def create_widgets(self):
        # Título
        title = ttk.Label(self, text="Agregar Nuevo Animal", font=('Segoe UI', 24))
        title.pack(pady=20)
        
        # Especie
        species_label = ttk.Label(self, text="Especie", font=('Segoe UI', 14))
        species_label.pack(anchor=tk.W, padx=20)
        
        self.species_combo = ttk.Combobox(self, values=[
            "Cow", "Fox", "Chicken", "Donkey", 
            "Sheep", "Horse", "Llama"
        ])
        self.species_combo.pack(fill=tk.X, padx=20, pady=5)
        self.species_combo.current(0)
        
        # Nombre
        name_label = ttk.Label(self, text="Nombre", font=('Segoe UI', 14))
        name_label.pack(anchor=tk.W, padx=20)
        
        self.name_entry = ttk.Entry(self)
        self.name_entry.pack(fill=tk.X, padx=20, pady=5)
        self.name_entry.insert(0, "Nombre de la criatura")
        self.name_entry.bind('<FocusIn>', self.clear_placeholder)
        
        # Peso
        weight_label = ttk.Label(self, text="Peso", font=('Segoe UI', 14))
        weight_label.pack(anchor=tk.W, padx=20)
        
        self.weight_entry = ttk.Entry(self)
        self.weight_entry.pack(fill=tk.X, padx=20, pady=5)
        self.weight_entry.insert(0, "0")
        
        # Edad
        age_label = ttk.Label(self, text="Edad", font=('Segoe UI', 14))
        age_label.pack(anchor=tk.W, padx=20)
        
        self.age_entry = ttk.Entry(self)
        self.age_entry.pack(fill=tk.X, padx=20, pady=5)
        self.age_entry.insert(0, "0")
        
        # Botón agregar
        add_btn = ttk.Button(
            self, text="Agregar", 
            command=self.add_animal,
            style='Dark.TButton'
        )
        add_btn.pack(pady=20)
    
    def clear_placeholder(self, event):
        if self.name_entry.get() == "Nombre de la criatura":
            self.name_entry.delete(0, tk.END)
    
    def add_animal(self):
        name = self.name_entry.get().strip()
        specie = self.species_combo.get()
        weight_str = self.weight_entry.get().strip()
        age_str = self.age_entry.get().strip()
        
        # Validaciones
        if not name or name == "Nombre de la criatura":
            messagebox.showerror("Error", "Por favor ingrese un nombre válido")
            return
        
        try:
            weight = float(weight_str)
            age = int(age_str)
            
            if weight < 0 or age < 0:
                messagebox.showerror("Error", "El peso y la edad no pueden ser negativos")
                return
                
            # Crear el animal según la especie
            from animal import Animal
            animal = Animal(name, specie, weight, age)
            
            self.manager.add_animal(animal)
            messagebox.showinfo("Éxito", "Animal agregado correctamente")
            
            # Limpiar campos
            self.name_entry.delete(0, tk.END)
            self.weight_entry.delete(0, tk.END)
            self.weight_entry.insert(0, "0")
            self.age_entry.delete(0, tk.END)
            self.age_entry.insert(0, "0")
            
            # Actualizar la tabla en MenuPanel si existe
            if self.menu_panel:
                self.menu_panel.load_animals_table(self.manager.get_animals())
                
        except ValueError:
            messagebox.showerror("Error", "Ingrese valores numéricos válidos para peso y edad")