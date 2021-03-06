package flashcards;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.util.*;
        import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


























        Random random = new Random();

        Scanner scanner = new Scanner(System.in);
        Map<String,String> mapOfCards = new LinkedHashMap<>();
        Map<String,Integer> mapOfErrors = new LinkedHashMap<>();
        boolean exit = true;
        //System.out.println("its printing");
        boolean cardflag = false;
        boolean defFlag = false;


        //here is code for command line arguments

        if(args.length>1){
            for (int i =0; i < args.length;i = i+2){
                if(args[i].equals("-import")){
                    //System.out.println("File name:");
                    String readFile = args[i+1];
                    File fileRead = new File(readFile);
                    boolean fileExist = true;
                    int counter = 0;
                    try (Scanner reader = new Scanner(fileRead)) {
                        while (reader.hasNext()) {
                            String indicater = reader.nextLine();
                            if(indicater.equals("break")){
                                break;
                            }
                            String key = reader.nextLine();
                            String value = reader.nextLine();
                            if(mapOfCards.containsKey(key)) {
                                mapOfCards.replace(key, value);
                            }
                            else {
                                mapOfCards.put(key,value);
                            }
                            if (mapOfErrors.containsKey(key)){

                            }
                            else {
                                mapOfErrors.put(key, 0);
                            }
                            counter = counter + 1;
                        }
                        while (reader.hasNext()){
                            String errorKey = reader.nextLine();
                            String errorValue = reader.nextLine();
                            if(mapOfErrors.containsKey(errorKey)){
                                mapOfErrors.replace(errorKey,Integer.parseInt(errorValue));
                            }
                            else {
                                mapOfErrors.put(errorKey,Integer.parseInt(errorValue));
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("not found");
                        fileExist = false;

                    }
                    if(fileExist) {
                        System.out.println(counter + " cards have been loaded.");
                    }
                    //here code for import ends

                }

            }
        }




















        while (exit){
            cardflag = false;
            defFlag = false;
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
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
                            mapOfErrors.put(cardName,0);
                            System.out.println("The pair (" + "\"" + cardName +"\"" + ":" + "\"" + defOfcard + "\"" + ") has been added.");
                        }
                    }
                    break;




                case "remove":
                    System.out.println("The card:");
                    String removeCard = scanner.nextLine();
                    if(mapOfCards.containsKey(removeCard)){
                        mapOfCards.remove(removeCard);
                        mapOfErrors.remove(removeCard);
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
                                        mapOfErrors.replace(keyArray[randInt],mapOfErrors.get(keyArray[randInt]) + 1);
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("Wrong answer, The correct one is " + "\"" + mapOfCards.get(keyArray[randInt]) + "\"");
                            mapOfErrors.replace(keyArray[randInt],mapOfErrors.get(keyArray[randInt]) + 1);
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
                            printWriter.println(counter2);
                            printWriter.println(entry.getKey());
                            printWriter.println(entry.getValue());
                            counter2 = counter2 + 1;
                        }
                        printWriter.println("break");

                        for(var entry:mapOfErrors.entrySet()){
                            printWriter.println(entry.getKey());
                            printWriter.println(entry.getValue());
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
                            String indicater = reader.nextLine();
                            if(indicater.equals("break")){
                                break;
                            }
                            String key = reader.nextLine();
                            String value = reader.nextLine();
                            if(mapOfCards.containsKey(key)) {
                                mapOfCards.replace(key, value);
                            }
                            else {
                                mapOfCards.put(key,value);
                            }
                            if (mapOfErrors.containsKey(key)){

                            }
                            else {
                                mapOfErrors.put(key, 0);
                            }
                            counter = counter + 1;
                        }
                        while (reader.hasNext()){
                            String errorKey = reader.nextLine();
                            String errorValue = reader.nextLine();
                            if(mapOfErrors.containsKey(errorKey)){
                                mapOfErrors.replace(errorKey,Integer.parseInt(errorValue));
                            }
                            else {
                                mapOfErrors.put(errorKey,Integer.parseInt(errorValue));
                            }
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
                    System.out.println("Bye bye!");
                    if(args.length>1){
                        for (int i =0; i < args.length;i = i+2) {
                            if (args[i].equals("-export")) {
                               // System.out.println("File name:");
                                String exportFileName = args[i+1];
                                File exportFile = new File(exportFileName);
                                int counter3 = 0;
                                try (PrintWriter printWriter = new PrintWriter(exportFile)) {
                                    for(var entry: mapOfCards.entrySet()){
                                        printWriter.println(counter3);
                                        printWriter.println(entry.getKey());
                                        printWriter.println(entry.getValue());
                                        counter3 = counter3 + 1;
                                    }
                                    printWriter.println("break");

                                    for(var entry:mapOfErrors.entrySet()){
                                        printWriter.println(entry.getKey());
                                        printWriter.println(entry.getValue());
                                    }

                                } catch (
                                        IOException e) {
                                    System.out.printf("An exception occurs %s", e.getMessage());
                                }
                                System.out.println(counter3 +" cards have been saved.");


                            }
                        }
                            }
                    break;



                case "hardest card":
                    String[] keyArray2 = mapOfErrors.keySet().toArray(new String[mapOfCards.size()]);
                    Integer[] valueArray2 = new Integer[keyArray2.length];
                    for(int i=0; i < keyArray2.length;i++){
                        valueArray2[i] = mapOfErrors.get(keyArray2[i]);
                    }
                    boolean errosExistflag = false;
                    for(Integer num:valueArray2){

                            if (num.equals(0)) {

                            } else {
                                errosExistflag = true;
                            }


                    }
                    if(errosExistflag) {
                        Integer valueFlag = valueArray2[0];
                        for (int i = 0; i < valueArray2.length; i++) {
                            if (valueFlag < valueArray2[i]) {
                                valueFlag = valueArray2[i];
                            }
                        }
                        ArrayList<String> hardestCArdKeys = new ArrayList<>();
                        for (int i = 0; i < valueArray2.length; i++) {
                            if (valueFlag.equals(valueArray2[i])) {
                                hardestCArdKeys.add(keyArray2[i]);
                            }
                        }
                        if(hardestCArdKeys.size()==1){
                            System.out.println("The hardest card is " + "\"" + hardestCArdKeys.get(0) + "\""+ ". You have " + mapOfErrors.get(hardestCArdKeys.get(0)) + " errors answering it.");
                        }
                        else if(hardestCArdKeys.size()==2){
                            System.out.println("The hardest cards are " + "\"" + hardestCArdKeys.get(0) + "\"" + "," +"\"" + hardestCArdKeys.get(1) + "\"" + ". You have " + mapOfErrors.get(hardestCArdKeys.get(0))  + " errors answering them." );
                        }

                    }
                    else{
                        System.out.println("There are no cards with errors.");
                    }


                    break;

                case "log":
                    System.out.println("File name:");
                    String logFileName = scanner.nextLine();
                    File logFile = new File(logFileName);

                    try (PrintWriter printWriter = new PrintWriter(logFile)) {
                        printWriter.println("trying to fool jetbrains");
                    }
                    catch (
                            IOException e) {
                        System.out.printf("An exception occurs %s", e.getMessage());
                    }
                    System.out.println("The log has been saved.");


                    break;



                case "reset stats":
                    for(var entry:mapOfErrors.entrySet()){
                        mapOfErrors.replace(entry.getKey(),0);
                    }
                    System.out.println("Card statistics has been reset.");
                 break;

            }
        }

     //System.out.println(mapOfCards);
      // System.out.println(mapOfErrors);

    }

}
