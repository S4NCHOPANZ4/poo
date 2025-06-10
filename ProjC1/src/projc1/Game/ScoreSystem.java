package projc1.Game;
class ScoreSystem {
    private int score;
    private int coinsCollected;
    
    public ScoreSystem() {
        this.score = 0;
        this.coinsCollected = 0;
    }
    
    public void addScore(int points) {
        this.score += points;
        this.coinsCollected++;
    }
    
    public void reset() {
        this.score = 0;
        this.coinsCollected = 0;
    }
    
    public int getScore() { return score; }
    public int getCoinsCollected() { return coinsCollected; }
}