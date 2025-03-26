package work;

import java.util.List;

public class UpdateScrrenBL {
    public List<UpdateDto> executeSelectUserInfo() {

        // データベースにアクセスしてユーザー情報を取得
        UpdateDao dao = new UpdateDao();
        List<UpdateDto> dto = dao.doSelect();

        return dto;
    }
}
