//Состав класса Comicstore (магазин комиксов): фамилия владельца, наличие лицензии, количество наименований продукции.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        ComicStore taganrock = new ComicStore("Alex", false, 5);
        WriteInfo writeInfo = new WriteInfo(taganrock);
        writeInfo.printInfo();
        writeInfo.checkLicense();
        /*System.out.println(taganrock.getInfo());
        System.out.println(taganrock.cockOrNot());*/
    }
}
class ComicStore{
    private String owner;
    private boolean license;
    private int products;

    public  ComicStore(String owner, boolean license, int products){
        this.owner = owner;
        this.license = license;
        this.products = products;
    }

    public ComicStore(String owner, int products){
        this.owner = owner;
        this.products = products;
    }

    public ComicStore(){
        this.owner = "none";
        this.license = false;
        this.products = 0;
    }
    public String getInfo(){
        return "Владелец: "+ owner + ", статус лицензии: " + license + ", количество товара: " + products;
    }
    public String cockOrNot(){
        if(license){
            return "Порядочный гражданин, налоги платит.";
        }else return "Вот этот питушара, вяжи его ребята !";
    }
}

class WriteInfo{
    private ComicStore store;

    public WriteInfo(ComicStore store){
        this.store = store;
    }

    public void printInfo(){
        System.out.println(store.getInfo());
    }

    public void checkLicense(){
        System.out.println(store.cockOrNot());

    }
}