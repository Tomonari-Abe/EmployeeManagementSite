package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateDao {
  //-------------------------------------------
    //データベースへの接続情報
    //-------------------------------------------

    //JDBCドライバの相対パス
    //※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    //接続先のデータベース
    //※データベース名が「test_db」でない場合は該当の箇所を変更してください
    String JDBC_URL    = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";

    //接続するユーザー名
    //※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
    String USER_ID     = "test_user";

    //接続するユーザーのパスワード
    //※パスワードが「test_pass」でない場合は該当の箇所を変更してください
    String USER_PASS   = "test_pass";


    //----------------------------------------------------------------
    //メソッド
    //----------------------------------------------------------------


    /**----------------------------------------------------------------------*
     *■doSelectメソッド
     *概要　：「survey」テーブルのデータを全件抽出する
     *引数　：なし
     *戻り値：抽出結果（DTOリスト）
     *----------------------------------------------------------------------**/
    public List<UpdateDto> doSelect() {

        //-------------------------------------------
        //JDBCドライバのロード
        //-------------------------------------------
        try {
            Class.forName(DRIVER_NAME);       //JDBCドライバをロード＆接続先として指定
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //-------------------------------------------
        //SQL発行
        //-------------------------------------------

        //JDBCの接続に使用するオブジェクトを宣言
        Connection        con = null ;   // Connection（DB接続情報）格納用変数
        PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数
        ResultSet         rs  = null ;   // ResultSet（SQL抽出結果）格納用変数

        //抽出結果格納用DTOリスト
        List<UpdateDto> dtoList = new ArrayList<UpdateDto>();

        try {

            //-------------------------------------------
            //接続の確立（Connectionオブジェクトの取得）
            //-------------------------------------------
            con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

            //-------------------------------------------
            //SQL文の送信 ＆ 結果の取得
            //-------------------------------------------

            //発行するSQL文の生成（SELECT）
            StringBuffer buf = new StringBuffer();
            buf.append("SELECT a.姓,                       ");
            buf.append("       a.名,                       ");
            buf.append("       a.生年月日,                 ");
            buf.append("        a.入社日,                  ");
            buf.append("       b.部門名 as 所属部門        ");
            buf.append("FROM 社員マスタ a,                 ");
            buf.append("    部門マスタ b,                  ");
            buf.append("    所属部門トラン c.              ");
//            buf.append("WHERE a.ID= b.ID=c.ID              ");











            ps = con.prepareStatement(buf.toString());
            rs = ps.executeQuery();

            //ResultSetオブジェクトからDTOリストに格納
            while (rs.next()) {
                UpdateDto dto = new UpdateDto();
                dto.setLast_name(          rs.getString(    "姓"               ) );
                dto.setFirst_name(       rs.getString(       "名"                ) );
                dto.setBirth_date(  rs.getString(       "生年月日"                ) );
                dto.setJoining_date( rs.getString(       "入社日" ) );
                dto.setDepartment(        rs.getString(    "所属部門"            ) );

                dtoList.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //-------------------------------------------
            //接続の解除
            //-------------------------------------------

            //ResultSetオブジェクトの接続解除
            if (rs != null) {    //接続が確認できている場合のみ実施
                try {
                    rs.close();  //接続の解除
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //PreparedStatementオブジェクトの接続解除
            if (ps != null) {    //接続が確認できている場合のみ実施
                try {
                    ps.close();  //接続の解除
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //Connectionオブジェクトの接続解除
            if (con != null) {    //接続が確認できている場合のみ実施
                try {
                    con.close();  //接続の解除
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //抽出結果を返す
        return dtoList;
    }



}
