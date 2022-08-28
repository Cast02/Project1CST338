package arena;

public class Fight extends Thread{

    public static int counter = 0;

    public static void main(String[] args) {
        Fight thread = new Fight();
        thread.start();
        System.out.print("waiting");
        while(thread.isAlive()){
            System.out.print(".");
        }

        System.out.println("\n"+counter);
        counter++;
        System.out.println(counter);
    }

    @Override
    public void run() {
        System.out.println("Start was called?");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counter++;
    }
}
