public class StudentNode {
    //Data fields
    String name;
    String course;
    int credit;
    double grade;
    double cgpa;

    // Pointer to next node
    StudentNode next;

    // Constructor to initialize a node
    public StudentNode(String name, String course, int credit, double grade, double cgpa) {
        this.name = name;
        this.course = course;
        this.credit = credit;
        this.grade = grade;
        this.cgpa = cgpa;
        this.next = null; // Initially, next points to null
    }

    public static void main(String[] args) {
    StudentLinkedList.testCase1();
    StudentLinkedList.testCase2();
    StudentLinkedList.testCase3();
    }
}