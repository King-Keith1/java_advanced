package Chapter12.Page399;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Songs songsData = new Songs();
        List<Song> songs = songsData.getSongs();

        for (Song s : songs) {
            System.out.println(s);
        }
    }
}


