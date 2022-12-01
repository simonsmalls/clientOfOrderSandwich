public class PrintServer extends PacketHandler{

    private PrintingStrategy printingStrategy;


    public PrintServer(String address, PrintingStrategy printingStrategy) {
        super(address);
        this.printingStrategy = printingStrategy;
    }

    public PrintingStrategy getPrintingStrategy() {
        return printingStrategy;
    }

    public void setPrintingStrategy(PrintingStrategy printingStrategy) {
        this.printingStrategy = printingStrategy;
    }



    public void print(){
        System.out.println(" from printer "+ this.getAddress());
        if (printingStrategy!=null){
            printingStrategy.print();
        }

    }

    @Override
    public void doJob() {
        print();
    }


    @Override
    public void notifyObservers(Packet packet) {

    }
}
