public class FileServer  extends PacketHandler{
    public FileServer(String address) {
        super(address);
    }

    @Override
    public void doJob() {
        file();
    }

    public void file(){
        System.out.println("save it "+ this.getAddress());
    }

    @Override
    public void notifyObservers(Packet packet) {

    }
}
