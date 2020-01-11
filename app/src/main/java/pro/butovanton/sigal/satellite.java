package pro.butovanton.sigal;

import android.widget.ImageView;

public class satellite {
    private int image;
    private String name;
    private String description;

    public satellite(int image,String name, String description){
        this.image = image;
        this.name = name;
        this.description = description;

    }

    public int getImage() {
        return this.image; }

    public String getname() {
        return this.name;
    }

    public String getdescription() {
        return this.description;
    }


}
