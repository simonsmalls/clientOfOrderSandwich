public abstract class PacketFactory {

    public static PacketFactory createFactory(PacketType p) {
        switch (p) {
            default :
            case BASIC:
                return SimplePacketFactory.getInstance();
            case SUPER:
                return SuperPacketFactory.getInstance();
        }
    }
    public abstract PacketComponent createPacket(String destination,String content);
}
