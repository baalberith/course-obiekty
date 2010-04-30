package l6z3;

class Buffer {
    private static final int N = 5;
    private int[] buffer = new int[N];
    private int in = 0, out = 0;
    private int count = 0;

    public synchronized void append(int value) {
        while (count == N) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        buffer[in] = value;
        in = (in + 1) % N;
        count = count + 1;

        notifyAll();
    }

    public synchronized int take () {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        int elem = buffer[out];
        out = (out + 1) % N;
        count = count - 1;

        notifyAll();

        return elem;
    }
}

class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            buffer.append(i);

            System.out.println("Produced: " + i);

            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}

class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int v = buffer.take();

            System.out.println("Consumed: " + v);

            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buf = new Buffer();

        Producer p1 = new Producer(buf);
        Consumer c1 = new Consumer(buf);

        p1.start();
        c1.start();
    }
}
