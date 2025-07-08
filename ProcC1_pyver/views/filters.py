import tkinter as tk
from tkinter import ttk

class FiltersWindow(tk.Toplevel):
    def __init__(self, parent, callback):
        super().__init__(parent)
        self.callback = callback
        
        self.title("Filtros de búsqueda")
        self.geometry("350x400")
        self.configure(bg="#333333")
        self.create_widgets()
    
    def create_widgets(self):
        title = ttk.Label(self, text="Filtros de búsqueda", font=('Segoe UI', 18))
        title.pack(pady=10)
        weight_label = ttk.Label(self, text="Peso")
        weight_label.pack(anchor=tk.W, padx=20)

        
        self.weight_combo = ttk.Combobox(self, values=[
            "Todos",
            "Peso < 10kg",
            "10kg ≤ Peso < 50kg",
            "50kg ≤ Peso < 100kg",
            "Peso ≥ 100kg"
        ])
        self.weight_combo.pack(fill=tk.X, padx=20, pady=5)
        self.weight_combo.current(0)
        
        age_label = ttk.Label(self, text="Edad")
        age_label.pack(anchor=tk.W, padx=20)
        
        self.age_combo = ttk.Combobox(self, values=[
            "Todos",
            "Edad < 1",
            "1 ≤ Edad < 5",
            "5 ≤ Edad < 8",
            "Edad ≥ 8"
        ])
        self.age_combo.pack(fill=tk.X, padx=20, pady=5)
        self.age_combo.current(0)
        
        species_label = ttk.Label(self, text="Especie")
        species_label.pack(anchor=tk.W, padx=20)
        
        self.species_combo = ttk.Combobox(self, values=[
            "Todos",
            "Cow",
            "Fox",
            "Chicken",
            "Donkey",
            "Sheep",
            "Horse",
            "Llama"
        ])
        self.species_combo.pack(fill=tk.X, padx=20, pady=5)
        self.species_combo.current(0)
        
        save_btn = ttk.Button(
            self, text="Guardar", 
            command=self.on_save
        )
        save_btn.pack(pady=20)
    
    def on_save(self):
        species = self.species_combo.get()
        age = self.age_combo.get()
        weight = self.weight_combo.get()
        
        self.callback(species, age, weight)
        self.destroy()