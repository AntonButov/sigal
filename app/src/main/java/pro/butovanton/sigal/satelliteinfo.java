package pro.butovanton.sigal;

public class satelliteinfo {
    private String shortname;
    private String name;
    private String description;
    private int coner;

    public satelliteinfo(String shortname, String name, String description, int coner) {
        this.shortname = shortname;
        this.name = name;
        this.description = description;
        this.coner = coner;
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
