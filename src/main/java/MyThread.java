import java.util.Arrays;

public class MyThread {
    Object lock1 = new Object();
    Object lock2 = new Object();

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    float[] arr = new float[SIZE];
    float[] arr1 = new float[HALF];
    float[] arr2 = new float[HALF];
    float[] array = new float[SIZE];

    public void fillArray(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = 1;
        }
    }

    public void arrayTime(){

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            array[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Running time in one thread: " + (System.currentTimeMillis() - a));
    }

    public void  arrayInTwoTime(){

        long a = System.currentTimeMillis();

        System.arraycopy(arr,0,arr1,0,HALF);
        System.arraycopy(arr,HALF,arr2,0,HALF);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    for (int i = 0; i < arr1.length; i++) {
                        arr1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    int real;
                    for (int i = 0; i < arr2.length; i++) {
                        real = i + HALF;
                        arr2[i] = (float)(arr[real] * Math.sin(0.2f + real / 5) * Math.cos(0.2f + real/ 5) * Math.cos(0.4f + real / 2));
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1,0,arr,0,HALF);
        System.arraycopy(arr2,0,arr,HALF,HALF);

        System.out.println("Working time in two streams: " + (System.currentTimeMillis() - a));

        System.out.println("Arrays are equal: " + Arrays.equals(arr,array));
    }
}
