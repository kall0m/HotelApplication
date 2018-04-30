package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static b4a.example.types._currentuser _currentuser = null;
public b4a.example.loginscreen _loginscr = null;
public b4a.example.userinterfacemainscreen _uiscreen = null;
public b4a.example.tasktable _tabletasks = null;
public b4a.example.mytasks _usertasks = null;
public b4a.example.types _types = null;
public b4a.example.helperfunctions1 _helperfunctions1 = null;
public b4a.example.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create"))
	return (String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime});
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="currentuser.Initialize";
_currentuser.Initialize();
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="currentuser.username = \"TestUser\"";
_currentuser.username = "TestUser";
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="currentuser.password = \"testelsys1\"";
_currentuser.password = "testelsys1";
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="currentuser.TypeOfWorker = 1   '<- 0 = not a work";
_currentuser.TypeOfWorker = (int) (1);
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="currentuser.available = True";
_currentuser.available = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="LoginScr.Initialize";
mostCurrent._loginscr._initialize(null,mostCurrent.activityBA);
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="UIscreen.Initialize";
mostCurrent._uiscreen._initialize(null,mostCurrent.activityBA);
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="TableTasks.Initialize";
mostCurrent._tabletasks._initialize(null,mostCurrent.activityBA);
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="UserTasks.Initialize";
mostCurrent._usertasks._initialize(null,mostCurrent.activityBA);
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="Activity.AddView(LoginScr.AsView,0,0,100%x,100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._loginscr._asview(null).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131088;
 //BA.debugLineNum = 131088;BA.debugLine="LoginScr.AsView.Visible = True";
mostCurrent._loginscr._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="Activity.AddView(UIscreen.AsView,0,0,100%x,100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._uiscreen._asview(null).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="UIscreen.AsView.Visible = False";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="Activity.AddView(TableTasks.AsView,0,0,100%x,100%";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._tabletasks._asview(null).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="TableTasks.AsView.Visible = False";
mostCurrent._tabletasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="Activity.AddView(UserTasks.AsView,0,0,100%x,100%y";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._usertasks._asview(null).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="UserTasks.AsView.Visible = False";
mostCurrent._usertasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress"))
	return (Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode});
int _ext = 0;
int _result = 0;
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="Dim ext As Int = 0";
_ext = (int) (0);
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
RDebugUtils.currentLine=786437;
 //BA.debugLineNum = 786437;BA.debugLine="If UIscreen.asView.Visible = True Then";
if (mostCurrent._uiscreen._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="UIscreen.asView.Visible = False";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786439;
 //BA.debugLineNum = 786439;BA.debugLine="LoginScr.asView.Visible = True";
mostCurrent._loginscr._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=786440;
 //BA.debugLineNum = 786440;BA.debugLine="ext = ext + 1";
_ext = (int) (_ext+1);
RDebugUtils.currentLine=786441;
 //BA.debugLineNum = 786441;BA.debugLine="LoginScr.loginbtn.Enabled = True";
mostCurrent._loginscr._loginbtn.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=786444;
 //BA.debugLineNum = 786444;BA.debugLine="If TableTasks.AsView.Visible = True Then";
if (mostCurrent._tabletasks._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=786445;
 //BA.debugLineNum = 786445;BA.debugLine="TableTasks.AsView.Visible = False";
mostCurrent._tabletasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786446;
 //BA.debugLineNum = 786446;BA.debugLine="UIscreen.asView.Visible = True";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=786447;
 //BA.debugLineNum = 786447;BA.debugLine="UIscreen.MenuHolder.Visible = True";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=786448;
 //BA.debugLineNum = 786448;BA.debugLine="ext = ext + 1";
_ext = (int) (_ext+1);
 };
RDebugUtils.currentLine=786451;
 //BA.debugLineNum = 786451;BA.debugLine="If UserTasks.AsView.Visible = True Then";
if (mostCurrent._usertasks._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=786452;
 //BA.debugLineNum = 786452;BA.debugLine="UserTasks.AsView.Visible = False";
mostCurrent._usertasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=786453;
 //BA.debugLineNum = 786453;BA.debugLine="UIscreen.asView.Visible = True";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=786454;
 //BA.debugLineNum = 786454;BA.debugLine="UIscreen.MenuHolder.Visible = True";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=786455;
 //BA.debugLineNum = 786455;BA.debugLine="ext = ext + 1";
_ext = (int) (_ext+1);
 };
RDebugUtils.currentLine=786457;
 //BA.debugLineNum = 786457;BA.debugLine="ext = ext - 1";
_ext = (int) (_ext-1);
RDebugUtils.currentLine=786459;
 //BA.debugLineNum = 786459;BA.debugLine="If LoginScr.asView.Visible = True And ext = -1 T";
if (mostCurrent._loginscr._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.True && _ext==-1) { 
RDebugUtils.currentLine=786461;
 //BA.debugLineNum = 786461;BA.debugLine="Dim result As Int";
_result = 0;
RDebugUtils.currentLine=786462;
 //BA.debugLineNum = 786462;BA.debugLine="result = Msgbox2(\"Exit application?\",\"Exit\",\"Ye";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Exit application?"),BA.ObjectToCharSequence("Exit"),"Yes","Cancel","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
RDebugUtils.currentLine=786463;
 //BA.debugLineNum = 786463;BA.debugLine="If result = DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
RDebugUtils.currentLine=786464;
 //BA.debugLineNum = 786464;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 };
 };
RDebugUtils.currentLine=786469;
 //BA.debugLineNum = 786469;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
RDebugUtils.currentLine=786472;
 //BA.debugLineNum = 786472;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
RDebugUtils.currentLine=786475;
 //BA.debugLineNum = 786475;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume"))
	return (String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null);
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="End Sub";
return "";
}
public static String  _loadmytasks(anywheresoftware.b4a.objects.collections.Map _tasks) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadmytasks"))
	return (String) Debug.delegate(mostCurrent.activityBA, "loadmytasks", new Object[] {_tasks});
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Public Sub LoadMyTasks(Tasks As Map)";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="UserTasks.GetMyTasks(Tasks)";
mostCurrent._usertasks._getmytasks(null,_tasks);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="End Sub";
return "";
}
public static String  _setuseravailable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setuseravailable"))
	return (String) Debug.delegate(mostCurrent.activityBA, "setuseravailable", null);
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub SetUserAvailable";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="UIscreen.SetAvailable";
mostCurrent._uiscreen._setavailable(null);
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="End Sub";
return "";
}
public static String  _setuserbusy() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "setuserbusy"))
	return (String) Debug.delegate(mostCurrent.activityBA, "setuserbusy", null);
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub SetUserBusy";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="UIscreen.SetBusy";
mostCurrent._uiscreen._setbusy(null);
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="End Sub";
return "";
}
public static boolean  _showmenu3() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showmenu3"))
	return (Boolean) Debug.delegate(mostCurrent.activityBA, "showmenu3", null);
