class ScoreSystem:
    def __init__(self):
        self.score = 0
        self.coins_collected = 0
    
    def add_score(self, points):
        self.score += points
        self.coins_collected += 1
    
    def reset(self):
        self.score = 0
        self.coins_collected = 0
    
    def get_score(self): return self.score
    def get_coins_collected(self): return self.coins_collected