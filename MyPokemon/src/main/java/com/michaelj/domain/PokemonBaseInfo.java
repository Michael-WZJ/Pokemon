package com.michaelj.domain;

public class PokemonBaseInfo {
    private String code;
    private String name;
    private String gen;
    private String prop1;
    private String prop2;
    private String nameJpn;
    private String nameEng;
    private String pic;

    @Override
    public String toString() {
        return code + " " + name + " " + gen;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGen() {
        return gen;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public String getNameJpn() {
        return nameJpn;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getPic() {
        return pic;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    public void setNameJpn(String nameJpn) {
        this.nameJpn = nameJpn;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
