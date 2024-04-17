package com.michaelj.domain.entity;

//import jakarta.persistence.*;

public class PokemonBaseInfo {
    /**
     * Id
     */
//    @Id
//    @GeneratedValue(generator = "JDBC")
    private Long pokeBaseId;

    /**
     * 编号
     */
    private String pokeBaseCode;

    /**
     * 名称
     */
    private String pokeBaseName;

    /**
     * 世代
     */
    private String gen;

    /**
     * 属性1
     */
    private String prop1;

    /**
     * 属性2
     */
    private String prop2;

    /**
     * 日文名
     */
    private String nameJpn;

    /**
     * 英文名
     */
    private String nameEng;

    /**
     * 图片路径
     */
    private String pokeBasePic;

    @Override
    public String toString() {
        return pokeBaseCode + " " + pokeBaseName + " " + gen;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /*                      Accessor and Mutator Methods                        */
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public Long getPokeBaseId() {
        return pokeBaseId;
    }

    public String getPokeBaseCode() {
        return pokeBaseCode;
    }

    public String getPokeBaseName() {
        return pokeBaseName;
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

    public String getPokeBasePic() {
        return pokeBasePic;
    }

    public void setPokeBaseId(Long pokeBaseId) {
        this.pokeBaseId = pokeBaseId;
    }

    public void setPokeBaseCode(String pokeBaseCode) {
        this.pokeBaseCode = pokeBaseCode;
    }

    public void setPokeBaseName(String pokeBaseName) {
        this.pokeBaseName = pokeBaseName;
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

    public void setPokeBasePic(String pokeBasePic) {
        this.pokeBasePic = pokeBasePic;
    }
}
