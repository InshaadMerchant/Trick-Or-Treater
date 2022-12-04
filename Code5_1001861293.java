/*
 * Inshaad Merchant 1001861293
 */
package code5_1001861293;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code5_1001861293
{

    public static void CreateCandyList(String cfilename, HashMap<String, Integer> CandyList) throws FileNotFoundException
    {
        File FH = new File(cfilename);
        Scanner inFileRead = null;
        String MyArr[];
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
        while (inFileRead.hasNextLine())
        {
            MyArr = inFileRead.nextLine().split("[|]");
            CandyList.put(MyArr[0], Integer.parseInt(MyArr[1]));
        }
        inFileRead.close();
    }

    public static String CreateHouses(String hfilename, HashMap<String, Integer> CandyList, ArrayList<House> arrlist)
    {
        String HouseHeading = "          ";
        File FH = new File(hfilename);
        Scanner inFileRead = null;
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
        while (inFileRead.hasNextLine())
        {
            Random rn = new Random();
            String FileLine = inFileRead.nextLine();
            HouseHeading = HouseHeading + FileLine;
            for (int i = 0; i < 11 - FileLine.length(); i++)
            {
                HouseHeading += " ";
            }
            if (rn.nextInt(2) == 0)
            {
                arrlist.add(new CandyHouse(FileLine, CandyList));
            }
            else
            {
                arrlist.add(new ToothbrushHouse(FileLine, CandyList));
            }    
        }
        inFileRead.close();
        HouseHeading += "\n\n";
        return HouseHeading;
    }
    
    public static void CreateTOTs(String tfilename, ArrayList<House> arrlist, ArrayList<TrickOrTreater> TOTList)
    {
        File FH = new File(tfilename);
        Scanner inFileRead = null;
        try
        {
            inFileRead = new Scanner(FH);
        }
        catch (FileNotFoundException e)
        {
            System.exit(0);
        }
        while (inFileRead.hasNextLine())
        {
            String FileLine = inFileRead.nextLine();
            TOTList.add(new TrickOrTreater(FileLine,arrlist));
        }    
        inFileRead.close();
    }   
    
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(System.in);
        String CandyFile = args[0].substring(args[0].indexOf('=')+1);
        String HouseFile = args[1].substring(args[1].indexOf('=')+1);
        String TOTFile = args[2].substring(args[2].indexOf('=')+1);
        HashMap<String,Integer> CandyList = new HashMap<>();
        ArrayList<House> HouseList = new ArrayList<>();
        ArrayList<TrickOrTreater> TOTList = new ArrayList<>();
        CreateCandyList(CandyFile, CandyList);
        String Househeading = CreateHouses(HouseFile, CandyList, HouseList);
        CreateTOTs(TOTFile ,HouseList, TOTList);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (TrickOrTreater it: TOTList)
        {
            executorService.execute(it);
        }
        TextAreaFrame TAF = new TextAreaFrame();
        TAF.setVisible(true);
        while (TrickOrTreater.ImDone != TOTList.size())
        {
            String ScreenOutput = String.format("%s", Househeading);
            for (TrickOrTreater it: TOTList)
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(),it.getName());
            }
            TAF.textArea.setText(ScreenOutput);
        }
        executorService.shutdown();
        String BucketContents = "Candy!!\n\n";
        for (TrickOrTreater it: TOTList)
        {
            BucketContents = BucketContents.concat(it.printBucket());
        } 
        TAF.textArea.setText(BucketContents);
    }
}
