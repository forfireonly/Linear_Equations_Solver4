class Person {

    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Person{name=%s,age=%d}", name, age);
    }
}

class Patient extends Person {

    protected int height;

    public Patient(String name, int age, int height) {
        super(name, age);
        this.height = height;
    }
    @Override
    public String toString() {
        return String.format("Patient{name=%s,age=%d,height=%d}", name, age, height);
    }
}