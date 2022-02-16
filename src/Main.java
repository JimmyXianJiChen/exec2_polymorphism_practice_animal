import java.util.Scanner;

public class Main {
    public static boolean invalidWeight(float weight){
        return (weight<=0);
    }
    public static boolean invalidIndex(int idx, int num, boolean forDel){
        //check index for delete() and insert(), insert allows idx==num cause it equals to adding in the end of array;
        if(forDel) {
            return(idx<0 || idx>=num);
        }
        else {
            return (idx<0 || idx>num);
        }
    }
    public static boolean invalidName(String name){
        return (name.isBlank()||name.isEmpty());
    }
    public static boolean invalidMinMaxWeight(float min_weight, float max_weight){
        return (min_weight>max_weight || invalidWeight(min_weight) || invalidWeight(max_weight));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cmd,type,idx;
        float min_weight,max_weight;
        String name;
        float weight;
        AnimalArray arr = new AnimalArray();
        System.out.println(
        """
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        command instructions:
        -----------------------------------------------------
        add: add an animal to the list at the end of the list
        insert: insert an animal to the specified index in the list
        delete: delete the animal at the specified index in the list
        sort: sort the animals in the array by their weights/names
        show: show all the animals in the list by their current order
        exist: quit the application
        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        """
        );
        boolean go = true;
        while(go){
            System.out.println("Which action do you want to take? (1.add/2.insert/3.delete/4.sort/5.search/6.show/7.exit)");
            cmd = sc.nextInt();
            switch (cmd){
                case 1: //add
                case 2:{//insert
                    System.out.println("Which genre do you want to add/insert? (1.dog/2.cat/3.mouse)");
                    type = sc.nextInt();
                    if(type>3||type<=0){
                        System.out.println("Invalid animal genre! Please try again!");
                        break;
                    }
                    System.out.println("Please enter the name of the animal:");
                    name = sc.nextLine();
                    name = sc.nextLine();
                    if(invalidName(name)) {
                        System.out.println("Invalid animal name! Please try again!");
                        break;
                    }
                    System.out.println("Please enter the weight of the animal:");
                    weight = sc.nextFloat();
                    if(invalidWeight(weight)){
                        System.out.println("Invalid animal weight! Please try again!");
                        break;
                    }
                    if(cmd==1) {
                        idx = arr.getAnimalNum();
                    }
                    else{
                        System.out.println("Please enter the index that you would like to insert the animal:");
                        idx = sc.nextInt();
                        if(invalidIndex(idx,arr.getAnimalNum(),false)){
                            System.out.println("Invalid inserting index! Please try again!");
                            break;
                        }
                    }

                    switch (type) {
                        case 1:{//("dog"): {
                            arr.addAnimalAt(idx, new Dog(name, weight));
                            break;
                        }
                        case 2:{//("cat"): {
                            arr.addAnimalAt(idx, new Cat(name, weight));
                            break;
                        }
                        case 3:{//("mouse"): {
                            arr.addAnimalAt(idx, new Mouse(name, weight));
                            break;
                        }
                        default: {
                            System.out.println("Animal type doesn't exist, please try other species.");
                            break;
                        }
                    }
                    break;
                }
                case 3:{//"delete": {
                    System.out.println("Which animal do you want to delete? (Please enter the index)");
                    idx = sc.nextInt();
                    if (invalidIndex(idx, arr.getAnimalNum(), true)) {
                        break;
                    }
                    arr.delete(idx);
                    break;
                }
                case 4:{//"sort":{
                    System.out.println("Which sorting method do you want to use? (1.byWeight/2.byName)");
                    cmd = sc.nextInt();
                    switch (cmd){
                        case 1: {//"byWeight":
                            arr.sortByWeight();
                            break;
                        }
                        case 2: {//"byName":
                            arr.sortByName();
                            break;
                        }
                        default: {
                            System.out.println("Sorting method does not exist! Please try again!");
                            break;
                        }
                    }
                    break;
                }
                case 5:{//"search": {
                    System.out.println("Which search method do you want to use? (1.byName/2.byFirstChar/3.MinMaxWeight/4.Genre)");
                    cmd = sc.nextInt();
                    switch (cmd) {
                        case 1:{//"byName": {
                            System.out.println("Please enter the name you would like to search for:");
                            name = sc.nextLine();
                            name = sc.nextLine();
                            if (name.length() == 0) {
                                System.out.println("Name can't be blank!");
                                System.out.println("Please try again!");
                                break;
                            }
                            arr.searchByName(name);
                            break;
                        }
                        case 2:{//"byFirstChar": {
                            System.out.println("Please enter the first character you would like to search for:");
                            name = sc.nextLine();
                            name = sc.nextLine();
                            if (name.length() == 0) {
                                System.out.println("First character can't be blank!!");
                                break;
                            } else if (name.length() > 1) {
                                System.out.println("Please enter only one character at a time!");
                                break;
                            }
                            arr.searchByFirstChar(name.charAt(0));
                            break;
                        }
                        case 3:{//"byMinMaxWeight"
                            System.out.println("Please enter the min value of the weight:");
                            min_weight = sc.nextFloat();
                            System.out.println("Please enter the min value of the weight:");
                            max_weight = sc.nextFloat();
                            if(invalidMinMaxWeight(min_weight,max_weight)){
                                System.out.println("Invalid min weight and max weight pair! Please try again!");
                                break;
                            }
                            arr.searchByMinMaxWeight(min_weight, max_weight);
                            break;
                        }
                        case 4:{//"byGenre"
                            System.out.println("Please choose the genre you would like to search for: (1.Dog/2.Cat/3.Mouse)");
                            cmd = sc.nextInt();
                            switch(cmd){
                                case 1:{
                                    arr.searchByType("Dog");
                                    break;
                                }
                                case 2:{
                                    arr.searchByType("Cat");
                                    break;
                                }
                                case 3:{
                                    arr.searchByType("Mouse");
                                    break;
                                }
                                default:{
                                    System.out.println("Invalid genre! Please try again!");
                                    break;
                                }
                            }
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 6:{//"show": {
                    arr.printAllAnimals();
                    break;
                }
                case 7:{//"exit": {
                    go = false;
                    break;
                }
                default:{
                    System.out.println("Action not found, please enter an available command!");
                    break;
                }
            }
            System.out.println("============================================");
        }
        System.out.println("============================================");
        System.out.println("Program exited!");
        System.out.println("============================================");
    }
}
