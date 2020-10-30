/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3dgame;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author lucasgabrielpatriciodoamaral
 */
public class Handler {
    
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void reset()
    {
    	for(int i = 0; i < object.size();)
    	{
    		removeObject(object.get(i));
    	
    	}
    }
    
    public void tick()
    {
         for(int i = 0; i < object.size(); i++)
         {
             GameObject tempObject = object.get(i);
             
             tempObject.tick();
             
             
             
         }
    }
    int k;
    public void render(Graphics g)
    {
        organize();
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
             
            tempObject.render(g);  
             
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
        k = this.object.size();
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    public void organize()
    {
        LinkedList<GameObject> render = new LinkedList<GameObject>();
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getID() == ID.Trail)
            {
                render.addFirst(tempObject);
            }else{
                render.addLast(tempObject);
            }

        }
        object = render;
    }
    
}
