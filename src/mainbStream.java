import java.io.*;
public class mainbStream 
{
	
	public static void main(String[] args) throws IOException
	{
		FileInputStream sourceStream = null;
		FileOutputStream targetStream = null;
	
		try
		{
			sourceStream = new FileInputStream("sourcefile.txt");
			targetStream = new FileOuputStream("target.txt");
			
//reading file and writing content to target file byte by byte
			int temp;
			while ((temp = sourceStream.read()) != -1)
				targetStream.write ((byte)temp);;
		}
		finally
		{
			 if (sourceStream !=null)
				 sourceStream.close();
			 if (targetStream !=null)
				targetStream.close();
		}
			
	}
}
