import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import java.time.Month;
public class MarketTest {
public static void main(String[] args) {
        Market sokMarket = new Market ("Sok", "Beytepe Yurtlar");
        sokMarket.addItem (new FoodItem (1, "cokomel", LocalDate. of (2021, Month. APRIL, 8)));
        sokMarket.addItem (new FoodItem (4, "canakkale asi", LocalDate.of(1915, Month.MARCH, 18)));
        sokMarket.addItem (new ClothingItem (3, "kaft tshirt", ClothingItem.SMALL));
        sokMarket.addItem (new HomeItem (5, "bardak", true));
        sokMarket.listItems();
        System.out.println (Market.getNumberOfFoodItems());
        System.out.println (sokMarket.getNumbefofExpiredFoodItems(LocalDate.now()));


        }
}


class Market{
    private String name;
    private String branch;
    public ArrayList<MarketItems> marketItems=new ArrayList<>();

    public Market(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }
    public void listItems(){
        marketItems.sort(Comparator.comparing(MarketItems::getId));
        for (MarketItems market:marketItems) {
            System.out.println(market.getId()+" "+market.getName());
        }
    }
    public void addItem(MarketItems Items){
        marketItems.add(Items);
    }

    public static int getNumberOfFoodItems(){

        return FoodItem.fooditem;
    }
    public int getNumbefofExpiredFoodItems(LocalDate date){
        int number_experied=0;
        for (MarketItems market:marketItems) {
            if(market instanceof FoodItem){
                if(((FoodItem) market).getDate().compareTo(date)<0){
                        number_experied++;
                }
            }
        }
        return number_experied;
    }
}


class MarketItems{
    private String name;
    private int id;

    public MarketItems( int id,String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}


class FoodItem extends MarketItems{
    private LocalDate date;
    public static int fooditem;

    public FoodItem(int id,String name,  LocalDate date) {
        super(id, name);
        this.date = date;
        fooditem++;
    }

    public LocalDate getDate() {
        return date;
    }
}


class ClothingItem extends MarketItems{
    public static final String SMALL = "S";
    public static final String MEDIUM = "M";
    public static final String LARGE = "L";
    private String size;

    public ClothingItem(int id,String name, String size) {
        super(id, name);
        this.size = size;
    }
}


class HomeItem extends MarketItems{
    private boolean fragile;

    public HomeItem(int id,String name,  boolean fragile) {
        super(id,name);
        this.fragile = fragile;
    }
}