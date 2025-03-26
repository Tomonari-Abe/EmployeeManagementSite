package work;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check_registration_screen
 */
// @WebServlet("/Check_registration_screen")
public class CheckRegistrationScreen extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRegistrationScreen () {
        super ();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // レスポンス（出力データ）の文字コードを設定
        response.setContentType ("text/html;charset=UTF-8"); // 文字コードをUTF-8で設定
        // リクエスト（受信データ）の文字コードを設定
        request.setCharacterEncoding ("UTF-8"); // 文字コードをUTF-8で設定

        // セッションからユーザーデータを取得
        HttpSession session = request.getSession ();
        RegistrationDto userInfoOnSession = (RegistrationDto) session.getAttribute ("LOGIN_INFO");

        PrintWriter out = response.getWriter ();

        List<RegistrationDto> list = new ArrayList<RegistrationDto> ();
        RegistrationScreenBL logic = new RegistrationScreenBL ();
        list = logic.executeSelectSurvey ();

        for (int i = 0; i < list.size (); i++) {
            RegistrationDto dto = list.get (i);
            // RegistrationDto dto = new RegistrationDto();
            out.println (
                    "<!DOCTYPE html>                                                                                                            ");
            out.println (
                    "<html>                                                                                                                     ");
            out.println (
                    "    <head>                                                                                                                 ");
            out.println (
                    "       <meta charset=\"UTF-8\">                                                                                            ");
            out.println (
                    "        <title>社員情報登録　確認</title>                                                                                  ");
            out.println (
                    "    </head>                                                                                                                ");
            out.println (
                    "    <body>                                                                                                                 ");
            out.println (
                    "        <h1>社員情報登録　確認</h1>                                                                                        ");
            out.println (
                    "        <form action=\"\" method=\"post\">                                                                                 ");
            out.println (
                    "            <table class=\"table\">                                                                                        ");
            out.println (
                    "                <tr>                                                                                                       ");
            out.println (
                    "                    <td>姓</td>                                                                                            ");
            out.println ("                   <td> <label>" + replaceEscapeChar (dto.getLast_name ()) + "</label></td>");
            out.println (
                    "                </tr>                                                                                                      ");
            out.println (
                    "                <tr>                                                                                                       ");
            out.println (
                    "                    <td>名</td>                                                                                            ");
            out.println ("                   <td> <label>" + replaceEscapeChar (dto.getFirst_name ()) + "</label></td>");
            out.println (
                    "                </tr>                                                                                                      ");
            out.println (
                    "                <tr>                                                                                                       ");
            out.println (
                    "                    <td>生年月日</td>                                                                                      ");
            out.println ("                   <td> <label>" + replaceEscapeChar (dto.getBirth_date ())
                    + "</label><td>                                               ");
            out.println (
                    "                </tr>                                                                                                      ");
            out.println (
                    "                <tr>                                                                                                       ");
            out.println (
                    "                    <td>入社日</td>                                                                                        ");
            out.println ("               <td>     <label>" + replaceEscapeChar (dto.getJoining_date ())
                    + "</label> </td>                                               ");
            out.println (
                    "                </tr>                                                                                                      ");
            out.println (
                    "                <tr>                                                                                                       ");
            out.println (
                    "                    <td>所属部門</td>                                                                                      ");
            out.println ("              <td>      <label>" + replaceEscapeChar (dto.getDepartment ())
                    + "</label> <td>                                                 ");
            out.println (
                    "                </tr>                                                                                                      ");
            out.println (
                    "            </table>                                                                                                       ");
            // out.println(" <tbody> ");
            // out.println(" <td>")
            out.println (
                    "        </form>                                                                                                            ");
            out.println (
                    "    </body>                                                                                                                ");
            out.println (
                    "    <footer>                                                                                                               ");
            out.println (
                    "        <button type=\"button\" class=\"update-button\"><a href=\"./ListScreen\">登録</a></button>            ");
            out.println (
                    "    </footer>                                                                                                              ");
            out.println (
                    "</html>                                                                                                                    ");
            response.getWriter ().append ("Served at: ").append (request.getContextPath ());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet (request, response);
    }

    private String replaceEscapeChar (String inputText) {

        String charAfterEscape = inputText; // エスケープ後の文字列データ

        // 「&」を変換
        charAfterEscape = inputText.replace ("&", "&amp;");
        // 「<」を変換
        charAfterEscape = inputText.replace ("<", "&lt;");
        // 「>」を変換
        charAfterEscape = inputText.replace (">", "&gt;");
        // 「"」を変換
        charAfterEscape = inputText.replace ("\"", "&quot;");
        // 「'」を変換
        charAfterEscape = inputText.replace ("'", "&#039;");

        return charAfterEscape;
    }
}
