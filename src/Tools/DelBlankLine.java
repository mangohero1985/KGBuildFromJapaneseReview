/**
 * 
 */
package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * @author mangohero1985
 * @create-time     Jun 27, 2014   10:59:15 AM   
 */
public class DelBlankLine {

	public void Delete(BufferedReader br, BufferedWriter bw) throws IOException{
		
		String ReadLine= " ";
		while((ReadLine= br.readLine())!=null){
			if(ReadLine.equals("")||ReadLine.equals(" ")){
				continue;
			}else{
				bw.write(ReadLine);
				bw.newLine();
				bw.flush();
			}
		}
		System.out.println("删除空行完成");
	}
}
