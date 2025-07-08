import pygame
import random
import threading
import time
from utils.constantes import WIDTH, HEIGHT, ENEMY_SIZE, RED

class Enemigo:
    def __init__(self, dificultad):
        self.imagen = pygame.image.load("./assets/enemy.png")  
        self.imagen = pygame.transform.scale(self.imagen, (ENEMY_SIZE, ENEMY_SIZE))
        
        x = random.randint(0, WIDTH - ENEMY_SIZE)
        self.rect = self.imagen.get_rect()
        self.rect.topleft = (x, 0)

        self.velocidad = random.uniform(3, 5 + dificultad)
        self.activo = True
        self.pausado = False  
        self.lock = threading.Lock()

        self.thread = threading.Thread(target=self.mover_continuamente)
        self.thread.daemon = True
        self.thread.start()

    def mover_continuamente(self):
        while self.activo:
            if not self.pausado:
                with self.lock:
                    self.rect.y += self.velocidad
            time.sleep(1 / 60)

    def pausar(self):
        self.pausado = True

    def reanudar(self):
        self.pausado = False

    def detener(self):
        self.activo = False

    def dibujar(self, pantalla):
        pantalla.blit(self.imagen, self.rect)