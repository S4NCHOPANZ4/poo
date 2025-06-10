
package projc1.Game;

class PositionGenerator {
    private java.util.Random random;
    
    public PositionGenerator() {
        this.random = new java.util.Random();
    }
    
    // DEPENDENCIA: Este método depende de Position pero no la almacena
    public Position generateRandomPosition(int maxX, int maxY, Position playerPos, int minDistance) {
        int x, y;
        Position newPos;
        
        do {
            x = random.nextInt(maxX);
            y = random.nextInt(maxY);
            newPos = new Position(x, y);
        } while (newPos.distanceTo(playerPos) < minDistance);
        
        return newPos;
    }
}
