/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author DUBIC
 */
public class DTO {
    
    private String name;
    private int age;
        private DTO2 contact;

    public DTO2 getContact() {
        return contact;
    }

    public void setContact(DTO2 contact) {
        this.contact = contact;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
