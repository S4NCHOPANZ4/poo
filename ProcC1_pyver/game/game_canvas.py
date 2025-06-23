import tkinter as tk
from tkinter import font as tkfont


from game.components.position_generator import PositionGenerator
from game.components.render_engine import RenderEngine
from game.components.score_system import ScoreSystem
from game.entities.coin import Coin
from game.entities.player import Player

class GameCanvas(tk.Canvas):
    def __init__(self, parent, image_path):
        super().__init__(parent, bg="black", width=400, height=300)
        
        # Agregación
        self.score_system = ScoreSystem()
        self.render_engine = RenderEngine()
        self.position_generator = PositionGenerator()
        
        # Composición
        self.player = Player(50, 50)
        self.player.set_player_image(image_path)
        
        self.coin = None
        self.generate_new_coin()
        
        # Configuración de eventos
        self.bind("<KeyPress>", self.on_key_press)
        self.focus_set()
        
        # Game loop
        self.after(16, self.update_game)
    
    def generate_new_coin(self):
        max_x = self.winfo_width() - 20 if self.winfo_width() > 20 else 380
        max_y = self.winfo_height() - 20 if self.winfo_height() > 20 else 280
        
        coin_pos = self.position_generator.generate_random_position(
            max_x, max_y, self.player.get_position(), 50)
        
        if coin_pos:
            self.coin = Coin(coin_pos.get_x(), coin_pos.get_y())
    
    def draw(self):
        self.delete("all")
        
        # Dibujar moneda
        if self.coin:
            self.coin.draw(self, self.render_engine)
        
        # Dibujar jugador
        self.player.draw(self, self.render_engine)
        
        # Dibujar UI
        title_font = tkfont.Font(family="Arial", size=12, weight="bold")
        self.render_engine.draw_text(
            self, f"Puntuación: {self.score_system.get_score()}", 
            10, 20, title_font, "white")
        self.render_engine.draw_text(
            self, f"Monedas: {self.score_system.get_coins_collected()}", 
            10, 40, title_font, "white")
    
    def check_collisions(self):
        if (self.coin and 
            self.coin.intersects(self.player.get_position(), self.player.get_size())):
            self.score_system.add_score(self.coin.get_value())
            self.generate_new_coin()
    
    def on_key_press(self, event):
        key = event.keysym
        
        if key in ("Left", "a"):
            self.player.move_left(self.winfo_width())
        elif key in ("Right", "d"):
            self.player.move_right(self.winfo_width())
        elif key in ("Up", "w"):
            self.player.move_up()
        elif key in ("Down", "s"):
            self.player.move_down(self.winfo_height())
        
        self.draw()
    
    def update_game(self):
        self.check_collisions()
        self.draw()
        self.after(16, self.update_game)