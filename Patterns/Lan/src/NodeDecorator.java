public abstract class NodeDecorator implements DecoInterface{

    private DecoInterface node;


    public NodeDecorator(DecoInterface node) {


        this.node=node;
    }



    public abstract void assemble();


    public DecoInterface getNode() {
        return node;
    }

    public void setNode(DecoInterface node) {
        this.node = node;
    }
}
