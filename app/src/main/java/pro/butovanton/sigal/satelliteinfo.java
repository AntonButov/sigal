package pro.butovanton.sigal;

public class satelliteinfo {
    private String shortname;
    private String name;
    private String description;
    private int coner;

    public Poligons getPoligons() {
        return poligons;
    }

    private Poligons poligons;

    public satelliteinfo(String shortname, String name, String description, int coner, Poligons poligons) {
        this.shortname = shortname;
        this.name = name;
        this.description = description;
        this.coner = coner;
        this.poligons = poligons;
    }

    public String getShortname() { return this.shortname; }

    public String getname() {
        return this.name;
    }

    public String getdescription() {
        return this.description;
    }

    public int getConer() { return this.coner; }

    public String toString() {
        return shortname + " \n" +
               name + " \n" +
               description + " \n" +
               coner + " \n";
    }
}
