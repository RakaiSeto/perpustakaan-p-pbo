package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Pegawai {
    private int idPegawai;
    private String nama;
    private String username;
    private String password;
    private String alamat;
    private String telepon;

    public Pegawai(int idPegawai, String nama, String username, String password, String alamat, String telepon) {
        this.nama = nama;
        this.idPegawai = idPegawai;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public Pegawai() {}

    public String toString() {
        return this.nama;
    }

    public int getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    
    public ArrayList<Pegawai> getAll() {
        ArrayList<Pegawai> ListPegawai = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        
        try {
            while(rs.next()) {
                Pegawai pgw = new Pegawai();
                pgw.setIdPegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setTelepon(rs.getString("telepon"));
                
                ListPegawai.add(pgw);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }

    public ArrayList<String> getAllPegawaiName() {
        ArrayList<Pegawai> ListPegawai = getAll();

        ArrayList<String> result = new ArrayList<>();
        for (Pegawai pgw : ListPegawai) {
            result.add(pgw.getNama());
        }

        return result;
    }

    public Pegawai getById(int idPegawai){
        Pegawai pgw = new Pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE idpegawai = '" + idPegawai + "'");
        
        try{
            while(rs.next()){
                pgw.setIdPegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return pgw;
    }
    
    public Pegawai getByUsername(String username) {
        Pegawai pgw = new Pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE username = '" + username + "'");
        
        try {
            if (rs.next()) {
                pgw.setIdPegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pgw;
    }

    public ArrayList<Pegawai> search(String keyword) {
        ArrayList<Pegawai> ListPegawai = new ArrayList();
        String SQL = "SELECT * FROM pegawai WHERE "
                + " nama LIKE '%" + keyword + "%' "
                + " OR username LIKE '%" + keyword + "%' "
                + " OR alamat LIKE '%" + keyword + "%' "
                + " OR telepon LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(SQL);
        
        try {
            while(rs.next()) {
                Pegawai pgw = new Pegawai();
                pgw.setIdPegawai(rs.getInt("idpegawai"));
                pgw.setNama(rs.getString("nama"));
                pgw.setUsername(rs.getString("username"));
                pgw.setPassword(rs.getString("password"));
                pgw.setAlamat(rs.getString("alamat"));
                pgw.setTelepon(rs.getString("telepon"));
                
                ListPegawai.add(pgw);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPegawai;
    }
    
    public void save() {
        if(getById(idPegawai).getIdPegawai() == 0){
            String SQL = "INSERT INTO pegawai (nama, username, password, alamat, telepon) VALUES("
                    + " '" + this.nama + "', "
                    + " '" + this.username + "', "
                    + " '" + this.password + "', "
                    + " '" + this.alamat + "', "
                    + " '" + this.telepon + "' "
                    + " )";
            this.idPegawai = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE pegawai SET"
                    + " nama = '" + this.nama + "', "
                    + " username = '" + this.username + "', "
                    + " password = '" + this.password + "', "
                    + " alamat = '" + this.alamat + "', "
                    + " telepon = '" + this.telepon + "' "
                    + " WHERE idpegawai = '" + this.idPegawai + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM pegawai WHERE idpegawai = '" + this.idPegawai + "'";
        DBHelper.executeQuery(SQL);
    }
}
