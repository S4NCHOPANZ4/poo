import pygame
import sys
import time
import threading
import math
import json
import os
from jugador import Jugador
from enemigo import Enemigo
from bala import Bala
from utils.constantes import WIDTH, HEIGHT, WHITE, BG_COLOR, BLACK, RED, GREEN, FPS

class Juego:
    def __init__(self):
        pygame.init()
        self.pantalla = pygame.display.set_mode((WIDTH, HEIGHT))
        pygame.display.set_caption("Papu Game")
        self.reloj = pygame.time.Clock()
        self.fuente = pygame.font.SysFont(None, 48)
        self.fuente_pequena = pygame.font.SysFont(None, 32)

        try:
            self.fondo = pygame.image.load("./assets/background.png")
            self.fondo = pygame.transform.scale(self.fondo, (WIDTH, HEIGHT))
            self.tiene_fondo = True
        except pygame.error:
            self.fondo = None
            self.tiene_fondo = False

        self.titulo =  pygame.image.load("./assets/title.png")
        self.titulo = pygame.transform.scale(self.titulo, (580, 230))
        self.corazon = pygame.image.load("./assets/vida.png")
        self.corazon = pygame.transform.scale(self.corazon, (30, 30))
        self.jugador = Jugador()
        self.enemigos = []
        self.balas = []
        self.puntaje = 0
        self.vidas = 3
        self.en_ejecucion = True
        self.pausado = False
        self.juego_iniciado = False
        self.juego_terminado = False
        self.tiempo_inicio = None
        self.tiempo_total = 0
        self.tiempo_pausado_total = 0 
        self.tiempo_pausa_inicio = None  
        self.dificultad = 0
        self.cooldown_disparo = 3.0
        self.tiempo_ultimo_disparo = -self.cooldown_disparo
        self.boton_pausa_rect = pygame.Rect(WIDTH - 120, 10, 100, 40)
        self.hilo_enemigos = threading.Thread(target=self.generar_enemigos_periodicamente)
        self.hilo_enemigos.daemon = True

    def dibujar_fondo(self):
        if self.tiene_fondo:
            self.pantalla.blit(self.fondo, (0, 0))
        else:
            self.pantalla.fill(BG_COLOR)

    def generar_enemigos_periodicamente(self):
        while self.en_ejecucion:
            if self.juego_iniciado and not self.pausado:
                self.enemigos.append(Enemigo(self.dificultad))
            time.sleep(1.0)

    def reiniciar_juego(self):
        for enemigo in self.enemigos:
            enemigo.detener()
        
        self.enemigos.clear()
        self.balas.clear()
        
        self.jugador = Jugador()
        self.puntaje = 0
        self.vidas = 3
        self.pausado = False
        self.juego_terminado = False
        self.tiempo_inicio = time.time()
        self.tiempo_total = 0
        self.tiempo_pausado_total = 0
        self.tiempo_pausa_inicio = None
        self.dificultad = 0
        self.cooldown_disparo = 3.0
        self.tiempo_ultimo_disparo = -self.cooldown_disparo

    def pausar_juego(self):
        self.pausado = True
        self.tiempo_pausa_inicio = time.time()
        
        for enemigo in self.enemigos:
            enemigo.pausar()

    def reanudar_juego(self):
        if self.pausado and self.tiempo_pausa_inicio:
            tiempo_pausa = time.time() - self.tiempo_pausa_inicio
            self.tiempo_pausado_total += tiempo_pausa
            
            self.tiempo_ultimo_disparo += tiempo_pausa
            
        self.pausado = False
        self.tiempo_pausa_inicio = None
        
        for enemigo in self.enemigos:
            enemigo.reanudar()

    def manejar_enemigos(self):
        if not self.pausado:
            for enemigo in self.enemigos[:]:
                with enemigo.lock:
                    if enemigo.rect.colliderect(self.jugador.rect):
                        enemigo.detener()
                        self.enemigos.remove(enemigo)
                        self.vidas -= 1
                        if self.vidas <= 0:
                            self.juego_terminado = True
                            self.en_ejecucion = False
                    elif enemigo.rect.y > HEIGHT:
                        enemigo.detener()
                        self.enemigos.remove(enemigo)
                        self.puntaje += 1

    def manejar_balas(self):
        if not self.pausado:
            for bala in self.balas[:]:
                bala.mover()
                if bala.rect.y < 0:
                    self.balas.remove(bala)
                    continue
                for enemigo in self.enemigos[:]:
                    with enemigo.lock:
                        if bala.rect.colliderect(enemigo.rect):
                            enemigo.detener()
                            self.enemigos.remove(enemigo)
                            self.balas.remove(bala)
                            self.puntaje += 10  # suma 10 puntos
                            break

    def intentar_disparo(self):
        if not self.pausado:
            tiempo_actual = time.time()
            if tiempo_actual - self.tiempo_ultimo_disparo >= self.cooldown_disparo:
                self.tiempo_ultimo_disparo = tiempo_actual
                self.balas.append(Bala(self.jugador.rect.centerx, self.jugador.rect.y))

    def obtener_tiempo_juego_real(self):
        if self.tiempo_inicio:
            tiempo_actual = time.time()
            tiempo_transcurrido = tiempo_actual - self.tiempo_inicio
            
            # Si está pausado, agregar el tiempo de pausa actual
            if self.pausado and self.tiempo_pausa_inicio:
                tiempo_pausa_actual = tiempo_actual - self.tiempo_pausa_inicio
                return tiempo_transcurrido - self.tiempo_pausado_total - tiempo_pausa_actual
            else:
                return tiempo_transcurrido - self.tiempo_pausado_total
        return 0

    def mostrar_texto_centrado(self, texto, tamano=48, color=BLACK, y_offset=0):
        fuente = pygame.font.SysFont(None, tamano)
        render = fuente.render(texto, True, color)
        rect = render.get_rect(center=(WIDTH // 2, HEIGHT // 2 + y_offset))
        self.pantalla.blit(render, rect)

    def pantalla_inicio(self):
        esperando = True
        boton_ancho, boton_alto = 250, 60
        boton_rect = pygame.Rect(WIDTH // 2 - boton_ancho // 2, HEIGHT // 2 - 30, boton_ancho, boton_alto)
        boton_ranking = pygame.Rect(WIDTH // 2 - boton_ancho // 2, HEIGHT // 2 + 50, boton_ancho, boton_alto)

        while esperando:
            self.dibujar_fondo()
            mouse_pos = pygame.mouse.get_pos()
            mouse_clic = pygame.mouse.get_pressed()

            if boton_rect.collidepoint(mouse_pos):
                color_boton = (100, 200, 100)
                if mouse_clic[0]:
                    self.juego_iniciado = True
                    self.tiempo_inicio = time.time()
                    esperando = False
            else:
                color_boton = (0, 150, 0)

            pygame.draw.rect(self.pantalla, color_boton, boton_rect, border_radius=12)
            texto = self.fuente.render("Iniciar Juego", True, WHITE)
            texto_rect = texto.get_rect(center=boton_rect.center)
            self.pantalla.blit(texto, texto_rect)

            boton_ranking = pygame.Rect(WIDTH // 2 - 125, HEIGHT // 2 + 50, 250, 60)
            if boton_ranking.collidepoint(mouse_pos):
                color_ranking = (100, 100, 200)
                if mouse_clic[0]:
                    self.pantalla_ranking()
            else:
                color_ranking = (0, 0, 150)

            pygame.draw.rect(self.pantalla, color_ranking, boton_ranking, border_radius=12)
            texto_ranking = self.fuente.render("Ver Ranking", True, WHITE)
            texto_ranking_rect = texto_ranking.get_rect(center=boton_ranking.center)
            self.pantalla.blit(texto_ranking, texto_ranking_rect)
            titulo_rect = self.titulo.get_rect(center=(WIDTH // 2, HEIGHT // 2 - 200))
            self.pantalla.blit(self.titulo, titulo_rect)
            pygame.display.flip()

            for evento in pygame.event.get():
                if evento.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()

    def pantalla_muerte(self):
        self.dibujar_fondo()
        self.mostrar_texto_centrado("¡Fin del juego!", 64, RED, -60)
        self.mostrar_texto_centrado(f"Puntaje: {self.puntaje}", 40, BLACK)
        self.mostrar_texto_centrado(f"Tiempo: {self.formatear_tiempo(self.tiempo_total)}", 30, BLACK, 50)
        self.mostrar_texto_centrado("[R] para guardar tu puntaje", 32, BLACK, 120)
        self.mostrar_texto_centrado("[Q] para salir sin guardar", 32, BLACK, 150)
        pygame.display.flip()

        esperando = True
        while esperando:
            for evento in pygame.event.get():
                if evento.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                if evento.type == pygame.KEYDOWN:
                    if evento.key == pygame.K_r:
                        self.pantalla_ingresar_nombre()
                        self.__init__()
                        self.ejecutar()
                    if evento.key == pygame.K_q:
                        self.pantalla_ingresar_nombre()
                        pygame.quit()
                        sys.exit()

    def pantalla_ingresar_nombre(self):
        nombre = ""
        max_letras = 3
        escribiendo = True

        input_rect = pygame.Rect(WIDTH // 2 - 100, HEIGHT // 2 - 40, 200, 60)
        boton_guardar = pygame.Rect(WIDTH // 2 - 80, HEIGHT // 2 + 40, 160, 50)

        while escribiendo:
            self.dibujar_fondo()
            self.mostrar_texto_centrado("Ingresa tu nombre (3 letras)", 32, BLACK, -100)

            pygame.draw.rect(self.pantalla, WHITE, input_rect)
            pygame.draw.rect(self.pantalla, BLACK, input_rect, 3)
            texto_nombre = self.fuente.render(nombre.upper(), True, BLACK)
            nombre_rect = texto_nombre.get_rect(center=input_rect.center)
            self.pantalla.blit(texto_nombre, nombre_rect)

            mouse_pos = pygame.mouse.get_pos()
            mouse_clic = pygame.mouse.get_pressed()
            color_boton = (0, 180, 0) if boton_guardar.collidepoint(mouse_pos) else (0, 150, 0)
            pygame.draw.rect(self.pantalla, color_boton, boton_guardar, border_radius=10)
            texto_guardar = self.fuente_pequena.render("Guardar", True, WHITE)
            self.pantalla.blit(texto_guardar, texto_guardar.get_rect(center=boton_guardar.center))

            pygame.display.flip()

            for evento in pygame.event.get():
                if evento.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                elif evento.type == pygame.KEYDOWN:
                    if evento.key == pygame.K_BACKSPACE and len(nombre) > 0:
                        nombre = nombre[:-1]
                    elif evento.key == pygame.K_RETURN and len(nombre) == max_letras:
                        escribiendo = False
                    elif evento.unicode.isalpha() and len(nombre) < max_letras:
                        nombre += evento.unicode.upper()
                elif evento.type == pygame.MOUSEBUTTONDOWN and evento.button == 1:
                    if boton_guardar.collidepoint(mouse_pos) and len(nombre) == max_letras:
                        escribiendo = False

        self.guardar_ranking(nombre, self.puntaje, self.formatear_tiempo(self.tiempo_total))

    def cargar_ranking(self):
        if os.path.exists("ranking.json"):
            with open("ranking.json", "r") as f:
                try:
                    data = json.load(f)
                    lista = [{"nombre": k, **v} for k, v in data.items()]
                    return sorted(lista, key=lambda x: x["puntaje"], reverse=True)[:10]
                except json.JSONDecodeError:
                    return []
        return []

    def pantalla_ranking(self):
        mostrando = True
        top_scores = self.cargar_ranking()

        while mostrando:
            self.dibujar_fondo()
            self.mostrar_texto_centrado("Top 10 Puntajes", 48, BLACK, -300)

            for i, entry in enumerate(top_scores):
                texto = f"{i + 1}. {entry['nombre']} - {entry['puntaje']} pts - {entry['tiempo']}"
                render = self.fuente_pequena.render(texto, True, BLACK)
                self.pantalla.blit(render, (WIDTH // 2 - 200, 150 + i * 50))

            self.mostrar_texto_centrado("Presiona ESC para volver", 28, BLACK, 290)

            pygame.display.flip()

            for evento in pygame.event.get():
                if evento.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                elif evento.type == pygame.KEYDOWN and evento.key == pygame.K_ESCAPE:
                    mostrando = False

    def guardar_ranking(self, nombre, puntaje, tiempo):
        ranking = {}
        if os.path.exists("ranking.json"):
            with open("ranking.json", "r") as f:
                try:
                    ranking = json.load(f)
                except json.JSONDecodeError:
                    ranking = {}

        ranking[nombre] = {"puntaje": puntaje, "tiempo": tiempo}
        with open("ranking.json", "w") as f:
            json.dump(ranking, f, indent=4)

    def formatear_tiempo(self, tiempo):
        minutos = int(tiempo) // 60
        segundos = int(tiempo) % 60
        decimas = int((tiempo - int(tiempo)) * 10)
        return f"{minutos:02}:{segundos:02}:{decimas}"

    def mostrar_hud(self):
        for i in range(self.vidas):
            self.pantalla.blit(self.corazon, (10 + i * 35, 10))

        if self.tiempo_inicio:
            self.tiempo_total = self.obtener_tiempo_juego_real()
            tiempo_formateado = self.formatear_tiempo(self.tiempo_total)
            cronometro = self.fuente_pequena.render(f"{tiempo_formateado}", True, BLACK)
            puntaje_render = self.fuente_pequena.render(f"Puntaje: {self.puntaje}", True, BLACK)
            self.pantalla.blit(puntaje_render, (WIDTH - 150, 40))  
            self.pantalla.blit(cronometro, (WIDTH - 150, 10))

        tiempo_actual = time.time()
        tiempo_restante = max(0, self.cooldown_disparo - (tiempo_actual - self.tiempo_ultimo_disparo))
        porcentaje = 1 - (tiempo_restante / self.cooldown_disparo) if not self.pausado else 0

        centro = (WIDTH - 40, HEIGHT - 40)
        radio = 30
        pygame.draw.circle(self.pantalla, (180, 180, 180), centro, radio, 2)

        if porcentaje >= 1 and not self.pausado:
            pygame.draw.circle(self.pantalla, GREEN, centro, radio - 4)
        elif not self.pausado:
            end_angle = 2 * math.pi * porcentaje
            pygame.draw.arc(self.pantalla, RED,
                            (centro[0] - radio + 2, centro[1] - radio + 2, (radio - 2) * 2, (radio - 2) * 2),
                            -0.5 * math.pi, (-0.5 * math.pi + end_angle), 6)

    def ejecutar(self):
        self.hilo_enemigos.start()
        self.pantalla_inicio()

        while self.en_ejecucion:
            self.reloj.tick(FPS)
            self.dibujar_fondo()

            for evento in pygame.event.get():
                if evento.type == pygame.QUIT:
                    self.en_ejecucion = False
                if evento.type == pygame.KEYDOWN:
                    if evento.key == pygame.K_p:
                        if self.pausado:
                            self.reanudar_juego()
                        else:
                            self.pausar_juego()
                    if evento.key == pygame.K_SPACE and not self.pausado:
                        self.intentar_disparo()
            if not self.pausado:
                keys = pygame.key.get_pressed()
                self.jugador.mover(keys)
                self.manejar_enemigos()
                self.manejar_balas()
                if self.tiempo_inicio:
                    self.dificultad = self.obtener_tiempo_juego_real() // 10

            self.jugador.dibujar(self.pantalla)
            for enemigo in self.enemigos:
                enemigo.dibujar(self.pantalla)
            for bala in self.balas:
                bala.dibujar(self.pantalla)

            self.mostrar_hud()

            if self.pausado:
                self.mostrar_texto_centrado("PAUSA", 60, RED)
                self.mostrar_texto_centrado("Presiona P para continuar", 32, BLACK, 60)
                
                # Botón de reinicio
                mouse_pos = pygame.mouse.get_pos()
                mouse_clic = pygame.mouse.get_pressed()
                
                boton_reinicio = pygame.Rect(WIDTH // 2 - 100, HEIGHT // 2 + 120, 200, 50)
                color_boton = (200, 100, 100) if boton_reinicio.collidepoint(mouse_pos) else (180, 80, 80)
                
                pygame.draw.rect(self.pantalla, color_boton, boton_reinicio, border_radius=10)
                pygame.draw.rect(self.pantalla, BLACK, boton_reinicio, 2, border_radius=10)
                
                texto_reinicio = self.fuente_pequena.render("Reiniciar Juego", True, WHITE)
                texto_rect = texto_reinicio.get_rect(center=boton_reinicio.center)
                self.pantalla.blit(texto_reinicio, texto_rect)
                
                if boton_reinicio.collidepoint(mouse_pos) and mouse_clic[0]:
                    self.reiniciar_juego()

            pygame.display.flip()

        self.pantalla_muerte()