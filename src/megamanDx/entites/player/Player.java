package megamanDx.entites.player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import megamanDx.entites.Animation;
import megamanDx.entites.Entity;
import megamanDx.tileMap.TileMap;


public class Player extends Entity {

	private int health;
	private int maxHealth;
	private boolean dead;
	private boolean flinching;
	private long flinchTime;
	
	private boolean firing;
	private int laserDamage;
	
	private boolean punching;
	private int punchDamage;
	private int punchRange;
	
	private boolean canGlide, gliding;
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2,//IDLE
		3,//WALKING
		1,//JUMPING
		1,//FALLING
		3,//GLIDING
		1,//SHOOT
		2,//PUNCH
		3 //SHOOT AND RUN
	};
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int SHOOT = 5;
	private static final int PUNCH = 6;
	private static final int SHOOT_AND_RUN = 7;

	private TileMap tileMap;
	
	public Player(TileMap tm) {
		//TODO Insert Character Attributes
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed  = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		facingRight = true;
		health = maxHealth = 5;
		laserDamage = 5;
		punchDamage = 3;
		punchRange = 40;
		canGlide = true;
		gliding = false;
		tileMap = tm;
		try{
			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Player/playersprites.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < numFrames.length; i++){
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for(int j = 0; j < numFrames[i]; j++){
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				sprites.add(bi);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
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
	
	public void setFiring(){
		firing = true;
	}
	
	public void setPunching(){
		punching = true;
	}
	
	public void setGliding(boolean b){
		gliding = b;
	}
	
	public void update(){
		//Update Position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(currentAction == PUNCH){
			if(animation.hasPlayedOnce()) punching = false;
		}
		if(currentAction == SHOOT){
			if(animation.hasPlayedOnce()) firing = false;
		}
		
		//Animations
		if(punching){
			if(gliding) punching = false;
			if(currentAction != PUNCH){
				currentAction = PUNCH;
				animation.setFrames(sprites.get(PUNCH));
				animation.setDelay(400);
				width = tileMap.getTileSize();
			}
		}else if (firing) {
			if(gliding) firing = false;
			if(left || right){
				if (currentAction != SHOOT_AND_RUN) {
					currentAction = SHOOT_AND_RUN;
					animation.setFrames(
							sprites.get(SHOOT_AND_RUN));
					{
						animation.setDelay(100);
						width = tileMap.getTileSize();
					}
				}
			}
			else if (currentAction != SHOOT) {
				currentAction = SHOOT;
				animation.setFrames(sprites.get(SHOOT));
				{
					animation.setDelay(-1);
					width = tileMap.getTileSize();
				}
			}
		}else if(dy > 0){
			if(gliding){
				if(currentAction != GLIDING){
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = tileMap.getTileSize();
				}
			}else if(currentAction != FALLING){
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(-1);
				width = tileMap.getTileSize();
			}
		}else if (dy < 0){
			if(currentAction != JUMPING){
				currentAction = JUMPING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(-1);
				width = tileMap.getTileSize();
			}
		}else if(left || right){
			if(firing){
				if (currentAction != SHOOT_AND_RUN) {
					currentAction = SHOOT_AND_RUN;
					animation.setFrames(
							sprites.get(SHOOT_AND_RUN));
					{
						animation.setDelay(100);
						width = tileMap.getTileSize();
					}
				}
			} else {
				if (currentAction != WALKING) {
					currentAction = WALKING;
					animation.setFrames(sprites.get(WALKING));
					{
						animation.setDelay(120);
						width = tileMap.getTileSize();
					}
				}
			}
		}else{
			if(currentAction != IDLE){
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = tileMap.getTileSize();
			}
		}
		animation.update();
		if(currentAction!=PUNCH&&currentAction!=SHOOT){
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		if(flinching){
			long elapsed = (System.nanoTime() - flinchTime) / 1000000;
			if(elapsed / 100 % 2 == 0) return;
		}
		if(facingRight){
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - width / 2), null);
		}else{
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - width / 2), -width, height, null);
		}
	}

	private void getNextPosition() {
		if(left){
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		}else{
			if(dx > 0){
				dx -= stopSpeed;
				if(dx < 0){
					dx = 0;
				}
			}else if(dx < 0){
				dx += stopSpeed;
				if(dx > 0){
					dx = 0;
				}
			}
		}
		
		if(jumping && !falling){
			dy = jumpStart;
			falling = true;
		}
		if(falling){
			if(dy > 0 && gliding)
				dy += fallSpeed *0.1;else
				dy += fallSpeed;
			if(dy > 0)
				jumping = false;
			if(dy < 0 && !jumping)
				dy += stopJumpSpeed;
			if(dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A)setLeft(true);
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D)setRight(true);
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W)setUp(true);
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)setDown(true);
		if(k == KeyEvent.VK_SPACE)setJumping(true);
		if(k == KeyEvent.VK_E)setGliding(true);
		if(k == KeyEvent.VK_R)setPunching();
		if(k == KeyEvent.VK_F)setFiring();
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A)setLeft(false);
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D)setRight(false);
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W)setUp(false);
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)setDown(false);
		if(k == KeyEvent.VK_SPACE)setJumping(false);
		if(k == KeyEvent.VK_E)setGliding(false);
		if(k == KeyEvent.VK_F) firing = false;
	}
	
}
