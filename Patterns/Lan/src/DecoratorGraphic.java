public class DecoratorGraphic extends NodeDecorator{
    public DecoratorGraphic(DecoInterface node) {
        super(node);
    }

    public void assemble(){

        System.out.println("graphics");
        getNode().assemble();
    }
}
