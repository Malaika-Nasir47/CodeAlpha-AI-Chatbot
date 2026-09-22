import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class AIChatbotWeb {

    // =====================================================
    // CHATBOT RESPONSE LOGIC
    // =====================================================

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

            return "A class is a blueprint or template used to create " +
                   "objects in Java.";
        }

        // Object
        else if (input.contains("object")) {

            return "An object is an instance of a class. It contains " +
                   "data and can perform actions through methods.";
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

            return "A constructor is a special method used to initialize " +
                   "an object. It has the same name as the class and has no " +
                   "return type.";
        }

        // Method Overloading
        else if (input.contains("method overloading") ||
                 input.contains("overloading")) {

            return "Method overloading means having multiple methods with " +
                   "the same name but different parameters in the same class.";
        }

        // Method Overriding
        else if (input.contains("method overriding") ||
                 input.contains("overriding")) {

            return "Method overriding occurs when a child class provides " +
                   "its own implementation of a method inherited from the parent class.";
        }

        // Interface
        else if (input.contains("interface")) {

            return "An interface in Java is used to achieve abstraction " +
                   "and allows a class to implement multiple interfaces.";
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

            return "Java has four main access levels: public, private, " +
                   "protected and default.";
        }

        // Help
        else if (input.contains("help")) {

            return "You can ask me about Java, OOP, classes, objects, " +
                   "encapsulation, inheritance, polymorphism, abstraction, " +
                   "constructors, interfaces or method overriding.";
        }

        // Goodbye
        else if (input.equals("bye") ||
                 input.equals("exit") ||
                 input.equals("goodbye")) {

            return "Goodbye! Have a great day.";
        }

        // Unknown question
        else {

            return "Sorry, I don't understand that question. " +
                   "Please try asking about Java or an OOP concept.";
        }
    }


    // =====================================================
    // WEB PAGE
    // =====================================================

    private static final String HTML = """
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>CodeAlpha AI Chatbot</title>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #0f172a, #1e293b);
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .chat-container {
            width: 100%;
            max-width: 850px;
            height: 650px;
            background: #ffffff;
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.35);
            display: flex;
            flex-direction: column;
        }

        /* Header */

        .header {
            background: linear-gradient(135deg, #2563eb, #7c3aed);
            color: white;
            padding: 22px 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .bot-info {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .bot-icon {
            width: 52px;
            height: 52px;
            border-radius: 50%;
            background: white;
            color: #2563eb;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 25px;
        }

        .bot-name {
            font-size: 21px;
            font-weight: bold;
        }

        .bot-status {
            font-size: 13px;
            margin-top: 4px;
            opacity: 0.9;
        }

        .clear-btn {
            border: none;
            background: rgba(255,255,255,0.18);
            color: white;
            padding: 10px 15px;
            border-radius: 10px;
            cursor: pointer;
            font-size: 13px;
        }

        .clear-btn:hover {
            background: rgba(255,255,255,0.3);
        }

        /* Chat Area */

        .chat-box {
            flex: 1;
            padding: 25px;
            overflow-y: auto;
            background: #f8fafc;
        }

        .message {
            display: flex;
            margin-bottom: 18px;
        }

        .message.bot {
            justify-content: flex-start;
        }

        .message.user {
            justify-content: flex-end;
        }

        .bubble {
            max-width: 75%;
            padding: 13px 17px;
            border-radius: 15px;
            line-height: 1.5;
            font-size: 15px;
        }

        .bot .bubble {
            background: white;
            color: #1e293b;
            border: 1px solid #e2e8f0;
            border-bottom-left-radius: 4px;
        }

        .user .bubble {
            background: linear-gradient(135deg, #2563eb, #7c3aed);
            color: white;
            border-bottom-right-radius: 4px;
        }

        /* Suggestions */

        .suggestions {
            padding: 12px 20px;
            background: white;
            border-top: 1px solid #e2e8f0;
            display: flex;
            gap: 8px;
            flex-wrap: wrap;
        }

        .suggestion {
            border: 1px solid #cbd5e1;
            background: #f8fafc;
            color: #334155;
            padding: 8px 12px;
            border-radius: 20px;
            cursor: pointer;
            font-size: 12px;
        }

        .suggestion:hover {
            background: #e2e8f0;
        }

        /* Input */

        .input-area {
            padding: 18px;
            background: white;
            border-top: 1px solid #e2e8f0;
            display: flex;
            gap: 10px;
        }

        #messageInput {
            flex: 1;
            border: 1px solid #cbd5e1;
            border-radius: 12px;
            padding: 14px;
            outline: none;
            font-size: 15px;
        }

        #messageInput:focus {
            border-color: #2563eb;
        }

        #sendBtn {
            border: none;
            background: linear-gradient(135deg, #2563eb, #7c3aed);
            color: white;
            padding: 0 23px;
            border-radius: 12px;
            cursor: pointer;
            font-weight: bold;
        }

        #sendBtn:hover {
            opacity: 0.9;
        }

        /* Mobile */

        @media (max-width: 600px) {

            body {
                padding: 0;
            }

            .chat-container {
                height: 100vh;
                border-radius: 0;
            }

            .bubble {
                max-width: 85%;
            }

            .suggestions {
                overflow-x: auto;
                flex-wrap: nowrap;
            }

        }

    </style>

</head>


<body>

<div class="chat-container">

    <!-- HEADER -->

    <div class="header">

        <div class="bot-info">

            <div class="bot-icon">
                🤖
            </div>

            <div>

                <div class="bot-name">
                    CodeAlpha AI Chatbot
                </div>

                <div class="bot-status">
                    🟢 Online • Java & OOP Assistant
                </div>

            </div>

        </div>


        <button class="clear-btn"
                onclick="clearChat()">

            Clear Chat

        </button>

    </div>


    <!-- CHAT -->

    <div class="chat-box"
         id="chatBox">

        <div class="message bot">

            <div class="bubble">

                👋 Hello! I am your Java and OOP AI chatbot.
                <br><br>

                You can ask me about Java, classes, objects,
                inheritance, polymorphism, abstraction and
                other OOP concepts.

            </div>

        </div>

    </div>


    <!-- SUGGESTIONS -->

    <div class="suggestions">

        <button class="suggestion"
                onclick="askQuestion('What is Java?')">

            What is Java?

        </button>

        <button class="suggestion"
                onclick="askQuestion('What is OOP?')">

            What is OOP?

        </button>

        <button class="suggestion"
                onclick="askQuestion('What is inheritance?')">

            Inheritance

        </button>

        <button class="suggestion"
                onclick="askQuestion('What is polymorphism?')">

            Polymorphism

        </button>

        <button class="suggestion"
                onclick="askQuestion('What is encapsulation?')">

            Encapsulation

        </button>

    </div>


    <!-- INPUT -->

    <div class="input-area">

        <input
            type="text"
            id="messageInput"
            placeholder="Ask something about Java or OOP..."
            autocomplete="off"
        >

        <button id="sendBtn"
                onclick="sendMessage()">

            Send

        </button>

    </div>

</div>


<script>

    const input =
        document.getElementById("messageInput");

    const chatBox =
        document.getElementById("chatBox");


    // Send message

    function sendMessage() {

        const message =
            input.value.trim();

        if (message === "") {
            return;
        }

        addMessage(message, "user");

        input.value = "";

        fetch("/chat", {

            method: "POST",

            headers: {
                "Content-Type": "text/plain"
            },

            body: message

        })

        .then(response => response.text())

        .then(response => {

            addMessage(response, "bot");

        })

        .catch(error => {

            addMessage(
                "Sorry, there was a connection problem.",
                "bot"
            );

            console.error(error);

        });

    }


    // Add message to chat

    function addMessage(text, sender) {

        const message =
            document.createElement("div");

        message.className =
            "message " + sender;


        const bubble =
            document.createElement("div");

        bubble.className =
            "bubble";


        bubble.textContent = text;


        message.appendChild(bubble);

        chatBox.appendChild(message);


        chatBox.scrollTop =
            chatBox.scrollHeight;

    }


    // Suggestion buttons

    function askQuestion(question) {

        input.value = question;

        sendMessage();

    }


    // Enter key

    input.addEventListener(
        "keydown",
        function(event) {

            if (event.key === "Enter") {

                sendMessage();

            }

        }
    );


    // Clear chat

    function clearChat() {

        chatBox.innerHTML = "";

        addMessage(
            "Chat cleared. What would you like to learn about Java or OOP?",
            "bot"
        );

    }

</script>

</body>

</html>
""";


    // =====================================================
    // MAIN SERVER
    // =====================================================

    public static void main(String[] args) {

        try {

            HttpServer server =
                    HttpServer.create(
                            new InetSocketAddress(8080),
                            0
                    );


            // Homepage

            server.createContext(
                    "/",
                    AIChatbotWeb::handleHome
            );


            // Chat endpoint

            server.createContext(
                    "/chat",
                    AIChatbotWeb::handleChat
            );


            server.setExecutor(null);

            server.start();


            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "       CODEALPHA AI CHATBOT"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "Server started successfully!"
            );

            System.out.println();

            System.out.println(
                    "Open your browser and visit:"
            );

            System.out.println(
                    "http://localhost:8080"
            );

            System.out.println();

            System.out.println(
                    "Press Ctrl + C to stop the server."
            );


        } catch (IOException e) {

            System.out.println(
                    "Could not start server."
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // HOME PAGE
    // =====================================================

    private static void handleHome(
            HttpExchange exchange)
            throws IOException {

        byte[] response =
                HTML.getBytes(
                        StandardCharsets.UTF_8
                );


        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "text/html; charset=UTF-8"
                );


        exchange.sendResponseHeaders(
                200,
                response.length
        );


        OutputStream output =
                exchange.getResponseBody();


        output.write(response);

        output.close();
    }


    // =====================================================
    // CHAT REQUEST
    // =====================================================

    private static void handleChat(
            HttpExchange exchange)
            throws IOException {

        if (!exchange.getRequestMethod()
                .equalsIgnoreCase("POST")) {

            exchange.sendResponseHeaders(
                    405,
                    -1
            );

            return;
        }


        InputStream input =
                exchange.getRequestBody();


        String userMessage =
                new String(
                        input.readAllBytes(),
                        StandardCharsets.UTF_8
                );


        String response =
                getResponse(userMessage);


        byte[] responseBytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );


        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "text/plain; charset=UTF-8"
                );


        exchange.sendResponseHeaders(
                200,
                responseBytes.length
        );


        OutputStream output =
                exchange.getResponseBody();


        output.write(responseBytes);

        output.close();
    }
}