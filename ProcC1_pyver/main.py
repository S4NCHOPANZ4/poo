import tkinter as tk
from tkinter import ttk
from views.main_window import MainWindow

def main():
    root = tk.Tk()
    root.title("Zoo App")
    root.geometry("1000x650")
    
    style = ttk.Style()
    style.theme_use('clam')
    style.configure('.', background='#333333', foreground="#5e5e5e")
    style.configure('TButton', background='#333333', foreground="#ffffff", font=('Segoe UI', 12))
    
    app = MainWindow(root)
    root.mainloop()

if __name__ == "__main__":
    main()