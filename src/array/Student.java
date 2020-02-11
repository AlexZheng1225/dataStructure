package array;

/**
 * @Author: ZhengCheng
 * @Date: created in 16:28  2020/2/9
 * @Annotation:
 */
public class Student {
    private int stuNo;
    private String stuName;

    public Student(int stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "array.Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}






