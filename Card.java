import java.awt.Graphics;

public class Card {
	String name;
	int mana;
	int attack;
	int health;
	int x;
	int y;
	static int width = 75;
	static int height = 100;

	public Card(String[] a) {
		name = a[0];
		mana = Integer.parseInt(a[1]);
		attack = Integer.parseInt(a[2]);
		health = Integer.parseInt(a[3]);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void takeDamage(int damage) {
		health = health - damage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public void click() {
		width *= 1.2;
		height *= 1.2;
	}

	public void unclick() {
		width /= 1.2;
		height /= 1.2;
	}

	public String getName() {
		return name;
	}

	public int getMana() {
		return mana;
	}

	public int getAttack() {
		return attack;
	}

	public int getHealth() {
		return health;
	}

	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
}