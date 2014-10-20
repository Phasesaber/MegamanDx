package megamanDx.entites.player.character;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Char {
	
	private String name; //Megaman
	private String desc; //Blue Bomber
	
	private int width;
	private int height;
	private int cwidth;
	private int cheight;
	private double moveSpeed;
	private double maxSpeed;
	private double stopSpeed;
	private double fallSpeed;
	private double maxFallSpeed;
	private double jumpStart;
	private double stopJumpSpeed;
	private boolean facingRight = true;
	private int health, maxHealth;
	private int laserDamage;
	private int punchDamage;
	private int punchRange;
	private boolean canGlide;
	private boolean gliding;
	private int[] frames;
	
	private BufferedImage spritesheet;
	
	public Char(String name, String desc, int width, int height,
			int cwidth, int cheight, double moveSpeed, double maxSpeed,
			double stopSpeed, double fallSpeed, double maxFallSpeed, double jumpStart,
			double stopJumpSpeed, boolean facingRight, int health,
			int laserDamage, int punchDamage, int punchRange, boolean canGlide, int[] frames) {
		super();
		this.name = name;
		this.desc = desc;
		this.width = width;
		this.height = height;
		this.cwidth = cwidth;
		this.cheight = cheight;
		this.moveSpeed = moveSpeed;
		this.maxSpeed = maxSpeed;
		this.stopSpeed = stopSpeed;
		this.fallSpeed = fallSpeed;
		this.maxFallSpeed = maxFallSpeed;
		this.jumpStart = jumpStart;
		this.stopJumpSpeed = stopJumpSpeed;
		this.facingRight = facingRight;
		this.health = this.maxHealth =  health;
		this.laserDamage = laserDamage;
		this.punchDamage = punchDamage;
		this.punchRange = punchRange;
		this.canGlide = canGlide;
		this.gliding = false;
		this.frames = frames;
		try {
			spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Character/" + name.toLowerCase() + ".png"));
		} catch(IOException e){e.printStackTrace();}
	}
	
	public static final Char Megaman = new Char("Megaman", "Blue Bomber", 30, 30, 20, 20, 0.3, 1.6, 1.0, 0.15, 4.0, -4.8, 0.3, true, 5, 5, 3, 40, true, new int[]{2,3,1,1,3,1,2,3,1});
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCwidth() {
		return cwidth;
	}

	public void setCwidth(int cwidth) {
		this.cwidth = cwidth;
	}

	public int getCheight() {
		return cheight;
	}

	public void setCheight(int cheight) {
		this.cheight = cheight;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getStopSpeed() {
		return stopSpeed;
	}

	public void setStopSpeed(int stopSpeed) {
		this.stopSpeed = stopSpeed;
	}

	public double getFallSpeed() {
		return fallSpeed;
	}

	public void setFallSpeed(int fallSpeed) {
		this.fallSpeed = fallSpeed;
	}

	public double getMaxFallSpeed() {
		return maxFallSpeed;
	}

	public void setMaxFallSpeed(int maxFallSpeed) {
		this.maxFallSpeed = maxFallSpeed;
	}

	public double getJumpStart() {
		return jumpStart;
	}

	public void setJumpStart(int jumpStart) {
		this.jumpStart = jumpStart;
	}

	public double getStopJumpSpeed() {
		return stopJumpSpeed;
	}

	public void setStopJumpSpeed(int stopJumpSpeed) {
		this.stopJumpSpeed = stopJumpSpeed;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getLaserDamage() {
		return laserDamage;
	}

	public void setLaserDamage(int laserDamage) {
		this.laserDamage = laserDamage;
	}

	public int getPunchDamage() {
		return punchDamage;
	}

	public void setPunchDamage(int punchDamage) {
		this.punchDamage = punchDamage;
	}

	public int getPunchRange() {
		return punchRange;
	}

	public void setPunchRange(int punchRange) {
		this.punchRange = punchRange;
	}

	public boolean getCanGlide() {
		return canGlide;
	}

	public void setCanGlide(boolean canGlide) {
		this.canGlide = canGlide;
	}

	public boolean isGliding() {
		return gliding;
	}

	public void setGliding(boolean gliding) {
		this.gliding = gliding;
	}

	public BufferedImage getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(BufferedImage spritesheet) {
		this.spritesheet = spritesheet;
	}

	public int[] getFrames() {
		return frames;
	}
	
}
