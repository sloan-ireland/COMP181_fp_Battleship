import java.util.Scanner;

public class ShipMovement {
    private int x;
    private int y;


    public void moveUp() {y--}

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (input == 'W') {
                myShip.moveUp();
        } else if (input == 'A') {
                myShip.moveLeft();
        } else if (input == 'S') {
                myShip.moveDown();
        } else if (input == 'D') {
                myShip.moveRight();
        } else {
                System.out.println("Invalid input.use wasd.");
        }

    }
}
