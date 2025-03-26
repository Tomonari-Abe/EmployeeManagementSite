package work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Update_screen
 */
//@WebServlet("/Update_screen")
public class UpdateScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateScreen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    //レスポンス（出力データ）の文字コードを設定
        response.setContentType("text/html;charset=UTF-8");  //文字コードをUTF-8で設定
        //リクエスト（受信データ）の文字コードを設定
        request.setCharacterEncoding("UTF-8");                  //文字コードをUTF-8で設定

        //セッションからユーザーデータを取得
        HttpSession session           = request.getSession();
        UpdateDto userInfoOnSession = (UpdateDto)session.getAttribute("LOGIN_INFO");

	    PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>                                                                                                                   ");
        out.println("<html>                                                                                                                            ");
        out.println("    <head>                                                                                                                        ");
        out.println("        <meta charset=\"UTF-8\">                                                                                                  ");
        out.println("        <title>社員情報更新</title>                                                                                               ");
        out.println("    </head>                                                                                                                       ");
        out.println("    <body>                                                                                                                        ");
        out.println("        <h1>社員情報更新</h1>                                                                                                     ");
        out.println("        <form action=\"\" method=\"post\">                                                                                        ");
        out.println("            <div class=\"form-group\">                                                                                            ");
        out.println("                <label for=\"family-name\">姓</label>                                                                             ");
        out.println("                <input type=\"text\" id=\"family-name\" name=\"family-name\" placeholder=\"例：山田\">                            ");
        out.println("            </div>                                                                                                                ");
        out.println("            <div class=\"form-group\">                                                                                            ");
        out.println("                <label for=\"first-name\">名</label>                                                                              ");
        out.println("                <input type=\"text\" id=\"first-name\" name=\"first-name\" placeholder=\"例：太郎\">                              ");
        out.println("            </div>                                                                                                                ");
        out.println("            <div class=\"form-group\">                                                                                            ");
        out.println("                <label for=\"birthday\">生年月日</label>                                                                          ");
        out.println("                <input type=\"date\" id=\"birthday\" name=\"birthday\">                                                           ");
        out.println("            </div>                                                                                                                ");
        out.println("            <div class=\"form-group\">                                                                                            ");
        out.println("                <label for=\"entering-date\">入社日</label>                                                                       ");
        out.println("                <input type=\"date\" id=\"entering-date\" name=\"entering-date\">                                                 ");
        out.println("            </div>                                                                                                                ");
        out.println("            <div class=\"form-group\">                                                                                            ");
        out.println("                <label for=\"department\">所属部門</label>                                                                        ");
        out.println("                <input type=\"text\" id=\"department\" name=\"department\">                                                       ");
        out.println("            </div>                                                                                                                ");
        out.println("                                                                                                                                  ");
        out.println("        </form>                                                                                                                   ");
        out.println("    </body>                                                                                                                       ");
        out.println("    <footer>                                                                                                                      ");
        out.println("        <button type=\"button\" class=\"update-button\"><a href=\"./CheckUpdateScreen\">確認</a></button>           ");
        out.println("        </footer>                                                                                                                 ");
        out.println("</html>                                                                                                                 ");

	       // TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	    // TODO Auto-generated method stub
		doGet(request, response);
	}
	private String replaceEscapeChar(String inputText) {

        String charAfterEscape = inputText ; //エスケープ後の文字列データ

        // 「&」を変換
        charAfterEscape = inputText.replace("&", "&amp;");
        // 「<」を変換
        charAfterEscape = inputText.replace("<", "&lt;");
        // 「>」を変換
        charAfterEscape = inputText.replace(">", "&gt;");
        // 「"」を変換
        charAfterEscape = inputText.replace("\"", "&quot;");
        // 「'」を変換
        charAfterEscape = inputText.replace("'", "&#039;");

        return charAfterEscape;
    }




}









                                                                                                                                                         ;;



