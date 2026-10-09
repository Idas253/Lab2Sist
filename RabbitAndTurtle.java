    class AnimalThread extends Thread {
        private String animalName;
        private int animalPriority;

        private int meters = 0;
        private int animalId;
        private int[] sharedDistances;


        public AnimalThread(String name, int priority, int animalId, int[] sharedDistances) {
            super(name);
            this.animalName = name;
            this.animalPriority = priority;
            this.animalId = animalId;
            this.sharedDistances = sharedDistances;
            setPriority(priority);
        }

        @Override
        public void run() {
            int finishLine = 100;

            for (meters = 0; meters < finishLine; meters++) {
                sharedDistances[animalId] = meters;


                int myDist = sharedDistances[animalId];
                int otherDist = sharedDistances[1 - animalId];

                if (myDist < otherDist) {
                    this.setPriority(Thread.MAX_PRIORITY);
                } else {
                    this.setPriority(Thread.MIN_PRIORITY);
                }

                Thread.yield();


                long sum = 0;
                for (int i = 0; i < 10000; i++) {
                    sum += i;
                }


                if (meters % 10 == 0) {
                    System.out.printf("%s пробежал %d м. Текущий приоритет: %d%n", animalName, meters, this.getPriority());
                }
            }
            System.out.println(animalName + " финишировал!");
        }
    }

    public class RabbitAndTurtle {
        public static void main(String[] args) {

            int[] distances = new int[2];


            AnimalThread rabbit = new AnimalThread("Кролик", Thread.MIN_PRIORITY, 0, distances);
            AnimalThread turtle = new AnimalThread("Черепаха", Thread.MAX_PRIORITY, 1, distances);

            System.out.println("Начало гонки!\n");

            rabbit.start();
            turtle.start();
        }
    }