public class StudentTest{
	public static void main(String args[]){
		Student s = new Student("Ari","2015","Abrenner","pass");
		s.addWallPost("wall post 1","ari");
		s.addWallPost("wall post 2","bob");
		s.addWallPost("wall post 3","zoe");
		s.addWallPost("wall post 4","sam");
		s.addWallPost("wall post 5","ray");
		s.addWallPost("wall post 6","jenny");
		s.addWallPost("wall post 7","james");
		s.showWallPosts();
	}
}
