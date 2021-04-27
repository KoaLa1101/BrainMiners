package ru.itlab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
//@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "properties")
public class Properties {
    private int id;
    private String education;
    private String busyness;
    private String experience;
    private String sphereOfWork;
    private String salaryWork;
    private String levelOfEnglish;

    private User user;

    @OneToOne(mappedBy = "properties", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "education", nullable = true, length = 100)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "busyness", nullable = true, length = 100)
    public String getBusyness() {
        return busyness;
    }

    public void setBusyness(String busyness) {
        this.busyness = busyness;
    }

    @Basic
    @Column(name = "experience", nullable = true, length = 100)
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "sphereOfWork", nullable = true, length = 100)
    public String getSphereOfWork() {
        return sphereOfWork;
    }

    public void setSphereOfWork(String sphereOfWork) {
        this.sphereOfWork = sphereOfWork;
    }

    @Basic
    @Column(name = "salaryWork", nullable = true, length = 100)
    public String getSalaryWork() {
        return salaryWork;
    }

    public void setSalaryWork(String salaryWork) {
        this.salaryWork = salaryWork;
    }

    @Basic
    @Column(name = "levelOfEnglish", nullable = true, length = 100)
    public String getLevelOfEnglish() {
        return levelOfEnglish;
    }

    public void setLevelOfEnglish(String levelOfEnglish) {
        this.levelOfEnglish = levelOfEnglish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(id, that.id) && Objects.equals(education, that.education) && Objects.equals(busyness, that.busyness) && Objects.equals(experience, that.experience) && Objects.equals(sphereOfWork, that.sphereOfWork) && Objects.equals(salaryWork, that.salaryWork) && Objects.equals(levelOfEnglish, that.levelOfEnglish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, education, busyness, experience, sphereOfWork, salaryWork, levelOfEnglish);
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
