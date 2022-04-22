
//Ashvin Ganesan
//Homework 23
//Algorithms
//Friday May 7th 2021
public class Homework_23 {

    public static void main(String[] args)   {
        //String[] margs = {"CA","NY", "KS", "FL", "HI"};
        //String[] margs = {"HI"};
        
        boolean can = false;
        Farthest farthest;
        for(String arg: args) {
            if(arg.equals("-canada")) {
                can = true;
                //System.out.println("can set to true");
            }
            else {
                //System.out.println(can);
                if(can) {
                    farthest = new Farthest(arg, can);
                } else {
                   farthest = new Farthest(arg); 
                }
                String distInStr = String.format("%.2f", farthest.farthestDistance());
                System.out.println(arg + " to " + farthest.farthestState() 
                    + " (" + distInStr + " miles): " 
                    + farthest.getPathToFarthestState());
            }
        }
        
    }
    
    
    
}
