package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class Recipe extends DataEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1396825210827801096L;

	private Long schoolId;

    private Long classId;

    private Date recipeDate;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Date getRecipeDate() {
        return recipeDate;
    }

    public void setRecipeDate(Date recipeDate) {
        this.recipeDate = recipeDate;
    }

}