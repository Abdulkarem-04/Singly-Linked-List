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

    static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        list.loadFromCSV("students.csv"); // Loads 1k students for this specific csv file!
        System.out.println("Loaded: " + list.getSize() + " students");
    }
}
