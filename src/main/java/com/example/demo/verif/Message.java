package com.example.demo.verif;

import org.springframework.stereotype.Component;

@Component
public  class Message {
    public static String PASSWORD;
    public static String HTML_MESSAGE(){
        String pass = PASSWORD;
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Verification Code</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            color: #333;\n" +
                "            line-height: 1.6;\n" +
                "            margin: 20px;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "            padding: 20px;\n" +
                "            border: 1px solid #ddd;\n" +
                "            border-radius: 8px;\n" +
                "            background-color: #f9f9f9;\n" +
                "        }\n" +
                "        .code {\n" +
                "            font-size: 18px;\n" +
                "            font-weight: bold;\n" +
                "            color: #007bff;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .note {\n" +
                "            font-size: 14px;\n" +
                "            color: #666;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p>Your Email Verification Code is:</p>\n" +
                "        <p class=\"code\">"+ pass +"</p>\n" +
                "        <p class=\"note\">Do not share this code with anyone.</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }


}
