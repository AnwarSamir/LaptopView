package Models;

/**
 * Created by ascom on 14/03/2016.
 */
public class ApiModel {
    private String name;
    private String price;
    private String hd;
    private String ram;
    public ApiModel()
    {}
    public ApiModel(String text1, String text2, String hd, String ram){
        name = text1;
        price = text2;
        this.hd=hd;
        this.ram=ram;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String desc) {
        this.price = desc;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String tag) {
        this.hd = tag;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String link) {
        this.ram = link;
    }
}
