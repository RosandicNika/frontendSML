package hr.fer.ruazosa.sharemylocation;

/**
 * Created by Nika on 6/25/2017.
 */



import java.util.List;

/**
 * Created by antun on 21.06.17..
 */

public class Group {

    private long id;
    private String name;
    private String admin;
    private Integer noOfMembers;
    private String icon;

    public Group() { }

    public Group(long id, String name, String admin, Integer noOfMembers, String icon) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.noOfMembers = noOfMembers;
        this.icon = icon;
    }



    private String errorMessage;
    private List fieldValidationErrors;

    public List getFieldValidationErrors() {
        return fieldValidationErrors;
    }

    public void setFieldValidationErrors(List fieldValidationErrors) {
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Integer getNoOfMembers() {
        return noOfMembers;
    }

    public void setNoOfMembers(Integer noOfMembers) {
        this.noOfMembers = noOfMembers;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
