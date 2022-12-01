public class Node extends LanComponent implements DecoInterface{
    private NodeDecorator nodeDecorator;


    public Node(String address) {
        super(address);
    }

    @Override
    public void send(PacketComponent p) throws WentAroundException {
        getNextComponent().receive(p);

    }

    @Override
    public void receive(PacketComponent p) throws WentAroundException {

        this.send(p);

    }

    public NodeDecorator getNodeDecorator() {
        return nodeDecorator;
    }

    public void setNodeDecorator(NodeDecorator nodeDecorator) {
        this.nodeDecorator = nodeDecorator;
    }

    @Override
    public void assemble() {
        System.out.println("circle ");

    }
}
