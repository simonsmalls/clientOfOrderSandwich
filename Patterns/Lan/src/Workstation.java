public class Workstation extends Node implements Observer{
    public Workstation(String address) {
        super(address);
    }
    public void originate(PacketComponent p) throws WentAroundException {


        p.setOriginAddress(this.getAddress());
        send(p);

    }
    public void receive(PacketComponent packet) throws WentAroundException {

        if(packet.getOriginAddress().equals(this.getAddress())) throw new WentAroundException("already was here");

        send(packet);

    }

    @Override
    public void update() {

    }
    public void update(DestinationEvent destinationEvent){
        System.out.println(destinationEvent.getAddressPacketHandler());
        System.out.println("has been received");
    }
}
