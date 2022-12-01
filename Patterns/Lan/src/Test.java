import java.util.List;

public class Test {
    public static void main(String[] args) {
        Printer3D d=new Printer3D();

        Packet p= new Packet("6","first msg");
/*
        LanComponent n1= new Node("1");
        LanComponent n2 =new Node("3");
        LanComponent p1=new PrintServer("2",new InjektPrinter());
        LanComponent p2=new PrintServer("4",new LaserPrinter());
        LanComponent w=new Workstation("1");
        LanComponent f1=new FileServer("6");
        w.setNextComponent(n1);
        n1.setNextComponent(p1);
        p1.setNextComponent(n2);
        n2.setNextComponent(p2);
        p2.setNextComponent(f1);
        f1.setNextComponent(w);
        /*
 */

        //p2.setPrintingStrategy((new LaserPrinter()));


        PacketFactory factory=PacketFactory.createFactory(PacketType.SUPER);
        PacketComponent packet=factory.createPacket("2","test here. does it work.");


        LanFactory lanfactory=LanFactory.createFactory(LanType.SIMPLE);


        LanFacade l= new LanFacade();
        List<LanComponent> lan= l.constructLan();
        try {
            Workstation w=lanfactory.findWorkstation(lan, "12");
            w.originate(packet);


        } catch (NoWorkstationException | WentAroundException e) {
            System.out.println(e.getMessage());

        }

        Node n=new FileServer("58");
        DecoInterface dom= new DecoratorWordsIn(new GreyDecorator(new DecoratorWordsIn(n)));
        dom.assemble();

    }
}
