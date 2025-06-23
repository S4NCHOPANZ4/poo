
from game.components.position import Position


class Coin:
    SIZE = 20
    
    def __init__(self, x, y):
        self.position = Position(x, y)  # Composición
        self.color = "gold"
        self.value = 10
    
    def draw(self, canvas, renderer):
        x = self.position.get_x()
        y = self.position.get_y()
        
        # Moneda
        renderer.draw_oval(canvas, x, y, self.SIZE, self.SIZE, self.color)
        renderer.draw_oval(canvas, x, y, self.SIZE, self.SIZE, "#daa520")  # borde
        
        # Punto brillante
        renderer.draw_oval(canvas, x + 5, y + 5, 6, 6, "white")
        
        # Símbolo de moneda
        renderer.draw_text(canvas, "$", x + 8, y + 14, ("Arial", 10, "bold"), "black")
    
    def intersects(self, player_pos, player_size):
        x = self.position.get_x()
        y = self.position.get_y()
        px = player_pos.get_x()
        py = player_pos.get_y()
        
        return (px < x + self.SIZE and px + player_size > x and 
                py < y + self.SIZE and py + player_size > y)
    
    def get_value(self): return self.value
    def get_position(self): return self.position