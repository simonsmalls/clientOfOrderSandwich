import java.util.List;

public abstract class LanFactory {

    public static LanFactory createFactory(LanType p) {
        switch (p) {
            default :
            case SIMPLE:
                return SimpleLanFactory.getInstance();
            case EXTENDED:
                return ExtendedLanFactory.getInstance();
        }
    }

    public abstract List<LanComponent> createNodes();
    public abstract void createLan(List<LanComponent> list);
    public abstract Workstation findWorkstation(List<LanComponent> list, String name) throws NoWorkstationException;
}
