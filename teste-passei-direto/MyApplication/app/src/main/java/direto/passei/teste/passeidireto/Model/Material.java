package direto.passei.teste.passeidireto.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by lunid on 25/01/2018.
 */



@DatabaseTable
public class Material {

    @DatabaseField(id = true, canBeNull = false)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String subjectName;

    @DatabaseField
    private String universityName;

    @DatabaseField
    private Boolean favorite;


    public Material(){}

    public Material(Integer id, String name, String subjectName, String universityName, Boolean favorite){
        this.id = id;
        this.name = name;
        this.subjectName = subjectName;
        this.universityName = universityName;
        this.favorite = favorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}