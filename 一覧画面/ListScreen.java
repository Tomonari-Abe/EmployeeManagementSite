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
 * Servlet implementation class List_screen
 */
// @WebServlet("/List_screen")
public class ListScreen extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListScreen () {
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
        response.setContentType ("text/html;charset=UTF-8");

        // セッションからユーザーデータを取得
        HttpSession session = request.getSession ();
        // RegistrationDto userInfoOnSession =
        // (RegistrationDto)session.getAttribute("LOGIN_INFO");

        // ログイン状態によって表示画面を振り分ける
        // if (userInfoOnSession != null) {
        // ログイン済：回答一覧画面を出力

        // 出力用のストリームの取得
        PrintWriter out = response.getWriter ();

        // 「survey」テーブルのデータを全件抽出
        List<RegistrationDto> list = new ArrayList<RegistrationDto> ();
        RegistrationScreenBL logic = new RegistrationScreenBL ();
        list = logic.executeSelectSurvey ();

        out.println (
                "<!DOCTYPE html>                                                                                                                                ");
        out.println (
                "<html>                                                                                                                                         ");
        out.println (
                "  <head>                                                                                                                                       ");
        out.println (
                "        <meta charset=\"UTF-8\">                                                                                                               ");
        out.println (
                "        <link rel=\"stylesheet\" href=\"style.css\">                                                                                           ");
        out.println (
                "        <title>社員情報一覧</title>                                                                                                            ");
        out.println (
                "        <style>                                                                                                                                ");
        out.println (
                "            body {                                                                                                                             ");
        out.println (
                "                font-family: Arial, sans-serif;                                                                                                ");
        out.println (
                "                margin: 20px;                                                                                                                  ");
        out.println (
                "          }                                                                                                                                    ");
        out.println (
                "        h1 {                                                                                                                                   ");
        out.println (
                "          text-align: center;                                                                                                                  ");
        out.println (
                "          margin-bottom: 20px;                                                                                                                 ");
        out.println (
                "   }                                                                                                                                           ");
        out.println (
                "    .register-link {                                                                                                                           ");
        out.println (
                "         text-align: right;                                                                                                                    ");
        out.println (
                "          margin-bottom: 20px;                                                                                                                 ");
        out.println (
                "      }                                                                                                                                        ");
        out.println (
                "        .register-link button {                                                                                                                ");
        out.println (
                "          background-color: orange;                                                                                                            ");
        out.println (
                "          border: none;                                                                                                                        ");
        out.println (
                "          padding: 10px 20px;                                                                                                                  ");
        out.println (
                "           color: white;                                                                                                                       ");
        out.println (
                "         cursor: pointer;                                                                                                                      ");
        out.println (
                "     }                                                                                                                                         ");
        out.println (
                "      .register-link button a {                                                                                                                ");
        out.println (
                "          text-decoration: none;                                                                                                               ");
        out.println (
                "          color: white;                                                                                                                        ");
        out.println (
                "  }                                                                                                                                            ");
        out.println (
                "     table.input-items {                                                                                                                       ");
        out.println (
                "      /* display: block; */                                                                                                                    ");
        out.println (
                "       flex-direction: column;                                                                                                                 ");
        out.println (
                "          margin-left: 20px;                                                                                                                   ");
        out.println (
                "          margin-right: 20px;                                                                                                                  ");
        out.println (
                "        margin-top: 20px;                                                                                                                      ");
        out.println (
                "          margin-bottom: 20px;                                                                                                                 ");
        out.println (
                "           border: 1px solid #333;                                                                                                             ");
        out.println (
                "     }                                                                                                                                         ");
        out.println (
                "            table{                                                                                                                             ");
        out.println (
                "               /* display: flex-box; */                                                                                                        ");
        out.println (
                "            width: 100%;                                                                                                                       ");
        out.println (
                "            border-collapse: collapse;                                                                                                         ");
        out.println (
                "            margin-bottom: 20px;                                                                                                               ");
        out.println (
                "       }                                                                                                                                       ");
        out.println (
                "       th, td {                                                                                                                                ");
        out.println (
                "               border: 1px solid black;                                                                                                        ");
        out.println (
                "               padding: 10px;                                                                                                                  ");
        out.println (
                "               text-align: left;                                                                                                               ");
        out.println (
                "               white-space: nowrap; /* テーブルセル内の余分な空白を削除 */                                                                     ");
        out.println (
                "                   }                                                                                                                           ");
        out.println (
                "       th {                                                                                                                                    ");
        out.println (
                "               background-color: #f2f2f2;                                                                                                      ");
        out.println (
                "      }                                                                                                                                        ");
        out.println (
                "      .edit-button {                                                                                                                           ");
        out.println (
                "               background-color: orange;                                                                                                       ");
        out.println (
                "               color: white;                                                                                                                   ");
        out.println (
                "               cursor: pointer;                                                                                                                ");
        out.println (
                "            }                                                                                                                                  ");
        out.println (
                "            .transportation {                                                                                                                  ");
        out.println (
                "               text-align: center;                                                                                                             ");
        out.println (
                "            }                                                                                                                                  ");
        out.println (
                "            .transportation button {                                                                                                           ");
        out.println (
                "                background-color: orange;                                                                                                      ");
        out.println (
                "               border: none;                                                                                                                   ");
        out.println (
                "                padding: 10px 20px;                                                                                                            ");
        out.println (
                "                color: white;                                                                                                                  ");
        out.println (
                "               cursor: pointer;                                                                                                                ");
        out.println (
                "            }                                                                                                                                  ");
        out.println (
                "            .transportation button a {                                                                                                         ");
        out.println (
                "                text-decoration: none;                                                                                                         ");
        out.println (
                "               color: white;                                                                                                                   ");
        out.println (
                "            }                                                                                                                                  ");
        out.println (
                "        </style>                                                                                                                               ");
        out.println (
                "   </head>                                                                                                                                     ");
        out.println (
                "    <body>                                                                                                                                     ");
        out.println (
                "        <h1>社員情報一覧</h1>                                                                                                                  ");
        out.println (
                "        <div class=\"register-link\">                                                                                                          ");
        out.println (
                "            <button type=\"button\"><a href=\"../登録画面/登録画面.html\">登録＞ </a></button>                                                 ");
        out.println (
                "        </div>                                                                                                                                 ");
        out.println (
                "        <table >                                                                                                                               ");
        out.println (
                "            <thead>                                                                                                                            ");
        out.println (
                "                <tr>                                                                                                                           ");
        out.println (
                "                    <th>姓名</th>                                                                                                              ");
        out.println (
                "                    <th>生年月日</th>                                                                                                          ");
        out.println (
                "                    <th>入社日</th>                                                                                                            ");
        out.println (
                "                    <th>所属部門</th>                                                                                                          ");
        out.println (
                "                   <th>退社日</th>                                                                                                             ");
        out.println (
                "                    <th>編集</th>                                                                                                              ");
        out.println (
                "                </tr>                                                                                                                          ");
        out.println (
                "            </thead>                                                                                                                           ");

