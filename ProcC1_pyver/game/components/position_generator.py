import random
from game.components.position import Position

class PositionGenerator:
    def __init__(self):
        self.random = random.Random()
    
    def generate_random_position(self, max_x, max_y, player_pos, min_distance):
        x, y = 0, 0
        new_pos = None
        
        for _ in range(100): 
            x = self.random.randint(20, max_x - 20)
            y = self.random.randint(20, max_y - 20)
            new_pos = Position(x, y)
            
            if new_pos.distance_to(player_pos) >= min_distance:
                break
        
        return new_pos