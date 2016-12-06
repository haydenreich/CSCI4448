package survive;

import java.util.*;

import javax.swing.JDialog;

import java.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class HighScores
{	
	private Connection conn;
	public List<ScoreObj> scoreList;
	
	public class ScoreObj
	{
		private String name;
		private String score;
		public ScoreObj() {
			this.name = "";
			this.score = "";
		}
		public void setName(String aName) {
			this.name = aName;
		}
		public void setScore(String aScore) {
			this.score = aScore;
		}
		
		public String getName() {
			return this.name;
		}
		
		public String getScore() {
			return this.score;
		}
	}

	public HighScores() {
		scoreList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/High_Scores";
			conn = DriverManager.getConnection(url, "root", "root");
			queryDb();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printList()
	{
		for(ScoreObj obj : scoreList) {
			System.out.print(obj.getName());
			System.out.print(" : ");
			System.out.print(obj.getScore());
			System.out.print("\n");
		}
	}
	
	public void queryDb()
	{
		String query = "SELECT * from HIGH_SCORES";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				String lName;
				String lScore;
				ScoreObj scoreObj = new ScoreObj();
				lName = rs.getString("PLAYER");
				lScore = rs.getString("SCORE");
				scoreObj.setName(lName);
				scoreObj.setScore(lScore);
				scoreList.add(scoreObj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}