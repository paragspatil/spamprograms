package flashcards;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.util.*;
        import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        Scanner scanner = new Scanner(System.in);
        Map<String,String> mapOfCards = new LinkedHashMap<>();
        boolean exit = true;
        //System.out.println("its printing");
        boolean cardflag = false;
        boolean defFlag = false;
        while (exit){
            cardflag = false;
            defFlag = false;
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String userIn = scanner.nextLine();
            switch (userIn) {
                case  "add":
                    System.out.println("The card:");
                    String cardName = scanner.nextLine();
                    for (var entry : mapOfCards.entrySet()) {
                        if (entry.getKey().equals(cardName)){
                            cardflag = true;
                        }
                    }
                    if(cardflag){
                        System.out.println("The card " +  "\"" + cardName +  "\"" + " already exists");
                    }
                    else {
                        System.out.println("The definition of the card:");
                        String defOfcard = scanner.nextLine();
                        for (var entry : mapOfCards.entrySet()) {
                            if (entry.getValue().equals(defOfcard)) {
                                defFlag = true;
                            }
                        }
                        if(defFlag){
                            System.out.println("The definition "+  "\"" + defOfcard +  "\"" + " already exists");

                        }
                        else {
                            mapOfCards.put(cardName,defOfcard);
                            System.out.println("The pair (" + "\"" + cardName +"\"" + ":" + "\"" + defOfcard + "\"" + ") has been added.");
                        }
                    }
                    break;




                case "remove":
                    System.out.println("The card:");
                    String removeCard = scanner.nextLine();
                    if(mapOfCards.containsKey(removeCard)){
                        mapOfCards.remove(removeCard);
                        System.out.println("The card has been removed.");
                    }
                    else {
                        System.out.println("Can't remove " +  "\"" + removeCard  + "\""  + "there is no such card.");
                    }
                    break;


                case "ask":
                    System.out.println("How many times to ask?");
                    int askNum = scanner.nextInt();
                    scanner.nextLine();
                    String[] keyArray = mapOfCards.keySet().toArray(new String[mapOfCards.size()]);
                    String[] valueArray = new String[keyArray.length];
                    for(int i=0; i < keyArray.length;i++){
                       valueArray[i] = mapOfCards.get(keyArray[i]);
                    }
                    for (int i = 0;i<askNum;i++){
                        int randInt = random.nextInt(keyArray.length);
                        System.out.println("Print the definition of"  + "\"" + keyArray[randInt]  + "\"" + ":");
                        String answer = scanner.nextLine();
                        if(mapOfCards.containsValue(answer)){
                            if(mapOfCards.get(keyArray[randInt]).equals(answer)){
                                 System.out.println("Correct answer");
                            }
                            else {
                                for(var entry: mapOfCards.entrySet()){
                                    if(entry.getValue().equals(answer)){
                                        System.out.println("Wrong answer, The correct one is " + "\"" + mapOfCards.get(keyArray[randInt]) + "\""+ ", you've just written the definition of " +"\"" + entry.getKey() + "\"");
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("Wrong answer, The correct one is " + "\"" + mapOfCards.get(keyArray[randInt]) + "\"");
                        }
                    }
                    break;





                case "export":
                    System.out.println("File name:");
                    String fileName = scanner.nextLine();
                    File file = new File(fileName);
                    int counter2 = 0;
                    try (PrintWriter printWriter = new PrintWriter(file)) {
                        for(var entry: mapOfCards.entrySet()){
                            printWriter.println(entry.getKey());
                            printWriter.println(entry.getValue());
                            counter2 = counter2 + 1;
                        }

                    } catch (
                            IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                    System.out.println(counter2 +" cards have been saved.");
                    break;




                case "import":
                    System.out.println("File name:");
                    String readFile = scanner.nextLine();
                    File fileRead = new File(readFile);
                    boolean fileExist = true;
                    int counter = 0;
                    try (Scanner reader = new Scanner(fileRead)) {
                        while (reader.hasNext()) {
                            String key = reader.nextLine();
                            String value = reader.nextLine();
                            mapOfCards.put(key,value);
                            counter = counter + 1;
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("not found");
                        fileExist = false;

                    }
                    if(fileExist) {
                        System.out.println(counter + " cards have been loaded.");
                    }
                    break;


                case "exit":
                    exit = false;
                    break;

            }
        }

     System.out.println(mapOfCards);

    }

}
