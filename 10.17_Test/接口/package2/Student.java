package package2;

class Student implements Comparable{
    private String name;
    private int score;
    public Student(String name,int score){
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString(){
        return "[" + this.name + ":" + this.score + "]";
    }
    @Override
    //Student的对象方法，调用该方法与o比较，
    public int compareTo(Object o){
        Student s = (Student) o;
        if(this.score > s.score){
            return -1;
        }else if(this.score < s.score){
            return 1;
        }
        return 0;
    }
}