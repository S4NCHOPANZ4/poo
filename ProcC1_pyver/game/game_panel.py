import tkinter as tk
from tkinter import ttk
from game.game_canvas import GameCanvas

class GamePanel(tk.Toplevel):
    def __init__(self, parent, image_path):
        super().__init__(parent)
        self.title("El bro game")
        self.geometry("420x350")
        self.resizable(False, False)
        
        self.game_canvas = GameCanvas(self, image_path)
        self.game_canvas.pack(expand=True, fill=tk.BOTH, padx=10, pady=10)
        self.protocol("WM_DELETE_WINDOW", self.on_close)
    
    def on_close(self):
        self.destroy()