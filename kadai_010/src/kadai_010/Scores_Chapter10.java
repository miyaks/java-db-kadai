package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_java",
                    "root",
                    "Kosei1120@"
            );

            System.out.println("データベース接続成功");

            statement = con.createStatement();
            String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";

            int rowCnt = statement.executeUpdate(updateSql);
            System.out.println(rowCnt + "件のレコードが更新されました");

            String selectSql = "SELECT id, name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC;";
            System.out.println("データ取得を実行：" + selectSql);
            ResultSet result = statement.executeQuery(selectSql);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");
                System.out.println(result.getRow() + "件目：生徒ID=" + id
                        + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english);
            }

        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
           
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}