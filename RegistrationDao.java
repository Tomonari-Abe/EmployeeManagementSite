package work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao {
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
     *■doInsertメソッド
     *概要　：「survey」テーブルに対象のアンケートデータを挿入する
     *引数　：対象のアンケートデータ（RegistrationDto型）
     *戻り値：実行結果（真：成功、偽：例外発生）
     *----------------------------------------------------------------------**/
    public boolean doInsert(RegistrationDto dto) {

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
        //※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
        Connection        con = null ;   // Connection（DB接続情報）格納用変数
        PreparedStatement ps  = null ;   // PreparedStatement（SQL発行用オブジェクト）格納用変数

        //実行結果（真：成功、偽：例外発生）格納用変数
        //※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
        boolean isSuccess = true ;

        try {

            //-------------------------------------------
            //接続の確立（Connectionオブジェクトの取得）
            //-------------------------------------------
            con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

            //-------------------------------------------
            //トランザクションの開始
            //-------------------------------------------
            //オートコミットをオフにする（トランザクション開始）
            con.setAutoCommit(false);

            //-------------------------------------------
            //SQL文の送信 ＆ 結果の取得
            //-------------------------------------------

            //発行するSQL文の生成（INSERT）
            StringBuffer buf = new StringBuffer();
            buf.append("INSERT INTO 社員マスタ (   ");
            buf.append("  姓,                  ");
            buf.append("  名,                  ");
            buf.append("  生年月日,            ");
            buf.append("  入社日            ");
            buf.append(") VALUES (            ");
            buf.append("  ?,                  ");
            buf.append("  ?,                  ");
            buf.append("  ?,                  ");
            buf.append("  ?                  ");
            buf.append(");                     ");

            buf.append("INSERT INTO 部門マスタ (   ");
            buf.append("  ID            ");
            buf.append("  部門名            ");
            buf.append(") VALUES (            ");
            buf.append("  ? ,                 ");
            buf.append("  ?                  ");
            buf.append(");                     ");

            buf.append("INSERT INTO 所属部門トラン (   ");
            buf.append("  社員ID,            ");
            buf.append("  部門ID,            ");
            buf.append("  所属開始日            ");
            buf.append(") VALUES (            ");
            buf.append("  ?,                  ");
            buf.append("  ?,                  ");
            buf.append("  ?                  ");
            buf.append(");                     ");

            //PreparedStatementオブジェクトを生成＆発行するSQLをセット
            ps = con.prepareStatement(buf.toString());

            //パラメータをセット
            ps.setString(    1, dto.getLast_name()              ); //第1パラメータ
            ps.setString(       2, dto.getFirst_name()               ); //第2パラメータ
            ps.setString(       3, dto.getBirth_date()               ); //第3パラメータ
            ps.setString(       4, dto.getJoining_date() ); //第4パラメータ
            ps.setInt(    5, dto.getDepartmentID()           ); //第5パラメータ
            ps.setString(    6, dto.getDepartment()           ); //第6パラメータ
            ps.setInt(    7, dto.getEmployeeID()           ); //第7パラメータ
            ps.setInt(    8, dto.getDepartmentID()           ); //第8パラメータ
            ps.setString(       9, dto.getJoining_date() ); //第9パラメータ


            //SQL文の実行
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            //実行結果を例外発生として更新
            isSuccess = false ;

        } finally {
            //-------------------------------------------
            //トランザクションの終了
            //-------------------------------------------
            if(isSuccess){
                //明示的にコミットを実施
                try {
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }else{
                //明示的にロールバックを実施
                try {
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //-------------------------------------------
            //接続の解除
            //-------------------------------------------

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

        //実行結果を返す
        return isSuccess;
    }




    /**----------------------------------------------------------------------*
     *■doSelectメソッド
     *概要　：「survey」テーブルのデータを全件抽出する
     *引数　：なし
     *戻り値：抽出結果（DTOリスト）
     *----------------------------------------------------------------------**/
    public List<RegistrationDto> doSelect() {

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
        List<RegistrationDto> dtoList = new ArrayList<RegistrationDto>();

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

            buf.append("    SELECT                   ");
            buf.append("        a.姓,                ");
            buf.append("        a.名,                ");
            buf.append("        a.生年月日,          ");
            buf.append("        a.入社日,            ");
            buf.append("        b.部門名 AS 所属部門 ");
            buf.append("    FROM                     ");
            buf.append("        社員マスタ a          ");
            buf.append("    INNER JOIN                 ");
            buf.append("        所属部門トラン c        ");
            buf.append("    ON                         ");
            buf.append("        a.ID = c.社員ID         ");
            buf.append("    INNER JOIN                 ");
            buf.append("        部門マスタ b            ");
            buf.append("    ON                         ");
            buf.append("        c.部門ID = b.ID         ");
//            buf.append("    WHERE                      ");
//            buf.append("        a.ID = 1;               ");

            ps = con.prepareStatement(buf.toString());

            //パラメータをセット
            //rs.setInt(1, dto.getID());

            //SQL文の送信＆戻り値としてResultSet（SQL抽出結果）を取得
            rs = ps.executeQuery();


            //ResultSetオブジェクトからDTOリストに格納
            while (rs.next()) {
                RegistrationDto dto = new RegistrationDto();
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


