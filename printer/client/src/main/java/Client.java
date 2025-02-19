public class Client {
    public static void main(String[] args) {
        
        long startTimeInns = System.nanoTime();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if (printer == null) {
                throw new Error("Invaid proxy");
            } 
            printer.printString("Hello world");
        }
        

        long endTimeInns = System.nanoTime();

        System.out.println("Time taken in ns: " + (endTimeInns - startTimeInns));
        System.out.println("Time taken in ms: " + (endTimeInns - startTimeInns) / 1000000);
        System.out.println("Time taken in s: " + (endTimeInns - startTimeInns) / 1000000000);
    } 
}
