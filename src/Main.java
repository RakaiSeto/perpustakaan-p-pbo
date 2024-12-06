import backend.*;
public class Main {
    public static void main(String[] args)
    {
        Kategori kat1 = new Kategori("Novel", "Koleksi buku novel");
        Kategori kat2 = new Kategori("Referensi", "Buku referensi ilmiah");
        Kategori kat3 = new Kategori("Komik", "Komik anak-anak");
// test insert
        kat1.save();
        kat2.save();
        kat3.save();
// test update
        kat2.setKeterangan("Koleksi buku referensi ilmiah");
        kat2.save();
// test delete
        kat3.delete();
// test select all
        for(Kategori k : new Kategori().getAll())
        {
            System.out.println("Nama: " + k.getNama() + ", Ket: " + k.getKeterangan());
        }
// test search
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
    }

}