package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Peminjaman {
    private int idpeminjaman;
    private Anggota anggota = new Anggota();
    private Pegawai pegawai = new Pegawai();
    private Buku buku = new Buku();
    private String tanggalpinjam;
    private String tanggalkembali;


    public Peminjaman(){

    }

    public Peminjaman(Anggota anggota, Pegawai pegawai, Buku buku, String tanggalpinjam, String tanggalkembali) {
        this.anggota = anggota;
        this.pegawai = pegawai;
        this.buku = buku;
        this.tanggalpinjam = tanggalpinjam;
        this.tanggalkembali = tanggalkembali;
    }

    public int getIdpeminjaman() {
        return idpeminjaman;
    }

    public void setIdpeminjaman(int idpeminjaman) {
        this.idpeminjaman = idpeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
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
                pem.setAnggota(new Anggota().getById(rs.getInt("idanggota")));
                pem.setPegawai(new Pegawai().getById(rs.getInt("idpegawai")));
                pem.setBuku(new Buku().getById(rs.getInt("idbuku")));
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
                pem.setAnggota(new Anggota().getById(rs.getInt("idanggota")));
                pem.setPegawai(new Pegawai().getById(rs.getInt("idpegawai")));
                pem.setBuku(new Buku().getById(rs.getInt("idbuku")));
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
                pem.setAnggota(new Anggota().getById(rs.getInt("idanggota")));
                pem.setPegawai(new Pegawai().getById(rs.getInt("idpegawai")));
                pem.setBuku(new Buku().getById(rs.getInt("idbuku")));
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
        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            String SQL = "INSERT INTO peminjaman (idpeminjaman, idanggota, idpegawai, idbuku, tanggalpinjam, tanggalkembali) VALUES ('"
            + this.idpeminjaman + "', '"
            + this.anggota.getIdanggota() + "', '"
            + this.pegawai.getIdPegawai() + "', '"
            + this.buku.getIdBuku() + "', '"
            + this.tanggalpinjam + "', '"
            + this.tanggalkembali + "')";
            this.idpeminjaman = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE peminjaman SET "
                    + "idanggota = '" + this.anggota.getIdanggota() + "', "
                    + "idbuku = '" + this.buku.getIdBuku() + "', "
                    + "tanggalpinjam = '" + this.tanggalpinjam + "', "
                    + "tanggalkembali = '" + this.tanggalkembali + "' "
                    + "WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    public void saveKembali() {
        String SQL = "UPDATE peminjaman SET tanggalkembali = '" + this.tanggalkembali + "' WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }

    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }  
}
