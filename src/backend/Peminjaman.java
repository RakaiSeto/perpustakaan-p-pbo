package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Peminjaman {
    private int idpeminjaman;
    private int idanggota;
    private int idbuku;
    private String tanggalpinjam;
    private String tanggalkembali;


    public Peminjaman(){

    }

    public Peminjaman(int idanggota, int idbuku, String tanggalpinjam, String tanggalkembali) {
        this.idanggota = idanggota;
        this.idbuku = idbuku;
        this.tanggalpinjam = tanggalpinjam;
        this.tanggalkembali = tanggalkembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public int getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(int idanggota) {
        this.idanggota = idanggota;
    }

    public int getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    public String getTanggalpinjam() {
        return tanggalpinjam;
    }

    public void setTanggalpinjam(String tanggalpinjam) {
        this.tanggalpinjam = tanggalpinjam;
    }

    public String getTanggalkembali() {
        return tanggalkembali;
    }

    public void setTanggalkembali(String tanggalkembali) {
        this.tanggalkembali = tanggalkembali;
    }

    public Peminjaman getById(int id){
        Peminjaman pem = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman WHERE idpeminjaman = '" + id + "'");

        
        try{
            while(rs.next()){
                pem.setIdpeminjaman(rs.getInt("idpeminjaman"));
                pem.setIdanggota(rs.getInt("idanggota"));
                pem.setIdbuku(rs.getInt("idbuku"));
                pem.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pem.setTanggalkembali(rs.getString("tanggalkembali"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pem;
    }
    
    public ArrayList<Peminjaman> getAll(){
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman");

        try{
            while(rs.next()){
                Peminjaman pem = new Peminjaman();
                pem.setIdpeminjaman(rs.getInt("idpeminjaman"));
                pem.setIdanggota(rs.getInt("idanggota"));
                pem.setIdbuku(rs.getInt("idbuku"));
                pem.setTanggalpinjam(rs.getString("tanggalpinjam"));
                pem.setTanggalkembali(rs.getString("tanggalkembali"));

                ListPeminjaman.add(pem);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    
    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();

        // Query untuk mencari data dalam tabel kategori
        String sql = "SELECT * FROM peminjaman WHERE " +
                    "idpeminjaman LIKE '%" + keyword + "%' OR " +
                    "idanggota LIKE '%" + keyword + "%' OR " +
                    "idbuku LIKE '%" + keyword + "%' OR " +
                    "tanggalpinjam LIKE '%" + keyword + "%' OR " +
                    "tanggalkembali LIKE '%" + keyword + "%'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman pem = new Peminjaman();
                pem.setIdpeminjaman(rs.getInt("idpeminjaman"));
                pem.setIdanggota(rs.getInt("idanggota"));
                pem.setIdbuku(rs.getInt("idbuku"));
                pem.setTanggalpinjam(rs.getDate("tanggalpinjam").toString());
                pem.setTanggalkembali(rs.getDate("tanggalkembali").toString());

                ListPeminjaman.add(pem);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Menampilkan pesan kesalahan jika ada exception
        }

        return ListPeminjaman;
    }
    
    public void save() {
        if (getById(idanggota).getIdanggota()== 0) {
            String SQL = "INSERT INTO peminjaman (idpeminjaman, idanggota, idbuku, tanggalpinjam, tanggalkembali) VALUES ('"
            + this.idpeminjaman + "', '"
            + this.idanggota + "', '"
            + this.idbuku + "', '"
            + this.tanggalpinjam + "', '"
            + this.tanggalkembali + "')";
            this.idanggota = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + "idanggota = '" + this.idanggota + "', "
                    + "idbuku = '" + this.idbuku + "', "
                    + "tanggalpinjam = '" + this.tanggalpinjam + "', "
                    + "tanggalkembali = '" + this.tanggalkembali + "' "
                    + "WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }  
}
