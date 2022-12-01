public class PacketComponent {

    private String destination;
    private String contents;
    private String originAddress;

    public PacketComponent(String destination, String contents) {
        this.destination = destination;
        this.contents = contents;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }
}
