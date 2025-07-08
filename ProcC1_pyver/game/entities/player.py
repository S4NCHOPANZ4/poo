from PIL import Image, ImageTk
import tkinter as tk
from game.components.position import Position
class Player:
    SIZE = 30
    SPEED = 5
    
    def __init__(self, start_x, start_y):
        self.position = Position(start_x, start_y)  # Composicion
        self.player_image = None
        self.image_tk = None
    
    def move_left(self, max_width):
        if self.position.get_x() > 0:
            self.position.move(-self.SPEED, 0)
    
    def move_right(self, max_width):
        if self.position.get_x() < max_width - self.SIZE:
            self.position.move(self.SPEED, 0)
    
    def move_up(self):
        if self.position.get_y() > 0:
            self.position.move(0, -self.SPEED)
    
    def move_down(self, max_height):
        if self.position.get_y() < max_height - self.SIZE:
            self.position.move(0, self.SPEED)
    
    def reset(self):
        self.position.set_position(50, 50)
    
    def draw(self, canvas, renderer):
        x = self.position.get_x()
        y = self.position.get_y()
        
        if self.image_tk:
            canvas.create_image(x, y, image=self.image_tk, anchor=tk.NW)
        else:
            renderer.draw_oval(canvas, x, y, self.SIZE, self.SIZE, "blue")
    
    def set_player_image(self, image_path):
        try:
            img = Image.open(image_path)
            img = img.resize((self.SIZE, self.SIZE), Image.LANCZOS)
            self.image_tk = ImageTk.PhotoImage(img)
        except Exception as e:
            print(f"Error loading player image: {e}")
            self.image_tk = None
    
    def get_position(self): return self.position
    def get_size(self): return self.SIZE