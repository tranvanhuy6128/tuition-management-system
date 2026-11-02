import java.util.List;
import java.util.Scanner;

public class Main{
    private static final Scanner scanner = new Scanner(System.in);
    private static final TuitionService service = new TuitionService();

    public static void main(String[] args){
        while(true){
            System.out.println("\n===== Tuition management system =====");
            System.out.println("1. Show student list");
            System.out.println("2. Add student");
            System.out.println("3. Update student");
            System.out.println("4. Remove student");
            System.out.println("5. Search student");
            System.out.println("6. Show invoice");
            System.out.println("7. Pay");
            System.out.println("0. Quit");
            System.out.println("=====================================\n");

            System.out.print("Choice: ");
            String choice = scanner.nextLine();
            try{
                switch (choice){
                    case "1":
                        showAll();
                        break;
                    case "2":
                        addStudent();
                        break;
                    case "3":
                        updateStudent();
                        break;
                    case "4":
                        deleteStudent();
                        break;
                    case "5":
                        searchStudent();
                        break;
                    case "6":
                        showInvoice();
                        break;
                    case "7":
                        pay();
                        break;
                    case "0":
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
            catch (RuntimeException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 1. Display
    private static void showAll(){
        List<Student> list = service.getAllStudents();
        if(list.isEmpty()){
            System.out.println("Empty list!");
            return;
        }
        for(Student student : list){
            System.out.println("\n-------------------------------------");
            System.out.println(student);
            System.out.println("-------------------------------------");
        }
    }

    // 2. Add
    private static void addStudent(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Major: ");
        String major = scanner.nextLine();
        System.out.print("Number of credits: ");
        while(!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.print("Please enter a number: ");
        }
        int credit = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1. Regular Student");
        System.out.println("2. Policy Student");
        System.out.print("Choice: ");
        String type = scanner.nextLine();

        Student student = switch (type) {
            case "1" -> new RegularStudent(id, name, major, credit);
            case "2" -> new PolicyStudent(id, name, major, credit);
            default -> throw new TuitionException("Invalid choice!");
        };
        service.addStudent(student);
        System.out.println("Student added!");
        System.out.println("-------------------------------------");
    }

    // 3. Update
    private static void updateStudent(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine();
        Student student = service.getStudentById(id);
        String name = student.getFullName();
        String major = student.getMajor();
        int credit = student.getCredit();

        while(true){
            System.out.println("\n-------------------------------------");
            System.out.println("What do you want to update?");
            System.out.println("1. Name");
            System.out.println("2. Major");
            System.out.println("3. Number of credits");
            System.out.println("4. Update all.");
            System.out.println("0. Done");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            if(choice.equals("0"))
                break;

            switch(choice){
                case "1":
                    System.out.print("New name: ");
                    name = scanner.nextLine();
                    break;
                case "2":
                    System.out.print("New major: ");
                    major = scanner.nextLine();
                    break;
                case "3":
                    System.out.print("New number of credits: ");
                    while(!scanner.hasNextInt()){
                        scanner.nextLine();
                        System.out.print("Please enter a number: ");
                    }
                    credit = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case "4":
                    System.out.print("New name: ");
                    name = scanner.nextLine();
                    System.out.print("New major: ");
                    major = scanner.nextLine();
                    System.out.print("Number of credits: ");
                    while(!scanner.hasNextInt()){
                        scanner.nextLine();
                        System.out.print("Please enter a number: ");
                    }
                    credit = scanner.nextInt();
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            service.updateStudent(id, name, major, credit);
            System.out.println("Updated!");
        }
    }

    // 4. Delete
    private static void deleteStudent(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.println("Delete: " + service.getStudentById(id));
        System.out.print("Are you sure (y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")){
            service.deleteStudent(id);
            System.out.println("Deleted!");
        } else{
            System.out.println("Canceled!");
        }
        System.out.println("-------------------------------------");
    }

    // 5. Search
    private static void searchStudent(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        Student student = service.searchById(id);
        if (student == null){
            System.out.println("Can't find student!");
        } else{
            System.out.println(student);
        }
        System.out.println("-------------------------------------");
    }

    // 6. Invoice
    private static void showInvoice(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        TuitionInvoice invoice = service.createInvoice(id);
        System.out.println("===== Invoice =====");
        System.out.println(invoice);
        System.out.println("-------------------------------------");

    }

    private static void pay(){
        System.out.println("\n-------------------------------------");
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        service.payTuition(id);
        System.out.println("PAID!");
        System.out.println("-------------------------------------");
    }
}