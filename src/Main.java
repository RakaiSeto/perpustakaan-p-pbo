import backend.*;
public class Main {
    public static void main(String[] args)
    {
        Kategori kat1 = new Kategori("Novel", "Koleksi buku novel");
        Kategori kat2 = new Kategori("Referensi", "Buku referensi ilmiah");
        Kategori kat3 = new Kategori("Komik", "Komik anak-anak");

        kat1.save();
        kat2.save();
        kat3.save();

        kat2.setKeterangan("Koleksi buku referensi ilmiah");
        kat2.save();

        kat3.delete();

        for(Kategori k : new Kategori().getAll())
        {
            System.out.println("Nama: " + k.getNama() + ", Ket: " + k.getKeterangan());
        }

        for(Kategori k : new Kategori().search("ilmiah"))
        {
            System.out.println("Nama: " + k.getNama() + ", Ket: " + k.getKeterangan());
        }

        Buku buku = new Buku(kat2, "Buku 1", "Penerbit", "Penulis");
        buku.save();

        Buku buku2 = new Buku(kat1, "Novel 2", "Penerbit", "Penulis");
        buku2.save();

        Buku buku3 = new Buku(kat2, "Buku 3", "Penerbit", "Penulis");
        buku3.save();

        for(Buku b : new Buku().getAll())
        {
            System.out.println("Judul: " + b.getJudul() + ", Kategori: " + b.getKategori().getNama());
        }

        for(Buku b : new Buku().search("Novel"))
        {
            System.out.println("Judul: " + b.getJudul() + ", Kategori: " + b.getKategori().getNama());
        }

        Anggota anggota1 = new Anggota("agus iqbal", "jl. simpang", "123456789");
        Anggota anggota2 = new Anggota("saputro", "jl. nanas", "123456789");

        anggota1.save();
        anggota2.save();

        for(Anggota a : new Anggota().getAll())
            System.out.println(a.getNama() + ", " + a.getAlamat() + ", " + a.getTelepon());

        anggota1.setNama("budiman");
        anggota1.save();

        for(Anggota a : new Anggota().getAll())
            System.out.println(a.getNama() + ", " + a.getAlamat() + ", " + a.getTelepon());


        Peminjaman peminjaman = new Peminjaman(anggota1.getIdanggota(), buku3.getIdBuku(), "2024-12-12", "2024-12-15");
        peminjaman.save();

        for (Peminjaman p : new Peminjaman().getAll())
            System.out.println(p.getIdanggota() + ", " + p.getIdbuku() + ", " + p.getTanggalpinjam() + ", " + p.getTanggalkembali());

        for (Peminjaman p : new Peminjaman().search("dor"))
            System.out.println(p.getIdanggota() + ", " + p.getIdbuku() + ", " + p.getTanggalpinjam() + ", " + p.getTanggalkembali());
    }

}