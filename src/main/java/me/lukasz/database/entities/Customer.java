package me.lukasz.database.entities;

public class Customer
{

    private String id;
    private String fname;
    private String lname;
    private short age;
    private String address;
    private String postcode;
    private String city;
    private String email;

    public Customer(String id, String fname, String lname, short age, String address, String postcode, String city, String email)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
    }

    public Customer(){}

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public short getAge()
    {
        return age;
    }

    public void setAge(short age)
    {
        this.age = age;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Customer: " +
                "ID: " + id +
                ", First Name: " + fname + '\'' +
                ", Last Name: " + lname + '\'' +
                ", Age: " + age +
                ", Address: " + address + '\'' +
                ", Postcode: " + postcode + '\'' +
                ", City: " + city + '\'' +
                ", Email: " + email + '\'';
    }
}
