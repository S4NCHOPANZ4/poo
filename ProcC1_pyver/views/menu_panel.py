import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
from views.filters import FiltersWindow

class MenuPanel(ttk.Frame):
    def __init__(self, parent, manager):
        super().__init__(parent)
        self.manager = manager
        self.current_text_filter = ""
        self.current_species_filter = "Todos"
        self.current_age_filter = "Todos"
        self.current_weight_filter = "Todos"
        
        self.create_widgets()
        self.load_animals_table(self.manager.get_animals())
    
    def create_widgets(self):
        # Título
        title = ttk.Label(self, text="Menu", font=('Segoe UI', 18))
        title.pack(pady=10)
        
        # Buscador
        search_label = ttk.Label(self, text="Buscar por nombre")
        search_label.pack(anchor=tk.W, padx=10)
        
        self.search_entry = ttk.Entry(self)
        self.search_entry.pack(fill=tk.X, padx=10, pady=5)
        self.search_entry.bind('<KeyRelease>', self.update_text_filter)
        
        # Botón de filtros
        filter_btn = ttk.Button(self, text="Filtrar", command=self.open_filters)
        filter_btn.pack(pady=5)
        
        # Tabla de animales
        self.create_table()
    
    def create_table(self):
        columns = ("Nombre", "Especie", "Peso", "Edad")
        self.tree = ttk.Treeview(self, columns=columns, show='headings')
        
        for col in columns:
            self.tree.heading(col, text=col)
            self.tree.column(col, width=150, anchor=tk.CENTER)
        
        self.tree.pack(expand=True, fill=tk.BOTH, padx=10, pady=10)
        self.tree.bind('<Double-1>', self.on_row_double_click)
    
    def load_animals_table(self, animals):
        # Limpiar tabla
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        # Filtrar animales
        filtered = self.filter_animals(animals)
        
        # Agregar a la tabla
        for animal in filtered:
            self.tree.insert('', tk.END, values=(
                animal.name,
                animal.specie,
                animal.weight,
                animal.age
            ))
    
    def filter_animals(self, animals):
        filtered = []
        for animal in animals:
            # Filtro por texto
            if (self.current_text_filter and 
                self.current_text_filter.lower() not in animal.name.lower()):
                continue
                
            # Filtro por especie
            if (self.current_species_filter != "Todos" and 
                animal.specie != self.current_species_filter):
                continue
                
            # Filtro por edad
            if not self.apply_age_filter(animal.age):
                continue
                
            # Filtro por peso
            if not self.apply_weight_filter(animal.weight):
                continue
                
            filtered.append(animal)
        return filtered
    
    def apply_age_filter(self, age):
        if self.current_age_filter == "Todos":
            return True
        elif self.current_age_filter == "Edad < 1":
            return age < 1
        elif self.current_age_filter == "1 ≤ Edad < 5":
            return 1 <= age < 5
        elif self.current_age_filter == "5 ≤ Edad < 8":
            return 5 <= age < 8
        elif self.current_age_filter == "Edad ≥ 8":
            return age >= 8
        return True
    
    def apply_weight_filter(self, weight):
        if self.current_weight_filter == "Todos":
            return True
        elif self.current_weight_filter == "Peso < 10kg":
            return weight < 10
        elif self.current_weight_filter == "10kg ≤ Peso < 50kg":
            return 10 <= weight < 50
        elif self.current_weight_filter == "50kg ≤ Peso < 100kg":
            return 50 <= weight < 100
        elif self.current_weight_filter == "Peso ≥ 100kg":
            return weight >= 100
        return True
    
    def update_text_filter(self, event=None):
        self.current_text_filter = self.search_entry.get()
        self.load_animals_table(self.manager.get_animals())
    
    def open_filters(self):
        FiltersWindow(self, self.apply_filters)
    
    def apply_filters(self, species, age, weight):
        self.current_species_filter = species
        self.current_age_filter = age
        self.current_weight_filter = weight
        self.load_animals_table(self.manager.get_animals())
    
    def on_row_double_click(self, event):
        item = self.tree.selection()[0]
        values = self.tree.item(item, 'values')
        
        from views.extra_info import ExtraInfoWindow
        ExtraInfoWindow(
            self,
            self.manager,
            values[0],  # name
            values[1],  # specie
            float(values[2]),  # weight
            int(values[3])   # age
        )