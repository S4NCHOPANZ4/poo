class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def get_x(self): return self.x
    def get_y(self): return self.y
    def set_x(self, x): self.x = x
    def set_y(self, y): self.y = y
    
    def distance_to(self, other):
        return ((other.x - self.x)**2 + (other.y - self.y)**2)**0.5


class Position:
    def __init__(self, x, y):
        self.coordinate = Point(x, y)  # Composición
    
    def get_x(self): return self.coordinate.get_x()
    def get_y(self): return self.coordinate.get_y()
    
    def move(self, delta_x, delta_y):
        self.coordinate.set_x(self.coordinate.get_x() + delta_x)
        self.coordinate.set_y(self.coordinate.get_y() + delta_y)
    
    def set_position(self, x, y):
        self.coordinate.set_x(x)
        self.coordinate.set_y(y)
    
    def distance_to(self, other):
        return self.coordinate.distance_to(other.coordinate)