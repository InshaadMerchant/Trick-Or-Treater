/*
 * Inshaad Merchant 1001861293
 */
package code5_1001861293;

import java.util.HashMap;
import java.util.Random;
public class CandyHouse extends House
{
    public CandyHouse(String houseName, HashMap<String,Integer> CandyList)
    {
        super(houseName,CandyList);
    }
    @Override
    public synchronized String ringDoorBell(TrickOrTreater TOT)
    {
        String Candy = "";
        String key;
        TOT.addToPath("+");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        Random rn = new Random();
        int randomnum = rn.nextInt(CandyList.size()-1)+1;  
        for (HashMap.Entry mapElement : CandyList.entrySet())
        {
            if ((int)mapElement.getValue() == randomnum)
            {
                key = (String)mapElement.getKey();
                Candy = key;
            }    
        }    
        return Candy;
    }        
}
