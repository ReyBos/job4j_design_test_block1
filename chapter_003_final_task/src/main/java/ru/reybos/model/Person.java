package ru.reybos.model;

public class Person extends Model {
    private int companyID;
    private Company company;

    public Person(String name) {
        super(name);
    }

    public Person(String name, int companyID) {
        super(name);
        this.companyID = companyID;
    }

    public Person(int id, String name, int companyID) {
        super(id, name);
        this.companyID = companyID;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public int getCompanyID() {
        return companyID;
    }
}
