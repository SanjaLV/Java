import java.util.Random;

public class People {
    private String name;
    private String surname;
    private Integer age;
    private Sex sex;

    public String getSurname() {
        return surname;
    }

    public People setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getName() {
        return name;
    }

    public People setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public People setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public People setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString ( ) {
        return getName() + " " + getSurname() + " " + getAge().toString() ;
    }

    public People() {
        Random rand = new Random();

        this.age = rand.nextInt(90)+1;
        this.sex = ( rand.nextInt(2) == 0 ) ? Sex.MALE : Sex.FEMALE ;
        if ( Sex.MALE.equals( this.sex ) )
            this.name = NAMES.getMALE();
        else
            this.name = NAMES.getFEMALE();
        this.surname = NAMES.getSURNAME();
    }


}
