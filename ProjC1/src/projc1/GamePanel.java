package projc1;
import projc1.Game.GameCanvas;
import javax.swing.*;

public class GamePanel extends JFrame {
    
    public GamePanel(String imageRoute) {
        GameCanvas gameCanvas = new GameCanvas(imageRoute);
        setTitle("El bro game");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        add(gameCanvas);
        setSize(420, 350); 
        setLocationRelativeTo(null);
        setVisible(true);
        gameCanvas.requestFocusInWindow();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GamePanel("/res/cow.png");
        });
    }
}