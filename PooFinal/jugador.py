import pygame
from utils.constantes import PLAYER_SIZE, WIDTH

class Jugador:
    def __init__(self):
        self.imagen = pygame.image.load("./assets/player.png")
        self.imagen = pygame.transform.scale(self.imagen, (PLAYER_SIZE, PLAYER_SIZE))
        self.rect = self.imagen.get_rect()
        self.rect.centerx = WIDTH // 2
        self.rect.bottom = 720
        self.velocidad = 7

    def mover(self, keys):
        if keys[pygame.K_LEFT] and self.rect.left > 0:
            self.rect.x -= self.velocidad
        if keys[pygame.K_RIGHT] and self.rect.right < WIDTH:
            self.rect.x += self.velocidad

    def dibujar(self, pantalla):
        pantalla.blit(self.imagen, self.rect)
