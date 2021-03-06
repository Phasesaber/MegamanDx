package megamanDx.entites;

import java.awt.Rectangle;

import megamanDx.main.GamePanel;
import megamanDx.main.math.Vector2i;
import megamanDx.tileMap.Tile;
import megamanDx.tileMap.TileMap;


public abstract class Entity {
	
	//TileMap
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//Position & Vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//Dimensions
	protected int width;
	protected int height;
	
	//Collision Box
	protected int cwidth;
	protected int cheight;
	
	//Collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	//Animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	//Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	//Movemnt Attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	
	//Constructer
	public Entity(TileMap tm){
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersect(Entity other){
		Rectangle r1 = getCollisionBox();
		Rectangle r2 = other.getCollisionBox();
		return r1.intersects(r2);
	}
	
	public Rectangle getCollisionBox(){
		return new Rectangle((int)x-cwidth, (int)y - cheight, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y){
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight /2 - 1) / tileSize;
		if(topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()){
			topLeft = topRight = bottomLeft = bottomRight = false;
			return;
		}
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		topLeft = tl == Tile.BLOCK;
		topRight = tr == Tile.BLOCK;
		bottomLeft = bl == Tile.BLOCK;
		bottomRight = br == Tile.BLOCK;
	}
	
	public void checkTileMapCollision(){
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		xdest = x + dx;
		ydest = y + dy;
		xtemp = x;
		ytemp = y;
		calculateCorners(x, ydest);
		if(dy < 0){
			if(topLeft || topRight){
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
			}else{
				ytemp += dy;
			}
		}
		if(dy > 0){
			if(bottomLeft || bottomRight){
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			}else{
				ytemp += dy;
			}
		}
		calculateCorners(xdest, y);
		if(dx < 0){
			if(topLeft || bottomLeft){
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			}else{
				xtemp += dx;
			}
		}
		if(dx > 0){
			if(topRight || bottomRight){
				dx = 0;
				xtemp = (currCol+1) * tileSize - cwidth / 2;
			}else{
				xtemp += dx;
			}
		}
		if(!falling){
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight){
				falling = true;
			}
		}
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int) y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getCWidth(){
		return cwidth;
	}
	
	public int getCHeight(){
		return cheight;
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(Vector2i v) {
		setPosition(v.x,v.y);
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition(){
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}
	
	public void setLeft(boolean b){
		left = b;
	}
	
	public void setRight(boolean b){
		right = b;
	}
	
	public void setDown(boolean b){
		down = b;
	}
	
	public void setUp(boolean b){
		up = b;
	}
	
	public void setJumping(boolean b){
		jumping = b;
	}
	
	public void setFalling(boolean b){
		falling = b;
	}
	
	public boolean notOnScreen() {
		return x + xmap + width < 0 
				|| x + xmap - width > GamePanel.WIDTH
				|| y + ymap + height < 0
				|| y + ymap - height > GamePanel.HEIGHT;
	}
	
}
