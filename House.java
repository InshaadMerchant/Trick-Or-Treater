/*
 * Inshaad Merchant 1001861293
 */
package code5_1001861293;
import java.util.HashMap;
public abstract class House
{
    private String houseName;
    HashMap<String,Integer> CandyList = new HashMap<>();
    public House(String houseName, HashMap<String,Integer> CandyList)
    {
        this.houseName = houseName;
        this.CandyList = CandyList;
    }
    public abstract String ringDoorBell(TrickOrTreater TOT);
}
