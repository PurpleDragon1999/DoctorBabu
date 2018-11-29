package com.shubham.dell.doctorbabu;

class Userinformation {
    public String name,email,phone,role;

    public Userinformation(String name, String email, String phone,String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role=role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    public String getRole(){
        return role;
    }
}
