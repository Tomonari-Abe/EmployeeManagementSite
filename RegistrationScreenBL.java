package work;

import java.util.List;

public class RegistrationScreenBL {
    public List<RegistrationDto> executeSelectSurvey() {

        //-------------------------------------------
        //データベースへの接続を実施
        //-------------------------------------------

        //DAOクラスをインスタンス化＆対象のユーザーデータを登録するよう依頼
        RegistrationDao dao = new RegistrationDao();
        List<RegistrationDto> dtoList= dao.doSelect();

        return dtoList;
    }
}

