import tkinter as tk
from tkinter import ttk
from views.menu_panel import MenuPanel
from views.add_animal import AddAnimalPanel

class MainWindow:
    def __init__(self, root):
        self.root = root
        self.menu = None
        self.add_animal = None
        
        self.create_widgets()
    
    def create_widgets(self):
        # Panel izquierdo (menú)
        left_panel = ttk.Frame(self.root, width=200, style='Dark.TFrame')
        left_panel.pack(side=tk.LEFT, fill=tk.Y)
        
        # Panel derecho (contenido)
        self.content_panel = ttk.Frame(self.root)
        self.content_panel.pack(side=tk.RIGHT, expand=True, fill=tk.BOTH)
        
        # Título
        title = ttk.Label(left_panel, text="Menu Zoo", font=('Segoe UI', 24), style='Dark.TLabel')
        title.pack(pady=20)
        
        # Botones
        consult_btn = ttk.Button(
            left_panel, text="Consultar", 
            command=self.show_menu,
            style='Dark.TButton'
        )
        consult_btn.pack(pady=10, padx=20, fill=tk.X)
        
        new_btn = ttk.Button(
            left_panel, text="Nuevo", 
            command=self.show_add_animal,
            style='Dark.TButton'
        )
        new_btn.pack(pady=10, padx=20, fill=tk.X)
        
        # Mostrar el menú por defecto
        self.show_menu()
    
    def show_menu(self):
        self.clear_content()
        if self.menu is None:
            from animal_manager import AnimalManager
            manager = AnimalManager()
            self.menu = MenuPanel(self.content_panel, manager)
        self.menu.pack(expand=True, fill=tk.BOTH)
    
    def show_add_animal(self):
        self.clear_content()
        if self.add_animal is None:
            from animal_manager import AnimalManager
            manager = AnimalManager()
            self.add_animal = AddAnimalPanel(self.content_panel, manager, self.menu)
        self.add_animal.pack(expand=True, fill=tk.BOTH)
    
    def clear_content(self):
        for widget in self.content_panel.winfo_children():
            widget.pack_forget()