public class Animal implements Comparable<Animal>{
    String name;
    float weight;
    /*
    public Animal(String name, float weight){
        this.name = name;
        this.weight = weight;
    }
     */
    public String getName(){return this.name;}
    public float getWeight(){return this.weight;}



    @Override
    public int compareTo(Animal b){
        return (this.weight<b.getWeight()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
