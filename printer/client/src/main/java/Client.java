public class Client {
    public static void main(String[] args) {

        long startTimeInns = System.nanoTime();

        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invaid proxy");
            }
            String message = "Hello world";
            printer.printString(message);

            long messageSize = message.getBytes().length;

            long endTimeInns = System.nanoTime();

            long latency = endTimeInns - startTimeInns;

            double throughput = messageSize / latency;

            System.out.println("Time taken in ns: " + (latency));
            System.out.println("Time taken in ms: " + (latency) / 1000000);
            System.out.println("Time taken in s: " + (latency) / 1000000000);
            System.out.println("Tamaño del mensaje en bytes: " + messageSize);
            System.out.println("Throughput: " + throughput + " bytes/segundo");
        }
    }
}
