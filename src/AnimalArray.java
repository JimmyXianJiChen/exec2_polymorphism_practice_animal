import java.util.*;

public class AnimalArray{
    private int max_load, length;
    private Animal[] animalList;
    public AnimalArray(){
        this.length = 0;
        animalList = new Animal[1];
    }

    private boolean animalListIsFull(Animal[] list, int len){
        return (list.length==len);
    }

    private Animal[] augment_animalList(Animal[] animal_list){
        Animal[] tmp = new Animal[animal_list.length*2];
        System.arraycopy(animal_list, 0, tmp, 0, animal_list.length);
        return tmp;
    }

    private int[] augment_intList(int[] index_list){
        int[] tmp = new int[index_list.length*2];
        System.arraycopy(index_list, 0, tmp, 0, index_list.length);
        return tmp;
    }

    public void addAnimal(Animal animal){
        if (animalListIsFull(this.animalList,this.length)) {
            this.animalList = augment_animalList(this.animalList);
        }
        animalList[this.length++] = animal;
    }


    public void addAnimalAt(int insert_index,Animal animal){
        if (animalListIsFull(this.animalList,this.length)) {
            this.animalList = augment_animalList(this.animalList);
        }
        System.arraycopy(this.animalList,insert_index, this.animalList, insert_index+1, this.length-insert_index);
//        for(int i=this.length; i>insert_index; i--){
//            this.animalList[i] = this.animalList[i-1];
//        }
        this.animalList[insert_index] = animal;
        this.length++;
    }

    public int getAnimalNum(){
        return this.length;
    }

    private void printAnimalList(Animal[] ani, int len){
        for(int i=0; i<len; i++){
            System.out.printf("%d: ", i);
            System.out.println(ani[i]);
            System.out.println("---------------------------------------");
        }
    }

    public void printAllAnimals(){
        printAnimalList(this.animalList, this.length);
    }

    public void delete(int idx){
        this.animalList[idx] = null;
        System.arraycopy(this.animalList, idx+1, this.animalList, idx, this.length-idx-1);
        this.animalList[--this.length] = null;
//        for(int i=idx; i<this.length-1; i++){
//            this.animalList[i] = this.animalList[i+1];
//        }
//        this.animalList[this.length-1] = null;
//        this.length--;
    }

    public void sortByWeight(){
        Arrays.sort(this.animalList, 0, this.length, compareByWeight);
    }

    public void sortByName(){
        Arrays.sort(this.animalList, 0, this.length, compareByName);
    }

    public void searchByName(String name){
        for(int i=0; i<this.length; i++){
            if(animalList[i].getName().equals(name)){
                System.out.printf("%d: ", i);
                System.out.println(animalList[i]);
                return;
            }
        }
        System.out.println("No animal called " + name + " is found!");
    }

    public void searchByFirstChar(char ch){
//        List<Animal> ans = new ArrayList<Animal>();
//        Animal[] ans = new Animal[1];
//        int[] idxs = new int[1];
//        int ans_len = 0;
//        for(int i=0; i<this.length; i++){
//            if(this.animalList[i].getName().charAt(0)==ch){
//                if(animalListIsFull(ans,ans_len)){
//                    ans = augment_animalList(ans);
//                    idxs = augment_intList(idxs);
//                }
//                idxs[ans_len] = i;
//                ans[ans_len++] = this.animalList[i];
//
//            }
//        }
//        this.printAnimalList(ans, ans_len);
        for(int i=0; i<this.length; i++){
            if(this.animalList[i].getName().charAt(0)==ch){
                System.out.println(i+": "+this.animalList[i]);
                System.out.println("---------------------------------------");
            }
        }
    }

    public void searchByMinMaxWeight(float min_weight, float max_weight){
//        Animal[] ans = new Animal[1];
//        int[] idxs = new int[1];
//        int ans_len = 0;
//        for(int i=0; i<this.length; i++){
//            if(this.animalList[i].getWeight()>=min_weight && this.animalList[i].getWeight()<=max_weight){
//                if(animalListIsFull(ans,ans_len)){
//                    ans = augment_animalList(ans);
//                    idxs = augment_intList(idxs);
//                }
//                idxs[ans_len] = i;
//                ans[ans_len++] = this.animalList[i];
//            }
//        }
//        this.printAnimalList(ans, ans_len);
        for(int i=0; i<this.length; i++){
            if(this.animalList[i].getWeight()>=min_weight && this.animalList[i].getWeight()<=max_weight){
                System.out.println(i+": "+this.animalList[i]);
                System.out.println("---------------------------------------");
            }
        }
    }

    public void searchByType(String class_name) {
        for(int i=0; i<this.length; i++){
            if(this.animalList[i].getClass().getSimpleName()==class_name){
                System.out.println(i+": "+this.animalList[i]);
                System.out.println("---------------------------------------");
            }
        }
    }

    public Comparator<Animal> compareByWeight = new Comparator<Animal>() {
        @Override
        public int compare(Animal s1, Animal s2) {
            return (s1.getWeight()<s2.getWeight()) ? -1:1;
        }
    };

    public Comparator<Animal> compareByName = new Comparator<Animal>() {
        @Override
        public int compare(Animal s1, Animal s2) {
            return (s1.getName().compareTo(s2.getName())<0) ? -1:1;
        }
    };
}
