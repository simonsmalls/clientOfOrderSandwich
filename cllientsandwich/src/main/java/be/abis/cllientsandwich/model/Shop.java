package be.abis.cllientsandwich.model;

public class Shop {

    private int id;
    private String name;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Shop)){
            return false;
        } else return this.getName().equals(((Shop)o).getName());
    }

    @Override
    public int hashCode(){
        return this.getName().length();
    }


    // getset

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
