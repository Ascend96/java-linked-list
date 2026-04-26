import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList linkedList = new CustomLinkedList();

        // read from file
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                // remove the UTF-8 Byte Order Mark (BOM) if it exists.
                String line = scanner.nextLine().replace("\uFEFF", "");
                // remove leading/trailing whitespace and split the line into tokens.
                //   \\s = any whitespace character (space, tab, newline, etc.)
                //   + = one or more occurrences
                String[] parts = line.trim().split("\\s+");

                // loop through each token
                for (String part : parts) {
                    // skip any empty strings although the regex likely prevents this possibility
                    if (!part.isEmpty()) {
                        // parse the value to an int and insert into the linked list
                        linkedList.insert(Integer.parseInt(part));
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            return;
        }

        // show list after insertion
        System.out.println("After inserting elements from file:");
        printList(linkedList);

        // demonstrate deletion
        System.out.println("\nDeleting value 22...");
        linkedList.delete(22);

        System.out.println("List after deleting 22:");
        printList(linkedList);

        // delete another value
        System.out.println("\nDeleting value 11 (head node)...");
        linkedList.delete(11);

        System.out.println("List after deleting 11:");
        printList(linkedList);

        // try deleting something not in list
        System.out.println("\nDeleting value 100 (not in list)...");
        linkedList.delete(100);

        System.out.println("List after attempting to delete 100:");
        printList(linkedList);

        // final traversal using iterator
        System.out.println("\nFinal traversal using iterator:");
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    public static void printList(CustomLinkedList list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}