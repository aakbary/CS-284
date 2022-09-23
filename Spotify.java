import java.util.ArrayList;
import java.util.Collection;


// A music object is made up 2 string; song name and artist
class Music {
	String song;
	String artist;

	public Music(String song, String artist) {
		this.song = song;
		this.artist = artist;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return String.format("%-21s: %s", song, artist);
	}
}

// Each node will hold on to a song and the following song in the queue (the next node)
class Node {
	Music song;
	Node next;

	public Node(Music song) {
		this.song = song;
	}

	public Node() {
	}

	@Override
	public String toString() {
		if (song == null) {
			return "";
		}
		return song.toString();
	}
}

public class Spotify {
	Node current;
	Node last;

	/*
	 * TODO: You will take in a list of songs, in the form of arraylist, linkedlist,
	 * etc (Collection) and you will add that to the end of your queue
	 */
	public void addAll(Collection<Music> listOfSongs) {
		Object temp[] = (listOfSongs.toArray());
		addSong((Music)(temp[0]));
		for (int i = 1; i < temp.length ; i++){
			Node listt = new Node((Music)(temp[i]));
			last.next = listt;
			last = last.next;
		}
		last.next = current;
		
	}

	/*
	 * TODO: You will remove the song that is in the 1st position
	 */
	public void removeSong() {
		last.next = current.next;
		current = current.next;
	}

	/*
	 * TODO: You will add a song to the end of the queue
	 */
	public void addSong(Music song) {
		Node listt = new Node(song);
		if (last == null){
			current = listt;
			last = listt;
		} else{
		last.next = listt;
		listt.next = current;
		last = listt;
	}
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the next song
	 */
	public void nextSong() {
		last = current;
		current = current.next;
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the previous
	 * song
	 */
	public void prevSong() {
		current = last;
		while (last.next != current){
			last = last.next;
		}
	}

	/*
	 * TODO: You will tell us the song in the 1st position
	 */
	public Music currentSong() {
		if (current == null){
			return null;
		} else{
		return current.song;
	}
	}

	// use String.format( "%3s) %s\n", i, theCurrentNode );
	// i: the count starting at 1
	// theCurrentNode: current song node
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("=====[Current Queue]=====\n");
		if (current == null) {
			stringBuilder.append("no songs\n");
			return stringBuilder.toString();
		}
		Node temp = current;

		int i = 1;
		while (temp.next != null && temp.next != last.next) {
			stringBuilder.append(String.format("%3s) %s\n", i, temp));
			temp = temp.next;
			i++;
		}

		stringBuilder.append(String.format("%3s) %s\n", i, temp));

		return stringBuilder.toString();

	}

	public static void main(String[] args) {
		// Example 1
//		ArrayList<Music> songs = new ArrayList<>();
//		songs.add(new Music("Happy birthday", "shawn"));
//		songs.add(new Music("Space Cadet", "Metro Boomin"));
//		songs.add(new Music("100", "The Game"));
//
//		Spotify spotify = new Spotify();
//		spotify.addAll(songs);
//
//		String ans = "=====[Current Queue]=====\n" + "  1) Happy birthday       : shawn\n"
//				+ "  2) Space Cadet          : Metro Boomin\n" + "  3) 100                  : The Game\n";
//
//		String expectedAnswer = ans.replaceAll("\\s", "");
//		String actualAnswer = spotify.toString().replaceAll("\\s", "");
//
//		if (expectedAnswer.equals(actualAnswer)) {
//			System.out.println("Example 1 working");
//		} else {
//			System.out.println("Issue in example 1");
//	
//		}
//		
		Spotify spotify = new Spotify();
		spotify.currentSong();
		
//		spotify.addSong(new Music("Happy birthday", "shawn"));
//		spotify.addSong(new Music("Space Cadet", "Metro Boomin"));
//		spotify.addSong(new Music("100", "The Game"));
//		spotify.addSong(new Music("L@D", "A@AP Rocky"));
//		spotify.addSong(new Music("Hot Man", "Bobby Shmurda"));
//		spotify.addSong(new Music("Men in Paris", "Kanye East and Jay-Y"));
//		spotify.addSong(new Music("Im Sad", "Aubrey "));
//		
//		String inputString = "[new Music(\"Happy birthday\", \"shawn\"), new Music(\"Space Cadet\", \"Metro Boomin\"), "
//				+ "new Music(\"100\", \"The Game\"), new Music(\"L@D\", \"A@AP Rocky\"), "
//				+ "new Music(\"Hot Man\", \"Bobby Shmurda\"), new Music(\"Men in Paris\", \"Kanye East and Jay-Y\"), "
//				+ "new Music(\"Im Sad\", \"Aubrey \")]";
//		
//		String ans = "=====[Current Queue]=====\n" + "  1) Happy birthday       : shawn\n"
//				+ "  2) Space Cadet          : Metro Boomin\n" + "  3) 100                  : The Game\n"
//				+ "  4) L@D                  : A@AP Rocky\n" + "  5) Hot Man              : Bobby Shmurda\n"
//				+ "  6) Men in Paris         : Kanye East and Jay-Y\n" + "  7) Im Sad               : Aubrey \n";
//		
		
	}
	
}
