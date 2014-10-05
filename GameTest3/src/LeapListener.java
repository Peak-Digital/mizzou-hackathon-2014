import com.leapmotion.leap.*;

public class LeapListener extends Listener
{
	public Gesture.Type gestureType;
	public boolean hasNulled = false; // Fixes the init null problem

	public void onConnect(Controller leapMotion)
	{
		System.out.println("Controller connected...");
		leapMotion.enableGesture(Gesture.Type.TYPE_SWIPE, true);
		leapMotion.enableGesture(Gesture.Type.TYPE_CIRCLE, true);
		leapMotion.enableGesture(Gesture.Type.TYPE_KEY_TAP, true);
		leapMotion.enableGesture(Gesture.Type.TYPE_SCREEN_TAP, true);
		System.out.println("Gestures enabled...");
		leapMotion.config().save();
	}

	public void onDisconnect(Controller leapMotion)
	{
		System.out.println("Controller disconnected...");
	}

	private void setGestureType(Gesture.Type newGestureType)
	{
		this.gestureType = newGestureType;
	}

	public String getGestureType()
	{	
		Gesture.Type gestureTypeAtRead = gestureType;	//Makes a copy of gestureType to avoid concurrency issues
		
		if (gestureTypeAtRead == null)
		{
			return "";
		}
		else
		{
			switch (gestureTypeAtRead)
			{
			case TYPE_SWIPE:
				return "SWIPE";
			case TYPE_CIRCLE:
				System.out.println("Circle!");
				return "CIRCLE";
			case TYPE_KEY_TAP:
				return "KEY_TAP";
			case TYPE_SCREEN_TAP:
				return "SCREEN_TAP";
			default:
				return "";
			}
		}

	}

	public void onFrame(Controller leapMotion)
	{
		hasNulled = false;
		Frame frame = leapMotion.frame();
		GestureList gestures = frame.gestures();
		for (int x = 0; x < gestures.count(); x++)
		{
			Gesture gesture = gestures.get(x);

			setGestureType(gesture.type());

			switch (gesture.type())
			{
			case TYPE_SWIPE:
				SwipeGesture swipe = new SwipeGesture(gesture);
				setGestureType(swipe.type());
				break;
			case TYPE_CIRCLE:
				CircleGesture circle = new CircleGesture(gesture);
				setGestureType(circle.type());
				break;
			case TYPE_KEY_TAP:
				KeyTapGesture keytap = new KeyTapGesture(gesture);
				setGestureType(keytap.type());
				break;
			case TYPE_SCREEN_TAP:
				ScreenTapGesture screentap = new ScreenTapGesture(gesture);
				setGestureType(screentap.type());
				break;
			default:
				setGestureType(null);
				break;
			}
		}
		if (hasNulled == false)
		{
			setGestureType(null);
			hasNulled = true;
		}
	}
}