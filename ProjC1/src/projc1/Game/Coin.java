
package projc1.Game;

class Coin {
    private Position position; // COMPOSICIÓN: Coin tiene Position
    private java.awt.Color color;
    private int value;
    private static final int SIZE = 20;
    
    public Coin(int x, int y) {
        this.position = new Position(x, y); // COMPOSICIÓN
        this.color = java.awt.Color.YELLOW;
        this.value = 10;
    }
    
    // ASOCIACIÓN: Usa RenderEngine para dibujarse
    public void draw(java.awt.Graphics2D g2d, RenderEngine renderer) {
        int x = position.getX();
        int y = position.getY();
        
        // Dibujar la moneda
        renderer.drawOval(g2d, x, y, SIZE, SIZE, color);
        renderer.drawOval(g2d, x, y, SIZE, SIZE, color.darker());
        
        // Punto brillante
        renderer.drawOval(g2d, x + 5, y + 5, 6, 6, java.awt.Color.WHITE);
        
        // Símbolo de moneda
        renderer.drawText(g2d, "$", x + 6, y + 14, 
                         new java.awt.Font("Arial", java.awt.Font.BOLD, 12), 
                         java.awt.Color.BLACK);
    }
    
    public boolean intersects(Position playerPos, int playerSize) {
        int x = position.getX();
        int y = position.getY();
        int px = playerPos.getX();
        int py = playerPos.getY();
        
        return px < x + SIZE && px + playerSize > x && 
               py < y + SIZE && py + playerSize > y;
    }
    
    public int getValue() { return value; }
    public Position getPosition() { return position; }
}