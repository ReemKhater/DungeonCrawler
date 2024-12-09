import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;

    //Instance
    private HealthIndicator healthIndicator;

    public RenderEngine(JFrame jFrame,HealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
        renderList = new ArrayList<>();
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
        //draw the health bar
        if (healthIndicator != null) {
            healthIndicator.draw(g);
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}
