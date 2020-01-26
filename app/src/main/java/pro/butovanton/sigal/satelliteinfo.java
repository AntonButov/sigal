package pro.butovanton.sigal;

public class satelliteinfo {
    private String name;
    private String description;
    private int coner;

    public satelliteinfo(String name, String description, int coner){
        this.name = name;
        this.description = description;
        this.coner = coner;

    }

    public String getname() {
        return this.name;
    }

    public String getdescription() {
        return this.description;
    }

    public int getConer() { return this.coner; }
}
