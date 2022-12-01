public class TapeStreamerAdapter extends PacketHandler{

    private TapeStreamerInterface handler;

    public TapeStreamerAdapter(String address,TapeStreamerInterface handler){
        super(address);
        this.handler=handler;

    }



    @Override
    public void doJob() {
        handler.stream();

    }

    @Override
    public void notifyObservers(Packet packet) {

    }
}
