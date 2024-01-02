package client;
import course.Question;
import course.Quiz;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/pbo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static boolean validateLogin(String username, String password){
        String sql = "select * from user where username = ? and password = md5(?)";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<Mahasiswa> getMhs(){
        ArrayList<Mahasiswa> mhs = new ArrayList();
        String sql = "select * from user where role = 'Mahasiswa'";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String nim = rs.getString("identnum");
                String dosen = rs.getString("nip");
                String nama = rs.getString("fullname");
                String username = rs.getString("username");
                mhs.add(new Mahasiswa(nim, dosen, nama, username));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mhs;
    }
    
    public static ArrayList<Dosen> getDosen(){
        ArrayList<Dosen> dosen = new ArrayList();
        String sql = "select * from user where role = 'Dosen'";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String nip = rs.getString("identnum");
                String nama = rs.getString("fullname");
                String username = rs.getString("username");
                dosen.add(new Dosen(nip, nama, username));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dosen;
    }
    
    public static ArrayList<Admin> getAdmin(){
        ArrayList<Admin> admin = new ArrayList();
        String sql = "select * from user where role = 'Admin'";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String id = rs.getString("identnum");
                String nama = rs.getString("fullname");
                String username = rs.getString("username");
                admin.add(new Admin(id, nama, username));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return admin;
    }
    
    public static String findRole(User u){
        String sql = "select * from user";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                if(rs.getString("username").equals(u.getUsername())){
                    System.out.println("awe");
                    return rs.getString("role");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void addMahasiswa(Mahasiswa m, String pass){
        String sql = "INSERT INTO user (identnum, nip, fullname, username, password, role) VALUES (?, ?, ?, ?, MD5(?), ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                if(findDosen(m.getDosenSiapa())){
                    stmt.setString(1, m.getNim());
                    stmt.setString(2, m.getDosenSiapa());
                    stmt.setString(3, m.getFullname());
                    stmt.setString(4, m.getUsername());
                    stmt.setString(5, pass);
                    stmt.setString(6, "Mahasiswa");
                    stmt.executeUpdate();
                    System.out.println("Mahasiswa berhasil ditambahkan");
                }else{
                    System.out.println("Dosen tidak ditemukan");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean findMahasiswa(String nim){
        String sql = "select * from user";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                if(rs.getString("identnum")==nim&&rs.getString("role")=="Mahasiswa"){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean findDosen(String nip){
        String sql = "select * from user";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                if(rs.getString("identnum").equals(nip)&&rs.getString("role").equals("Dosen")){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean findQuiz(int id){
        String sql = "select * from quiz";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                if(rs.getInt("id")==id){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void addDosen(Dosen d, String pass){
        String sql = "INSERT INTO user (identnum, fullname, username, password, role, nip) VALUES (?, ?, ?, MD5(?), ?, NULL)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, d.getNip());
                stmt.setString(2, d.getFullname());
                stmt.setString(3, d.getUsername());
                stmt.setString(4, pass);
                stmt.setString(5, "Dosen");
                stmt.executeUpdate();
                System.out.println("Dosen berhasil ditambahkan");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void deleteUser(String id){
        String sql = "DELETE FROM user WHERE identnum = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, id);
                stmt.executeUpdate();
                System.out.println("User berhasil dihapus");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void updateUser(String id, String fullname, String username, String role, String lecturerNip){
        String sql = "UPDATE user "+
                "SET fullname = ?, username = ?, role = ?, nip = ? "+
                "WHERE identnum = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, role);
            stmt.setString(4, lecturerNip);
            stmt.setString(5, id);
            stmt.executeUpdate();
            System.out.println("User berhasil diedit");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getNMahasiswa(){
        String sql = "SELECT COUNT(*) FROM user WHERE role = 'Mahasiswa'";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) { 
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static int getNDosen(){
        String sql = "SELECT COUNT(*) FROM user WHERE role = 'Dosen'";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) { 
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static ArrayList<Quiz> getQuiz(){
        ArrayList<Quiz> quiz = new ArrayList();
        String sql = "select * from quiz";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String heldBy = rs.getString("nip");
                int id = rs.getInt("id");
                String quizTitle = rs.getString("title");
                quiz.add(new Quiz(heldBy, id, quizTitle));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return quiz;
    }
    
    public static ArrayList<Question> getQuestion(){
        ArrayList<Question> questions = new ArrayList();
        String sql = "select * from question";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                int id = rs.getInt("id");
                int idQuiz = rs.getInt("quiz_id");
                String question = rs.getString("question");
                String choice1 = rs.getString("c1");
                String choice2 = rs.getString("c2");
                String choice3 = rs.getString("c3");
                String choice4 = rs.getString("c4");
                String answer = rs.getString("answer");
                double bobot = rs.getDouble("bobot");
                questions.add(new Question(id,idQuiz, question,choice1,choice2,choice3,choice4,answer,bobot));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return questions;
    }
    
    public static void addQuiz(Quiz q){
        String sql = "INSERT INTO quiz (id, title, nip) VALUES (NULL, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                if(findDosen(q.getHeldBy())){
                    stmt.setString(2, q.getHeldBy());
                    stmt.setString(1, q.getQuizTitle());
                    stmt.executeUpdate();
                    System.out.println("Quiz berhasil ditambahkan");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void addQuestion(Question q){
        System.out.println("sampe");
        String sql = "INSERT INTO question (id, quiz_id, question, c1, c2, c3, c4, answer, bobot) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                if(findQuiz(q.getIdQuiz())){
                    stmt.setInt(2, q.getIdQuiz());
                    stmt.setString(3, q.getQuestion());
                    stmt.setString(4, q.getChoice1());
                    stmt.setString(5, q.getChoice2());
                    stmt.setString(6, q.getChoice3());
                    stmt.setString(7, q.getChoice4());
                    stmt.setString(8, q.getAnswer());
                    stmt.setDouble(9, q.getBobot());
                    stmt.executeUpdate();
                    System.out.println("Question berhasil ditambahkan");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteQuiz(int id){
        String sql = "DELETE FROM quiz WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Quiz berhasil dihapus");
                cascadeDeleteQuestion(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void deleteQuestion(int id){
        String sql = "DELETE FROM question WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Question berhasil dihapus");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void cascadeDeleteQuestion(int quizId){
        String sql = "DELETE FROM question WHERE quiz_id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, quizId);
                stmt.executeUpdate();
                System.out.println("Question berhasil dihapus");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void updateQuestion(int id, String question, String c1, String c2, String c3, String c4, String answer, double bobot){
        String sql = "UPDATE question "+
                "SET question = ?, c1 = ?, c2 = ?, c3 = ?, c4 = ?, answer = ?, bobot = ? "+
                "WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, question);
            stmt.setString(2, c1);
            stmt.setString(3, c2);
            stmt.setString(4, c3);
            stmt.setString(5, c4);
            stmt.setString(6, answer);
            stmt.setDouble(7, bobot);
            stmt.setInt(8, id);
            stmt.executeUpdate();
        System.out.println("Question berhasil diedit");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateQuiz(int id, String title){
        String sql = "UPDATE quiz "+
                "SET title = ?"+
                "WHERE id = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, title);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        System.out.println("Quiz berhasil diedit");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Grade> getNilai(){
        ArrayList<Grade> grade = new ArrayList();
        String sql = "select * from grade";
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String nim = rs.getString("identnum");
                int id_quiz = rs.getInt("id_quiz");
                double nilai = rs.getDouble("nilai");
                grade.add(new Grade(nim, id_quiz, nilai));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return grade;
    }
    
    public static void addNilai(Mahasiswa m, int id_quiz, double nilai){
        String sql = "INSERT INTO grade (identnum, id_quiz, nilai) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, m.getNim());
                stmt.setInt(2, id_quiz);
                stmt.setDouble(3, nilai);
                stmt.executeUpdate();
                System.out.println("Nilai berhasil ditambahkan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean findNilai(Mahasiswa m, int id_quiz){
        String sql = "select * from grade";
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                if(rs.getString("identnum").equals(m.getNim())&&rs.getInt("id_quiz")==id_quiz){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void editNilai(Mahasiswa m, int id_quiz, double nilai){
        String sql = "UPDATE grade "+
                "SET nilai = ?"+
                "WHERE identnum = ? AND id_quiz = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setDouble(1, nilai);
            stmt.setString(2, m.getNim());
            stmt.setInt(3, id_quiz);
            stmt.executeUpdate();
        System.out.println("Nilai berhasil diedit");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getNilaiSpecific(String nim, int id_quiz){
        String sql = "select * from grade where identnum = "+nim+" and id_quiz = "+id_quiz;
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                return rs.getInt("nilai");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public static void deleteNilai(String nim, int id_quiz){
        String sql = "DELETE FROM grade WHERE identnum = ? AND id_quiz = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, nim);
                stmt.setInt(2, id_quiz);
                stmt.executeUpdate();
                System.out.println("Attempt berhasil dihapus");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void changePassword(String id, String newPass){
        String sql = "UPDATE user "+
                "SET password = md5(?) "+
                "WHERE identnum = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) { 
            stmt.setString(1, newPass);
            stmt.setString(2, id);
            stmt.executeUpdate();
            System.out.println("Password berhasil diedit");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}