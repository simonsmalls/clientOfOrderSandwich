import java.util.List;

public class LanFacade {

    public List<LanComponent> constructLan(){

        LanFactory l=LanFactory.createFactory(LanType.SIMPLE);
        List<LanComponent> list=l.createNodes();
        l.createLan(list);
        return list;
    }
}


