package projc1.Game;
import java.awt.Graphics2D;
import java.net.URL;
import java.awt.Image;
import java.awt.Toolkit;
class Player {
    private Position position; // COMPOSICIÓN: Player tiene Position
    private static final int SIZE = 30;
    private static final int SPEED = 5;
    private Image playerImage;

    public Player(int startX, int startY) {
        this.position = new Position(startX, startY); // COMPOSICIÓN
        playerImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/cow.png"));

    }
    
    public void moveLeft(int maxWidth) {
        if (position.getX() > 0) {
            position.move(-SPEED, 0);
        }
    }
    
    public void moveRight(int maxWidth) {
        if (position.getX() < maxWidth - SIZE) {
            position.move(SPEED, 0);
        }
    }
    
    public void moveUp() {
        if (position.getY() > 0) {
            position.move(0, -SPEED);
        }
    }
    
    public void moveDown(int maxHeight) {
        if (position.getY() < maxHeight - SIZE) {
            position.move(0, SPEED);
        }
    }
    
    public void reset() {
        position.setPosition(50, 50);
    }
    
    // ASOCIACIÓN: Usa RenderEngine para dibujarse
    public void draw(java.awt.Graphics2D g2d, RenderEngine renderer) {
        int x = position.getX();
        int y = position.getY();
        g2d.drawImage(playerImage, x, y, SIZE, SIZE, null);

    }

    public void setPlayerImage(String resourcePath) {
        if (resourcePath.startsWith("src/")) {
            resourcePath = resourcePath.substring(3); 
        }
        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            System.err.println("ERROR: No se encontró la imagen: " + resourcePath);
            return;
        }

        System.out.println("Imagen cargada correctamente desde: " + url.toString());
        playerImage = Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public Position getPosition() { return position; }
    public int getSize() { return SIZE; }
}
