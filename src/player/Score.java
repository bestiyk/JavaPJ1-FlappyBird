/*
 * Copyright (c) 2019.
 * Author Matyas Dedek
 * Project JavaPJ1-FlappyBird
 *
 */

/*******************************************************************************
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 ******************************************************************************/
package player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {
	
	private int score;
	private int highScore;
	private File highScoreFile;
	
	public Score() {
		score = 0;
		highScoreFile = new File("score/highScore.txt");
		readHighScore();
	}

	private void readHighScore() {
		Scanner scan;
		
		try {
			scan = new Scanner(highScoreFile);
			this.highScore = scan.nextInt();
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Your highscore was not found.");;
			this.highScore = 0;
		}
		
	}
	
	public void writeHighScore() {
		if(highScoreFile.exists()) {
			if (this.score > this.highScore) {
				try {
					FileWriter fw = new FileWriter(highScoreFile, false);
					fw.write(new Integer(this.score).toString());
					fw.close();
					readHighScore();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				highScoreFile.createNewFile();
				FileWriter fw = new FileWriter(highScoreFile, false);
				fw.write(new Integer(this.score).toString());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore() {
		this.score++;
	}
	
	public int getHighScore() {
		return highScore;
	}
}
