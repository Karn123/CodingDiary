package controller;

/**
 * Created by joy12 on 2017/3/16.
 */
public class PersonData{
    private Integer id;
    private String nickName;
    private String portraitUrl;

    public PersonData() {
        id = -1;
        nickName = null;
        portraitUrl = null;
    }

    public PersonData(Integer ID, String name){
        id = ID;
        nickName = name;
        portraitUrl = null;
    }
    public PersonData(Integer ID, String name,String url){
        id = ID;
        nickName = name;
        portraitUrl = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }
    @Override
    public String toString() {
        String str = "id:" + id +"  nickName: " + nickName + "  portrait: ";
        if (portraitUrl != null){
            str += portraitUrl;
        }
        return str;
    }
}
