public class DecoratorWordsIn extends NodeDecorator{


    public DecoratorWordsIn(DecoInterface node) {
        super(node);
    }

    @Override
    public void assemble() {
        DecoInterface deco;
        deco=getNode();

        while (deco instanceof NodeDecorator){
            deco=((NodeDecorator) deco).getNode();
        }

        System.out.println("words in " + deco.getClass().getSimpleName());
        this.getNode().assemble();
    }
}
