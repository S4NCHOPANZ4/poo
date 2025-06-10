package projc1.Game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameCanvas extends JPanel implements KeyListener, ActionListener {
    // AGREGACIÓN: GameCanvas tiene estos objetos pero pueden existir independientemente
    private ScoreSystem scoreSystem;
    private RenderEngine renderEngine;
    private PositionGenerator positionGenerator;
    
    // COMPOSICIÓN: Estos objetos son parte integral del juego
    private Player player;
    private Coin coin;
    private Timer timer;
    
    public GameCanvas(String imageRoute) {
        initializeGame(imageRoute);
        setupUI();
    }
    
    private void initializeGame(String imageRoute) {
        // AGREGACIÓN: Creamos objetos que podrían existir independientemente
        scoreSystem = new ScoreSystem();
        renderEngine = new RenderEngine();
        positionGenerator = new PositionGenerator();
        
        // COMPOSICIÓN: Estos objetos son parte integral del juego
        player = new Player(50, 50);
        player.setPlayerImage(imageRoute);

        coin = null;
        
        generateNewCoin();
        
        // Timer para el game loop
        timer = new Timer(16, this);
        timer.start();
    }
    
    private void setupUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }
    
    // DEPENDENCIA: Usa PositionGenerator para crear nueva moneda
    private void generateNewCoin() {
        int maxX = getWidth() - 20;
        int maxY = getHeight() - 20;
        
        if (maxX <= 0) maxX = 350;
        if (maxY <= 0) maxY = 250;
        
        // DEPENDENCIA: Usamos PositionGenerator
        Position coinPosition = positionGenerator.generateRandomPosition(
            maxX, maxY, player.getPosition(), 50);
        
        coin = new Coin(coinPosition.getX(), coinPosition.getY());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // ASOCIACIÓN: Usar RenderEngine para dibujar
        if (coin != null) {
            coin.draw(g2d, renderEngine);
        }
        
        player.draw(g2d, renderEngine);
        
        // Dibujar UI usando RenderEngine
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        Font instructionFont = new Font("Arial", Font.PLAIN, 12);
        
        renderEngine.drawText(g2d, "Puntuación: " + scoreSystem.getScore(), 
                             10, 25, titleFont, Color.WHITE);
        renderEngine.drawText(g2d, "Monedas: " + scoreSystem.getCoinsCollected(), 
                             10, 45, titleFont, Color.WHITE);
        

    }
    
    private void checkCollisions() {
        if (coin != null && coin.intersects(player.getPosition(), player.getSize())) {
            // AGREGACIÓN: Usar ScoreSystem
            scoreSystem.addScore(coin.getValue());
            generateNewCoin();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                player.moveLeft(getWidth());
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                player.moveRight(getWidth());
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                player.moveDown(getHeight());
                break;

        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollisions();
        repaint();
    }
}