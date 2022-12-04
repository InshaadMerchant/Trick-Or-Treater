/*
 * Inshaad Merchant 1001861293
 */
package code5_1001861293;

import java.util.HashMap;
public class ToothbrushHouse extends House
{
    public ToothbrushHouse(String houseName, HashMap<String,Integer> CandyList)
    {
        super(houseName,CandyList);
    }
    @Override
    public synchronized String ringDoorBell(TrickOrTreater TOT)
    {
        TOT.addToPath("-");
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        return "ToothBrush";
        
    }        
}
