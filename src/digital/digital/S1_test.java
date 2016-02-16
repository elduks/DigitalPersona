/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.digital;

import digital.util.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mijzcx.synapse.desk.utils.Lg;

/**
 *
 * @author Guinness
 */
public class S1_test {

    public static class to_test {

        public final int id;
        public final byte[] finger_print;

        public to_test(int id, byte[] finger_print) {
            this.id = id;
            this.finger_print = finger_print;
        }
    }

    public static void add_data(List<to_test> to_test1) {
        try {
            Connection conn = MyConnection.connect();

            for (to_test to_test : to_test1) {
                String query = "insert into test (finger_print) VALUES (?)";
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query);
                pstmt.setBytes(1, to_test.finger_print);
                pstmt.execute();
                Lg.s(S1_test.class, "Successfully Added");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

//    public static void update_data(to_test to_test) {
//        try {
//            Connection conn = MyConnection.connect();
//            String s0 = "update test set "
//                    + "finger_print= :finger_print "
//                    + " where id='" + to_test.id + "' "
//                    + " ";
//
//            s0 = SqlStringUtil.parse(s0)
//                    .setString("finger_print", to_test.finger_print)
//                    .ok();
//
//            PreparedStatement stmt = conn.prepareStatement(s0);
//            stmt.execute();
//            Lg.s(S1_test.class, "Successfully Updated");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            MyConnection.close();
//        }
//    }
//
//    public static void delete_data(to_test to_test) {
//        try {
//            Connection conn = MyConnection.connect();
//            String s0 = "delete from test  "
//                    + " where id='" + to_test.id + "' "
//                    + " ";
//
//            PreparedStatement stmt = conn.prepareStatement(s0);
//            stmt.execute();
//            Lg.s(S1_test.class, "Successfully Deleted");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            MyConnection.close();
//        }
//    }
    public static List<to_test> ret_data(String where) {
        List<to_test> datas = new ArrayList();

        try {
            Connection conn = MyConnection.connect();
            String s0 = "select "
                    + "id"
                    + ",finger_print"
                    + " from test"
                    + " " + where;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(s0);
            while (rs.next()) {
                int id = rs.getInt(1);
                byte[] finger_print = rs.getBytes(2);

                to_test to = new to_test(id, finger_print);
                datas.add(to);
            }
            return datas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyConnection.close();
        }
    }

}
