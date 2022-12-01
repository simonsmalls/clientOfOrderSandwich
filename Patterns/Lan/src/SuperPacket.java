import java.util.ArrayList;
import java.util.List;

public class SuperPacket extends PacketComponent{

    private List<PacketComponent> packetList=new ArrayList<>();


    public SuperPacket(String destination) {
        super(destination,null);

    }

    @Override
    public String getContents() {
        String contents="";

        for(PacketComponent packet:packetList){
            if(packet instanceof SuperPacket){
                contents+=packet.getContents();
            }else {

                contents+= packet.getContents()+" ";

            }

        }
        //contents=contents.replace("  "," ");

        return contents;
    }

    public void addPacket(PacketComponent p){


        packetList.add(p);
    }

    public List<PacketComponent> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<PacketComponent> packetList) {
        this.packetList = packetList;
    }
}
