package pro.butovanton.sigal;

public class detailsat {
    private int image;
    private String name;
    private String price;
    private String description;

    public detailsat(int image, String name, String price, String description){
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public int getImage() {
        return this.image; }

    public String getname() {
        return this.name;
    }

    public String getprice() {
        return this.price;
    }

    public String getdescription() {
        return this.description;
    }



}
