package projc1.Game;

class Position {
    private Point coordinate; // COMPOSICIÓN: Position contiene Point
    
    public Position(int x, int y) {
        this.coordinate = new Point(x, y); // Point se crea con Position
    }
    
    public int getX() { return coordinate.getX(); }
    public int getY() { return coordinate.getY(); }
    
    public void move(int deltaX, int deltaY) {
        coordinate.setX(coordinate.getX() + deltaX);
        coordinate.setY(coordinate.getY() + deltaY);
    }
    
    public void setPosition(int x, int y) {
        coordinate.setX(x);
        coordinate.setY(y);
    }
    
    public double distanceTo(Position other) {
        return this.coordinate.distanceTo(other.coordinate);
    }
}
