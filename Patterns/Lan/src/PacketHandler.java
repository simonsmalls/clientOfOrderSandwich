import java.util.ArrayList;
import java.util.List;

public abstract class PacketHandler extends Node implements Subject {

    static List<Observer> observerList=new ArrayList<>();

    public PacketHandler(String address) {
        super(address);
    }
    public PacketHandler(String address, List<Observer> observerList) {
        super(address);
        this.observerList=observerList;
    }

    public void register(Observer observer){
        observerList.add(observer);
    }

    public void remove(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObservers(PacketComponent packet){
        System.out.println("send something to observers");
        for (Observer o:observerList){
            if(packet.getOriginAddress().equals(((Workstation) o).getAddress())) {
                System.out.println(((Workstation) o).getAddress());
                DestinationEvent destinationEvent=new DestinationEvent();
                destinationEvent.setAddressPacketHandler(this.getAddress());
                ((Workstation) o).update(destinationEvent);
            }

        }

    }
    public void getUpdate(Observer observer){

    }

    public void receive(PacketComponent p) throws WentAroundException {
        if(p.getDestination().equals(this.getAddress())){
            System.out.println(p.getContents());
            doJob();
            if (observerList.size()>0) notifyObservers(p);
        }else {
            send(p);
        }

    }
    public abstract void doJob();


}
