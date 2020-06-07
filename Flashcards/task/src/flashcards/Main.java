package flashcards;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       Map<String,String> mapOfCards = new LinkedHashMap<>();
       System.out.println("Input the number of cards:");
       int numOfCards = scanner.nextInt();
       scanner.nextLine();
        boolean cardflag = false;
        boolean defFlag = false;
        int i = 1;

       while (i<=numOfCards){
           cardflag = false;
           defFlag = false;
            System.out.println("The card #" + i + ":");
            String cardName = scanner.nextLine();
            for (var entry : mapOfCards.entrySet()) {
                if (entry.getKey().equals(cardName)){
                    cardflag = true;
                }
            }
            if(cardflag){

                while (cardflag){
                    System.out.println("The card " +  "\"" + cardName +  "\"" + " already exists");
                    //System.out.println("The card #" + i + ":");
                    cardName = scanner.nextLine();
                    if(mapOfCards.containsKey(cardName)){
                        cardflag = true;
                    }
                    else{
                        cardflag = false;

                        break;
                    }
                }
            }

                System.out.println("The definition of the card # " + i + ":");
                String defOfcard = scanner.nextLine();
                for (var entry : mapOfCards.entrySet()) {
                    if (entry.getValue().equals(defOfcard)){
                        defFlag = true;
                    }
                }
                if(defFlag){

                    while (defFlag){
                        System.out.println("The definition "+  "\"" + defOfcard +  "\"" + " already exists");
                        //System.out.println("The definition of the card # " + i + ":");
                        defOfcard = scanner.nextLine();
                        if(mapOfCards.containsValue(defOfcard)){
                            defFlag = true;
                        }
                        else{
                            defFlag = false;
                            mapOfCards.put(cardName,defOfcard);
                            i = i + 1;
                        }
                    }
                }
                else {
                    mapOfCards.put(cardName,defOfcard);
                    i = i + 1;
                }

        }
        //System.out.println(card.equals(ans) ? "Your answer is right!" : "Your answer is wrong...");


        //This section starts asking question.

        boolean answerFlag = false;
       i = 1;
       while (i <= numOfCards){
           for (var entry : mapOfCards.entrySet()) {
               System.out.println("Print the definition of " + "\"" + entry.getKey() + "\"" + ":");
               String ans = scanner.nextLine();
               if(mapOfCards.containsValue(ans)){
                   if(entry.getValue().equals(ans)){
                       System.out.println("Correct answer.");
                       i = i + 1;
                   }
                   else {
                       for (var anotherentry : mapOfCards.entrySet()) {
                           if(anotherentry.getValue().equals(ans)){
                               System.out.println("Wrong answer, The correct one is " + "\"" + entry.getValue() + "\""+ ", you've just written the definition of " +"\"" + anotherentry.getKey() + "\"");
                               i = i + 1;
                           }
                       }

                   }
               }
               else {
                   System.out.println("Wrong answer. The correct one is " + "\"" + entry.getValue() + "\".");
                   i = i + 1;
               }
           }


       }




    }
}
