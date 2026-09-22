import java.util.Scanner;

public class AIChatbot {

    // Convert user message into a response
    public static String getResponse(String input) {

        input = input.toLowerCase().trim();

        // Greeting
        if (input.contains("hello") ||
            input.contains("hi") ||
            input.contains("hey")) {

            return "Hello! How can I help you with Java or OOP?";
        }

        // Name
        else if (input.contains("your name")) {

            return "I am CodeAlpha AI Chatbot.";
        }

        // How are you
        else if (input.contains("how are you")) {

            return "I am doing great! Thank you for asking.";
        }

        // Java
        else if (input.contains("java")) {

            return "Java is an object-oriented programming language " +
                   "used to build desktop, web and enterprise applications.";
        }

        // OOP
        else if (input.contains("oop") ||
                 input.contains("object oriented")) {

            return "The main OOP concepts are Encapsulation, " +
                   "Inheritance, Polymorphism and Abstraction.";
        }

        // Class
        else if (input.contains("class")) {

            return "A class is a blueprint or template used to create objects " +
                   "in Java.";
        }

        // Object
        else if (input.contains("object")) {

            return "An object is an instance of a class. It contains data " +
                   "and can perform actions through methods.";
        }

        // Encapsulation
        else if (input.contains("encapsulation")) {

            return "Encapsulation means wrapping data and methods together " +
                   "inside a class and protecting data using access modifiers " +
                   "such as private.";
        }

        // Inheritance
        else if (input.contains("inheritance")) {

            return "Inheritance allows one class to acquire the properties " +
                   "and methods of another class using the extends keyword.";
        }

        // Polymorphism
        else if (input.contains("polymorphism")) {

            return "Polymorphism means one interface or method can have " +
                   "different forms. In Java, it is commonly achieved through " +
                   "method overloading and method overriding.";
        }

        // Abstraction
        else if (input.contains("abstraction")) {

            return "Abstraction means hiding implementation details and " +
                   "showing only the essential features. Java uses abstract " +
                   "classes and interfaces for abstraction.";
        }

        // Constructor
        else if (input.contains("constructor")) {

            return "A constructor is a special method used to initialize an " +
                   "object. It has the same name as the class and has no return type.";
        }

        // Method Overloading
        else if (input.contains("method overloading") ||
                 input.contains("overloading")) {

            return "Method overloading means having multiple methods with the " +
                   "same name but different parameters in the same class.";
        }

        // Method Overriding
        else if (input.contains("method overriding") ||
                 input.contains("overriding")) {

            return "Method overriding occurs when a child class provides its " +
                   "own implementation of a method inherited from the parent class.";
        }

        // Interface
        else if (input.contains("interface")) {

            return "An interface in Java is used to achieve abstraction and " +
                   "allows a class to implement multiple interfaces.";
        }

        // Abstract Class
        else if (input.contains("abstract class") ||
                 input.contains("abstract")) {

            return "An abstract class cannot normally be instantiated directly. " +
                   "It can contain abstract as well as non-abstract methods.";
        }

        // Access Modifiers
        else if (input.contains("access modifier") ||
                 input.contains("access modifiers")) {

            return "Java has four main access levels: public, private, protected " +
                   "and default.";
        }

        // Inheritance Types
        else if (input.contains("types of inheritance") ||
                 input.contains("types inheritance")) {

            return "Java supports single, multilevel and hierarchical inheritance " +
                   "through classes. Multiple inheritance is achieved using interfaces.";
        }

        // Help
        else if (input.contains("help")) {

            return "You can ask me about Java, OOP, classes, objects, " +
                   "encapsulation, inheritance, polymorphism, abstraction, " +
                   "constructors, interfaces or method overriding.";
        }

        // Goodbye
        else if (input.contains("bye") ||
                 input.contains("goodbye") ||
                 input.contains("exit")) {

            return "Goodbye! Keep learning Java and OOP.";
        }

        // Unknown question
        else {

            return "Sorry, I don't understand that question. " +
                   "Please try asking about Java or an OOP concept.";
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("          CODEALPHA AI CHATBOT");
        System.out.println("======================================");

        System.out.println(
            "Bot: Hello! I am your Java and OOP AI chatbot."
        );

        System.out.println(
            "Bot: You can ask me about Java, OOP, classes, objects, " +
            "inheritance, polymorphism and more."
        );

        System.out.println(
            "Bot: Type 'bye' or 'exit' to end the conversation."
        );


        while (true) {

            System.out.print("\nYou: ");

            String userInput = scanner.nextLine();

            String response = getResponse(userInput);

            System.out.println("Bot: " + response);


            // End conversation
            if (userInput.equalsIgnoreCase("bye") ||
                userInput.equalsIgnoreCase("exit") ||
                userInput.equalsIgnoreCase("goodbye")) {

                break;
            }
        }


        scanner.close();

        System.out.println("\nChatbot session ended.");
    }
}