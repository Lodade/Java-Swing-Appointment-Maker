/* Jacob Wagner
 * 500754931
 */
import javax.swing.JFrame;
import java.io.*;

public class AppointmentViewer
{  
	
   public static void main(String[] args) throws IOException
   {  
      JFrame frame = new AppointmentFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Appointments");
      frame.setVisible(true);      
   }

}