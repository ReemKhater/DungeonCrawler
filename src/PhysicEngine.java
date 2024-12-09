import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;

    //instance
    private HealthIndicator healthIndicator;

    public PhysicEngine(HealthIndicator healthIndicator) {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
        this.healthIndicator = healthIndicator;
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);

            //collision logic
            for (Sprite sprite : environment){
                if (dynamicSprite.collidesWith(sprite)){
                    System.out.println("Collision Detected between " + sprite + " and " + dynamicSprite);
                    if (sprite instanceof Trap){
                        //Handle collision with traps
                        Trap trap = (Trap) sprite;
                        healthIndicator.decreaseHealth(trap.getDamage());
                        System.out.println("Trap triggered! Health reduced by "+trap.getDamage());
                    }else if (sprite instanceof SolidSprite){
                        //Handle collision with solid sprites
                        System.out.println("Collision with solid objects.");
                    }
                }
            }
            //check if the player is dead
            if (healthIndicator.isDead()){
                //Trigger Game Over logic
                System.out.println("Game Over");
            }
        }
    }
}
