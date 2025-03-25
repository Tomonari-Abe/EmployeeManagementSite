package work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check_registration_screen
 */
//@WebServlet("/Check_registration_screen")
public class CheckRegistrationScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRegistrationScreen() {
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
        RegistrationDto userInfoOnSession = (RegistrationDto)session.getAttribute("LOGIN_INFO");

	    PrintWriter out = response.getWriter();

        out.println(" <!DOCTYPE html>                                                         ");
        out.println("<html>                                                                   ");
        out.println("     <head>                                                              ");
        out.println("         <meta charset=\"UTF-8\">                                        ");
        out.println("         <title>社員情報登録　確認</title>                               ");
        out.println("         <!-- <link rel=\"stylesheet\" href=\"style.css\"> -->           ");
        out.println("         <style>                                                         ");
        out.println("             .table {                                                    ");
        out.println("               width: 100%;                                              ");
        out.println("                 border-collapse: collapse;                              ");
        out.println("                 margin-bottom: 20px;                                    ");
        out.println("             }                                                           ");
        out.println("            .table th, .table td {                                       ");
        out.println("                 border: 1px solid #ddd;                                 ");
        out.println("                 padding: 8px;                                           ");
        out.println("             }                                                           ");
        out.println("         </style>                                                        ");
        out.println("     <body>                                                              ");
        out.println("         <h1>社員情報登録　確認</h1>                                     ");
        out.println("         <form action=\"\" method=\"post\">                              ");
        out.println("             <table class=\"table\">                                     ");
        out.println("                 <tr>                                                    ");
        out.println("                     <td>姓</td>                                         ");
        out.println("                     <td><input type=\"text\" name=\"last_name\"></td>   ");
        out.println("                 </tr>                                                   ");
        out.println("                 <tr>                                                    ");
        out.println("                     <td>名</td>                                         ");
        out.println("                     <td><input type=\"text\" name=\"first_name\"></td>  ");
        out.println("                 </tr>                                                   ");
        out.println("                 <tr>                                                    ");
        out.println("                     <td>生年月日</td>                                   ");
        out.println("                     <td><input type=\"date\" name=\"birth_date\"></td>  ");
        out.println("                 </tr>                                                   ");
        out.println("                 <tr>                                                    ");
        out.println("                     <td>入社日</td>                                     ");
        out.println("                     <td><input type=\"date\" name=\"joining_date\"></td>");
        out.println("                 </tr>                                                   ");
        out.println("                 <tr>                                                    ");
        out.println("                     <td>所属部門</td>                                   ");
        out.println("                     <td><input type=\"text\" name=\"department\"></td>  ");
        out.println("                 </tr>                                                   ");
        out.println("             </table>                                                    ");
        out.println("             <input type=\"submit\" value=\"確認\">                      ");
        out.println("         </form>                                                         ");
        out.println("     </body>                                                             ");
        out.println(" </html>                                                                 ");// TODO Auto-generated method stub

        response.getWriter().append("Served at: ").append(request.getContextPath());
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


