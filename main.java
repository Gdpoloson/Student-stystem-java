import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private ArrayList<Integer> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public void updateGrade(int index, int newGrade) {
        if (index >= 0 && index < grades.size()) {
            grades.set(index, newGrade);
        } else {
            System.out.println("无效的成绩索引！");
        }
    }

    public void removeGrade(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
        } else {
            System.out.println("无效的成绩索引！");
        }
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }
}

public class GradeManager {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1. 添加学生");
            System.out.println("2. 给学生添加成绩");
            System.out.println("3. 显示学生平均成绩");
            System.out.println("4. 删除学生");
            System.out.println("5. 显示所有学生及其平均成绩");
            System.out.println("6. 更新学生成绩");
            System.out.println("7. 显示学生成绩列表");
            System.out.println("8. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    showAverageGrade();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    showAllStudents();
                    break;
                case 6:
                    updateStudentGrade();
                    break;
                case 7:
                    showStudentGrades();
                    break;
                case 8:
                    System.out.println("退出系统。");
                    return;
                default:
                    System.out.println("无效选择，请重新选择。");
            }
        }
    }

    private static void addStudent() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("学生添加成功！");
    }

    private static void addGradeToStudent() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("未找到该学生！");
            return;
        }
        System.out.println("请输入成绩：");
        int grade = scanner.nextInt();
        scanner.nextLine(); // consume newline
        student.addGrade(grade);
        System.out.println("成绩添加成功！");
    }

    private static void showAverageGrade() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("未找到该学生！");
            return;
        }
        double average = student.getAverageGrade();
        System.out.println("学生 " + student.getName() + " 的平均成绩为：" + average);
    }

    private static void removeStudent() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("未找到该学生！");
            return;
        }
        students.remove(student);
        System.out.println("学生已删除！");
    }

    private static void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("没有学生记录！");
            return;
        }
        for (Student student : students) {
            System.out.println("学生姓名：" + student.getName() + "，平均成绩：" + student.getAverageGrade());
        }
    }

    private static void updateStudentGrade() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("未找到该学生！");
            return;
        }
        System.out.println("请输入要更新的成绩索引：");
        int index = scanner.nextInt();
        System.out.println("请输入新的成绩：");
        int newGrade = scanner.nextInt();
        scanner.nextLine(); // consume newline
        student.updateGrade(index, newGrade);
        System.out.println("成绩更新成功！");
    }

    private static void showStudentGrades() {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        Student student = findStudentByName(name);
        if (student == null) {
            System.out.println("未找到该学生！");
            return;
        }
        System.out.println("学生 " + student.getName() + " 的成绩列表：");
        ArrayList<Integer> grades = student.getGrades();
        for (int i = 0; i < grades.size(); i++) {
            System.out.println("成绩 " + (i + 1) + ": " + grades.get(i));
        }
    }

    private static Student findStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
