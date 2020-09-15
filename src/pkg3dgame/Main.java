
package pkg3dgame;




public class Main {
    
    public static float clamp(float var, float min, float max)
    {
        if(var >= max){
            return max;
        }
        else if(var <= min){
            return min;
        }else{
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
    
}
//