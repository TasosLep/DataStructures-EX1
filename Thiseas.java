import java.io.*;
import java.util.*;

public class Thiseas
{
	//N = rows and M = columns
	private   int N,M;
	private  char[][] labyrinth;
	//The cordinates of E
	private  int[] E;									 
	//A stack to help us find a way out.									 
	private  StringStackImpl<String> stack;
	
	//The maze solver method.
	public void solveMaze(String[] args)
	{
		stack = new StringStackImpl<String>();
		E = new int[2];
		if(args.length != 0)//checking if the user didn't give a labyrinth.txt
		{
			labyrinth = loadFile(args[0]);
			if(findPath(E[0],E[1]))System.out.println(stack.peek());
			else System.out.println("No way out of here!");
		}else
			System.out.println("No labyrinth was given!");
	}
	
	//The main method of the program.
	public static void main(String[] args)
	{
		new Thiseas().solveMaze(args);
	}
	
	//This method checks if a point is valid.
	public boolean isSafe(int x,int y)
	{
		return x>=0 && x<N && y>=0 && y<M && labyrinth[x][y] !='1';
	}
	
	//This method checks each possible path from a given point.
	public boolean findPath(int x, int y)
	{
		//check if x,y is safe.
		if(isSafe(x,y))
		{
			//If the point is safe, then push it to the stack.
			stack.push(""+x+" "+y);
			//Check if we have reached an exit.
			if(labyrinth[x][y] == '0' && (y==M-1  || y == 0  || x == 0 || x == N-1) )return true;
			
			//if x,y is safe and it is not an exit
			//then mark it as 1 and start
			//the search again from x,y.
			labyrinth[x][y]='1';
			//Try the point south of x,y.
			if(findPath(x+1,y))return true;
			
			//Try the point east of x,y
			if(findPath(x,y+1))return true;
			
			//Try the point west of x,y.
			if(findPath(x,y-1))return true;
			
			//Try the point north of x,y.
			if(findPath(x-1,y))return true;
			
			//If we can not go anywere from x,y then
			//it leads to nowere and we sould remove it from the stack
			//and return false.
			stack.pop();
			return false;
		}
		return false;
	}
	
	public char[][] loadFile(String data)
	{
			int counter = 0;
			char[][] labyrinth = null;//a table that we will use for the labyrinth
			
			File f = null;
			BufferedReader reader = null;
			String line;
			
			try{
				f = new File(data);
			}
			catch (NullPointerException e){
				System.err.println ("File not found.");
			}
	
			try{
				reader = new BufferedReader(
					     new InputStreamReader(
			                      new FileInputStream(f), "UTF8"));
			}
			catch (Exception e ){
				System.err.println("Error opening file!");
			}
			
			try	{
				// ##################   TABLE  #############
				line = reader.readLine();
				if (line!=null) {
					counter++;
					String[] temp = line.split(" ");
				
						if(lenght(line.split(" ").length))//checking if user gave us 2 inputs for table length
						{ 
							if(isInteger(temp[0]) && isInteger(temp[1]))//checking if user gave us right inputs for table length
							{
								labyrinth = new char [Integer.parseInt(temp[0])][Integer.parseInt(temp[1])];//creating the table with the input dimensions
								N = Integer.parseInt(temp[0]);
								M = Integer.parseInt(temp[1]);
							}
							else
							{
								line = null;
								System.out.println("Be Careful.You can insert only numbers for table length!");
							}
						}
						else
						{	
							System.out.println("Be Careful.You can insert only numbers for table length!");
							return labyrinth;
						}
					
					// ##################   END OF TABLE  #############
					// ##################   	COORDINATES OF THE ENTRANCE #############
					line = reader.readLine();
					String[] temp1 = line.split(" ");
				
						if(lenght(line.split(" ").length))//check if users gives right size of coordinates for the entrance
						{
							if(isInteger(temp1[0]) && isInteger(temp1[1])) //check if users gives number inputs for the entrance
							{
								E[0] = Integer.parseInt(temp1[0]);
								E[1] = Integer.parseInt(temp1[1]);
							}
							else
							{
								line = null;
								System.out.println("Be Careful.You can insert only numbers for the entrance position!");
							}
						}else{
							System.out.println("Be Careful.The input doesnt suit to the length of the table");
							return labyrinth;
						
						}// ##################   END OF COORDINATES  #############
					
					// ##################   COPYING THE LABYRINTH IN A TABLE  #############			
					int p=0;//loac  variable
					int count=0;//local variable
					for (int a = 0; a < N; a++)
					{	
						
						line = reader.readLine();
						if(line == null) break;
						count++;
						
						String[] value = line.split(" ");//splitting the line and putting the split words in a String table
							for (int b = 0; b < M; b++)
							{	
								
								if(lenghtlaby(value.length,M)) //check if users gives right inputs that match the size of the table
								{
									if ( value[b].equals("0") || value[b].equals("1") || value[b].equals("E")) //checking the inputs for the labyrinth
									{
										if(value[b].equals("E"))
											p++;
										labyrinth [a][b] = value[b].charAt(0);//putting the the split word in the table
									}
									else 
									{
										System.out.println("Be Careful.You can insert only numbers or the charachter E for entrance");
										return labyrinth;
									}
								}else{
									System.out.println("Be Careful.The input doesn't match to the length of the table");
									return labyrinth;
								}
							}
							if(p>1)
							{
								System.out.println("Be Careful.You can insert only one entrance 'E'");
								return labyrinth;
							}
					
					}// ################## END OF COPYING THE LABYRINTH IN A TABLE  #############
					if(count != N)
					{
						System.out.println("Be Careful.The input doesn't match to the length of the table");
						return labyrinth;
					} 
				}
			}
			catch (IOException e){
				System.err.println("Error reading line " + counter + ".");
			}

			try {
				reader.close();
			}
			catch (IOException e){
				System.err.println("Error closing file.");
			}
			
			return labyrinth;			
	}
		
		//###### METHODS ######
	public static boolean isInteger( String input )  //Checking if the input is Integer
	{  
		try{  
		    Integer.parseInt( input );  
		    return true;  
		}catch( Exception e ) {  
		    return false;  
		}  
	}
		
	public static boolean lenght(int input)//Checking the length of the inputs
	{
		return (input == 2);
	}
		
	public static boolean lenghtlaby(int input,int input1)//Checking the length of the labyrinth
	{
		return (input == input1);
	}
}
