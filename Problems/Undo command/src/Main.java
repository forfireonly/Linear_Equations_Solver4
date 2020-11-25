interface Movable {
    int getX();
    int getY();
    void setX(int newX);
    void setY(int newY);
}

interface Storable {
    int getInventoryLength();
    String getInventoryItem(int index);
    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements Movable, Command {
    Movable entity;
    int xMovement;
    int yMovement;

    @Override
    public int getX() {
        return xMovement;
    }

    @Override
    public int getY() {
        return yMovement;
    }

    @Override
    public void setX(int newX) {

        this.xMovement = newX;
    }

    @Override
    public void setY(int newY) {

        this.yMovement = newY;
    }

    @Override
    public void execute() {
        entity.setX(xMovement + entity.getX());
        entity.setY(yMovement + entity.getY());

    }

    @Override
    public void undo() {

        entity.setX(entity.getX() - xMovement);
        entity.setY(entity.getY() - yMovement);
    }
}

class CommandPutItem implements Storable, Command {
    Storable entity;
    String item;
    String[] myArray;


    @Override
    public int getInventoryLength() {
        return myArray.length;
    }

    @Override
    public String getInventoryItem(int index) {
        return myArray[index];
    }

    @Override
    public void setInventoryItem(int index, String item) {
        myArray[index] = item;

    }

    @Override
    public void execute() {

        for (int i = 0; i < entity.getInventoryLength(); i++) {
            if (entity.getInventoryItem(i) == null) {
                entity.setInventoryItem(i, item);
                break;
            }
        }
    }

    @Override
    public void undo() {

        for (int i = entity.getInventoryLength() - 1; i >= 0; i--) {
            if (entity.getInventoryItem(i) != null) {
                entity.setInventoryItem(i, null);
                break;
            }
        }

    }
}