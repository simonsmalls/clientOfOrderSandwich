public class MQServer extends PacketHandler{
    public MQServer(String address) {
        super(address);
    }

    @Override
    public void doJob() {
        message();

    }
    public void message(){
        System.out.println("mqserver");
    }

    @Override
    public void notifyObservers(Packet packet) {

    }
}
