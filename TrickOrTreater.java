/*
 * Inshaad Merchant 1001861293
 */
package code5_1001861293;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrickOrTreater implements Runnable
{

    public static int ImDone;

    private String name;
    private String path = ".";
    private ArrayList<House> ListOfHouses = new ArrayList<>();
    private HashMap<String, Integer> Bucket = new HashMap<>();

    public TrickOrTreater(String name, ArrayList<House> ListOfHouses)
    {
        this.name = name;
        this.ListOfHouses = ListOfHouses;
    }

    public String getName()
    {
        return name;
    }

    public String getPath()
    {
        return path;
    }

    public void addToPath(String str)
    {
        path = path + str;
    }

    private void Walk(int speed)
    {
        for (int i = 0; i < 10; i++)
        {
            path = path + ".";
            try
            {
                Thread.sleep(speed);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String printBucket()
    {
        String Candy;
        String BucketContents = String.format("%-10s - ", name);
        int CandyCount = 0;

        for (HashMap.Entry bucketElement : Bucket.entrySet())
        {
            Candy = (String)bucketElement.getKey();
            CandyCount = (int)bucketElement.getValue();
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
        BucketContents += "\n";
        return BucketContents;
    }

    @Override
    public void run()
    {
        int count = 0;
        int speed = 0;
        Random rn = new Random();
        for (House it : ListOfHouses)
        {
            speed = rn.nextInt(1501) + 1;
            Walk(speed);
            String Candy = it.ringDoorBell(this);
            if (Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy);
                Bucket.put(Candy, ++count);
            }
            else
            {
                Bucket.put(Candy, 1);
            }
        }
        synchronized (this)
        {
            ImDone++;
        }
    }

}
