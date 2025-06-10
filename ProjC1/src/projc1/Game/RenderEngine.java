package projc1.Game;

class RenderEngine {
    public void drawRectangle(java.awt.Graphics2D g2d, int x, int y, int width, int height, java.awt.Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }
    
    public void drawOval(java.awt.Graphics2D g2d, int x, int y, int width, int height, java.awt.Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, width, height);
    }
    
    public void drawBorder(java.awt.Graphics2D g2d, int x, int y, int width, int height, java.awt.Color color) {
        g2d.setColor(color);
        g2d.drawRect(x, y, width, height);
    }
    
    public void drawText(java.awt.Graphics2D g2d, String text, int x, int y, java.awt.Font font, java.awt.Color color) {
        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text, x, y);
    }
}