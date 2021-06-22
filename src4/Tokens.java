public class Tokens {
    private final String ID;
    private final String itemPart;
    private int countOfItems;

    public Tokens(String ID, String item, int countOfItems) {
        this.ID = ID;
        this.itemPart = item;
        this.countOfItems = countOfItems;
    }

    public String getID() {
        return ID;
    }

    public String getItemPart() {
        return itemPart;
    }

    public int getCountOfItems() {
        return countOfItems;
    }

    public void setCountOfItems(int countOfItems) {
        this.countOfItems = countOfItems;
    }
}
