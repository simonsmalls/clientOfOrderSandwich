import java.util.ArrayList;
import java.util.List;

public class SimpleLanFactory extends LanFactory{
    private static SimpleLanFactory simpleLanFactory =new SimpleLanFactory();

    private SimpleLanFactory() {
    }

    public static SimpleLanFactory getInstance(){
        return simpleLanFactory;
    }

    @Override
    public List<LanComponent> createNodes() {
        LanComponent n1= new Node("10");
        LanComponent n2 =new Node("3");
        LanComponent p1=new PrintServer("2",new InjektPrinter());
        LanComponent p2=new PrintServer("4",new LaserPrinter());
        LanComponent w=new Workstation("1");
        LanComponent w2=new Workstation("12");
        LanComponent f1=new FileServer("6");
        ((PacketHandler) p1).register((Observer) w);
        ((PacketHandler) p1).register((Observer) w2);
        List<LanComponent> nodeList= new ArrayList<>();

        nodeList.add(n1);
        nodeList.add(n2);
        nodeList.add(w2);
        nodeList.add(p1);
        nodeList.add(p2);
        nodeList.add(w);
        nodeList.add(f1);

        return nodeList;


    }

    @Override
    public void createLan(List<LanComponent> list) {
        int index=0;
        for(int i=0; i<list.size()-1;i++){
            list.get(i).setNextComponent(list.get(i+1));
            index++;
        }

        list.get(index).setNextComponent(list.get(0));

    }

    public List<LanComponent> constructLan(){

        List<LanComponent> list=createNodes();
        createLan(list);
        return list;
    }

    @Override
    public Workstation findWorkstation(List<LanComponent> list, String name) throws NoWorkstationException {

        for (LanComponent node:list){
            if (node instanceof Workstation && name.equals(node.getAddress())){
                return (Workstation) node;
            }
        }

        if(true) throw new NoWorkstationException("Workstation not found");


        return null;
    }
}
