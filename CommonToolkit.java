import java.util.Scanner;


public class CommonToolkit
{
    public String convertToEnglishFormat(String swedenNumber)
    {
        String engFormat = swedenNumber.replace(",", ".");
        return engFormat;
    }

    public String getNextDoesTime(String previousDoesTime, String duration)
    {
        String hour=previousDoesTime.split(":")[0];
        String halfOfDay=previousDoesTime.substring(previousDoesTime.length()-2,previousDoesTime.length());
        String min=previousDoesTime.split(":")[1].substring(0,2);
        String[] duraionArray=duration.split(":");

        int newHour=Integer.parseInt(hour)+Integer.parseInt(duraionArray[0]);

        if(newHour>12 && halfOfDay.equalsIgnoreCase("AM")){
            newHour=newHour-12;
            halfOfDay = "PM";
        }else if(newHour>12 && halfOfDay.equalsIgnoreCase("PM")){
            newHour=newHour-12;
            halfOfDay = "AM";
        }
        int newMinutes=Integer.parseInt(min)+Integer.parseInt(duraionArray[1]);
        int[] newTimeArray={newHour,newMinutes};
        int temp=Integer.parseInt(hour)+Integer.parseInt(duraionArray[0]);
        if(newTimeArray[1]>60){
            int hourToAdd=newTimeArray[1]/60;
            int minuteToShow=newTimeArray[1]%60;
            newTimeArray[0]= newTimeArray[0]+hourToAdd;
            newTimeArray[1]=minuteToShow;
        }

        return  newTimeArray[0]+":"+newTimeArray[1]+halfOfDay;
    }

    public String getSwedishText(String textWithUnicode)
    {
        String swedishText = "";
        String split[] = textWithUnicode.replace("\\u", ";").split(";");
        if (split.length > 1)
        {
            for (int i = 0; i < split.length; i++)
            {
                if (split[i].length() > 0)
                {
                    String unicodeDecimal = getUnicodeDecimal(split[i]);
                    char c = (char) Integer.parseInt(unicodeDecimal);
                    swedishText += split[i].replace(split[i].substring(0, unicodeDecimal.length()), c + "");
                }
            }
            return swedishText;
        } else
        {
            return textWithUnicode;
        }

    }

    private String getUnicodeDecimal(String text)
    {
        String decimalVal = "";
        char array[] = text.toCharArray();
        for (char ch : array)
        {
            if(Character.isDigit(ch)) {
                decimalVal += ch;
            }else {
                break;
            }
        }
        return decimalVal;
    }

    public static void main(String[] args)
    {
        CommonToolkit commonToolkit = new CommonToolkit();

        System.out.println("Assignment 01");
        String swedish = "2,5 ml";
        String engFormat = commonToolkit.convertToEnglishFormat(swedish);
        System.out.println("Swdish Format: " + swedish);
        System.out.println("English Format: " + engFormat);
        System.out.println();

        System.out.println("Assignment 02");
        Scanner sc = new Scanner(System.in,"UTF-8");
        System.out.print("Previous does time(HH:mm am/pm): ");
        String previousDoesTime=sc.nextLine();
        previousDoesTime=previousDoesTime.replaceAll("\\s+","");
        System.out.print("Time duration between doses (HH:mm) :");
        String duration=sc.next();
        sc.close();
        String output;
        try {
            // CommonToolkit commonToolkit1 = new CommonToolkit();
            output = commonToolkit.getNextDoesTime(previousDoesTime, duration);
        }catch (Exception e){
            output ="";
            System.out.println("Inputs are not in the correct format");
        }
        System.out.println("Next Does Time : "+output);

//        int time=commonToolkit.getNextDoesTime();
//        System.out.println(time);

        System.out.println();
        System.out.println("Assignment 03");
        System.out.println(commonToolkit.getSwedishText("\\u7847sk\\u228da"));
        //System.out.println(commonToolkit.convertToEnglishFormat(2.5 ,2,5  ));

    }
}