public class Comet {
    int x, y, width, height;
    public Image cometSprite;
    cometSprite = new ImageIcon("assets/comet.png".getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    public Comet(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
   }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width / 2, height / 2);
    }
    pubic void move() {
        x -=5;
        y-=5;
    }
    public void draw(Graphics g) {
        g.drawImage(cometSprite, x, y, width, height, null);
    }
} 
