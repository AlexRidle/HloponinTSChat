/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-06-16 23:10:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class chat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Title</title>\n");
      out.write("    <style>\n");
      out.write("        .chatbox {\n");
      out.write("            /*display: none;*/\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .messages {\n");
      out.write("            background-color: cadetblue;\n");
      out.write("            width: 500px;\n");
      out.write("            padding: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .messages .msg {\n");
      out.write("            background-color: aliceblue;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("            overflow: hidden;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .messages .msg .from {\n");
      out.write("            background-color: antiquewhite;\n");
      out.write("            line-height: 30px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .messages .msg .text {\n");
      out.write("            padding: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        textarea.msg {\n");
      out.write("            width: 530px;\n");
      out.write("            padding: 15px;\n");
      out.write("            resize: none;\n");
      out.write("            border: none;\n");
      out.write("            box-shadow: 2px 2px 5px 0 inset;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>ChatBox</h1>\n");
      out.write("\n");
      out.write("<div class=\"startbox\">\n");
      out.write("\n");
      out.write("    <button id=\"start\"> start</button>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"chatbox\">\n");
      out.write("    <textarea class=\"msg\"></textarea>\n");
      out.write("    <div class=\"messages\"></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    let chatUnit = {\n");
      out.write("        init() {\n");
      out.write("            this.startBox = document.querySelector(\".startbox\"); // Инициализация класса start\n");
      out.write("            this.chatBox = document.querySelector(\".chatbox\"); // Инициализация класса chatbox\n");
      out.write("\n");
      out.write("            this.nameInput = this.startBox.querySelector(\"input\");\n");
      out.write("            this.statrButton = this.startBox.querySelector(\"button\"); // Инициализация кнопки\n");
      out.write("\n");
      out.write("            this.msgTextArea = this.chatBox.querySelector(\"textarea\"); // Инициализация textarea\n");
      out.write("            this.chatBoxContainer = this.chatBox.querySelector(\".messages\");\n");
      out.write("\n");
      out.write("            this.bindEvents();  // Обработчик событий\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        bindEvents() {\n");
      out.write("            this.statrButton.addEventListener(\"click\", ev => this.openSocket()); // При нажатии на кнопку происходит открытие сокета\n");
      out.write("            this.msgTextArea.addEventListener(\"keyup\", ev => {\n");
      out.write("                if (ev.ctrlKey && ev.keyCode === 13) {\n");
      out.write("                    ev.preventDefault();\n");
      out.write("                    this.send(this.msgTextArea.value);\n");
      out.write("                }\n");
      out.write("            }); // При нажатии на кнопку ctrl передаем в msgTextArea value\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        send(){ // Вызывает метод sendMessage и передает в него параметры\n");
      out.write("            this.sendMessage({\n");
      out.write("                // name: this.name,\n");
      out.write("                text: this.msgTextArea.value\n");
      out.write("            });\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        onOpenSocket() {\n");
      out.write("\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        onMessage(msg) { // Получает сообщение и выводит его на веб\n");
      out.write("            let msgBlock = document.createElement(\"div\");\n");
      out.write("            msgBlock.className = \"msg\";\n");
      out.write("\n");
      out.write("            let fromBlock = document.createElement(\"div\");\n");
      out.write("            fromBlock.className = \"from\";\n");
      out.write("            fromBlock.innerText = msg.name;\n");
      out.write("\n");
      out.write("            let textBlock = document.createElement(\"div\");\n");
      out.write("            textBlock.className = \"text\";\n");
      out.write("            textBlock.innerText = msg.text;\n");
      out.write("\n");
      out.write("            msgBlock.appendChild(fromBlock);\n");
      out.write("            msgBlock.appendChild(textBlock);\n");
      out.write("\n");
      out.write("            this.chatBoxContainer.appendChild(msgBlock);\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        onClose() {\n");
      out.write("\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        sendMessage() { // Отправляет сообщение\n");
      out.write("            // this.onMessage({name: \"I'm\", text: msg.text });\n");
      out.write("            this.msgTextArea.value = \"\";\n");
      out.write("            this.ws.send(JSON.stringify(msg));\n");
      out.write("        },\n");
      out.write("\n");
      out.write("        openSocket() {\n");
      out.write("            this.ws = new WebSocket(\"ws://localhost:8080/web/chat\"); // По нажатию открыли сокет\n");
      out.write("            this.ws.onopen = () => this.onOpenSocket();\n");
      out.write("            this.ws.onmessage = (ev) => this.onMessage(JSON.parse(ev.data));\n");
      out.write("            this.ws.onclose = () => this.onClose();\n");
      out.write("\n");
      out.write("            // this.name = this.nameInput.value;\n");
      out.write("            this.startBox.style.display = \"none\"; // После соединения прячем кнопку\n");
      out.write("            this.chatBox.style.display = \"block\"; // После соединения непрячем chatbox\n");
      out.write("        },\n");
      out.write("\n");
      out.write("    };\n");
      out.write("\n");
      out.write("    window.addEventListener(\"load\", ev => chatUnit.init()); // ? При любом событии, при загрузке запускать метод init\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
