package com.shubham.dell.doctorbabu;

public class doctorinfo {
    String name,email,phone,role,special;
    public doctorinfo()
    {}

    public doctorinfo(String name, String email, String phone,String role,String special) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role=role;
        this.special=special;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecial() {
        return special;
    }

    public String getRole()
    {
        return role;

    }
}
