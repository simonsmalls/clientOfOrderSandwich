import java.util.List;

public class SuperPacketFactory extends PacketFactory {

    private static SuperPacketFactory superPacketFactory =new SuperPacketFactory();

    private SuperPacketFactory() {
    }

    public static SuperPacketFactory getInstance(){
        return superPacketFactory;
    }

    @Override
    public PacketComponent createPacket(String destination, String content) {
        SuperPacket superPacket=new SuperPacket(destination);


        String[] stringList= content.split("\\.");

        for (int i=0;i<stringList.length;i++){
            stringList[i]+=".";

        }

        for (String s:stringList){

            PacketComponent packet= oneSentence(destination,s);
            superPacket.addPacket(packet);

        }

       return superPacket;



    }


    public PacketComponent oneSentence(String destination, String content){
        SuperPacket superPacket=new SuperPacket(destination);
        String[] stringList= content.split(" ");
        for(String s:stringList){
            if(!s.equals("")) {
                superPacket.addPacket(new Packet(destination, s));

            }
        }
        return superPacket;

    }
}
