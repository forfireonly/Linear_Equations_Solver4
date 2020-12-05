class Vehicle {

    protected String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return String.format("Vehicle{licensePlate=%s}", licensePlate);
    }
}

class Car extends Vehicle {

    protected int numberOfSeats;

    public Car(String licensePlate, int numberOfSeats) {
        super(licensePlate);
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return String.format("Car{licensePlate=%s,numberOfSeats=%d}", licensePlate, numberOfSeats);
    }
}