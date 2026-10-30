
package com.feedback;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handles GET request
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        processFeedback(request, response, "GET");
    }

    // Handles POST request
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        processFeedback(request, response, "POST");
    }

    // Processes feedback
    private void processFeedback(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String method) throws IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String feedback = request.getParameter("feedback");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // Check whether data is submitted
        if (name == null || email == null || feedback == null) {

            out.println("<html><body>");
            out.println("<h3>No feedback submitted.</h3>");
            out.println("<a href='index.html'>Go to feedback form</a>");
            out.println("</body></html>");

            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Feedback Received</title>");

        out.println("<style>");

        out.println("body { font-family: Arial, sans-serif; background: #f2f4f8; }");

        out.println(".box { width: 450px; margin: 60px auto; background: white; "
                + "padding: 25px; border-radius: 8px; "
                + "box-shadow: 0 2px 8px rgba(0,0,0,0.15); }");

        out.println(".ok { color: #1b7f3b; font-weight: bold; "
                + "text-align: center; font-size: 18px; }");

        out.println("td { padding: 6px 10px; vertical-align: top; }");

        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='box'>");

        out.println("<p class='ok'>Thank you! Your feedback was submitted successfully.</p>");

        out.println("<table>");

        out.println("<tr><td><b>Method used:</b></td><td>"
                + method + "</td></tr>");

        out.println("<tr><td><b>Name:</b></td><td>"
                + escape(name) + "</td></tr>");

        out.println("<tr><td><b>Email:</b></td><td>"
                + escape(email) + "</td></tr>");

        out.println("<tr><td><b>Feedback:</b></td><td>"
                + escape(feedback) + "</td></tr>");

        out.println("</table>");

        out.println("<p><a href='index.html'>Submit another response</a></p>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    // Prevents HTML and script injection
    private String escape(String s) {

        if (s == null) {
            return "";
        }

        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