RDebugUtils.currentLine=5767168;
 //BA.debugLineNum = 5767168;BA.debugLine="Sub ShowMenu3 As Boolean";
RDebugUtils.currentLine=5767169;
 //BA.debugLineNum = 5767169;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=5767170;
 //BA.debugLineNum = 5767170;BA.debugLine="End Sub";
return false;
}
public static boolean  _showmenu4() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showmenu4"))
	return (Boolean) Debug.delegate(mostCurrent.activityBA, "showmenu4", null);
RDebugUtils.currentLine=6160384;
 //BA.debugLineNum = 6160384;BA.debugLine="Sub ShowMenu4 As Boolean";
RDebugUtils.currentLine=6160385;
 //BA.debugLineNum = 6160385;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=6160386;
 //BA.debugLineNum = 6160386;BA.debugLine="End Sub";
return false;
}
public static String  _showmytasks() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showmytasks"))
	return (String) Debug.delegate(mostCurrent.activityBA, "showmytasks", null);
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub ShowMyTasks";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="If UserTasks.AsView.Visible = False Then";
if (mostCurrent._usertasks._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="UserTasks.AsView.Visible = True";
mostCurrent._usertasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="UIscreen.MenuHolder.Visible = False";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="UserTasks.AsView.Visible = False";
mostCurrent._usertasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="UIscreen.MenuHolder.Visible = True";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="End Sub";
return "";
}
public static String  _showtasktable() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showtasktable"))
	return (String) Debug.delegate(mostCurrent.activityBA, "showtasktable", null);
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub ShowTaskTable";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="If TableTasks.AsView.Visible = False Then";
if (mostCurrent._tabletasks._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="TableTasks.AsView.Visible = True";
mostCurrent._tabletasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="UIscreen.MenuHolder.Visible = False";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=524293;
 //BA.debugLineNum = 524293;BA.debugLine="TableTasks.AsView.Visible = False";
mostCurrent._tabletasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524294;
 //BA.debugLineNum = 524294;BA.debugLine="UIscreen.MenuHolder.Visible = True";
mostCurrent._uiscreen._menuholder.setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="End Sub";
return "";
}
public static String  _showui() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showui"))
	return (String) Debug.delegate(mostCurrent.activityBA, "showui", null);
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub ShowUI";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="ProgressDialogShow(\"Loading...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Loading..."));
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="If 	UIscreen.AsView.Visible = False Then";
if (mostCurrent._uiscreen._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="UIscreen.AsView.Visible = True";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="UIscreen.AsView.Visible = False";
mostCurrent._uiscreen._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
RDebugUtils.currentLine=655368;
 //BA.debugLineNum = 655368;BA.debugLine="End Sub";
return "";
}
public static String  _tasktabletomytasks() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "tasktabletomytasks"))
	return (String) Debug.delegate(mostCurrent.activityBA, "tasktabletomytasks", null);
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub TaskTableToMyTasks";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="If TableTasks.AsView.Visible = True Then";
if (mostCurrent._tabletasks._asview(null).getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="TableTasks.AsView.Visible = False";
mostCurrent._tabletasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="UserTasks.AsView.Visible = True";
mostCurrent._usertasks._asview(null).setVisible(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="End Sub";
return "";
}
}