public class SimplePacketFactory extends PacketFactory {

    private static SimplePacketFactory simplePacketFactory=new SimplePacketFactory();

    private SimplePacketFactory() {
    }

    public static SimplePacketFactory getInstance(){
        return simplePacketFactory;
    }

    public Packet createPacket(String destination,String content){
        return new Packet(destination,content);
    }
}
