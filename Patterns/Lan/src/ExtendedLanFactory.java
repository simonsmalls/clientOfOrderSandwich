import java.util.ArrayList;
import java.util.List;

public class ExtendedLanFactory extends LanFactory {

    private static ExtendedLanFactory extendedLanFactory =new ExtendedLanFactory();

    private ExtendedLanFactory() {
    }

    public static ExtendedLanFactory getInstance(){
        return extendedLanFactory;
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
