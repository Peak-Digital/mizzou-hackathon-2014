import com.leapmotion.leap.*;

public class Leap
{
	public static void main(String[] args)
	{
		Controller leapMotion = new Controller();
		LeapListener listener = new LeapListener();
		leapMotion.addListener(listener);

		while(true)
		{
			if(leapMotion.isConnected())
			{
				try
				{
					if(listener.getGestureType() != null)
					{
						System.out.println(listener.getGestureType());
						listener.disableGestures(leapMotion);
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}