        for (int i = 0; i < list.size (); i++) {
            RegistrationDto dto = list.get (i);

            out.println (
                    "            <tbody>                                                                                                                            ");
            out.println (
                    "                <tr>                                                                                                                           ");
            out.println (" <td>" + replaceEscapeChar (dto.getLast_name ()) + "</td>");
            out.println (" <td>" + replaceEscapeChar (dto.getFirst_name ()) + "</td>");
            out.println (" <td>" + replaceEscapeChar (dto.getBirth_date ()) + "</td>");
            out.println (" <td>" + replaceEscapeChar (dto.getJoining_date ()) + "</td>");
            out.println (" <td>" + replaceEscapeChar (dto.getDepartment ()) + "</td>");
            out.println (
                    "                    <td><button type=\"button\" class=\"edit-button\"><a href=\"../更新画面/更新画面.html\">編集</a></button></td>             ");
            out.println (
                    "               </tr>                                                                                                                           ");
        }

        out.println (
                "            </tbody>                                                                                                                           ");
        out.println (
                "        </table>                                                                                                                               ");
        out.println (
                "        <div class=\"transportation\">                                                                                                         ");
        out.println (
                "            <button type=\"button\"><a href=\"../一覧画面/一覧画面.html\">前へ</a></button>                                                    ");
        out.println (
                "            <button type=\"button\"><a href=\"../一覧画面/一覧画面.html\">次へ</a></button>                                                    ");
        out.println (
                "        </div>                                                                                                                                 ");
        out.println (
                "    </body>                                                                                                                                    ");
        out.println (
                "</html>                                                                                                                                        ");
        // }else {
        // 未ログイン：ログイン画面へ転送
        // response.sendRedirect("Login");
        // }
        // TODO Auto-generated method stub
        doGet (request, response);
    }

    // TODO Auto-generated method stub
    // response.getWriter().append("Served at: ").append(request.getContextPath());


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
