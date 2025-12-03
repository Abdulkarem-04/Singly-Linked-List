import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentLinkedList {
    private StudentNode head; // Points to first node
    private int size; // Tracks number of nodes

    public StudentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Methods will go here...

    public void addFirst(String name, String course, int credit, double grade, double cgpa) {
        //Creating new node
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);

        // Point new node to current head
        newNode.next = head;
        head = newNode; // Update head to new node
        size++;
    }

    public void addLast(String name, String course, int credit, double grade, double cgpa) {
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);

        // Edge case: empty list
        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        // Traverse to last node
        StudentNode current = head;
        while (current.next != null) {
            current = current.next; // Move to next node
        }

        // current is now at last node
        current.next = newNode;
        size++;
    }

    public void insertAt(int index, String name, String course, int credit, double grade, double cgpa) {
        // Validate index
        if (index < 0 || index > size) {
            System.out.println("Index out of bounds!");
            return;
        }

        // Special case: insert at beginning
        if (index == 0) {
            addFirst(name, course, credit, grade, cgpa);
            return;
        }

        // Create new node
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);

        // Travers to new position before insertion point
        StudentNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        // Insert: new node points to current's next
        newNode.next = current.next;
        current.next = newNode; // Current points to new node

        size++;
    }

    public void deleteFirst() {
        // Edge case: empty list
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        // Move head to next node
        head = head.next;
        size--;
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        // Edge case: only one node
        if (head.next == null) {
            head = null;
            size--;
            return;
        }

        // Traverse to second-last node
        StudentNode current = head;
        while (current.next.next !=null) {
            current = current.next;
        }

        // Remove last node
        current.next = null;
        size--;
    }

    public void deleteAt(int index) {
        // Validate index
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds!");
            return;
        }

        // Special case delete first
        if (index == 0) {
            deleteFirst();
            return;
        }

        // Traverse to node before deletion point
        StudentNode current = head;
        for (int i = 0; i < index - 1; i++){
            current = current.next;
        }

        // Bypass the node to delete
        current.next = current.next.next;
        size--;
    }

    public int search(String name) {
        StudentNode current = head;
        int index = 0;

        // Traverse and compare
        while (current != null) {
            if (current.name.equals(name)) {
                return index; // Found!
            }
            current = current.next;
            index++;
        }

        return -1; // Note found
    }

    public void display() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        StudentNode current = head;
        System.out.println("Student List:");
        System.out.println("------------------------------");

        while (current != null) {
            System.out.printf("Name: %s, CGPA: %2f%n", current.name, current.cgpa);
            current = current.next;
        }
        System.out.println("------------------------------");
    }

    public int getSize() {
        return size;
    }

    public void loadFromCSV(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            //Skip header line
            line = br.readLine();

            // Read each subsequent line
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(",");

                // Extract fields
                String name = data[0];
                String course = data[1];
                int credit = Integer.parseInt(data[2]);
                double grade = Double.parseDouble(data[3]);
                double cgpa = Double.parseDouble(data[4]);

                // Add to list
                addLast(name, course, credit, grade, cgpa);
            }

            br.close();
            System.out.println("Loaded " + size + " student from CSV");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void measurePerformance() {
        // Measure addFirst performance
        long startTime = System.nanoTime();
        addFirst("Test Student", "CS999", 3, 90.0, 3.8);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("addFirst took: " + duration + " nanoseconds");

        // Convert to milliseconds for readability
        double milliseconds = duration / 1_000_000.0;
        System.out.println("addFirst took: " + milliseconds + " ms");
    }

    public void performanceTest() {
        StudentLinkedList list = new StudentLinkedList();

        // Load 1000 student form CSV
        list.loadFromCSV("students.csv");

        System.out.println("\n=== Performance Test (1000 students) ===\n");

        // Test addFirst
        long start = System.nanoTime();
        list.addFirst("New Student", "CS100", 3, 85.0, 3.5);
        long end = System.nanoTime();
        System.out.println("addFirst: " + (end - start) + " ns");

        // Test addLast
        start = System.nanoTime();
        list.addLast("Last Student", "CS200", 4, 90.0, 3.8);
        end = System.nanoTime();
        System.out.println("addLast: " + (end - start) + " ns");

        // Test search (worst case - not found)
        start = System.nanoTime();
        list.search("NonExistent");
        end = System.nanoTime();
        System.out.println("search (not found): " + (end - start) + " ns");

        // Test deleteFirst
        start = System.nanoTime();
        list.deleteFirst();
        end = System.nanoTime();
        System.out.println("DeleteFirst: " + (end -start) + " ns");

        // Test deleteLast
        start = System.nanoTime();
        list.deleteLast();
        end = System.nanoTime();
        System.out.println("deleteLast: " + (end - start) + " ns");
    }
}