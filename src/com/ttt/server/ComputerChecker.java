package com.ttt.server;

public class ComputerChecker {
	//to track the move is 'O' or 'X'
		String stamp;
		String[][] clicked;
		
		public ComputerChecker(String stamp, String[][] clicked){
			this.stamp = stamp;
			this.clicked = clicked;
		}

		public String getStamp() {
			return stamp;
		}

		public void setStamp(String stamp) {
			this.stamp = stamp;
		}
		
		private String checkRow(String stamp){
			for (int i=0;i<clicked.length;i++){
				int count = 0;
				for (int j=0;j<clicked.length;j++){
					if (clicked[i][j].equals(stamp)) count++;
				}
				if (count == 2){
					for (int j=0;j<clicked.length;j++)
						if (!clicked[i][j].equals(stamp)) return Integer.toString(i)+Integer.toString(j);
				}
			}
			
			return null;
		}
		
		private String checkColumn(String stamp){
			for (int j=0;j<clicked.length;j++){
				int count = 0;
				for (int i=0;i<clicked.length;i++){
					if (clicked[i][j].equals(stamp)) count++;
				}
				if (count == 2){
					for (int i=0;i<clicked.length;i++)
						if (!clicked[i][j].equals(stamp)) return Integer.toString(i)+Integer.toString(j);
				}
			}
			return null;
		}
		
		private String checkCross(String stamp){
			int count1 = 0;
			for (int i=0;i<clicked.length;i++){
				if (clicked[i][i].equals(stamp)) count1++;
			}
			if (count1 == 2){
				for (int i=0;i<clicked.length;i++)
					if (!clicked[i][i].equals(stamp)) return Integer.toString(i)+Integer.toString(i);
			}
			
			int count2 = 0;
			for (int i=0;i<clicked.length;i++){
				if (clicked[i][clicked.length-1-i].equals(stamp)) count2++;
			}
			if (count2 == 2){
				for (int i=0;i<clicked.length;i++)
					if (!clicked[i][clicked.length-1-i].equals(stamp)) return Integer.toString(i)+Integer.toString(clicked.length-1-i);
			}
			return null;
		}
		
		public String check(){
			if (checkRow(this.stamp)!=null) return checkRow(this.stamp);
			if (checkColumn(this.stamp)!=null) return checkColumn(this.stamp);
			if (checkCross(this.stamp)!=null) return checkCross(this.stamp);
			return null;
		}
}
