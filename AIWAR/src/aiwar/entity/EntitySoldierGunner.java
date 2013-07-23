package aiwar.entity;

public class EntitySoldierGunner extends EntitySoldier
{
	public EntitySoldierGunner(int t, double x, double y)
	{
		super(t);
		posX = x;
		posY = y;
	}
	
	public void tick()
	{
		walk();
	}
}
