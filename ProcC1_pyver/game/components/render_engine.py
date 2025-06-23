class RenderEngine:
    def draw_oval(self, canvas, x, y, width, height, color):
        canvas.create_oval(x, y, x + width, y + height, fill=color, outline="")
    
    def draw_border(self, canvas, x, y, width, height, color):
        canvas.create_rectangle(x, y, x + width, y + height, outline=color)
    
    def draw_text(self, canvas, text, x, y, font, color):
        canvas.create_text(x, y, text=text, font=font, fill=color)