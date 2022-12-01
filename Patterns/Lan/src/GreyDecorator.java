public class GreyDecorator extends NodeDecorator{


    public GreyDecorator(DecoInterface node) {
        super(node);
    }

    public void assemble() {
       System.out.println("grey");
       getNode().assemble();
    }
}
