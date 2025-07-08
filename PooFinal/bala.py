import pygame

class Bala:
    def __init__(self, x, y):
        self.imagen = pygame.image.load("./assets/bullet.png")  
        self.imagen = pygame.transform.scale(self.imagen, (20, 20)) 
        self.rect = self.imagen.get_rect()
        self.rect.centerx = x 
        self.rect.y = y
        self.velocidad = 12

    def mover(self):
        self.rect.y -= self.velocidad

    def dibujar(self, pantalla):
        pantalla.blit(self.imagen, self.rect)
